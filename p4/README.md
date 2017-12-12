
Assuming you are in the directory containing this README:

## To clean:
ant -buildfile wordTree/src/build.xml clean

-----------------------------------------------------------------------
## To compile: 
ant -buildfile wordTree/src/build.xml all

-----------------------------------------------------------------------
## To run by specifying arguments from command line 
ant -buildfile wordTree/src/build.xml run -Darg0=FIRST -Darg1=SECOND -Darg2=THIRD ...
This program requires 5 arguments: input_file output_file num_threads delete_words debug_value

-----------------------------------------------------------------------

