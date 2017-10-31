package com.archer.lab05;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private List<String> list;

    DatabaseHelper dbhelper;

    public void initialize() {
        list = new ArrayList<>();

        dbhelper = new DatabaseHelper(this);

        Cursor cursor = dbhelper.getData();
        if(cursor.moveToFirst()) {
            while(cursor.moveToNext()) {
                double firstArg = cursor.getDouble(cursor.getColumnIndex("firstnum"));
                double secondArg = cursor.getDouble(cursor.getColumnIndex("secondnum"));
                String operation = cursor.getString(cursor.getColumnIndex("operation"));
                double resultArg = cursor.getDouble(cursor.getColumnIndex("resultnum"));

                String oneCommand = String.format("%.2f", firstArg) + " " + operation + " " + String.format("%.2f", secondArg) + " = " + String.format("%.2f", resultArg);
                list.add(oneCommand);
            }
        }

        String[] tempArray = new String[ list.size() ];
        list.toArray(tempArray);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.activity_history, tempArray);

        ListView listView = (ListView) findViewById(R.id.mobile_list);
        listView.setAdapter(adapter);

        listView.setSelection(adapter.getCount() - 1);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.history_options_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.eraseHistoryItem:
                dbhelper.eraseHistory();
                finish();
                return true;
            case R.id.exitItem:
                finish();
                return true;
            default: return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history2);

        initialize();
    }
}
