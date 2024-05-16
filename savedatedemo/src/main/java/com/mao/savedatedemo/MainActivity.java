package com.mao.savedatedemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import javax.security.auth.login.LoginException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences sp;
    private EditText et_username, et_password;
    private CheckBox cb_remember, cb_autoLogin;
    private Button btn_login, btn_register, btn_jump;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
         * 安卓中实现数据保存的形式
         * 1 将数据保存在配置文件sp，路径data/data/com.mao.savedatedemo/shared_prefs
         * 2 将数据保存在内置数据中 SQLite(嵌入式，体积小，几十kb)
         *      支持的数据类型NULL / INTEGER / LONG / FLOAT / DOUBLE / STRING / BYTE / BOOLEAN / STRING_SET
         *          特点：1 会将不同的数据类型保存为字段--转换为text
         *              2 SQLite的主键只能是INTEGER，并且主键要求一般_id，或者~id~
         *              在安卓底层sqlite.c，可以进行动态生成数据库
         */

        // 1 获取配置文件信息
        sp = getSharedPreferences("saveSpInfo", Context.MODE_PRIVATE);

        initView();

        boolean isRemember = sp.getBoolean("isRemember", false);
        boolean isAutoLogin = sp.getBoolean("isAutoLogin", false);

        if (isRemember) {
            String username = sp.getString("username", null);
            String password = sp.getString("password", null);
            // 自动填充用户名
            et_username.setText(username);
            // 自动填充密码
            et_password.setText(password);
            // 自动勾选记住密码
            cb_remember.setChecked(true);
        }

        if (isAutoLogin) {
            // 自动登录
            cb_autoLogin.setChecked(true);
            Intent intent = new Intent(this, LoginSuccessActivity.class);
            startActivity(intent);
//            Toast.makeText(this, "自动登录", Toast.LENGTH_SHORT).show();
        }

        btn_jump.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SQLiteActivity.class);
                startActivity(intent);
            }
        });

        btn_jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SQLiteActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initView() {

        et_username = findViewById(R.id.username);
        et_password = findViewById(R.id.password);
        cb_remember = findViewById(R.id.cb_remember);
        cb_autoLogin = findViewById(R.id.cb_autoLogin);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);
        btn_jump = findViewById(R.id.btn_jump);
        btn_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        btn_jump.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.btn_register) {

            String username = et_username.getText().toString().trim();
            String password = et_password.getText().toString().trim();
            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
            } else {
                // 判断是否记住密码
                if (cb_remember.isChecked()) {
                    // 如果为真，保存用户名和密码到文件中
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("username", username);
                    editor.putString("password", password);
                    // 记录保存密码状态
                    editor.putBoolean("isRemember", true);
                    editor.apply();
                }
                // 判断是否勾选自动登录
                if (cb_autoLogin.isChecked()) {
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean("isAutoLogin", true);
                    editor.apply();
                }
            }
        }
    }

    public void saveToSp(View view) {
        sp = getSharedPreferences("saveSpInfo", Context.MODE_PRIVATE);
        sp.edit().putString("username", "123").apply();
    }

    public void jump(View view) {
        Intent intent = new Intent(this, SQLiteActivity.class);
        startActivity(intent);
    }
}