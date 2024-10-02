package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> taskList;
    private ArrayAdapter<String> adapter;
    private EditText editTextTask;
    private ListView listViewTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize task list and adapter
        taskList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskList);

        // Bind UI elements
        editTextTask = findViewById(R.id.editTextTask);
        Button buttonAdd = findViewById(R.id.buttonAdd);
        listViewTasks = findViewById(R.id.listViewTasks);

        // Set adapter to ListView
        listViewTasks.setAdapter(adapter);

        // Add button click event
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = editTextTask.getText().toString();
                if (!task.isEmpty()) {
                    taskList.add(task);  // Add task to list
                    adapter.notifyDataSetChanged();  // Refresh ListView
                    editTextTask.setText("");  // Clear input field
                }
            }
        });

        // Remove task on long click
        listViewTasks.setOnItemLongClickListener((parent, view, position, id) -> {
            taskList.remove(position);  // Remove the selected task
            adapter.notifyDataSetChanged();  // Refresh ListView
            return true;
        });
    }
}
