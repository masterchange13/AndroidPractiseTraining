package com.example.fragmentdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fragmentdemo.adapter.MyFragmentPagerAdapter;
import com.example.fragmentdemo.fragments.Fragment2;

import java.util.ArrayList;

public class ViewPagerFragmentActivity extends AppCompatActivity implements View.OnClickListener {
    //    声明需要初始化的控件
    private LinearLayout llHome, llPhone, llFind, llMine,llCurrent;
    private ImageView ivHome, ivPhone, ivFind, ivMine;
    private TextView tvHome, tvPhone, tvFind, tvMine;

    private ViewPager2 viewPager2;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_fragment);

        viewPager2 = findViewById(R.id.vp2);

        initView();

        initViewPager2();
    }


    private void setBgItemColor(int id) {
        switch (id) {
            case R.id.ll_home:
                ivHome.setSelected(true);
                tvHome.setTextColor(getResources().getColor(R.color.nav_select_color));
                break;
            case R.id.ll_phone:
                ivPhone.setSelected(true);
                tvPhone.setTextColor(getResources().getColor(R.color.nav_select_color));
                break;
            case R.id.ll_find:
                ivFind.setSelected(true);
                tvFind.setTextColor(getResources().getColor(R.color.nav_select_color));
                break;
            case R.id.ll_mine:
                ivMine.setSelected(true);
                tvMine.setTextColor(getResources().getColor(R.color.nav_select_color));
                break;
            default:
                break;
        }
    }

    //    切换的时候去除点击后的样式
    public void resetBgColor() {
        ivHome.setSelected(false);
        tvHome.setTextColor(getResources().getColor(R.color.nav_color));
        ivPhone.setSelected(false);
        tvPhone.setTextColor(getResources().getColor(R.color.nav_color));
        ivFind.setSelected(false);
        tvFind.setTextColor(getResources().getColor(R.color.nav_color));
        ivMine.setSelected(false);
        tvMine.setTextColor(getResources().getColor(R.color.nav_color));
    }

    //   初始化底部导航栏的元素 或 页面控件
    private void initView() {
        llHome = findViewById(R.id.ll_home);
        llPhone = findViewById(R.id.ll_phone);
        llFind = findViewById(R.id.ll_find);
        llMine = findViewById(R.id.ll_mine);

        ivHome = findViewById(R.id.iv_home);
        ivPhone = findViewById(R.id.iv_phone);
        ivFind = findViewById(R.id.iv_find);
        ivMine = findViewById(R.id.iv_mine);

        tvHome = findViewById(R.id.tv_home);
        tvPhone = findViewById(R.id.tv_phone);
        tvFind = findViewById(R.id.tv_find);
        tvMine = findViewById(R.id.tv_mine);

//        初始化状态的元素信息
        llHome.setSelected(true);
        llCurrent = llHome;

        llHome.setOnClickListener(this);
        llPhone.setOnClickListener(this);
        llFind.setOnClickListener(this);
        llMine.setOnClickListener(this);

    }

    private void initViewPager2() {
        ArrayList<Fragment> fragments = new ArrayList<>();
//        将你所需要显示的Fragment页面添加进去
        fragments.add(TestFragment.newInstance("主页"));
        fragments.add(Fragment2.newInstance("通讯录"));
        fragments.add(TestFragment.newInstance("发现"));
        fragments.add(TestFragment.newInstance("个人中心"));

        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), getLifecycle(), fragments);
        viewPager2.setAdapter(myFragmentPagerAdapter);

//        监听viewPager2的操作事件
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                changeNav(position);  // 0 1 2 3
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    public void changeNav(int position) {
        llCurrent.setSelected(false);
        switch (position) {
            case R.id.ll_home:
                viewPager2.setCurrentItem(0);
            case 0:
                llHome.setSelected(true);
                llCurrent = llHome;
                break;
            case R.id.ll_phone:
                viewPager2.setCurrentItem(1);
            case 1:
                llPhone.setSelected(true);
                llCurrent = llPhone;
                break;
            case R.id.ll_find:
                viewPager2.setCurrentItem(2);
            case 2:
                llFind.setSelected(true);
                llCurrent = llFind;
                break;
            case R.id.ll_mine:
                viewPager2.setCurrentItem(3);
            case 3:
                llMine.setSelected(true);
                llCurrent = llMine;
                break;
            default:
                break;
        }
    }

    //    所有绑定过监听事件对象都在view里面
    @Override
    public void onClick(View view) {
        changeNav(view.getId()); // ll_home
    }
}