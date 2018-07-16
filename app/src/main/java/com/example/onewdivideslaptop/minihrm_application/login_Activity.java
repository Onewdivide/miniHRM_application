package com.example.onewdivideslaptop.minihrm_application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.onewdivideslaptop.minihrm_application.responseAndBody.loginResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class login_Activity extends AppCompatActivity {

    public EditText username;
    public EditText password;
    public Button submitBtn;
    public String result = "";
    String getUsername, getPassword;

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://minihrm-205709.appspot.com/")
            .addConverterFactory(GsonConverterFactory.create());

    public static Retrofit retrofit = builder.build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        submitBtn = (Button) findViewById(R.id.submitBtn);

        getUsername = username.getText().toString();
        getPassword = password.getText().toString();

        Log.e("test","testing");

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUsername = username.getText().toString();
                getPassword = password.getText().toString();
                Log.e("username : ",getUsername.toString());
                Log.e("password : ",getPassword.toString());
                loginExecute(getUsername,getPassword);
            }
        });


    }

    private void loginExecute (String username,String password){
        miniHRMClient miniHRMClient = retrofit.create(miniHRMClient.class);

        Call<loginResponse> call = miniHRMClient.login(username,password);

        call.enqueue(new Callback<loginResponse>() {
            @Override
            public void onResponse(Call<loginResponse> call, Response<loginResponse> response) {
                if (response.body() == null){
                    Toast.makeText(login_Activity.this,"wrong login", Toast.LENGTH_LONG).show();
                }
                else{
                    Log.e("debug!!!! : ",""+response.body().getId());
                    Log.e("debug!!!! : ",response.body().getUsername());
                    Log.e("debug!!!! : ",response.body().getToken());
                    staticData.id = response.body().getId();
                    staticData.username = response.body().getUsername();
                    staticData.token = response.body().getToken();
                    staticData.refreshToken = response.body().getRefreshToken();
                    staticData.timeStampCompare = System.currentTimeMillis()/1000;
                    staticData.tabIndex = 0;
//                    Toast.makeText(login_Activity.this,"id : "+response.body().getId()
//                            +"\n username : "+ response.body().getUsername()
//                            +"\n token : "+ response.body().getToken()
//                            , Toast.LENGTH_LONG).show();

                    Toast.makeText(login_Activity.this,"timeStampCompare : "+staticData.timeStampCompare.toString(),Toast.LENGTH_SHORT).show();
                    Log.e("timeStampCompare : ",staticData.timeStampCompare.toString());
                    Intent intent = new Intent(login_Activity.this,MainActivity.class);
                    startActivity(intent);

                }

//                if(response == null){
//                    Log.e("DEBUG!! : ","null");
//                }else {
//                    Log.e("DEBUG!! : ","not null");
//                }

            }

            @Override
            public void onFailure(Call<loginResponse> call, Throwable t) {
                Toast.makeText(login_Activity.this,"Fail", Toast.LENGTH_LONG).show();
            }
        });
    }
}
