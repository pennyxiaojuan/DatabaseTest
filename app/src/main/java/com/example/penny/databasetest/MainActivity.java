package com.example.penny.databasetest;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //修改数据库的版本号，表示对数据库进行升级
        dbHelper = new MyDatabaseHelper(this, "BookStore.db", null, 2);
        Button createDatabase = (Button) findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.getWritableDatabase();
            }
        });
        Button addData = (Button) findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                //开始组装第一条数据
                values.put("name", "The 12 Code");
                values.put("author", "Dan Brown");
                values.put("pages", "454");
                values.put("price", 16.96);
                db.insert("Book", null, values);//插入第一条数据
                //开始组装第二条数据
                values.put("name", "The Da Lost Symbol");
                values.put("author", "Dan Brown");
                values.put("pages", "510");
                values.put("price", 19.95);
                db.insert("Book", null, values);//插入第二条数据
            }
        });
    }
}