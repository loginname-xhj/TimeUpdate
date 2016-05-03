package com.example.anticlockwisedemo;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Chronometer;

public class Anticlockwise extends Chronometer {
	private long mTime;
	private long mNextTime;
	private OnTimeCompleteListener mListener;
	private SimpleDateFormat mTimeFormat;

	public Anticlockwise(Context context, AttributeSet attrs) {
		super(context, attrs);
		// �Զ����ɵĹ��캯�����
		mTimeFormat = new SimpleDateFormat("mm:ss");
		// ���ü���ʱ��仯��,ÿ��һ�����һ�����������ϵͳ��ԭ��
		this.setOnChronometerTickListener(listener);
	}

	public Anticlockwise(Context context) {
		super(context);
	}

	/**
	 * ����������ʱ
	 */
	public void reStart(long _time_s) {
		if (_time_s == -1) {
			mNextTime = mTime;
		} else {
			mTime = mNextTime = _time_s;
		}
		this.start();
	}

	public void reStart() {
		reStart(-1);
	}

	/**
	 * ������ʱ
	 */
	public void onResume() {
		this.start();
	}

	/**
	 * ��ͣ��ʱ
	 */
	public void onPause() {
		this.stop();
	}

	/**
	 * ����ʱ���ʽ
	 * 
	 * @param pattern
	 *            ��ʱ��ʽ
	 */
	public void setTimeFormat(String pattern) {
		mTimeFormat = new SimpleDateFormat(pattern);
	}

	public void setOnTimeCompleteListener(OnTimeCompleteListener l) {
		mListener = l;
	}

	OnChronometerTickListener listener = new OnChronometerTickListener() {
		@Override
		public void onChronometerTick(Chronometer chronometer) {
			if (mNextTime <= 0) {
				if (mNextTime == 0) {
					Anticlockwise.this.stop();
					if (null != mListener)
						mListener.onTimeComplete();
				}
				mNextTime = 0;
				updateTimeText();
				return;
			}
			mNextTime--;
			updateTimeText();
		}
	};

	/**
	 * ��ʼ��ʱ��
	 * 
	 * @param _time_s
	 */
	public void initTime(long _time_s) {
		mTime = mNextTime = _time_s;
		updateTimeText();
	}

	/**
	 * ��ʽ����������ʱ��,����ת���ɺ��롣
	 * 
	 */
	private void updateTimeText() {
		this.setText(mTimeFormat.format(new Date(mNextTime * 1000)));
	}

	interface OnTimeCompleteListener {
		void onTimeComplete();
	}

}
