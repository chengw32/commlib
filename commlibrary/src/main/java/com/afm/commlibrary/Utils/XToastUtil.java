package com.afm.commlibrary.Utils;

import android.text.TextUtils;
import android.widget.Toast;

import com.afm.commlibrary.application.BaseApplication;


public class XToastUtil {
	private static boolean isShow = true;
	

	private static Toast toast;

	public static void show(String msg) {
		if (!isShow)return;
		if (null == toast) {
			toast = Toast.makeText(BaseApplication.mInstance, msg, Toast.LENGTH_SHORT);
//            toast.setGravity(Gravity.BOTTOM, 0, 0);
//            LayoutInflater inflater = (LayoutInflater) context
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//            View view = inflater.inflate(R.layout.common_toast_layout, null);
//
//            toast_tv = (TextView) view.findViewById(R.id.common_toast_textview);
//            toast.setView(view);
		} else {
			toast.setText(msg);
		}
		toast.show();
	}
}
