package com.example.olay.bottomview;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 *
 * Created by OlAy on 2017/7/27.
 */

public class BottomGroup extends RadioGroup {
    //每个按钮的id
    private int[] id;
    //每个按钮的文本
    private String[] text;
    //平常的图片
    private int[] normal_img;
    //按下的图片
    private int[] press_img;
    //文字的平常和按下时的颜色
    private int[] color;
    private Context context;

    public BottomGroup(Context context) {
        this(context, null);
    }

    public BottomGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public void setColor(int[] color) {
        this.color = color;
    }

    //设置按钮参数
    public void start() {
        for (int i = 0; i < id.length; i++) {
            final RadioButton radioButton = new RadioButton(context);
            radioButton.setId(id[i]);
            radioButton.setText(text[i]);

            //设置drawingTop
            Drawable drawable = getResources().getDrawable(normal_img[i]);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            radioButton.setCompoundDrawables(null, drawable, null, null);

            //设置权重,默认均等分
            LayoutParams layoutParams = new LayoutParams
                    (0, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f);
            radioButton.setLayoutParams(layoutParams);
            //居中
            radioButton.setGravity(Gravity.CENTER);

            final int finalI = i;
            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        //改变字体颜色和图片状态
                        Drawable drawable = getResources().getDrawable(press_img[finalI]);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        radioButton.setCompoundDrawables(null, drawable, null, null);
                        radioButton.setTextColor(color[0]);
                        listener.OnClick(finalI);
                    } else {
                        Drawable drawable = getResources().getDrawable(normal_img[finalI]);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        radioButton.setCompoundDrawables(null, drawable, null, null);
                        radioButton.setTextColor(color[1]);
                    }
                }
            });
            //去掉左边的小圆点
            radioButton.setButtonDrawable(new BitmapDrawable());
            if (i == 0)
                radioButton.setChecked(true);
            addView(radioButton);
        }
    }

    public void setId(int[] id) {
        this.id = id;
    }

    public void setText(String[] text) {
        this.text = text;
    }

    public void setNormal_img(int[] normal_img) {
        this.normal_img = normal_img;
    }

    public void setPress_img(int[] press_img) {
        this.press_img = press_img;
    }

    //监听接口
    public interface OnButtonListener {
        void OnClick(int which);
    }

    private OnButtonListener listener;

    public void setOnButtonListener(OnButtonListener listener) {
        this.listener = listener;
    }
}
