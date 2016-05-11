package com.kevin.mobilesafe.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kevin.mobliesafe.R;


/**
 * Created by Kevin on 2016/3/27.
 */
public class SettingItemView extends RelativeLayout {

    TextView tvTitle;
    TextView tvDesc;
    CheckBox cbStatus;

    public SettingItemView(Context context) {
        super(context);
        initView();
    }

    public SettingItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public SettingItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    /**
     * 初始化布局
     */
    private void initView() {
        //将自定义的布局文件设置给当前的SettingItemView
        View view = View.inflate(getContext(), R.layout.view_setting_item, this);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvDesc = (TextView) findViewById(R.id.tv_description);
        cbStatus = (CheckBox) findViewById(R.id.cb_status);
    }

    public void setTitle(String title) {
        tvTitle.setText(title);

    }
    public void setDesc(String desc) {
        tvDesc.setText(desc);
    }

    /**
     *
     * 返回勾选状态
     * @return
     */
    public boolean isChecked() {
        return cbStatus.isChecked();
    }

    public void setChecked(boolean check) {
        cbStatus.setChecked(check);
    }
}
