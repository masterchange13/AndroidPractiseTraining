package com.example.fragmentdemo.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class MyViewPagerAdapter extends PagerAdapter {

//    创建一个图片集合，借用ViewPager实现滑动切换浏览
    private List<ImageView> imageViewList;

    public MyViewPagerAdapter(List<ImageView> imageViewList){
        this.imageViewList = imageViewList;
    }

//    统计页面数量
    @Override
    public int getCount() {
       /* if (imageViewList == null){
            return 0;
        }else {
            return imageViewList.size();
        }*/
        return imageViewList == null ? 0:imageViewList.size();
    }

//    判断内部的元素是否是view对象
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

//    最后交给viewPager的不是一个单独的view，而是一个整体viewGroup
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = imageViewList.get(position);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
       container.removeView((View) object);
    }
}
