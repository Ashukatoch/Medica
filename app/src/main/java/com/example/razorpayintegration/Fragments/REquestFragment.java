package com.example.razorpayintegration.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.razorpayintegration.Adapter.RequestAdapter;
import com.example.razorpayintegration.Model.Appointmentbystatus;
import com.example.razorpayintegration.Model.Essentials;
import com.example.razorpayintegration.Model.Request;
import com.example.razorpayintegration.R;
import com.example.razorpayintegration.Interface.RequestApi;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class REquestFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public RequestApi requestApi;
    public String username;
    public List<Appointmentbystatus> list=new ArrayList<>();
    public SharedPreferences sharedPreferences;
    private RecyclerView mrecyclerview;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private CompositeDisposable disposable=new CompositeDisposable();

    private OnFragmentInteractionListener mListener;
    private RequestAdapter requestAdapter;

    public REquestFragment() {
    }


    public static REquestFragment newInstance(String param1, String param2) {
        REquestFragment fragment = new REquestFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_request, container, false);
        requestApi= Essentials.getRequestApi();
        sharedPreferences=this.getContext().getSharedPreferences(Essentials.SHARED_PREF,Context.MODE_PRIVATE);
        username=sharedPreferences.getString("username","Hospital1");
        mrecyclerview=v.findViewById(R.id.request_recyclerview);
        mrecyclerview.setHasFixedSize(true);
        mrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        requestAdapter =new RequestAdapter(list,getActivity());
        mrecyclerview.setAdapter(requestAdapter);

        getrequest(username);

        return v;
    }


    public void getrequest(String username)
    {
        Log.d("USername",username);
        Call<List<Request>> call=Essentials.getRequestApi().getRequest(username);
        call.enqueue(new Callback<List<Request>>() {
            @Override
            public void onResponse(Call<List<Request>> call, Response<List<Request>> response)
            {
                if(!response.isSuccessful())
                {
                    Log.d("Code", String.valueOf(response.code()));
                    return;
                }
                list.clear();
                if(!response.body().isEmpty())

                {
                    for(int i=0;i<response.body().size();i++)
                    {
                        String [] arr=response.body().get(i).getSeatid().split(",");
                        for(int j=0;j<arr.length;j++)
                        {
                            Call<List<Appointmentbystatus>> call2=Essentials.getRequestApi().getrequestitem(arr[j]);
                            call2.enqueue(new Callback<List<Appointmentbystatus>>() {
                                @Override
                                public void onResponse(Call<List<Appointmentbystatus>> call, Response<List<Appointmentbystatus>> response) {
                                    if(!response.isSuccessful())
                                    {
                                        Log.d("CODE",String.valueOf(response.code()));
                                    }
                                    if(!response.body().isEmpty())
                                    {
                                            list.add(response.body().get(0));
                                        requestAdapter.notifyDataSetChanged();
                                    }
                                }

                                @Override
                                public void onFailure(Call<List<Appointmentbystatus>> call, Throwable t) {
                                    Log.d("OnFailure",t.getMessage());

                                }
                            });
                        }


                    }

                }



            }

            @Override
            public void onFailure(Call<List<Request>> call, Throwable t)
            {
                Log.d("OnFailure",t.getMessage());

            }
        });

    }




















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
