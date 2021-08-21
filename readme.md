## This repo contains solved examples of recrutation tasks for java developer position :)
### Content of tasks - pdf file in given repository

### Task 1 
##### Problem: Calculator
###### Write some code to calculate a result from a set of instructions.
###### Instructions comprise of a keyword and a number that are separated by a space per line. Instructions are loaded from file and results are output to the screen. Any number of Instructions can be specified. Instructions can be any binary operators of your choice(e.g., add, divide, subtract, multiply etc). The instructions will ignore mathematical precedence. The last instruction should be “apply” and a number (e.g., “apply 3”). The calculator is then initialised with that number and the previous instructions are applied to that number.
###### Examples of the calculator lifecycle might be:
##### Example 1
###### Input from file add 2
###### multiply 3
###### apply 10
###### Output 36
##### Explanation 10 + 2 * 3 = 36
##### Solution: storware repository

### Task 2
##### Problem: File operations
###### Your task relies on segregating files. At first program should create directories structure:
* HOME
* DEV
* TEST
###### At the moment when in HOME directory will appear file according to its extension it should be moved in given rules:
* jar file which creation time is even, will be moved to DEV directory
* jar file which creation time is not even, will be moved to TEST directory
* xml file will be moved to DEV directory
###### Solution: segregation repository


### Task 3
##### Problem: Spring Boot REST API
###### Create REST API that will count and report the users visiting web service. API should have 2 endpoints:
* POST /save which will save every user visitation in db with given input etc:
{
  "date": "2020-10-20",
  "ip": "212.34.52.103"
}
If any of paramaters are null, there should be returned HTTP 400.
* GET /statictics which will show number of user visitation grouped by user ip address and the visitation date
###### Solution: detector repository
