package com.example.penny.databasetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 Step 1：自定义一个类继承SQLiteOpenHelper类
 Step 2：在该类的构造方法的super中设置好要创建的数据库名,版本号
 Step 3：重写onCreate( )方法创建表结构
 Step 4：重写onUpgrade( )方法定义版本号发生改变后执行的操作

 * Created by penny on 2016/10/23.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_BOOK = "create table book("
    + "id integer primary key autoincrement,"
            +  "author text,"
            + "price real,"
            + "name text,"
            +"category_id integer)";
    public static final String CREATE_CATEGORY = "create table category("
            +"id integer primary key autoincrement,"
            +"category_name text,"
            +"category_code integer)";
    private Context mContext;
    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                            int version){
        super(context, name, factory,version);
        mContext = context;

    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_CATEGORY);
    }
    @Override
    public  void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //如果这两张表已经存在就删除掉重新建立表格
        switch (oldVersion){
            case 1:
                db.execSQL(CREATE_CATEGORY);
            case 2:
                db.execSQL("alter table Book add column category_id integer");
                default:
        }
    }
}
