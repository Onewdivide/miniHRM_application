package com.example.onewdivideslaptop.minihrm_application;

import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.onewdivideslaptop.minihrm_application.responseAndBody.getProjectsResponse;
import com.example.onewdivideslaptop.minihrm_application.responseAndBody.loginResponse;
import com.example.onewdivideslaptop.minihrm_application.responseAndBody.remainLeaveDayResponse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://minihrm-205709.appspot.com/")
            .addConverterFactory(GsonConverterFactory.create());

    public static Retrofit retrofit = builder.build();
    public String urlencode = "application/x-www-form-urlencoded";
    public int check = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            getUserDataFromInternalStorage();
            timeStampCompare(System.currentTimeMillis()/1000);
//            Log.e("staticData.token",staticData.token);
        } catch (IOException e) {
            e.printStackTrace();
        }

        tabLayout = (TabLayout) findViewById(R.id.tablayout_id);

        viewPager = (ViewPager) findViewById(R.id.viewPager_id);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        //Add Fragment
        viewPagerAdapter.addFragment(new ProfileFragment(),"Profile");
        viewPagerAdapter.addFragment(new calendarFragment(),"Timesheet");
        viewPagerAdapter.addFragment(new LeaveRequestFragment(),"Leave Request");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.userforminihrm);
        tabLayout.getTabAt(1).setIcon(R.drawable.calendarforminihrm);
        tabLayout.getTabAt(2).setIcon(R.drawable.mailminihrm);

        tabLayout.setScrollPosition(staticData.tabIndex,0f,true);
        viewPager.setCurrentItem(staticData.tabIndex);


        miniHRMClient miniHRMClient = retrofit.create(miniHRMClient.class);

        Call<List<getProjectsResponse>> call = miniHRMClient.getProject(urlencode,"Bearer "+staticData.token);

        if (staticData.id!=0)
        call.enqueue(new Callback<List<getProjectsResponse>>() {
            @Override
            public void onResponse(Call<List<getProjectsResponse>> call, Response<List<getProjectsResponse>> response) {
//                staticData.setProjectsResponses(response.body());
                try {
                    FileOutputStream writeProjectId = openFileOutput("listProjectID",MODE_PRIVATE);
                    FileOutputStream writeProjectName = openFileOutput("listProjectName",MODE_PRIVATE);
                    check = 2;
                    for (int i=0; i< response.body().size(); i++){
                        writeProjectId.write(String.valueOf(response.body().get(i).getId()).getBytes());
                        writeProjectId.write(("\n").getBytes());
                        Log.e("addProjectId",String.valueOf(response.body().get(i).getId()));
                        writeProjectName.write(response.body().get(i).getName().getBytes());
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (response.body() == null)
                    Toast.makeText(MainActivity.this,"it's null",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<getProjectsResponse>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"import projects description fail",Toast.LENGTH_SHORT).show();
            }
        });




    }

    public void getUserDataFromInternalStorage() throws IOException {
        String message;
        FileInputStream getUserId = openFileInput("userId");
        FileInputStream getUsername = openFileInput("username");
        FileInputStream getToken = openFileInput("token");
        FileInputStream getRefreshToken = openFileInput("refreshToken");
        FileInputStream getTimeStampCompare = openFileInput("timeStampCompare");

        InputStreamReader inputStreamReaderUserId = new InputStreamReader(getUserId);
        BufferedReader bufferedReaderUserId = new BufferedReader(inputStreamReaderUserId);

        InputStreamReader inputStreamReaderUsername = new InputStreamReader(getUsername);
        BufferedReader bufferedReaderUsername = new BufferedReader(inputStreamReaderUsername);

        InputStreamReader inputStreamReaderToken = new InputStreamReader(getToken);
        BufferedReader bufferedReaderToken = new BufferedReader(inputStreamReaderToken);

        InputStreamReader inputStreamReaderRefreshToken = new InputStreamReader(getRefreshToken);
        BufferedReader bufferedReaderRefreshToken = new BufferedReader(inputStreamReaderRefreshToken);

        InputStreamReader inputStreamReaderTimeStampCompare = new InputStreamReader(getTimeStampCompare);
        BufferedReader bufferedReaderTimeStampCompare = new BufferedReader(inputStreamReaderTimeStampCompare);

        while ((message=bufferedReaderUserId.readLine())!=null){
            staticData.id = Integer.parseInt(message);
        }

        while ((message=bufferedReaderUsername.readLine())!=null){
            staticData.username = message;
        }

        while ((message=bufferedReaderToken.readLine())!=null){
            staticData.token = message;
        }

        while ((message=bufferedReaderRefreshToken.readLine())!=null){
            staticData.refreshToken = message;
        }

        while ((message=bufferedReaderTimeStampCompare.readLine())!=null){
            staticData.timeStampCompare = Long.parseLong(message);
        }

    }

    public void timeStampCompare(Long timeStampNow) throws IOException {

        Log.e("timeStampNow",timeStampNow.toString());
        Log.e("static.timeStamp",staticData.timeStampCompare.toString());

        final FileOutputStream writeTokenInTimeStampCompare = openFileOutput("token",MODE_PRIVATE);
        FileOutputStream writeTimeStampCompare = openFileOutput("timeStampCompare",MODE_PRIVATE);

        writeTimeStampCompare.write(String.valueOf(System.currentTimeMillis()/1000).getBytes());

        if (timeStampNow - staticData.timeStampCompare >1 ){
            staticData.timeStampCompare = timeStampNow;
            //get new token!!

            miniHRMClient miniHRMClient = retrofit.create(miniHRMClient.class);

            Call<loginResponse> call = miniHRMClient.newToken(staticData.refreshToken);

            call.enqueue(new Callback<loginResponse>() {
                @Override
                public void onResponse(Call<loginResponse> call, Response<loginResponse> response) {
                    staticData.token = response.body().getToken();
                    try {
                        writeTokenInTimeStampCompare.write(response.body().getToken().getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<loginResponse> call, Throwable t) {

                }
            });

        }else{}


    }

}
