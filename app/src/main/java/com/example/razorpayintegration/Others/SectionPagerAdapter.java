package com.example.razorpayintegration.Others;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.razorpayintegration.Fragments.Appointment;
import com.example.razorpayintegration.Fragments.REquestFragment;

public class SectionPagerAdapter extends FragmentPagerAdapter
{
    public SectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
        switch(position)
        {
            case 0:
                Appointment appointment=new Appointment();
                return appointment;

            case 1:
                REquestFragment rEquestFragment=new REquestFragment();
                return rEquestFragment;

            default:return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
    public CharSequence getPageTitle(int position)
    {
        switch (position)
        {
            case 0: return "Appointment";
            case 1: return "Requests";
            default: return null;
        }
    }
}

