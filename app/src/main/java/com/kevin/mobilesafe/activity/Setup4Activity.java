package com.kevin.mobilesafe.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.kevin.mobliesafe.R;


/**
 * 第4个设置向导页
 * 
 * @author Kevin
 * 
 */
public class Setup4Activity extends BaseSetupActivity {

	private SharedPreferences mPref;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup4);

		mPref = getSharedPreferences("config", MODE_PRIVATE);
	}

	@Override
	public void showNextPage() {
		startActivity(new Intent(this, LostFindActivity.class));
		finish();

		// 两个界面切换的动画
		overridePendingTransition(R.anim.tran_in, R.anim.tran_out);// 进入动画和退出动画

		mPref.edit().putBoolean("configed", true).commit();// 更新sp,表示已经展示过设置向导,下次就不展示

	}

	@Override
	public void showPreviousPage() {
		startActivity(new Intent(this, Setup3Activity.class));
		finish();

		// 两个界面切换的动画
		overridePendingTransition(R.anim.tran_previous_in,
				R.anim.tran_previous_out);// 进入动画和退出动画
	}
}
