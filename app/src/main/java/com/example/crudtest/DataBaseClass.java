package com.example.crudtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static com.example.crudtest.Employee.TABLE_NAME;

public class DataBaseClass extends SQLiteOpenHelper {
    public static int DATABASE_VERSION = 1;

    public DataBaseClass(Context context) {
        super(context, TABLE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Employee.CREATE_TABLE);
    }

    public Employee addEmployee(Employee e) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Employee.COLUMN_ID, e.getId());
        values.put(Employee.COLUMN_FIRSTNAME, e.getFirstName());
        values.put(Employee.COLUMN_LASTNAME, e.getLastName());
        values.put(Employee.COLUMN_INSURED, e.isInsured() ? 1 : 0);
        db.insert(Employee.TABLE_NAME, null, values);
        return e;
    }

    public Employee getEmployee(int empId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(Employee.TABLE_NAME,
                new String[]{Employee.COLUMN_ID, Employee.COLUMN_FIRSTNAME, Employee.COLUMN_LASTNAME, Employee.COLUMN_INSURED},
                Employee.COLUMN_ID + "=?",
                new String[]{String.valueOf(empId)}, null, null, null, null
        );
        if (c != null)
            c.moveToFirst();
        Employee employee = new Employee();
        employee.setId(c.getInt(c.getColumnIndex(Employee.COLUMN_ID)));
        employee.setFirstName(c.getString(c.getColumnIndex(Employee.COLUMN_FIRSTNAME)));
        employee.setLastName(c.getString(c.getColumnIndex(Employee.COLUMN_LASTNAME)));
        employee.setInsured(c.getInt(c.getColumnIndex(Employee.COLUMN_INSURED)) != 0);
        return employee;
    }
    public List<Employee> getEmployees(){
        List <Employee> employees =new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery="select * from " + Employee.TABLE_NAME;
        Cursor c= db.rawQuery(selectQuery,null);
        if( c.moveToFirst())
        {
            do{
                Employee employee = new Employee();
                employee.setId(c.getInt(c.getColumnIndex(Employee.COLUMN_ID)));
                employee.setFirstName(c.getString(c.getColumnIndex(Employee.COLUMN_FIRSTNAME)));
                employee.setLastName(c.getString(c.getColumnIndex(Employee.COLUMN_LASTNAME)));
                employee.setInsured(c.getInt(c.getColumnIndex(Employee.COLUMN_INSURED)) != 0);
                employees.add(employee);
            }while (c.moveToNext());

        }
        if (c != null)
            c.moveToFirst();
        db.close();
        return employees;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
