package com.example.razorpayintegration.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.razorpayintegration.Model.Appointmentbystatus;
import com.example.razorpayintegration.R;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class AppointmentRecyclerViewAdapter extends RecyclerView.Adapter<AppointmentRecyclerViewAdapter.AppointmentviewHolder>
{
    public AppointmentRecyclerViewAdapter(List<Appointmentbystatus> list, Context context) {
        this.list = list;
        this.context = context;
    }

    List<Appointmentbystatus> list;
    Context context;
    @NonNull
    @Override
    public AppointmentviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v= LayoutInflater.from(context).inflate(R.layout.request_post_singleitem,null);
        return new AppointmentviewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentviewHolder holder, int position)
    {
        Log.d("OnBuilder","Build");

            holder.setDob(list.get(position).getDob());
            holder.setGender(list.get(position).getGender());
            holder.setStatus(list.get(position).getStatus());
            holder.setName(list.get(position).getName());
            holder.setAadharno(list.get(position).getAadhaar());
        holder.setDate(list.get(position).getId().substring(0,10));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class AppointmentviewHolder extends RecyclerView.ViewHolder {
        View v;
        TextView Name, gender, dob, status, aadharno,accept,decline,date;


        public AppointmentviewHolder(@NonNull View itemView)
        {
            super(itemView);
            v=itemView;
            Name = v.findViewById(R.id.name_item_appointment);
            gender = v.findViewById(R.id.gender_item_appointment);
            dob = v.findViewById(R.id.dob_item_appointment);
            status = v.findViewById(R.id.status_item_appointment);
            aadharno = v.findViewById(R.id.aadhar_item_appointment);
            accept=v.findViewById(R.id.accept_btn);
            decline=v.findViewById(R.id.reject_btn);
            accept.setVisibility(View.GONE);
            decline.setVisibility(View.GONE);
            date=v.findViewById(R.id.date_item_appointment);
        }

        public void setName(String name) {
            Name.setText("Name:" + name);
        }

        public void setGender(String Gender) {
            gender.setText("Gender:" + Gender);
        }

        public void setDob(String DOB)
        {
            dob.setText("Dob:" + DOB);
        }

        public void setStatus(String Status) {
            status.setText("Status:" + Status);
        }

        public void setAadharno(String aAdharno) {
            aadharno.setText("Aadharno:" + aAdharno);
        }
        public void setDate(String milli)
        {
            String dateFormat= java.text.DateFormat.getDateInstance().format(new Date());
            date.setText("Date:"+dateFormat);
        }
    }
    }
