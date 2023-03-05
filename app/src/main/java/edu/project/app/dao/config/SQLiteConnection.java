package edu.project.app.dao.config;

import static edu.project.app.dao.config.Constants.*;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class SQLiteConnection extends SQLiteOpenHelper {

    public SQLiteConnection(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + "(" +
                "task_id varchar(32), " +
                "title varchar(50)," +
                "description text," +
                "date_created long," +
                "last_modification long," +
                "status int," +
                "primary key(task_id)" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table taskapp");
        onCreate(sqLiteDatabase);
    }
}
