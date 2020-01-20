package com.example.razorpayintegration.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.razorpayintegration.Interface.RequestApi;
import com.example.razorpayintegration.Model.Essentials;
import com.example.razorpayintegration.Model.User;
import com.example.razorpayintegration.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity
{
    private EditText musername,mpassword;
    private Button mSignin;
    public RequestApi requestApi;
    public static  SharedPreferences sharedPreferences;
    public final  String SHARED_PREF=Essentials.SHARED_PREF;
    public  static SharedPreferences.Editor editor;
    private static ProgressDialog progressDialog;

    @Override
    protected void onStart() {
        super.onStart();
        if(sharedPreferences.getString("Logged In","false").matches("true"))
        {
            Log.d("Status",sharedPreferences.getString("Logged In","true") );
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        sharedPreferences=getSharedPreferences(SHARED_PREF,MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.putString("Logged In","false");

        musername=findViewById(R.id.Username_EditText);
        mpassword=findViewById(R.id.password_editText);
        editor.putString("username",musername.getText().toString());
        mSignin=findViewById(R.id.btn);
        progressDialog=new ProgressDialog(SignInActivity.this);
        requestApi=Essentials.getRequestApi();
        progressDialog.setMessage("Signing In...");
        mSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Pressed", "onClick: ");
                String spass=mpassword.getText().toString().trim();
                String susername=musername.getText().toString().trim();
                if(TextUtils.isEmpty(susername))
                {
                    musername.setError("Required Field....");
                    return;
                }
                if(TextUtils.isEmpty(spass))
                {
                    mpassword.setError("Required Field....");
                    return;
                }
                progressDialog.show();

                getUSER();
            }
        });


    }
    private void getUSER()
    {
        Call<List<User>> call=requestApi.getUser(musername.getText().toString(),mpassword.getText().toString());
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response)
            {
                if(!response.isSuccessful())
                {
                    Log.d("Code",String.valueOf(response.code()));
                    mpassword.setText("");
                    editor.putString("Logged In","false");
                    progressDialog.dismiss();
                    return;

                }
                Log.d("Status", "Success");
                Toast.makeText(getApplicationContext(), "Sign in successful!!", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("username",musername.getText().toString().trim());
                editor.putString("Logged In","true");
                startActivity(intent);
                finish();


            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t)
            {

                editor.putString("Logged In","false");
                mpassword.setText("");
                progressDialog.dismiss();
                Log.d("Failure", t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

    }

}
