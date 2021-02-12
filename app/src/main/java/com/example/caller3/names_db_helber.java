package com.example.caller3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class names_db_helber {

    static SQLiteOpenHelper sqLiteOpenHelper;
    static SQLiteDatabase sqLiteDatabase;
    static names_db_helber db_helper;

private  names_db_helber(Context con){
    sqLiteOpenHelper =new names_db(con);
}

    public static names_db_helber getpointer(Context context){
        if (db_helper == null)
            db_helper =new names_db_helber(context);
        return db_helper;
    }

    public void open(){
        sqLiteDatabase =sqLiteOpenHelper.getWritableDatabase();
    }
    public void close(){if(sqLiteDatabase != null) sqLiteDatabase.close();}

    public ArrayList<names_class> show_all(){
        ArrayList<names_class> nams =new ArrayList<>();
        Cursor c =sqLiteDatabase.rawQuery("select * from "+names_db.table_name,null);
        if (c.moveToFirst()) {
            do {
                nams.add(new names_class(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4)));
            }while (c.moveToNext());
        }
        return nams;
    }

    public ArrayList<names_class> show_some_name(String name){
        ArrayList<names_class> nams =new ArrayList<>();
        Cursor c =sqLiteDatabase.rawQuery("select * from "+names_db.table_name+" where "+names_db.col2+" Like '%"+name+"%'",null);
        if (c.moveToFirst()) {
            do {
                nams.add(new names_class(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4)));
            }while (c.moveToNext());
        }
        return nams;
    }
    public names_class show_some_id(int id){
        names_class nams =null;
        Cursor c =sqLiteDatabase.rawQuery("select * from "+names_db.table_name+" where "+names_db.col1+" = "+id,null);
        if (c.moveToFirst()) {
            do {
                nams =new names_class(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4));
            }while (c.moveToNext());
        }
        return nams;
    }
    public names_class show_last(){
        names_class nams =null;
        Cursor c =sqLiteDatabase.rawQuery("select * from "+names_db.table_name,null);
        if (c.moveToFirst()) {
            do {
                nams =new names_class(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4));
            }while (c.moveToNext());
        }
        return nams;
    }

    public int get_id(String name){
        int id ;
        Cursor c =sqLiteDatabase.rawQuery("select id from "+names_db.table_name+" where "+names_db.col2+" = '"+name+"'",null);
        if (c.moveToFirst()) {
            do {
                id=c.getInt(0);
                return id;
            }while (c.moveToNext());
        }
        return 0;
    }

   public boolean add(names_class na){
       ContentValues cv =new ContentValues();
       cv.put(names_db.col2,na.getName());
       cv.put(names_db.col3,na.getPhone());
       cv.put(names_db.col4,na.getAddress());
       cv.put(names_db.col5,na.getImage());
       long x =sqLiteDatabase.insert(names_db.table_name,null,cv);
       return x>-1;
   }

    public boolean update(names_class na){
        ContentValues cv =new ContentValues();
        cv.put(names_db.col2,na.getName());
        cv.put(names_db.col3,na.getPhone());
        cv.put(names_db.col4,na.getAddress());
        cv.put(names_db.col5,na.getImage());
        int x =sqLiteDatabase.update(names_db.table_name,cv,names_db.col1+" = "+na.getId(),null);
        return x>-1;
    }
    public boolean delete(int id){
        int x =sqLiteDatabase.delete(names_db.table_name,names_db.col1+" = "+id,null);
        return x>-1;
    }

}
