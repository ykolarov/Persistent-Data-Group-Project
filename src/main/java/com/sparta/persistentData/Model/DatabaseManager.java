package com.sparta.persistentData.Model;
import com.sparta.persistentData.View.Displayer;
import com.sparta.persistentData.database.ConnectionManager;
import com.sparta.persistentData.database.RecordDao;
import java.sql.Connection;
import java.util.HashSet;

public class DatabaseManager {

    ConnectionManager connectionManager;
    Connection conn;
    RecordDao recordDao;

    HashSet<Employee>[] dataForThreads;

    // [0] -> thread 1

    public DatabaseManager() {
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
        recordDao.saveAll(Employee.getValidRecords());
        var stop = System.currentTimeMillis();
        System.out.println("Time to insert values in DB: " + (stop-start)/1000 + "sec");
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
