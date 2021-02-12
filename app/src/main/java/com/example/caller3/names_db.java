package com.example.caller3;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class names_db extends SQLiteAssetHelper {

    public static final String table_name ="names";
    public static final String db_name = "phone.db";
    public static String col1 ="id";
    public static String col2 ="name";
    public static String col3 ="phone";
    public static String col4 ="address";
    public static String col5 ="photo";
    private static int version=1;

    names_db (Context context){
        super(context, db_name, null, version);
    }

}
