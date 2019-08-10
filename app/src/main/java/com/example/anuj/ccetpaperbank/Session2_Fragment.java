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


public class Session2_Fragment extends Fragment {

    private AppBarLayout appBar2;
    private TabLayout tab2;
    private ViewPager viewPager2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view2 = inflater.inflate(R.layout.fragment_session2_, container, false);
        View container1 = (View)container.getParent();


        appBar2=  container1.findViewById(R.id.appbar);
        tab2= new TabLayout(getActivity());
        tab2.setTabTextColors(Color.parseColor("#ffffff"),Color.parseColor("#ffffff"));
        appBar2.addView(tab2);

        viewPager2=view2.findViewById(R.id.pager2);
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getFragmentManager());
        viewPager2.setAdapter(pagerAdapter);
        tab2.setupWithViewPager(viewPager2);


        // Inflate the layout for this fragment
        return view2;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        appBar2.removeView(tab2);

    }
    public class  ViewPagerAdapter extends FragmentStatePagerAdapter{

        public ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);

        }
        String[] tabs={"Notes","Question Papers","Interview Questions"};

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0: return new Notes_2();
                case 1: return new questions_2();
                case 2: return new interview_2();


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







