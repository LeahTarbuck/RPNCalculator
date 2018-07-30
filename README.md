# RPNCalculator
Reverse Polish Notation Calculator

## Introduction
This maven built project is designed to read an input file: **resources/inputData.txt** which contains lines of calculations in Reverse Polish Notation.<br/>
e.g. **_6 3 * 2 - sqrt_** is the equivalent of **_√((6 * 3)-2)_** 
<br/>
It executes a calculator using the Stack object and outputs the calculated results in **target/computedResult.txt**. <br/>
Unit tests are executed on the build of the project.

The following operators are supported:<br/>* / + - sqrt

## Execution
Build the project using: <br/>
_mvn clean install_ <br/>

Then execute the program from the **root** directory using:<br />
_java -cp target/classes RPNMain_<br/>

(or execute the RPNMain.java file using an IDE.)

You can also execute the test file RPNCalculatorTest.java.
