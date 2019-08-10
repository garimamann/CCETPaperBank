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



public class Session1_Fragment extends Fragment {

    private AppBarLayout appBar1;
    private TabLayout tab1;
    private ViewPager viewPager1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view1 = inflater.inflate(R.layout.fragment_session1_, container, false);
        View container1 = (View)container.getParent();

        appBar1=  container1.findViewById(R.id.appbar);
        tab1= new TabLayout(getActivity());
        tab1.setTabTextColors(Color.parseColor("#ffffff"),Color.parseColor("#ffffff"));
        appBar1.addView(tab1);

        viewPager1=view1.findViewById(R.id.pager1);
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getFragmentManager());
        viewPager1.setAdapter(pagerAdapter);
        tab1.setupWithViewPager(viewPager1);



        // Inflate the layout for this fragment
        return view1;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        appBar1.removeView(tab1);

    }

    public class  ViewPagerAdapter extends FragmentStatePagerAdapter{

        public ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);

        }
        String[] tabs={"Notes","Question Papers","Interview Questions"};

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0: return new Notes_1();
                case 1: return new questions_1();
                case 2: return new interview_1();


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




