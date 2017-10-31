package com.archer.lab05;

import android.content.Intent;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView resultView;
    Operation operation;
    DatabaseHelper db;

    public void initialize() {
        resultView = (TextView) findViewById(R.id.resultView);
        operation = new Operation();
        db = new DatabaseHelper(this);
    }

    public void buttonClicked(View v) {
        Button clickedBtn = (Button) v;
        char clicked = clickedBtn.getText().charAt(0);
        if((clicked >= '0' && clicked <= '9') || clicked == '.') {
            if(operation.isFirstSet && operation.isOpSet) {
                if(resultView.getText().toString().matches("[+*-/]"))
                    resultView.setText("");
            }
            if(clicked == '.' && resultView.getText().toString().contains(".")) {
                return;
            }
            resultView.append(clickedBtn.getText().toString());
        }
        else if(clicked == '+' || clicked == '*' || clicked == '-' || clicked == '/') {
            if(!operation.isFirstSet) {
                operation.first = Double.parseDouble(resultView.getText().toString());
                resultView.setText(clickedBtn.getText());
                operation.isFirstSet = true;
            }
            operation.operator = clicked;
            operation.isOpSet = true;
        }
        else if(clicked == '=') {
            operation.second = Double.parseDouble(resultView.getText().toString());
            double res = operation.calculateResult(db);
            resultView.setText(Double.toString(res));
            operation.isOpSet = false;
            operation.isFirstSet = false;
        }
        else if(clicked == 'C') {
            resultView.setText("");
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_options_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.history:
                Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(intent);
                return true;
            case R.id.exit:
                finish();
                return true;
            default: return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }
}
