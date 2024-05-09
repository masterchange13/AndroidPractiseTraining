package com.example.fragmentdemo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 1. Fragment依赖于Activity的生命周期
 * 2. Fragment 和 Activity    Fragment 和 Fragment 实现彼此的信息传递
 *
 */
public class TestFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";

    private String mParam1;

    private TextView tv;

    public static TestFragment newInstance(String param1){
//        从Activity中获取的数据param1
        TestFragment testFragment = new TestFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1); // 通过bundle对象存放待处理的数据
        testFragment.setArguments(args); // fragment 通过Arguments进行get和set信息
        return testFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        view就是直接代表fragment页面
        // 渲染Fragment页面的，将数据显示到页面上
        tv = view.findViewById(R.id.tv_content);
        if (!TextUtils.isEmpty(mParam1)){
            tv.setText(mParam1);
        }
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test, container, false);
    }
}