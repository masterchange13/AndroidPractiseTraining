package com.example.fragmentdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.fragmentdemo.adapter.MyViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class viewPagerActivity extends AppCompatActivity {

    private ViewPager viewPager;

//  自定义viewPager的页面元素的适配器
    private MyViewPagerAdapter myViewPagerAdapter;

//    存放图片的集合
    private List<ImageView> imageViewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

//        1、找到ViewPager
        viewPager = findViewById(R.id.vp1);

//        2、初始化页面内容
        initImageView();

//        3、实例化适配器
        myViewPagerAdapter = new MyViewPagerAdapter(imageViewList);

//        4、将自定义的适配器传入ViewPager
        viewPager.setAdapter(myViewPagerAdapter);

//        5、对滑动切换的事件进行监听
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                当滑动到选中某一个元素的时候，弹窗提示一下
                Toast.makeText(viewPagerActivity.this,"第"+(position + 1)+"张",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    public void initImageView(){
        ImageView iv1 = new ImageView(this);
        iv1.setImageResource(R.drawable.apple);
        ImageView iv2 = new ImageView(this);
        iv2.setImageResource(R.drawable.peach);
        ImageView iv3 = new ImageView(this);
        iv3.setImageResource(R.drawable.fruit3);
        ImageView iv4 = new ImageView(this);
        iv4.setImageResource(R.drawable.fruit4);

        imageViewList = new ArrayList<>();
        imageViewList.add(iv1);
        imageViewList.add(iv2);
        imageViewList.add(iv3);
        imageViewList.add(iv4);

    }
}