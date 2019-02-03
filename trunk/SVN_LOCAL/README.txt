================================================================================
Author: Kenny Oduor
Date:   13.12.2016
--------------------------------------------------------------------------------
README.txt
================================================================================
{here you basically describe the content of the project. additions}

Each of the directories (in the directory where this readme file is) have their 
own ANT scripts. 

Most directories have 3 ANT files (They end in the extension XML).

Here is a brief description of what the purpose is for each directory:

dao                  - Used for putting your DAO code in. dao/src has a 
                       dependency on hibernate/src
                       
ear                  - Used for packaging the various components of a J2EE 
                       application up into an enterprise archive file for 
                       deployment to JBoss.
                      
hibernate            - Used for putting your hibernate code in. hibernate/src 
                       is used by dao/src

javadocs             - Used for generating javadoc HTML files from the various 
                       source trees

jboss                - Used for starting and stopping JBoss in normal and 
                       debug mode.
                       
lib                  - Used for storing the dependent jars

masterbuild          - Is the main directory that ties the ANT build together. 
                       If you open a command window and change to this 
                       directory you can see what ANT commands are available by 
                       typing in the console "ant -p"

web                  - Used for packaging the web components up into Web 
                       archive files.                       
                       
The steps to deploy {kejani} are as follows:

{here dependes on the projects mamanger}
 	
 