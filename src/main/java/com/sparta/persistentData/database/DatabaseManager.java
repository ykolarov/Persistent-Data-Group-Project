package com.sparta.persistentData.database;
import com.sparta.persistentData.Model.Employee;
import com.sparta.persistentData.View.Displayer;
import com.sparta.persistentData.database.ConnectionManager;
import com.sparta.persistentData.database.RecordDao;
import java.sql.Connection;
import java.util.HashSet;

public class DatabaseManager {

    private ConnectionManager connectionManager;
    private Connection conn;
    private RecordDao recordDao;

    private HashSet<Employee>[] dataForThreads;
    private final int numOfThreads;


    // [0] -> thread 1

    public DatabaseManager(int numOfThreads) {
         this.numOfThreads = numOfThreads;
         connectionManager = new ConnectionManager();
         conn = connectionManager.getDatabaseConnection();
         recordDao = new RecordDao(conn);
    }

    public void start(Displayer displayer) {
        insertRecordsInDb();
        allowUserToQueryDb(displayer);
        connectionManager.closeDatabaseConnection();
    }
    private void insertRecordsInDb() {
        var start = System.currentTimeMillis();
        recordDao.createTable("Employee");
        var records = Employee.getValidRecords();
        Thread t1 = getThread(records, 0, records.size()/4);
        Thread t2 = getThread(records, (records.size()/4)+1, (records.size())/2);
        Thread t3 = getThread(records, (records.size()/2)+1, records.size()/2 + records.size()/4);
        Thread t4 = getThread(records, (records.size()/2 + records.size()/4) + 1, records.size());
        startThreads(new Thread[] {t1 , t2, t3, t4});
        var stop = System.currentTimeMillis();
        System.out.println("Time to insert values in DB: " + (stop-start)/1000 + "sec");
    }

    private void startThreads(Thread[] threads) {
        try {
            for (Thread t: threads) t.start();
            for (Thread t: threads) t.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private Thread getThread(HashSet<Employee> records, int from, int to) {
        return new Thread(() -> {
            System.out.println("Thread: " + Thread.currentThread().getName());
            var firstHalf = records.stream().
                    toList().subList(from, to);
            recordDao.saveAll(new HashSet<>(firstHalf));
        });
    }


    private void allowUserToQueryDb(Displayer displayer) {
        while(true) {
            int empId = displayer.getKeyboardInput();
            if (empId == -1) break;
            Employee e = recordDao.get(empId);
            displayer.displayData(e.toString());
        }
    }

}
