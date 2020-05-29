package com.myapplicationdev.android.p05_ps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btAdd;
    ListView lv;
    ArrayAdapter aa;
    ArrayList<Task> task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btAdd = findViewById(R.id.btAdd);
        lv = findViewById(R.id.lv);
        DBHelper db = new DBHelper(this);
        task = new ArrayList<Task>();
        task = db.getAllTasks();
        aa = new taskAdapter(this, R.layout.row, task);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Add.class);
                startActivityForResult(i, 9);
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 9){
            task = new ArrayList<Task>();
            task.clear();
            DBHelper db = new DBHelper(MainActivity.this);
            task = db.getAllTasks();
            aa = new taskAdapter(this, R.layout.row, task);
            lv.setAdapter(aa);
        }
    }
}
