package com.kevin.mobilesafe.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;


import com.kevin.mobilesafe.view.SettingItemView;
import com.kevin.mobliesafe.R;


/**
 * 设置中心
 */
public class SettingActivity extends Activity {

    private String TAG = "kevin";
    private SettingItemView sivUpdate;
    private SharedPreferences mPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        mPref = getSharedPreferences("config", MODE_PRIVATE);

        sivUpdate = (SettingItemView) findViewById(R.id.siv_update);
        sivUpdate.setTitle("自动更新设置");
     //   Log.i(TAG, "1");
        boolean autoUpdate = mPref.getBoolean("auto_update", true);
      //  Log.i(TAG, "2");
        if (autoUpdate) {
           // Log.i(TAG, "3");
            sivUpdate.setDesc("自动更新已开启");
            sivUpdate.setChecked(true);
        }else {
           // Log.i(TAG, "4");
            sivUpdate.setDesc("自动更新已关闭");
            sivUpdate.setChecked(false);
        }
        sivUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sivUpdate.isChecked()) {
                    //设置不勾选
                    sivUpdate.setChecked(false);
                    sivUpdate.setDesc("自动更新已关闭");

                    mPref.edit().putBoolean("auto_update", false).commit();
                } else {
                    sivUpdate.setChecked(true);
                    sivUpdate.setDesc("自动更新已开启");
                    mPref.edit().putBoolean("auto_update", true).commit();
                }
            }
        });
    }
}
