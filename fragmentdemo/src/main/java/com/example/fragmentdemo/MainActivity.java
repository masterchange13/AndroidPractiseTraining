package com.example.fragmentdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //    声明需要初始化的控件
    private LinearLayout llHome,llPhone,llFind,llMine;
    private ImageView ivHome,ivPhone,ivFind,ivMine;
    private TextView tvHome,tvPhone,tvFind,tvMine;

//    绑定Fragment容器
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initEvent();

//        点击切换Fragment ，三种形式 1、直接替换， 2、借助ViewPager进行滑动切换 （普通viewPager 和 viewPager+Fragment）
    }

    private void initEvent(){
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
//        不传递参数的话，直接实例化对象进行调用
        TestFragment testFragment = TestFragment.newInstance("Fragment容器中显示的内容");
//        如果你需要获取参数值，常用一个Bundle对象进行存取值
        transaction.replace(R.id.fcv1,testFragment).commit();

//        设置页面打开的效果，给底部导航设置初始效果，让首页属于被选中状态
        setBgItemColor(R.id.ll_home);

        llHome.setOnClickListener(this);
        llPhone.setOnClickListener(this);
        llFind.setOnClickListener(this);
        llMine.setOnClickListener(this);
    }

    private void setBgItemColor(int id){
        switch (id){
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
    public void resetBgColor(){
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
    private void initView(){
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
    }


//    所有绑定过监听事件对象都在view里面
    @Override
    public void onClick(View view) {
        int id = view.getId();
        resetBgColor();
        setBgItemColor(id);
        switch (id){
            case R.id.ll_home:
                fragmentManager =getSupportFragmentManager();
                transaction = fragmentManager.beginTransaction();
                TestFragment testFragment1 = TestFragment.newInstance("这是主页");
                transaction.replace(R.id.fcv1,testFragment1).commit();
                break;
            case R.id.ll_phone:
                fragmentManager =getSupportFragmentManager();
                transaction = fragmentManager.beginTransaction();
                TestFragment testFragment2 = TestFragment.newInstance("这是通讯录");
                transaction.replace(R.id.fcv1,testFragment2).commit();
                break;
            case R.id.ll_find:
                fragmentManager =getSupportFragmentManager();
                transaction = fragmentManager.beginTransaction();
                TestFragment testFragment3 = TestFragment.newInstance("我的发现");
                transaction.replace(R.id.fcv1,testFragment3).commit();
                break;
            case R.id.ll_mine:
                fragmentManager =getSupportFragmentManager();
                transaction = fragmentManager.beginTransaction();
                TestFragment testFragment4 = TestFragment.newInstance("个人中心");
                transaction.replace(R.id.fcv1,testFragment4).commit();
                break;
            default:
                break;
        }
    }

    public void toViewPager(View view) {
    //  切换 Activity页面
   //        Intent 意图 ，可以搭建Activity的环境，可以跳转当前app的新的Activity页面，也可以去跳转其他App的
        Intent intent = new Intent(this,viewPagerActivity.class);
//        找到对应的活动并启动
        startActivity(intent);
    }

    public void toViewPager2(View view) {
        //  切换 Activity页面
        //        Intent 意图 ，可以搭建Activity的环境，可以跳转当前app的新的Activity页面，也可以去跳转其他App的
        Intent intent = new Intent(this,ViewPagerFragmentActivity.class);
//        找到对应的活动并启动
        startActivity(intent);
    }
}