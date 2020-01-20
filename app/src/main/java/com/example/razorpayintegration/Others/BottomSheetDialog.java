package com.example.razorpayintegration.Others;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.razorpayintegration.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetDialog extends BottomSheetDialogFragment {

    private BottomSheetListener bottomSheetListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.bottomsheet,container,false);

        Button proceed=v.findViewById(R.id.proceed);
        final EditText newSeats=v.findViewById(R.id.new_seats);
        String pass,newS;

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!newSeats.getText().toString().isEmpty()){

                    dismiss();
                    View v=LayoutInflater.from(getContext()).inflate(R.layout.alertdialog,null);
                    final AlertDialog dialog=new AlertDialog.Builder(getContext()).create();
                    dialog.setView(v);
                    dialog.show();
                    final EditText pass=v.findViewById(R.id.password_alertdialog);
                    Button proceed=v.findViewById(R.id.proceed_alert);
                    Button back=v.findViewById(R.id.back_alertDialog);
                    proceed.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(pass.getText().toString().equals("yogesh99")){
                                dialog.dismiss();
                                bottomSheetListener.onButtonClicked(newSeats.getText().toString());
                                //Toast.makeText(getContext(), "seats edited successfully", Toast.LENGTH_SHORT).show();
                            }
                            //else
                                //Toast.makeText(getContext(), "Invalid User", Toast.LENGTH_SHORT).show();
                        }
                    });

                    back.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });

                    Toast.makeText(getContext(), "ButtonPressed"+newSeats.getText().toString(), Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getContext(), "fill all the entries", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return v;

    }

    public interface BottomSheetListener{
        void onButtonClicked(String text);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{
            bottomSheetListener=(BottomSheetListener)context;
        }
        catch (ClassCastException e){
            throw new ClassCastException(context.toString()+"must implement BottomsheetListener");
        }
    }
}
