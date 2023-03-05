package edu.project.app.dao.repository.impl;

import static edu.project.app.dao.config.Constants.TABLE_NAME;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import edu.project.app.dao.repository.TaskRepository;
import edu.project.app.entity.StatusType;
import edu.project.app.entity.Task;

public class TaskRepositoryImpl implements TaskRepository {

    private final SQLiteOpenHelper connection;

    public TaskRepositoryImpl(SQLiteOpenHelper connection) {
        this.connection = connection;
    }

    @Override
    public Task save(Task entity) {
        entity.setId(UUID.randomUUID());
        try {
            SQLiteDatabase db = connection.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("task_id", entity.getId().toString());
            values.put("title", entity.getTitle());
            values.put("description", entity.getDescription());
            values.put("date_created", entity.getDateCreation());
            values.put("last_modification", entity.getLastModification());
            values.put("status", entity.getStatus().ordinal());
            db.insert(TABLE_NAME, null, values);
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Task invalid");
        }
    }

    @Override
    public Optional<Task> findById(UUID id) {
        SQLiteDatabase db = connection.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where task_id= ? ", new String[]{id.toString()});
        cursor.moveToFirst();
        if (cursor.getString(0) == null)
            return Optional.empty();

        Task result = converter(cursor);
        cursor.close();
        return Optional.of(result);
    }

    @Override
    public List<Task> findAll() {
        List<Task> tasks = new ArrayList<>();
        SQLiteDatabase db = connection.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME, null);
        while (cursor.moveToNext()) {
            tasks.add(converter(cursor));
        }
        cursor.close();
        return tasks;
    }

    @Override
    public boolean existsById(UUID id) {
        SQLiteDatabase db = connection.getReadableDatabase();
        Cursor cursor = db.rawQuery("select count(*) from " + TABLE_NAME + " where task_id = ?",
                new String[]{id.toString()});
        int len = cursor.getInt(0);
        cursor.close();
        return len == 1;
    }

    @Override
    public Task updateById(Task entity) {
        ContentValues values = new ContentValues();
        values.put("title", entity.getTitle());
        values.put("description", entity.getDescription());
        values.put("date_created", entity.getDateCreation());
        values.put("last_modification", entity.getLastModification());
        values.put("status", entity.getStatus().ordinal());
        try {
            SQLiteDatabase db = connection.getWritableDatabase();
            db.update(TABLE_NAME,
                    values, "task_id = ?",
                    new String[]{entity.getId().toString()});
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Task invalid");
        }
    }

    @Override
    public boolean deleteById(UUID id) {
        try {
            SQLiteDatabase db = connection.getWritableDatabase();
            return db.delete(TABLE_NAME, "task_id = ?",
                    new String[]{id.toString()}) == 1;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Task invalid");
        }
    }

    @Override
    public Optional<Task> findWithOutDescription(UUID id) {
        SQLiteDatabase db = connection.getReadableDatabase();
        Cursor cursor = db.rawQuery("select task_id, title, description, date_created, last_modification, status from " + TABLE_NAME + " where task_id= ? ",
                new String[]{id.toString()});
        cursor.moveToFirst();
        if (cursor.getString(0) == null)
            return Optional.empty();

        Task result = converter(cursor);
        cursor.close();
        return Optional.of(result);
    }

    @Override
    public List<Task> findAllWithOutDescription() {
        List<Task> tasks = new ArrayList<>();
        SQLiteDatabase db = connection.getReadableDatabase();
        Cursor cursor = db.rawQuery("select task_id, title, description, date_created, last_modification, status from " + TABLE_NAME, null);
        while (cursor.moveToNext()) {
            tasks.add(converter(cursor));
        }
        cursor.close();
        return tasks;
    }

    @Override
    public List<Task> findAllWithOutDescription(int limit) {
        List<Task> tasks = new ArrayList<>();
        SQLiteDatabase db = connection.getReadableDatabase();
        Cursor cursor = db.rawQuery("select task_id, title, description, date_created, last_modification, status from " + TABLE_NAME + " limit ?",
                new String[]{String.valueOf(limit)});
        while (cursor.moveToNext()) {
            tasks.add(converter(cursor));
        }
        cursor.close();
        return tasks;
    }

    @Override
    public Optional<String> findDescriptionById(UUID id) {
        SQLiteDatabase db = connection.getReadableDatabase();
        Cursor cursor = db.rawQuery("select description from " + TABLE_NAME + " where task_id = ? ",
                new String[]{id.toString()});
        cursor.moveToFirst();
        if (cursor.getString(0) == null)
            return Optional.empty();

        String result = cursor.getString(0);
        cursor.close();
        return Optional.of(result);
    }

    private Task converter(Cursor cursor) {
        return new Task(
                UUID.fromString(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getLong(3),
                cursor.getLong(4),
                StatusType.valueOf(cursor.getInt(5))
        );
    }
}
