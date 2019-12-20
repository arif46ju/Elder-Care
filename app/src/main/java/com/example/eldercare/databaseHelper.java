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
    public static final String id="_id";
    public static final String activities="Activities";
    public static final String time="TIME";
    public static final int version=2;
    public static  String createTable="CREATE TABLE "+tableName+"("+id+" INTEGER  PRIMARY KEY AUTOINCREMENT,"+activities+" TEXT); ";
    public static final String dropTable="DROP TABLE IF EXISTS "+tableName;
    private Context context;
    public databaseHelper(@Nullable Context context) {
        super(context, dbName, null, version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(createTable);
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
    public boolean updateData(String id,String activities) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(this.id,id);
        contentValues.put(this.activities,activities);

        db.update(tableName, contentValues, "_id = ?",new String[] { id });
        return true;
    }
    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(tableName, "_id = ?",new String[] {id});
    }
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+tableName,null);
        return res;
    }


}
