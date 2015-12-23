// worked on this homework with Ammanuel

package com.murach.tasklist;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Shimul on 12/23/2015.
 */
public class ShowList extends Activity {

    TableLayout tblTasks;
    TaskListDB db = new TaskListDB(this);

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);

        StringBuilder sb = new StringBuilder();
        tblTasks = (TableLayout)findViewById(R.id.tlTasks);

        ArrayList<Task> tasks = db.getTasks("Personal");
        for (Task t : tasks) {
            TextView tvTaskname = new TextView(this);
            TextView tvTaskNotes = new TextView(this);
            TextView tvCompleted = new TextView(this);

            TableRow tr1 = new TableRow(this);

            tvTaskname.setText(t.getName());
            tvTaskNotes.setText(t.getNotes());
            tvCompleted.setText(t.getCompletedDate());

            tr1.addView(tvTaskname);
            tr1.addView(tvTaskNotes);
            tr1.addView(tvCompleted);

            tblTasks.addView(tr1);
        }

    }
}
