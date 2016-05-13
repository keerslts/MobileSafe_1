package com.kevin.mobilesafe.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.kevin.mobilesafe.utils.ToastUtils;
import com.kevin.mobliesafe.R;


/**
 * 第3个设置向导页
 *
 * @author Kevin
 */
public class Setup3Activity extends BaseSetupActivity {
    private EditText etPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup3);

        etPhone = (EditText) findViewById(R.id.et_phone);

        String phone = mPref.getString("safe_phone", "");
        etPhone.setText(phone);
    }

    @Override
    public void showNextPage() {
        String phone = etPhone.getText().toString().trim();// 注意过滤空格

        if (TextUtils.isEmpty(phone)) {
            // Toast.makeText(this, "安全号码不能为空!", Toast.LENGTH_SHORT).show();
            ToastUtils.showToast(this, "安全号码不能为空!");
            return;
        }

        mPref.edit().putString("safe_phone", phone).commit();// 保存安全号码
        startActivity(new Intent(this, Setup4Activity.class));
        finish();

        // 两个界面切换的动画
        overridePendingTransition(R.anim.tran_in, R.anim.tran_out);// 进入动画和退出动画

    }

    @Override
    public void showPreviousPage() {
        startActivity(new Intent(this, Setup2Activity.class));
        finish();

        // 两个界面切换的动画
        overridePendingTransition(R.anim.tran_previous_in,
                R.anim.tran_previous_out);// 进入动画和退出动画
    }

    /**
     * 选择联系人
     *
     * @param view
     */
    public void selectContact(View view) {
//		Intent intent = new Intent(this, ContactActivity.class);
//		startActivityForResult(intent, 1);

        Intent intent = new Intent(Intent.ACTION_PICK);
        //intent.setAction(Intent.ACTION_PICK);

        intent.setData(android.provider.ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

//		if (resultCode == Activity.RESULT_OK) {
//			String phone = data.getStringExtra("phone");
//			phone = phone.replaceAll("-", "").replaceAll(" ", "");// 替换-和空格
//
//			etPhone.setText(phone);// 把电话号码设置给输入框
//		}

        if (resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();

            // 得到ContentResolver对象

            ContentResolver cr = getContentResolver();

            // 取得电话本中开始一项的光标

            Cursor cursor = cr.query(uri, null, null, null, null);

            // 向下移动光标

            while (cursor.moveToNext()) {

                // 取得联系人名字

                int nameFieldColumnIndex = cursor
                        .getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                String contact = cursor.getString(nameFieldColumnIndex);

                String id = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.Contacts._ID));

                String hasPhone = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

                Cursor phones = getContentResolver().query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                                + " = " + id, null, null);
                while (phones.moveToNext()) {
                    String phoneNumber = phones
                            .getString(phones
                                    .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    //Log.i("safe", "onActivityResult: " + phoneNumber);
                    etPhone.setText(phoneNumber);// 把电话号码设置给输入框
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data);

    }
}
