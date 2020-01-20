package com.example.razorpayintegration.Adapter;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.razorpayintegration.Fragments.Appointment;
import com.example.razorpayintegration.Model.Appointmentbystatus;
import com.example.razorpayintegration.Model.Essentials;
import com.example.razorpayintegration.R;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.ViewHolder>
{
    List<Appointmentbystatus> list;
     Context context;

    public RequestAdapter(List<Appointmentbystatus> list,Context context)
    {
        this.context=context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        Log.d("Oncreate", "onCreateViewHolder: ");
        View v=LayoutInflater.from(context).inflate(R.layout.request_post_singleitem,null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {

        Log.d("Hello", list.get(position).getDob());
holder.setAadharno(list.get(position).getAadhaar());
holder.setDob(list.get(position).getDob());
holder.setGender(list.get(position).getGender());
holder.setStatus(list.get(position).getStatus());
holder.setName(list.get(position).getName());
holder.setDate(list.get(position).getId().substring(0,10));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        View v;
        TextView Name,gender,dob,status,aadharno,accept,reject,date;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            v=itemView;
            Name=v.findViewById(R.id.name_item_appointment);
            gender=v.findViewById(R.id.gender_item_appointment);
            dob=v.findViewById(R.id.dob_item_appointment);
            status=v.findViewById(R.id.status_item_appointment);
            aadharno=v.findViewById(R.id.aadhar_item_appointment);
            accept=v.findViewById(R.id.accept_btn);
            reject=v.findViewById(R.id.reject_btn);
date=v.findViewById(R.id.date_item_appointment);
            accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Call<List<Appointmentbystatus>> call= Essentials.getRequestApi().getaccepted(list.get(itemView.getVerticalScrollbarPosition()).getId());
                    call.enqueue(new Callback<List<Appointmentbystatus>>() {
                        @Override
                        public void onResponse(Call<List<Appointmentbystatus>> call, Response<List<Appointmentbystatus>> response)
                        {
                            if(!response.isSuccessful())
                            {
                                Log.d("Code",String.valueOf(response.code()));
                                return;
                            }
                            list.remove(itemView.getVerticalScrollbarPosition());
                            notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(Call<List<Appointmentbystatus>> call, Throwable t)
                        {
                            Log.d("OnFailure",t.getMessage());
                        }
                    });
                }
            });
            reject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                 Call<List<Appointmentbystatus>> call= Essentials.getRequestApi().getDecline(list.get(itemView.getVerticalScrollbarPosition()).getId());
                 call.enqueue(new Callback<List<Appointmentbystatus>>() {
                     @Override
                     public void onResponse(Call<List<Appointmentbystatus>> call, Response<List<Appointmentbystatus>> response)
                     {
                      if(!response.isSuccessful())
                      {
                          Log.d("Code",String.valueOf(response.code()));
                      }
                      list.remove(itemView.getVerticalScrollbarPosition());
                     }

                     @Override
                     public void onFailure(Call<List<Appointmentbystatus>> call, Throwable t) {

                     }
                 });
                }
            });


        }
        public void setName(String name)
        {
            Name.setText("Name:"+name);
        }
        public void setGender(String Gender)
        {
            gender.setText("Gender:"+Gender);
        }
        public void setDob(String Dob)
        {
            dob.setText("Dob:"+Dob);
        }
        public void setStatus(String Status)
        {
            status.setText("Status:"+Status);
        }
        public void setAadharno(String AAdharno)
        {
            aadharno.setText("Aadhar no:"+AAdharno);
        }
        public void setDate(String milli)
        {
            String dateFormat= java.text.DateFormat.getDateInstance().format(new Date());
            date.setText("Date:"+dateFormat);
        }


    }
}
