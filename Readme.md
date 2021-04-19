### -------KAYAK AIR WEB PAGE AUTOMATION---------

## INTRODUCTION

This is the solution for Endava's challenge, which seeks to evaluate my skills in test automation. This solution was made with Java and
Selenium WebDriver using Page object model. (This is only one way to implement POM pattern)


## Dependencies

- Java
- Maven

### Libraries

- Hamcrest 
- Junit
- Selenium

## Steps to make this project works

- Download the repo from https://github.com/rafaperez49/KayakAir.git
- Open git bash and enter the following command ---> git clone "https://github.com/rafaperez49/KayakAir.git"
- Open a cmd in the root project and enter 'mvn clean test'.

## To run the test

To run the test case enter the command mvn -Dtest=KayakStepDefinitions test

## To run test and the report

Enter the following command in a cmd in the project's root "mvn surefire-report:report"



