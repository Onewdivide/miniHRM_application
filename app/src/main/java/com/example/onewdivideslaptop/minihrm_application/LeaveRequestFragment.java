package com.example.onewdivideslaptop.minihrm_application;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onewdivideslaptop.minihrm_application.responseAndBody.remainLeaveDayResponse;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LeaveRequestFragment extends Fragment {

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://minihrm-205709.appspot.com/")
            .addConverterFactory(GsonConverterFactory.create());

    public static Retrofit retrofit = builder.build();
    public miniHRMClient miniHRMClient = retrofit.create(miniHRMClient.class);
    public String urlencode = "application/x-www-form-urlencoded";
    public TextView annualLeave,personalLeave,sickLeave,ordinationLeave;
    public Button leaveHistoryBtn,leaveRequestBtn,viewHolidaysBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_leave_request, container, false);

        annualLeave = (TextView) v.findViewById(R.id.annualLeave);
        personalLeave = (TextView) v.findViewById(R.id.personalLeave);
        sickLeave = (TextView) v.findViewById(R.id.sickLeave);
        ordinationLeave = (TextView) v.findViewById(R.id.ordinationLeave);

        leaveHistoryBtn = (Button) v.findViewById(R.id.leaveHistoryBtn);
        leaveRequestBtn = (Button) v.findViewById(R.id.leaveRequestBtn);
        viewHolidaysBtn = (Button) v.findViewById(R.id.viewHolidaysBtn);

        leaveRequestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),leaveRequestActivity.class);
                startActivity(intent);
            }
        });

        Call<remainLeaveDayResponse> call1 = miniHRMClient.getRemainLeaveDay(
                urlencode,
                "Bearer "+staticData.token,
                Calendar.getInstance().get(Calendar.YEAR),
                staticData.id
        );

        call1.enqueue(new Callback<remainLeaveDayResponse>() {
            @Override
            public void onResponse(Call<remainLeaveDayResponse> call, Response<remainLeaveDayResponse> response) {
                annualLeave.setText(String.valueOf(response.body().getAnnualLeaveRemain()));
                personalLeave.setText(String.valueOf(response.body().getPersonalLeaveRemain()));
                sickLeave.setText(String.valueOf(response.body().getSickLeaveRemain()));
                ordinationLeave.setText(String.valueOf(response.body().getOrdinationLeaveRemain()));
            }

            @Override
            public void onFailure(Call<remainLeaveDayResponse> call, Throwable t) {
                Toast.makeText(getContext(),"get remain leaveday fail : "+t,Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }


}
