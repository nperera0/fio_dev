Instructions on running the application
1. Extract the zip file
2. Make sure the mysql server is running (see instructions at the end)
3. Type following two commands to compile and run the program


javac -d . RegistrationSystem.java DBHandler.java -cp mysql-connector-java-5.1.38.jar
java -cp .:mysql-connector-java-5.1.38-bin.jar fio_dev.RegistrationSystem


Application Description


This is a simple java program that has access to a backend mysql server. Right now we assume the database is running on the localhost. This program makes simple sql queries to local database to create, search and update records.

Here is the list of functions supported by the program.
1 - Create a new Patient Demographic
2 - Retrieve a Patient Demographic
3 - Delete a Patient Demographic
4 - Search Patient Demographics
5 - View all Patient Demographics
6 - Add a new Patient Document
7 - Retrieve a Patient Document


Program works from command prompt input. Type a number to select any option from the main menu and use different features. Search and data Insert can be done with partial information, simply press enter without entering any characters to create empty entries.

Program access two tables in the database
1. Demographics table - It has First name, Last name, age, Date fields and each patient have a unique id.
2. Documents Table - It has Document type, Document issuer, Document id and the id of the patient who the document belongs to.
This data can be used by  Document Cross-reference Service (DCS) and  Demographics Service (DS) for processing later.


In cases when network connection is intermittent to connect these services data caching on client side and compress content for efficient delivery can be used.


Instructions on setting up the database


Here's a step by step explanation how to install and setup MySQL database:
1. Download and install the MySQL server from http://dev.mysql.com/downloads/mysql/
Refer to here http://dev.mysql.com/doc/refman/5.7/en/installing.html for any OS specific instructions.
2.  Use command line or GUI tool (download from here http://www.sequelpro.com/) to configure the database
3. Create a new user and grant it access
CREATE USER 'username'@'localhost' IDENTIFIED BY 'password';
GRANT ALL ON *.* TO 'username'@'localhost' IDENTIFIED BY 'password';
