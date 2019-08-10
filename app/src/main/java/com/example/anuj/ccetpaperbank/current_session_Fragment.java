package com.example.anuj.ccetpaperbank;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class current_session_Fragment extends Fragment {

    private AppBarLayout appBar;
    private TabLayout tab;
    private ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_current_session_, container, false);
        View container1 = (View)container.getParent();

        appBar= container1.findViewById(R.id.appbar);
        tab=new TabLayout(getActivity());
        tab.setTabTextColors(Color.parseColor("#ffffff"),Color.parseColor("#ffffff"));
        appBar.addView(tab);

        viewPager=view.findViewById(R.id.pager);
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        tab.setupWithViewPager(viewPager);


        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        appBar.removeView(tab);

    }

    public class  ViewPagerAdapter extends FragmentStatePagerAdapter{

        public ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);

        }
        String[] tabs={"Notes","Question Papers","Interview Questions"};

        @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 0: return new Notes_current();
                    case 1: return new questions_current();
                    case 2: return new interview_current();


                }

                return null;
            }

        @Override
        public int getCount() {
            return 3;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }
    }

    }






