package com.example.onewdivideslaptop.minihrm_application;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.onewdivideslaptop.minihrm_application.responseAndBody.addTaskResponse;
import com.example.onewdivideslaptop.minihrm_application.responseAndBody.taskDescriptionInRequest;
import com.example.onewdivideslaptop.minihrm_application.responseAndBody.timesheetAddRequest;
import com.example.onewdivideslaptop.minihrm_application.staticData;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class addTaskActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{

    public Spinner projectIdSpinner,taskSpinner;
    public List<String> projectIdList = new ArrayList<>();
    public List<String> taskList = new ArrayList<>();
    public Button chooseTimeIn,chooseTimeOut,addTaskBtn;
    public TextView timeIn,timeOut,username,date;
    public String checkWhereChangeTime;
    public EditText taskDescription;
    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://minihrm-205709.appspot.com/")
            .addConverterFactory(GsonConverterFactory.create());

    public static Retrofit retrofit = builder.build();
    public String urlencode = "application/x-www-form-urlencoded";
    public taskDescriptionInRequest taskDescriptionInRequest1;
    public timesheetAddRequest timesheetAddRequest1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        staticData.tabIndex = 1;

        try {
            setListProjectForSpinner();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        for (int i = 0; i<staticData.projectsResponses.size(); i++){
//            projectIdList.add(staticData.projectsResponses.get(i).getId());
//        }

        taskList.add("Development");
        taskList.add("Testing");
        taskList.add("Meeting");
        taskList.add("Training");

        timeIn = (TextView)  findViewById(R.id.timeIn);
        timeOut = (TextView) findViewById(R.id.timeOut);
        username = (TextView) findViewById(R.id.userId);
        date = (TextView) findViewById(R.id.date);

        username.setText(String.valueOf(staticData.id));
        date.setText(getIntent().getStringExtra("dateCompare"));

        projectIdSpinner = (Spinner) findViewById(R.id.projectIdSpinner);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(addTaskActivity.this,
                android.R.layout.simple_list_item_1,projectIdList);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        projectIdSpinner.setAdapter(myAdapter);

        taskSpinner = (Spinner) findViewById(R.id.taskSpinner);
        ArrayAdapter<String> taskSpinnerAdapter = new ArrayAdapter<String>(addTaskActivity.this,
                android.R.layout.simple_list_item_1,taskList);
        taskSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        taskSpinner.setAdapter(taskSpinnerAdapter);

        chooseTimeIn = (Button) findViewById(R.id.chooseTimeIn);
        chooseTimeIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkWhereChangeTime = "timeIn";
                DialogFragment timePicker = new timePickerFragment();
                timePicker.show(getSupportFragmentManager(),"timePicker");
            }
        });

        taskDescription = (EditText) findViewById(R.id.taskDescription);

        chooseTimeOut = (Button) findViewById(R.id.chooseTimeOut);
        chooseTimeOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkWhereChangeTime = "timeOut";
                DialogFragment timePicker = new timePickerFragment();
                timePicker.show(getSupportFragmentManager(),"timePicker");

            }
        });

//        Log.e("timeStampInAddTask : ",staticData.timeStampCompare.toString());

        addTaskBtn = (Button) findViewById(R.id.addTaskBtn);
        addTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                miniHRMClient miniHRMClient = retrofit.create(miniHRMClient.class);
                String tempToken = "Bearer "+staticData.token;

                timesheetAddRequest1 = new timesheetAddRequest();
                String tempProjectSpinner = "";

                Log.e("length",String.valueOf(projectIdSpinner.getSelectedItem().toString().length()));

                for (int i =0; i<projectIdSpinner.getSelectedItem().toString().length(); i++ ){
                    Log.e("each char",String.valueOf(projectIdSpinner.getSelectedItem().toString().charAt(i)));
                    if (i!=projectIdSpinner.getSelectedItem().toString().length()-1)
                        tempProjectSpinner = tempProjectSpinner+String.valueOf(projectIdSpinner.getSelectedItem().toString().charAt(i));
                }



                if (taskDescription.getText().toString().length() == 0)
                    taskDescription.setText(" ");

                timesheetAddRequest1.add(
                        staticData.id,
                        date.getText().toString(),
                        tempProjectSpinner,
                        timeIn.getText().toString(),
                        timeOut.getText().toString(),
                        taskSpinner.getSelectedItem().toString(),
                        taskDescription.getText().toString());




                Call<List<addTaskResponse>> call = miniHRMClient.addTaskTocalendar
                        (       "application/json",
                                tempToken,
                                timesheetAddRequest1
                                );

                call.enqueue(new Callback<List<addTaskResponse>>() {
                    @Override
                    public void onResponse(Call<List<addTaskResponse>> call, Response<List<addTaskResponse>> response) {
                        Toast.makeText(addTaskActivity.this,response.body().get(response.body().size()-1).toString(),Toast.LENGTH_LONG).show();
                        Log.e("Response",response.body().get(response.body().size()-1).toString());
                    }

                    @Override
                    public void onFailure(Call<List<addTaskResponse>> call, Throwable t) {
                        Toast.makeText(addTaskActivity.this,"Fail",Toast.LENGTH_LONG);
                    }
                });

                Intent intent = new Intent(addTaskActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });




    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {

        String hours,min;

        if (i<10)
            hours = "0"+String.valueOf(i);
        else
            hours = String.valueOf(i);

        if (i1<10)
            min = "0"+String.valueOf(i1);
        else
            min = String.valueOf(i1);

        if (checkWhereChangeTime.equals("timeIn")){
            timeIn.setText(hours +" : "+min);
        }else
            timeOut.setText(hours+" : "+min);
    }

    public void setListProjectForSpinner() throws IOException {
        String message,messageResult;
        List<String> messageId = new ArrayList<>();
        List<String> messageName = new ArrayList<>();
        FileInputStream getListProjectId = openFileInput("listProjectID");

        InputStreamReader inputStreamReaderListProjectId = new InputStreamReader(getListProjectId);
        BufferedReader bufferedReaderListProjectId = new BufferedReader(inputStreamReaderListProjectId);

        FileInputStream getListProjectName = openFileInput("listProjectName");

        InputStreamReader inputStreamReaderListProjectName = new InputStreamReader(getListProjectName);
        BufferedReader bufferedReaderListProjectName = new BufferedReader(inputStreamReaderListProjectName);

        StringBuffer stringBuffer = new StringBuffer(); ;

        while ((message=bufferedReaderListProjectId.readLine())!=null){
            stringBuffer.delete(0,stringBuffer.length());
            stringBuffer.append(message+"\n");
            messageId.add(stringBuffer.toString());
            Log.e("projectID",message);
        }

        while ((message=bufferedReaderListProjectName.readLine())!=null){
            stringBuffer.delete(0,stringBuffer.length());
            stringBuffer.append(message+"\n");
            messageName.add(stringBuffer.toString());
        }

        for (int i=0 ; i<messageId.size() ;i++){
//            messageResult = messageId.get(i)+" "+messageName.get(i);
            messageResult = messageId.get(i);
            projectIdList.add(messageResult);
        }

    }
}
