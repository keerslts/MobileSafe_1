package com.kevin.mobilesafe.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.kevin.mobliesafe.R;


/**
 * 手机防盗页面
 * Created by Kevin on 2016/4/1.
 */
public class LostFindActivity extends Activity {

    private SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mPrefs = getSharedPreferences("config", MODE_PRIVATE);

        boolean configed = mPrefs.getBoolean("configed", false);
        if (configed) {
            setContentView(R.layout.activity_lost_find);
        } else {
            startActivity(new Intent(this,Setup1Activity.class));
            finish();
        }

    }

    public void reEnter(View view) {
        startActivity(new Intent(this, Setup1Activity.class));
        finish();
    }
}
