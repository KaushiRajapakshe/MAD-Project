package com.example.user.microelectronic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.Sampler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import static android.support.constraint.Constraints.TAG;

public class DBConnector extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MicroElectronics.db";
    public static final String TABLE_NAME = "Order_table";
    public static final String TABLE_NAME2 = "Item_table";
    public static final String COL_1 = "OrderID";
    public static final String COL_2 = "ItemID";
    public static final String COL_3 = "ItemName";
    public static final String COL_4 = "Description";
    public static final String COL_5 = "Qty";
    public static final String COL_6 = "Total";
    public static final String COLL_1 = "StockLevel";
    public static final String TABL_NAME="Registration";
    public static final String CO_1="Username";
    public static final String CO_2="Email";
    public static final String CO_3="Address";
    public static final String CO_4="Mobile";
    public static final String CO_5="Password";
    public static final String CO_6="Value";

    public static final String TABLE_NAME3 = "Address_table";
    public static final String C_1="ADDID";
    public static final String C_2="FName";
    public static final String C_3="LName";
    public static final String C_4="Address";
    public static final String C_5="Zip";
    public static final String C_6="ContactNo";

    public DBConnector(Context context)  {

        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Registration (Username Text PRIMARY KEY,Email Text,Address Text,Mobile Text,Password Text, Value Int)");
        db.execSQL("CREATE TABLE " + TABLE_NAME +" (OrderID INTEGER PRIMARY KEY AUTOINCREMENT, ItemID TEXT, ItemName TEXT, Description TEXT, Qty TEXT, Total TEXT)" );
        db.execSQL("CREATE TABLE " + TABLE_NAME2 +" (ItemID1 INTEGER PRIMARY KEY AUTOINCREMENT, ItemName1 TEXT, Description1 TEXT, StockLevel INTEGER, Price REAL)" );
        db.execSQL("INSERT INTO Item_table VALUES (1, 'Arduino Uno', 'Sri Lanka', 40, 1000.00)");
        db.execSQL("INSERT INTO Item_table VALUES (2, 'Arduino Mega', 'Sri Lanka', 20, 1400.00)");
        db.execSQL("INSERT INTO Item_table VALUES (3, 'Arduino Nano', 'Sri Lanka', 30, 1600.00)");
        db.execSQL("INSERT INTO Item_table VALUES (4, 'Samsung Galaxy J7', 'Sri Lanka', 15, 48000.00)");
        db.execSQL("INSERT INTO Order_table VALUES (1, '1', 'Samsung Galaxy J7', 'Sri Lanka', '15', '48000.00')");
        db.execSQL("CREATE TABLE " + TABLE_NAME3 + " (ADDID INTEGER PRIMARY KEY AUTOINCREMENT, FName TEXT, LName TEXT, Address TEXT, Zip TEXT, ContactNo TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e(TAG, "Updating table from " + oldVersion + " to " + newVersion);
        /*db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME2);
        onCreate(db);*/
    }
    public Cursor getAllData(String itemNumber) {
        SQLiteDatabase db  = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT ItemID1,ItemName1,Description1,Price,StockLevel from " + TABLE_NAME2 + " WHERE ItemID1 = " + itemNumber, null);
        return res;
    }


    public boolean insertData1(String ItemID, String ItemName, String Description, String Qty,String Total){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValue=new ContentValues();
        contentValue.put(COL_2,ItemID);
        contentValue.put(COL_3,ItemName);
        contentValue.put(COL_4,Description);
        contentValue.put(COL_5,Qty);
        contentValue.put(COL_6,Total);
        long  result = db.insert(TABLE_NAME,null,contentValue);
        if(result == -1)
            return false;
        else
            return true;

    }
    public Cursor getOrderdata() {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor data=db.rawQuery("Select*from Order_table",null);
        return data;
    }

    public Cursor getAddressdata() {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor data=db.rawQuery("Select*from Address_table",null);
        return data;
    }

    public Integer deleteAddress(int ADDID){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME3,"ADDID="+ ADDID,null);
    }

    public Boolean updateData1(int ItemID,int Stock){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues content=new ContentValues();
        content.put(COLL_1,Stock);
        db.update(TABLE_NAME2,content,"ItemID1="+ItemID,null);
        return true;

    }

    public boolean insertDataAdd(String FName, String LName, String Address, String Zip, String ContactNo){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValu=new ContentValues();
        contentValu.put(C_2,FName);
        contentValu.put(C_3,LName);
        contentValu.put(C_4,Address);
        contentValu.put(C_5,Zip);
        contentValu.put(C_6,ContactNo);
        long  result = db.insert(TABLE_NAME3,null,contentValu);
        if(result == -1)
            return false;
        else
            return true;

    }
//Pula
    public boolean insertData(String Username,String Email,String Address,String Mobile,String Password){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(CO_1,Username);
        contentValues.put(CO_2,Email);
        contentValues.put(CO_3,Address);
        contentValues.put(CO_4,Mobile);
        contentValues.put(CO_5,Password);
        contentValues.put(CO_6,1);
        long result=db.insert("Registration",null,contentValues);
        if(result==-1)
            return false;
        else
            return true;


    }

    public Boolean change(int value,String id){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(CO_6,id);
        db.update(TABL_NAME,contentValues,"Value= 1",null);
        return true;
    }


    public Boolean Login(String Username,String Password){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select Username,Password from Registration where Username=? and Password=?",new String[]{Username,Password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

//pula
    public Cursor showData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor data=db.rawQuery("Select*from Registration",null);
        return data;
    }
    //pula
    public boolean updateData(String Username,String Email,String Address,String Mobile,String Password){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(CO_1,Username);
        contentValues.put(CO_2,Email);
        contentValues.put(CO_3,Address);
        contentValues.put(CO_4,Mobile);
        contentValues.put(CO_5,Password);
        db.update(TABL_NAME,contentValues,"Username=?",new String[] {Username});
        return true;

    }

//pula
    public Integer deleteData(String Username){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABL_NAME,"Username=?",new String[]{Username});
    }


    public Cursor check(){
        SQLiteDatabase db  = this.getWritableDatabase();
        //Select Username, Email, Address, Mobile, Password
        Cursor res = db.rawQuery("Select Value from Registration WHERE Value = 1", null);
        return res;
    }



}
