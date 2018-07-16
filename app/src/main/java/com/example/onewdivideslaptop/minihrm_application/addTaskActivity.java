package com.example.onewdivideslaptop.minihrm_application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class addTaskActivity extends AppCompatActivity {

    public Button returnToMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        staticData.tabIndex = 1;

        returnToMain = (Button) findViewById(R.id.testBtn);

        final Intent intent = new Intent(addTaskActivity.this,MainActivity.class);

        returnToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });

    }
}
