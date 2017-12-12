package genericCheckpointing.serStrategy;

import genericCheckpointing.util.SerializableObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import genericCheckpointing.util.FileProcessor;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.lang.reflect.InvocationTargetException;
 
public class XMLSerialization implements SerStrategyI {
    
    public XMLSerialization(){ }
    
    /**
     *	Serialize an object to a file
     *	@param o - The object to serialize
     *	@param fp - An open file to write to
     */
	public void serializeObject(SerializableObject o, FileProcessor fp) {  
	    try{
			Class<?> c = o.getClass();
			Field[] fields = c.getDeclaredFields();
			String class_name = c.getName();
			fp.writeLine("<DPSerialization>"); 
			fp.writeLine("	<complexType xsi:type=\"" + class_name + "\">"); 
			for(Field f : fields){
				fp.writeLine("		" + fieldToXML(o, c, f));
			}
			fp.writeLine("	</complexType>");
			fp.writeLine("</DPSerialization>");
		} catch (SecurityException  e) {
			e.printStackTrace();
			System.exit(1);
		}	 
	}

	/**
	 *	Deserialize an object from a file
	 *	@param fp - The open file
	 *	@return The object or null
	 */
	public SerializableObject deserializeObject(FileProcessor fp){
		Object o = null;
		try{
			String line = fp.readLine();
			if(line.equalsIgnoreCase("<DPSerialization>")){
				Class<?> c = null;				
				while(true){
					line = fp.readLine();
					if(line == null || line.trim().equalsIgnoreCase("</complexType>")){
						fp.readLine();
						return (SerializableObject)o;
					}
					if(line.contains("<complexType ")){
						String res = matchString("type=\"(.*?)\"", line);
						c = Class.forName(res);	
						o = c.newInstance();
					}else{
						parseLine(line, o, c);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			System.exit(1);
		}
		return (SerializableObject)o;
	}

	/**
	 *	Convert a class field to psuedo XML
	 *	@param obj - The object
	 *	@param c - The declaring class
	 *	@param f - The field
	 *	@return The field in XML
	 */
	private String fieldToXML(SerializableObject obj, Class<?> c, Field f){
		try{
			String type = f.getType().getSimpleName().toLowerCase();
			String f_name = f.getName();
			String method_name = "get" + f_name.toUpperCase().charAt(0) 
									+ f_name.substring(1,f_name.length());
			Method getm = c.getMethod(method_name);
			Object val = getm.invoke(obj);
			return "<" + f_name + " xsi:type=\"xsd:" + type + "\">" + val + "</" + f_name + ">";
		}catch(Exception e){
			e.printStackTrace(); 
			System.exit(1);
			return null; //... java
		}	
	}

	/**
	 *	Parse a line describing a field
	 *	@param line - The string to parse
	 *	@param o - The object the field belongs to
	 *	@param c - The declaring class
	 */
	private void parseLine(String line, Object o, Class<?> c){
		try{
			// Assume field name. Convert to method
			if(c == null)
				throw new Exception("XML is not properly formatted.");
			String f_name = matchString("<(.*?) xsi:", line);
			String method_name = "set" + f_name.toUpperCase().charAt(0) 
						+ f_name.substring(1,f_name.length());
			String type = matchString("xsi:type=\"xsd:(.*?)\"", line);
			String val = matchString("\">(.*?)</", line);	
			invokeFromString(c, o, method_name, type, val);
		}catch(Exception | Error e){
			e.printStackTrace();
			System.exit(1);
		}
		
	}

	/**
	 *	Invoke a setX method given the class, object, method name, type, and val
	 *	@param c - The declaring class
	 *	@param o - The object
	 *	@param mname - The method name
	 *	@param type - Param type
	 *	@param val - Param value
	 */
	private void invokeFromString(Class<?> c, Object o, String mname, String type, String val){
		try{
			Class<?>[] sig = new Class<?>[1];
			Method m = null;
			switch(type){
				case "int":
					int int_val = Integer.parseInt(val);
					sig[0] = int.class;
					m = c.getMethod(mname, sig);
					m.invoke(o, int_val);
					break;
				case "char":
					char c_val = val.charAt(0); // No spaces better!
					sig[0] = char.class;
					m = c.getMethod(mname, sig);
					m.invoke(o, c_val);
					break;
				case "short":
					short s_val = Short.parseShort(val);
					sig[0] = short.class;
					m = c.getMethod(mname, sig);
					m.invoke(o, s_val);
					break;
				case "long":
					long l_val = Long.parseLong(val);
					sig[0] = long.class;
					m = c.getMethod(mname, sig);
					m.invoke(o, l_val);
					break;
				case "double":
					double d_val = Double.parseDouble(val);
					sig[0] = double.class;
					m = c.getMethod(mname, sig);
					m.invoke(o, d_val);
					break;
				case "float":
					float f_val = Float.parseFloat(val);
					sig[0] = float.class;
					m = c.getMethod(mname, sig);
					m.invoke(o, f_val);
					break;
				case "boolean":
					boolean b_val = Boolean.parseBoolean(val);
					sig[0] = boolean.class;
					m = c.getMethod(mname, sig);
					m.invoke(o, b_val);
					break;
				case "string":
					sig[0] = String.class;
					m = c.getMethod(mname, sig);
					m.invoke(o, val);
					break;
				default:
					System.err.println("Invalid type " + type + ". Ignoring it");
					break;
			}
		}catch(IllegalAccessException | NoSuchMethodException |
			InvocationTargetException | NumberFormatException e){
			e.printStackTrace();
			System.exit(1);
		}
	}

	/** 
	 *	Match a string to a given pattern
	 *	@param to_match - The pattern
	 *	@param within - The string to search
	 *	@return The first match or null
	 */
	private String matchString(String to_match, String within){
		Pattern p = Pattern.compile(to_match);
		Matcher m = p.matcher(within);
		if(m.find())
			return m.group(1);
		return null;
	}
}