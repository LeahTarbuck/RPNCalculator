# RPNCalculator
Java Project for a Reverse Polish Notation Calculator

## Introduction
This maven built project is designed to read an input file: **resources/inputData.txt** which contains lines of calculations in Reverse Polish Notation.<br/>
e.g. **_6 3 * 2 - sqrt_** is the equivalent of **_√((6 * 3)-2)_** 
<br/>
It executes a calculator using the Stack object and outputs the calculated results in **target/computedResult.txt**. <br/>
Unit tests are executed on the build of the project.

The following operators are supported:<br/>
 \* - multiplication<br/> 
/ - division<br/>
 \+ - addition<br/>
\- - subtraction<br/>
sqrt - square root of one value<br/>
mod  - modulus of two values<br/>
avg - average of two values<br/>

## Execution
Build the project using: <br/>
_mvn clean install_ <br/>

Then execute the program from the **root** directory using:<br />
_java -cp target/classes RPNMain_<br/>

(or execute the RPNMain.java file using an IDE.)

You can also execute the test file RPNCalculatorTest.java.


## Potential Enhancements
* Add sine, cosine, tan operations
* Add a logger 
* Implement the Strategy pattern for RPNCalculator.computeLine() method
* Use spring for DI and IoC