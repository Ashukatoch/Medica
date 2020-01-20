package com.example.razorpayintegration.Interface;

import com.example.razorpayintegration.Model.Appointmentbystatus;
import com.example.razorpayintegration.Model.Hospital;
import com.example.razorpayintegration.Model.Opd_data;
import com.example.razorpayintegration.Model.Request;
import com.example.razorpayintegration.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RequestApi
{
    @GET("signin/hospital")
    Call<List<User>> getUser(@Query("username") String username, @Query("password") String password);
    @GET("hospitaluser")
    Call<Hospital> getHospital(@Query("Username") String Username);
    @GET("object")
    Call<List<Opd_data>> getOpd(@Query("username") String username,@Query("department") String department,@Query("date") String date);
    @GET("object3")
    Call<List<Request>> getRequest(@Query("username") String username);
    @GET("Appointmentbystatus")
    Call<List<Appointmentbystatus>> getrequestitem(@Query("id") String id);
    @GET("Accept")
    Call<List<Appointmentbystatus>> getaccepted(@Query("id") String id);
    @GET("Decline")
    Call<List<Appointmentbystatus>> getDecline(@Query("id") String id);
    @GET("getall")
    Call<List<Appointmentbystatus>> getAll();


}
