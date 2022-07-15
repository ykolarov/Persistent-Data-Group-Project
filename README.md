# **Persistent-Data-Group-Project**

IN THIS README WE  WOULD BE SHOWING THE TASKS THAT WE WOULD BE COMPLETING AND HOW WE COMPLETED THEM AND WHAT ISSUES WE HAD ALONG THE WAY!

Yanaki Kolarov - Worked on MVC structure; querying, insertion, table creation for database; conversion of csv text to an employee valid hashset or invalid arraylist(duplicates); multithreaded writing to database; Wrote tests.

Billie Parsons - Worked on validation conditions for the file reader, multithreading implementation and speed testing, user interface and file information, updated readme. 

Toby Gascoigne - Worked on Employee Object - Helped fix up Validation conditions and created the database generation methods and overall opening and closing connections. Reading properties from a .properties filed found under resources and loaded them into memory.

Alfred Abraham - Worked on validation conditions and all the overall validity checks within Employee.java and the worked closely with the team to create the main README.md
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
sakila with the valid employee objects we had created for phase 1 of this project. We disabled the autocommit feature
and used prepared statements, but also inserted the records as a batch to avoid unnecessary communication with the DB.


## *Phase Three*
- Use the second file, (EmployeeRecordsLarge.csv), which can be assumed to have already been cleaned.
- Record time taken to persist to MySQL before implementing multiple threads.
- Add multithreading to your application for writing the data to the database, comparing the execution time with thee single-threaded version.
- Try different numbers of threads and compare the results - what is the optimum number of threads? **Record this information in the README.md,**

### **SOLUTION**
We experimented with different amounts of threads when adding data from the EmployeeRecordsLarge.csv file to find the optimal time.
Originally it took 18 seconds to pass all the data into the database
We then optimised the code to add each data set to a batch, and then send the batch, rather than each data set individually.
This brought the time taken down to 14 seconds.
We then tried it with 2 threads, with each passing half of the data to the database. This still took 14 seconds.
3 threads took 15 seconds, and 4 threads took 14 seconds.
We had little to no time reduction between threads, so we tested if each thread was running concurrently or sequentially.
Below is a screenshot showing that all threads were running concurrently.

![Threads](https://media.discordapp.net/attachments/996431230880993453/997501085629562930/unknown.png?width=1280&height=903)




## *Phase Four*
- Modify code to make use of functional programming concepts - lambdas and streams.
- Keep the original code and then run tests to see if efficiency has improved by adding functional code.
  ### **SOLUTION**
Time for code to run: 1837msec without streams, 1287msec with streams
````
try (Stream<String> linesStream = Files.lines(file.toPath())) {
            linesStream
                    .skip(1) // skip header
                    .forEach(line -> addNewEmployee(line.split(",")));
        } catch (IOException e) {
            e.printStackTrace();
        }
````
Use of lambdas for threads to avoid class explosion problem. In this situation our lambdas act as an anonymous implementation of the run-able functional interface as it only had the run method requirement. 
````
private Thread getThread(HashSet<Employee> records, int from, int to) {
        return new Thread(() -> {
            System.out.println("Thread: " + Thread.currentThread().getName());
            var collectionPortion = records.stream().
                    toList().subList(from, to);
            recordDao.saveAll(new HashSet<>(collectionPortion));
        });
    }
````
