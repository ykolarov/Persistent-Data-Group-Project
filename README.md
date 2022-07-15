# **Persistent-Data-Group-Project**

IN THIS README WE  WOULD BE SHOWING THE TASKS THAT WE WOULD BE COMPLETING AND HOW WE COMPLETED THEM AND WHAT ISSUES WE HAD ALONG THE WAY!

## *Phase one*

- Create a new project and write code to read data from an Employee CSV file.
- As it is read in, add each record read to a new object of a suitable class and then add those objects to a collection.
- Any corrupt or duplicated data should be added to a separate collection for further analysis.
- Write tests to ensure data is being managed correctly
- Consider which data class would be best to use for the data fields. One in (java.util) and another in (java.sql)
- Provide a simple user interface to display the results of reading the file - how many unique, clean record there are. how many duplicates, how many records with missing fields, possibly display the questionable records.
- User the provided (EmployeeRecords1.csv) and (EmployeeRecords2.csv) for your testing and optionally create your own test files to help with your JUnit tests.
  ### **SOLUTION**
- 
We faced initial problems with reading the employee records CSV file into java and passing it into the employee class.
This was a simple fix, as we just needed to add a line of code to pass over the headers of the file and move to the data.
````BufferedReader bufferedReader = new BufferedReader(fileReader))
bufferedReader.readLine();
````

We created an employee class to pass the data from the file into. This would validate the data and return true or false.
We had issues finding the correct regex for some String objects so that anything other than alpha characters would return invalid.
We also had issues with other validation conditions.
This was especially difficult in the case of the email, as a very specific input would be required.
We solved this largely through trial and error by testing valid inputs via JUnit tests against out validation conditions.
````
@BeforeEach
    void setUp() {
        employee = new Employee(647173,"Mr.",
                "Milan","F","Krawczyk","M",
                "milan.krawczyk@hotmail.com",new Date("4/4/1980"),
                new Date("1/19/2012"),123681);
    }
````

## *Phase Two* 

- Write the SQL statements to create a table and to persist data to the table
- If the table exists, it will need to be dropped first
- Install the drivers for the database to be used (MySQL) and create a connection
- Create a data access object (DAO Pattern) to persist the data to the database
- Persist employee records and write code to retrieve individual records from the database
  ### **SOLUTION**
We connected to the sakila database using a dbproperties file and a ConenctionManager class. This allowed us to
create a table named employees and drop it if it existed. Then we were able to populate the employees table in 
sakila with the valid employee objects we had created for phase 1 of this project.




## *Phase Three*
- Use the second file, (EmployeeRecordsLarge.csv), which can be assumed to have already been cleaned.
- Record time taken to persist to MySQL before implementing multiple threads.
- Add multithreading to your application for writing the data to the database, comparing the execution time with thee single-threaded version.
- Try different numbers of threads and compare the results - what is the optimum number of threads? **Record this information in the README.md,**
  ### **SOLUTION**
- The large employee records took 14 seconds with a single thread (without multithreading)






## *Phase Four*
- Modify code to make use of functional programming concepts - lambdas and streams.
- Keep the original code and then run tests to see if efficiency has improved by adding functional code.
  ### **SOLUTION**