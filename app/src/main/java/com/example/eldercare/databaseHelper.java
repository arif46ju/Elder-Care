package com.example.eldercare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class databaseHelper extends SQLiteOpenHelper {
    public static final String dbName="ElderCare.db";
    public static final String tableName="dailyActivities";
    public static final String groceryTableName="groceryList";
    public static final String medicationTableName="medication";
    public static final String id="_id";
    public static final String groceryId="_groceryId";
    public static final String medicinId="_medicinId";
    public static final String activities="Activities";
    public static final String grocery="item";
    public static final String medicin="medicin";
    public static final String time="time";
    public  static int version=14;
    public static  String createTable="CREATE TABLE "+tableName+"("+id+" INTEGER  PRIMARY KEY AUTOINCREMENT,"+activities+" TEXT); ";
    public static  String createGroceryTable="CREATE TABLE "+groceryTableName+"("+groceryId+" INTEGER  PRIMARY KEY AUTOINCREMENT,"+grocery+" TEXT); ";
    public static  String createMedicationTable="CREATE TABLE "+medicationTableName+"("+medicinId+" INTEGER  PRIMARY KEY AUTOINCREMENT,"+medicin+" TEXT,"+time+" TEXT); ";
    public static final String dropTable="DROP TABLE IF EXISTS "+tableName;
    public static final String dropGroceryTable="DROP TABLE IF EXISTS "+groceryTableName;
    public static final String dropMedicationTable="DROP TABLE IF EXISTS "+medicationTableName;
    private Context context;
    public databaseHelper(@Nullable Context context) {
        super(context, dbName, null, version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(createTable);
            db.execSQL(createGroceryTable);
            db.execSQL(createMedicationTable);
            Toast.makeText(context,"onCreated is called",Toast.LENGTH_LONG).show();
        }
        catch (Exception e){
            Toast.makeText(context,"Exeption :"+e,Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try{
            Toast.makeText(context,"onUpgraded is called",Toast.LENGTH_LONG).show();
            db.execSQL(dropTable);
            db.execSQL(dropGroceryTable);
            db.execSQL(dropMedicationTable);
            onCreate(db);

        }
        catch (Exception e){
            Toast.makeText(context,"Exeption :"+e,Toast.LENGTH_LONG).show();
        }

    }
    public boolean insertData(String activities) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(this.activities,activities);
        long result = db.insert(tableName,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
     public boolean insertGroceryData(String activities) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(grocery,activities);
        long result = db.insert(groceryTableName,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
public boolean insertMedicationData(String activities,String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(medicin,activities);
        contentValues.put(this.time,time);
        long result = db.insert(medicationTableName,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean updateData(String id,String activities) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(this.id,id);
        contentValues.put(this.activities,activities);

        db.update(tableName, contentValues, "_id = ?",new String[] { id });
        return true;
    }
    public boolean updateGroceryData(String id,String activities) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(groceryId,id);
        contentValues.put(grocery,activities);

        db.update(groceryTableName, contentValues, "_groceryId = ?",new String[] { id });
        return true;
    }
 public boolean updateMedicationData(String id,String activities,String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(medicinId,id);
        contentValues.put(medicin,activities);
        contentValues.put(this.time,time);

        db.update(medicationTableName, contentValues, "_medicinId = ?",new String[] { id });
        return true;
    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(tableName, "_id = ?",new String[] {id});
    }
    public Integer deleteGroceryData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(groceryTableName, "_groceryId = ?",new String[] {id});
    }
 public Integer deleteMedicationData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(medicationTableName, "_medicinId = ?",new String[] {id});
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+tableName,null);
        return res;
    }
    public Cursor getAllGrocery() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+groceryTableName,null);
        return res;
    }
    public Cursor getAllMedicin() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+medicationTableName,null);
        return res;
    }


    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ tableName);
        db.close();
    }

public void deleteAllGrocery()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ groceryTableName);
        db.close();
    }

public void deleteAllMedication()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ medicationTableName);
        db.close();
    }


}
