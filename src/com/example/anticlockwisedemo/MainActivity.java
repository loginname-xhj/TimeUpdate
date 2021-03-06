package com.example.anticlockwisedemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.app.Activity;

/**
 * @Description: 定制定时器
 * @author 机器人编写
 * @date 2016年5月3日 下午4:04:29
 */
public class MainActivity extends Activity {

	private Anticlockwise mTimer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mTimer = (Anticlockwise) findViewById(R.id.id_timer);
		// 初始化时间
		mTimer.initTime(60);

		mTimer.setOnTimeCompleteListener(new Anticlockwise.OnTimeCompleteListener() {
			@Override
			public void onTimeComplete() {
				Toast.makeText(getApplicationContext(), "计时完成!", 0).show();
			}
		});

	}

	public void btnClick(View v) {
		int id = v.getId();

		switch (id) {
		case R.id.button1:
			mTimer.reStart();
			break;

		case R.id.button2:
			mTimer.onPause();
			break;

		case R.id.button3:
			mTimer.onResume();
			break;

		case R.id.button4:
			mTimer.stop();
			mTimer.initTime(60);
			break;
		}
	}

}
