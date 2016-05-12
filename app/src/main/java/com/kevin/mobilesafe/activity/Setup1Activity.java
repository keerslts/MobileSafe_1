package com.kevin.mobilesafe.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.kevin.mobliesafe.R;


public class Setup1Activity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup1);



    }

    //下一页
    public void next(View view) {
        startActivity(new Intent(this, Setup2Activity.class));
        finish();

        overridePendingTransition(R.anim.tran_in, R.anim.tran_out);// 进入动画和退出动画
    }


}
