package com.example.caller3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class you_called_helber {
    static SQLiteDatabase database;
    static you_called_helber youCalledHelber;
    static SQLiteOpenHelper sqLiteOpenHelper;

    private you_called_helber(Context context){
        sqLiteOpenHelper =new you_called(context);
    }

    static you_called_helber getpointer(Context context){
        if(youCalledHelber == null)
            youCalledHelber=new you_called_helber(context);
        return youCalledHelber;
    }
    public void open(){database=sqLiteOpenHelper.getWritableDatabase();}
    public void close(){if (database !=null)database.close();}

    public ArrayList<you_colled> show_all(){
        ArrayList<you_colled> nams =new ArrayList<>();
        Cursor c =database.rawQuery("select * from "+you_called.table_name,null);
        if (c.moveToFirst()) {
            do {
                nams.add(new you_colled(c.getInt(0),c.getString(1),c.getString(2),c.getString(3)));
            }while (c.moveToNext());
        }
        return nams;
    }
    public ArrayList<you_colled> show_some(String name ){
        ArrayList<you_colled> nams =new ArrayList<>();
        Cursor c =database.rawQuery("select * from "+you_called.table_name+" where "+you_called.col4+" Like '%"+name+"%'",null);
        if (c.moveToFirst()) {
            do {
                nams.add(new you_colled(c.getInt(0),c.getString(1),c.getString(2),c.getString(3)));
            }while (c.moveToNext());
        }
        return nams;
    }
    public ArrayList<you_colled> get_id(String phone){
        ArrayList<you_colled> nams =new ArrayList<>();
        Cursor c =database.rawQuery("select * from "+you_called.table_name+" where "+you_called.col2+" = '"+phone+"'",null);
        if (c.moveToFirst()) {
            do {
                nams.add(new you_colled(c.getInt(0),c.getString(1),c.getString(2),c.getString(3)));
            }while (c.moveToNext());
        }
        return nams;
    }

    public ArrayList<you_colled> get_by_id(int id){
        ArrayList<you_colled> nams =new ArrayList<>();
        Cursor c =database.rawQuery("select * from "+you_called.table_name+" where "+you_called.col1+" = "+id+"",null);
        if (c.moveToFirst()) {
            do {
                nams.add(new you_colled(c.getInt(0),c.getString(1),c.getString(2),c.getString(3)));
            }while (c.moveToNext());
        }
        return nams;
    }

    public boolean insert(you_colled opject){
        ContentValues cv =new ContentValues();
        cv.put(you_called.col1,opject.getId());
        cv.put(you_called.col2,opject.getPhone());
        cv.put(you_called.col3,opject.getWhen());
        cv.put(you_called.col4,opject.getName());
        long x =database.insert(you_called.table_name,null,cv);
        return x>-1;
    }


}
