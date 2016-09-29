package com.feicui.edu.housekeeper.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.feicui.edu.housekeeper.R;


public class ViewPagerFragment extends Fragment {

    private int resId;

    public void initData(int resId)
    {
        this.resId = resId;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_pager,null);
        ImageView iv = (ImageView) view.findViewById(R.id.iv_view_pager);
        iv.setImageResource(resId);
        return view;
    }
}
