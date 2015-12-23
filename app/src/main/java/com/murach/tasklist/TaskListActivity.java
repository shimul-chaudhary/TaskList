package com.murach.tasklist;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class TaskListActivity extends Activity {

    TextView tvTaskName;
    TextView tvNotes;
    Button btnAddTask;
    Button btnShowTasks;
    CheckBox chBoxCompleted;
    CheckBox chBoxHidden;
    TaskListDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);


        tvTaskName = (TextView)findViewById(R.id.etTaskName);
        tvNotes = (TextView)findViewById(R.id.etNotes);
        btnAddTask = (Button)findViewById(R.id.btnAddTask);
        btnShowTasks = (Button)findViewById(R.id.btnShowTasks);
        chBoxCompleted = (CheckBox)findViewById(R.id.chBoxCompleted);
        chBoxHidden = (CheckBox)findViewById(R.id.chBoxHidden);

        // get db and StringBuilder objects
        db = new TaskListDB(this);
        StringBuilder sb = new StringBuilder();
        
        // insert a task
        Task task = new Task(1, "Make dentist appointment", "", "0", "0");
        long insertId = db.insertTask(task);
        if (insertId > 0) {
            sb.append("Row inserted! Insert Id: " + insertId + "\n");
        }
        
        // update a task
        task.setId((int) insertId);
        task.setName("Update test");
        int updateCount = db.updateTask(task);
        if (updateCount == 1) {
            sb.append("Task updated! Update count: " + updateCount + "\n");
        }
        
        // delete a task
        int deleteCount = db.deleteTask(insertId);
        if (deleteCount == 1) {
            sb.append("Task deleted! Delete count: " + deleteCount + "\n\n");
        }
        
        // display all tasks (id + name)
        ArrayList<Task> tasks = db.getTasks("Personal");
        for (Task t : tasks) {
            sb.append(t.getId() + "|" + t.getName() + "\n");
        }
        
        // display string on UI
        TextView taskListTextView = (TextView) 
                findViewById (R.id.taskListTextView);
        //taskListTextView.setText(sb.toString());
        btnAddTask.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                int completed = 0, hidden = 0;
                if(chBoxCompleted.isChecked())
                    completed = 1;
                if(chBoxHidden.isChecked())
                    hidden = 1;

                Task t = new Task(1, tvTaskName.getText().toString(), tvNotes.getText().toString(),
                        String.valueOf(completed), String.valueOf(hidden));
                long insId = db.insertTask(t);
                if (insId > 0) {
                    Toast toast = Toast.makeText(getBaseContext(),"Task added to database",Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        btnShowTasks.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TaskListActivity.this, ShowList.class);
                startActivity(intent);
            }
        });
    }
}