package com.example.razorpayintegration.Activity;

import com.example.razorpayintegration.Fragments.Appointment;
import com.example.razorpayintegration.Fragments.REquestFragment;
import com.example.razorpayintegration.Model.Essentials;
import com.example.razorpayintegration.R;
import com.example.razorpayintegration.Others.SectionPagerAdapter;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;

import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends AppCompatActivity implements Appointment.OnFragmentInteractionListener,REquestFragment.OnFragmentInteractionListener
{
private DrawerLayout drawerLayout;
private ActionBarDrawerToggle drawerToggle;
private ViewPager mviewpager;
private TabLayout mtabLayout;
private Toolbar mtoolbar;
private String username;
private SharedPreferences sharedPreferences;
private SharedPreferences.Editor editor;
private SectionPagerAdapter sectionPagerAdapter;
private NavigationView navigationView;

private CompositeDisposable compositeDisposable=new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView=findViewById(R.id.navigation_drawer);
        username=getIntent().getStringExtra("username");
        drawerLayout=findViewById(R.id.main_drawerLayout);
        drawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.Open,R.string.Close);
        drawerToggle.setHomeAsUpIndicator(new ColorDrawable(Color.parseColor("#FFFFFF")));
        mtoolbar=findViewById(R.id.main_toolbar);
        mtabLayout=findViewById(R.id.main_tablayout);
        mviewpager=findViewById(R.id.main_viewpager);
        sectionPagerAdapter=new SectionPagerAdapter(getSupportFragmentManager());
        mviewpager.setAdapter(sectionPagerAdapter);
        mtabLayout.setupWithViewPager(mviewpager);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("Medica");
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sharedPreferences=getSharedPreferences(Essentials.SHARED_PREF,MODE_PRIVATE);
        editor=sharedPreferences.edit();
        //senddata();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {

             switch (menuItem.getItemId())
             {
                 case R.id.Manageseats_id  :
                     Intent intent=new Intent(getApplicationContext(), ManageSeatsActivity.class);
                     intent.putExtra("username",username);
                     startActivity(intent);
                     break;
                 case R.id.logout:
                     Intent intent2=new Intent(getApplicationContext(), SignInActivity.class);
                     editor.putString("Logged In","false");
                     startActivity(intent2);
                     break;

             }
               drawerLayout.closeDrawer(GravityCompat.START);
             return true;
          }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(drawerToggle.onOptionsItemSelected(item))
        {
        }
        return super.onOptionsItemSelected(item);
    }
//   public void senddata()
//   {
//       REquestFragment rEquestFragment=(REquestFragment)getSupportFragmentManager().findFragmentByTag("android:switcher:"+R.id.main_viewpager+":"+mviewpager.getCurrentItem());
//       rEquestFragment.recievedata(username)
//   }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
