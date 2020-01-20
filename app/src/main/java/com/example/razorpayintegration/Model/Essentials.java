package com.example.razorpayintegration.Model;

import com.example.razorpayintegration.Interface.RequestApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Essentials
{
    public final static String BASE_ADDRESS="https://64e04f55.ngrok.io/api/";

    public final  static String SHARED_PREF="shared_pref";
    public final  static String SHARED_PREF2="Shared_pref";

    public static Retrofit retrofit=new Retrofit.Builder().baseUrl(BASE_ADDRESS).addConverterFactory(GsonConverterFactory.create()).build();
    public static RequestApi requestApi=retrofit.create(RequestApi.class);

    public static RequestApi getRequestApi()
    {
        return requestApi;
    }


}
