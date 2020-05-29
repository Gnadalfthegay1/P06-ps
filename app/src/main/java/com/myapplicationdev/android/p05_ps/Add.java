package com.myapplicationdev.android.p05_ps;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

public class Add extends AppCompatActivity {
    EditText etTask, etDetail, etSec;
    Button btAddTask, btCancel;
    int reqCode = 12345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        etTask = findViewById(R.id.etTask);
        etDetail = findViewById(R.id.etDetail);
        etSec = findViewById(R.id.etSeconds);

        btAddTask = findViewById(R.id.btAddTask);
        btCancel = findViewById(R.id.btCancel);

        btAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(getApplicationContext());
                db.inserTask(etTask.getText().toString(), etDetail.getText().toString(), Integer.parseInt(etSec.getText().toString()));

                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND, Integer.parseInt(etSec.getText().toString()));

                Intent intent = new Intent(Add.this,
                        ScheduledNotificationReceiver.class);
                intent.putExtra("notification", etTask.getText().toString());

                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        Add.this, reqCode,
                        intent, PendingIntent.FLAG_CANCEL_CURRENT);

                AlarmManager am = (AlarmManager)
                        getSystemService(Activity.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                        pendingIntent);

                Intent i = getIntent();
                setResult(RESULT_OK, i);
                finish();
            }
        });
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
