package com.feicui.edu.housekeeper.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.feicui.edu.housekeeper.R;


/**
 * Created by Administrator on 2016/9/19 0019.
 */
public class ViewPagerFragment extends Fragment {
    private int iconId;
    public void initData(int iconId){
        this.iconId = iconId;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_pager, null);
        ImageView iv = (ImageView) view.findViewById(R.id.icon);
        iv.setImageResource(iconId);
        return view;
    }
}
