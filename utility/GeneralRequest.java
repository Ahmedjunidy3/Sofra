package com.example.sofra.utility;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;


import com.example.sofra.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GeneralRequest {
//    public static void getSpinnerData( final Activity activity, final Spinner spinner , final EmptySpinnerAdapter spinnerAdapter , final String hint ,
//                                      Call<GeneralResponse> method,  final Spinner spinner2 , final EmptySpinnerAdapter spinnerAdapter2 , final String hint2 ,
//                                       Call<GeneralResponse> method2){
//        HelperMethod.showProgressDialog(activity,activity.getString(R.string.wait));
//        method.enqueue(new Callback<GeneralResponse>() {
//            @Override
//            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
//                try{
//                    HelperMethod.dismissProgressDialog();
//
//                    if (response.body().getStatus() == 1){
//                        spinner.setVisibility(View.VISIBLE);
//                        spinnerAdapter.setData(response.body().getData() , hint);
//
//                        spinner.setAdapter(spinnerAdapter);
//
//                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                            @Override
//                            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
//                                if (i != 0 ){
//                                    getSpinnerData(activity, spinner2 , spinnerAdapter2 , hint2 , method2 );
//                                }else {
//                                    spinner2.setVisibility(View.GONE);
//                                }
//                            }
//
//                            @Override
//                            public void onNothingSelected(AdapterView<?> parent) {
//
//                            }
//                        });
//                    }
//                }catch (Exception e){
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<GeneralResponse> call, Throwable t) {
//                HelperMethod.dismissProgressDialog();
//            }
//        });
//    }
}
