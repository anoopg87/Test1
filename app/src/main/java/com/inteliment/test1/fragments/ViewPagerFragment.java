package com.inteliment.test1.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.inteliment.test1.R;

public class ViewPagerFragment extends Fragment {

    View mView;
    public static String NAME="NAME",POSTION="POSTION";
    String fragmentName="";
    String fragmentPosition="";
    FrameLayout mFrameLayout;
    ViewGroup container;
    public static ViewPagerFragment newInstance(String fragmentName,int postion) {
        Bundle args = new Bundle();
         ViewPagerFragment fragment = new ViewPagerFragment();
        args.putString(NAME,fragmentName);
        args.putString(POSTION,String.valueOf(postion));
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(null!=getArguments()){

            fragmentName=getArguments().getString(NAME);
            fragmentPosition=getArguments().getString(POSTION);

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mFrameLayout=new FrameLayout(getActivity());
        this.container=container;
        return populateView();
    }

    public View populateView(){
         mView=LayoutInflater.from(getActivity()).inflate(R.layout.fragment_layout,container,false);
        ((TextView)mView.findViewById(R.id.textView)).setText(fragmentName);

        ((RelativeLayout)mView.findViewById(R.id.mainPanel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(),"Fragment :"+fragmentPosition,Toast.LENGTH_SHORT).show();
            }
        });
        mFrameLayout.addView(mView);

        return mFrameLayout;
    }
}
