package com.example.olay.bottomview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {
    private BottomGroup bottomGroup;
    private int[] id = {1, 2, 3};
    private String[] text = {"主页", "消息", "设置"};
    private int[] normal_img = {R.drawable.home, R.drawable.govaffairs, R.drawable.setting};
    private int[] press_img = {R.drawable.home_press, R.drawable.govaffairs_press, R.drawable.setting_press};
    private int[] color = {Color.GREEN, Color.BLACK};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomGroup= (BottomGroup) findViewById(R.id.bottomGroup);
        bottomGroup.setId(id);
        bottomGroup.setText(text);
        bottomGroup.setNormal_img(normal_img);
        bottomGroup.setPress_img(press_img);
        bottomGroup.setColor(color);
        bottomGroup.setOnButtonListener(new BottomGroup.OnButtonListener() {
            @Override
            public void OnClick(int which) {
                Toast.makeText(getApplication(), text[which], Toast.LENGTH_SHORT).show();
            }
        });
        bottomGroup.start();
    }
}
