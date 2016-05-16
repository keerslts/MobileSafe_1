package com.kevin.mobilesafe.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kevin.mobilesafe.utils.MD5Utils;
import com.kevin.mobilesafe.R;



/**
 * 主页面
 *
 * @author Kevin
 */
public class HomeActivity extends Activity {

    private String TAG = "kevin";
    private GridView gvHome;
    private SharedPreferences mpref;

    private String[] mItems = new String[]{"手机防盗", "通讯卫士", "软件管理", "进程管理",
            "流量统计", "手机杀毒", "缓存清理", "高级工具", "设置中心"};

    private int[] mPics = new int[]{R.mipmap.home_safe, R.mipmap.home_callmsgsafe,
            R.mipmap.home_apps, R.mipmap.home_taskmanager, R.mipmap.home_netmanager,
            R.mipmap.home_trojan, R.mipmap.home_sysoptimize, R.mipmap.home_tools,
            R.mipmap.home_settings};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mpref = getSharedPreferences("config", MODE_PRIVATE);

        gvHome = (GridView) findViewById(R.id.gv_home);
        gvHome.setAdapter(new HomeAdapter());
        gvHome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        showPasswordDialog();
                        break;
                    case 7:
                        // 高级工具
                        startActivity(new Intent(HomeActivity.this,
                                AToolsActivity.class));
                        break;
                    case 8:
                        startActivity(new Intent(HomeActivity.this, SettingActivity.class));
                        break;

                    default:
                        break;

                }
            }
        });
    }

    protected void showPasswordDialog() {

        String savePassword = mpref.getString("password", null);
        if (!TextUtils.isEmpty(savePassword)) {
            showPasswordInputDialog();

        } else {
            showPasswordSetDailog();
        }

    }

    /**
     * 输入密码弹窗
     */
    private void showPasswordInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();

        View view = View.inflate(this, R.layout.dailog_input_password, null);
        //dialog.setView(view);
        dialog.setView(view, 0, 0, 0, 0);

        final EditText etPassword = (EditText) view.findViewById(R.id.et_password);

        Button btOk = (Button) view.findViewById(R.id.bt_ok);
        Button btCancle = (Button) view.findViewById(R.id.bt_cancel);

        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = etPassword.getText().toString();
                //     String savedPassword = mpref.getString("password", null);
                if (!TextUtils.isEmpty(password)) {
                    String savedPassword = mpref.getString("password", null);
                    //   Log.i(TAG, savedPassword);
                    if (MD5Utils.encode(password).equals(savedPassword)) {
//                        Toast.makeText(HomeActivity.this, "登陆成功",
//                                Toast.LENGTH_SHORT).show();
                        dialog.dismiss();

                        startActivity(new Intent(HomeActivity.this,LostFindActivity.class));

                    } else {
                        Toast.makeText(HomeActivity.this, "密码错误",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(HomeActivity.this, "输入内容不能为空",
                            Toast.LENGTH_SHORT).show();
                }


            }
        });

        btCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
        dialog.show();
    }


    private void showPasswordSetDailog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();

        View view = View.inflate(this, R.layout.dailog_set_password, null);
        //dialog.setView(view);
        dialog.setView(view, 0, 0, 0, 0);

        final EditText etPassword = (EditText) view.findViewById(R.id.et_password);
        final EditText etPasswordConfirm = (EditText) view.findViewById(R.id.et_password_confirm);

        Button btOk = (Button) view.findViewById(R.id.bt_ok);
        Button btCancle = (Button) view.findViewById(R.id.bt_cancel);

        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = etPassword.getText().toString();
                String passwordConfirm = etPasswordConfirm.getText().toString();

                if (!TextUtils.isEmpty(password) && !passwordConfirm.isEmpty()) {
                    if (password.equals(passwordConfirm)) {
//                        Toast.makeText(HomeActivity.this, "登陆成功！",
//                                Toast.LENGTH_SHORT).show();
                        mpref.edit().putString("password", MD5Utils.encode(password)).commit();
                        dialog.dismiss();

                        startActivity(new Intent(HomeActivity.this, LostFindActivity.class));
                    } else {
                        Toast.makeText(HomeActivity.this, "两次输入不一致！",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(HomeActivity.this, "输入框不能为空",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

        btCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
        dialog.show();
    }

    class HomeAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mItems.length;
        }

        @Override
        public Object getItem(int position) {
            return mItems[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(HomeActivity.this,
                    R.layout.home_list_item, null);
            ImageView ivItem = (ImageView) view.findViewById(R.id.iv_item);
            TextView tvItem = (TextView) view.findViewById(R.id.tv_item);

            tvItem.setText(mItems[position]);
            ivItem.setImageResource(mPics[position]);
            return view;
        }
    }

}
