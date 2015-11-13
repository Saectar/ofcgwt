# Introduction #
This guide will outline required steps needed to get a GWT application working with OFCGWT. Basic Java and GWT programming skills will be assumed, and that you are familiar with using Eclipse as your development environment.
_Note: whilst using Eclipse is assumed, instructions should work generically for any other kind of development environment_

## Requirements ##
The following items are what is required to be successful with OFCGWT (_though earlier version might work, they have not been tested_).

To begin you will need...
  * GWT 1.5.2
  * Java 1.6
  * OFCGWT 1.0.1
  * Eclipse 3.4

# The 5 Major Steps #
## 1. Create an Eclipse GWT Project ##
_Unless you know what you are doing, copy the project names etc exactly as provided._
> a) Using GWT tools at the command line, create a new GWT project `ProjectCreator -eclipse ofcgwttest -out ofcgwttest`

> b) Still with GWT tools command line, create a new GWT application `ApplicationCreator -eclipse ofcgwttest -out ofcgwttest com.gwttest.client.Test`

> c) Remove/delete the two `.cmd` files created by GWT in the project root directory - we won't be using these and they will only confuse things going forward.
These two steps will have created a new Eclipse project setup for GWT.
## 2. Copy Files from OFCGWT Download ##
To speed things up, we are going to copy in a java source file provided with the 1.0.1 download of OFCGWT.
> a) Copy the `Test.java` file included in the beta6.zip file into the `ofcgwttest\src\com\gwttest\client` directory overwriting the existing file.

> b) Now copy in the ofcgwt.jar file into the root of the project directory `ofcgwttest\`
## 3. Import and Add OFCGWT Jar ##
_Ok, this is the biggest step, so follow closely..._
> a) Launch Eclipse and close all other projects - they will only confuse things.

> b) Import the project _File->Import_ then under _General->Existing Projects into Workspace_ - then find the project root for `ofcgwttest` and then Finish. You should have a project with compile errors - that is ok as we haven't included the jar file.

> c) Expand the **ofcgwttest** project in eclipse and find the ofcgwt.jar file - Right click and choose _Build Path->Add to Build Path_. This should remove the compile errors and the project is almost ready.

> d) Now add the OFCGWT module to the test gwt module xml file. Within the project, find the `Test.gwt.xml` file under src/com.gwttest/public - edit this file and add `<inherits name='com.rednels.ofcgwt.OFCGWT'/>` after the `<!-- Other module inherits` section.
## 4. Configure Launch ##
_We need to modify the GWT included eclipse launch file to ensure the shell has a path to the OFCGWT jar file._
> a) Select the Eclipse menu Run->Run Configurations. You should see Test listed on the left hand side - select it.

> b) Under the Classpath tab, select the User Entries and then click Add JARs. Choose the ofcgwt.jar file and OK.
## 5. Have Fun! ##
_We are finished - you should be able to run the launch file and the shell will show the a OFCGWT demo_


