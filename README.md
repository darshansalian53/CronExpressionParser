# CronExpressionParser
Assessment 

Cron Expression consists of 6 fields that incluedes minute(0-59), hour(0-23), day of month(1-31), month(1-12), day of week(0-6) and command at last.
Parsing has '*' which has every value;
',' seperates values and it includes every value that is written with comma seperated;
'-' this describes the range from start to end;
'/' you can jump specific values(eg: */10 , it will jump 10 values everyrtime);


I. Approach:
1. I will collect entered String in single argument
2. Create one String array and add every field with space seperated to that array
3. Extract each field from String Array and store it in different variable
4. Call method named fieldCheck() with parameters including fieldname along with minimum and maximum value of their field
5. In fieldCheck() each and every field will be parsed for example if the minute field contains '*' then 0-59 every minute will be scheduled
6. Similarly every field will be parsed in fieldChech() and it will return List of values
7. That list of values will be sent to another method named printOutput(), where each field is printed along with field name and list of values.


II. Setup and Run
1. Requirement: Java 8 or high
2. Clone the repository: git clone repoUrl
3. compile the program: javac CronParser.java
4. Run: java CronParser "expression"

III. Example Command and Output
1. After compilation: java CronParser "*/15 0 1,15 * 1-5 usr/bin/find"
2. Output will be:

   minute        0 15 30 45
   hour          0
   day of month  1 15
   month         1 2 3 4 5 6 7 8 9 10 11 12
   day of week   1 2 3 4 5
   command       /usr/bin/find

