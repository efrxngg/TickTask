package edu.project.app;

import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.UUID;

import edu.project.app.dao.config.SQLiteConnection;
import edu.project.app.dao.repository.TaskRepository;
import edu.project.app.dao.repository.impl.TaskRepositoryImpl;
import edu.project.app.entity.StatusType;
import edu.project.app.entity.Task;

public class MainActivity extends AppCompatActivity {

    private final SQLiteOpenHelper connection = new SQLiteConnection(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TaskRepository taskRepository = new TaskRepositoryImpl(connection);
        long created = System.currentTimeMillis();
        taskRepository.save(new Task(UUID.randomUUID(), "Test", "Nps", created, created, StatusType.PENDING));
        taskRepository.findAll().forEach(System.out::println);
//        taskRepository.updateById(null);
    }

    @Override
    protected void onDestroy() {
        connection.close();
        super.onDestroy();
    }
}