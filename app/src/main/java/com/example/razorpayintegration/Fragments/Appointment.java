package com.example.razorpayintegration.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.razorpayintegration.Adapter.AppointmentRecyclerViewAdapter;
import com.example.razorpayintegration.Adapter.RequestAdapter;
import com.example.razorpayintegration.Model.Appointmentbystatus;
import com.example.razorpayintegration.Model.Essentials;
import com.example.razorpayintegration.Model.Request;
import com.example.razorpayintegration.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Appointment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private SharedPreferences sharedPreferences;
    public String username;
    private RecyclerView mrecyclerview;
    private AppointmentRecyclerViewAdapter appointmentRecyclerViewAdapter;

    private String mParam1;
    private String mParam2;
    private List<Appointmentbystatus> list=new ArrayList<>();

    private OnFragmentInteractionListener mListener;

    public Appointment() {
    }


    public static Appointment newInstance(String param1, String param2) {
        Appointment fragment = new Appointment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_appointment, container, false);
        sharedPreferences = this.getContext().getSharedPreferences(Essentials.SHARED_PREF, Context.MODE_PRIVATE);
        username = sharedPreferences.getString("username", "Hospital1");
        mrecyclerview = v.findViewById(R.id.appointment_recyclerview);
        mrecyclerview.setHasFixedSize(true);
        mrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        appointmentRecyclerViewAdapter = new AppointmentRecyclerViewAdapter(list, getActivity());
        mrecyclerview.setAdapter(appointmentRecyclerViewAdapter);

        getall();
        return v;
    }
    public void getall()
    {
Call<List<Appointmentbystatus>> call=Essentials.getRequestApi().getAll();
call.enqueue(new Callback<List<Appointmentbystatus>>() {
    @Override
    public void onResponse(Call<List<Appointmentbystatus>> call, Response<List<Appointmentbystatus>> response)
    {
     if(!response.isSuccessful())
     {
         Log.d("Code:", String.valueOf(response.code()));
         return;
     }
     list.clear();
     if(!response.body().isEmpty())
     {
         for(int i=0;i<response.body().size();i++)
         {
             list.add(response.body().get(i));
             appointmentRecyclerViewAdapter.notifyDataSetChanged();
         }
     }

    }

    @Override
    public void onFailure(Call<List<Appointmentbystatus>> call, Throwable t)
    {
      Log.d("OnFailure",t.getMessage());
    }
});

    }

















    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
