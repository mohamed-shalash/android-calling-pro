package com.example.caller3;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class you_called extends SQLiteAssetHelper {
    public static final String table_name ="you_colled";
    public static final String db_name = "phone.db";
    public static String col1 ="id";
    public static String col2 ="phone";
    public static String col3 ="when";
    public static String col4 ="name";
    private static int version=1;

    you_called (Context context){
        super(context, db_name, null, version);
    }
}
