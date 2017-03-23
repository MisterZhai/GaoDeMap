package com.senyuan.zmbp.senyuandz.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.amap.api.maps.AMap;
import com.amap.api.maps.SupportMapFragment;
import com.senyuan.zmbp.senyuandz.R;


/**
 * 空白页面
 */
public class Home1Fra extends BaseFragment implements OnClickListener {

    public static String FRAGMENT_TAG = Home1Fra.class.getSimpleName();

	private AMap mMap;
	private SupportMapFragment mapFragment;

	private Boolean isMapViewGone = false;

    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.index_1, null);

		setUpMapIfNeeded();

		return v;
	}

	@Override
	public void onClick(View v) {
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onResume() {

		// 此处setUserVisibleHint 无效,在3.4.0SDK 版本中还是可以隐藏地图的
		// mapFragment.setUserVisibleHint(false);

		if(!isMapViewGone){
			new Handler().postDelayed(new Runnable(){
				public void run() {
					//进入前台显示地图
					mapFragment.setUserVisibleHint(true);

				}
			}, 100);
		}
		super.onResume();

		setUpMapIfNeeded();
	}

	@Override
	public void onDestroy(){
		super.onDestroy();
	}


	@Override
	public void onStop() {

		//Log.i("MZ", "stop");
		new Handler().postDelayed(new Runnable(){
			public void run() {
				//退到后台时隐藏 地图
				mapFragment.setUserVisibleHint(false);

			}
		}, 50);

		super.onStop();
	}

	@Override
	public void onHiddenChanged(boolean hidden) {

		// 界面切换
		super.onHiddenChanged(hidden);

		if (hidden) {
			//Log.i("MZ", "onHiddenChanged: "+"hidden");

			new Handler().postDelayed(new Runnable(){
				public void run() {
					//execute the task
					isMapViewGone = true;
					mapFragment.setUserVisibleHint(false);
				}
			}, 30);

		}else {
			//Log.i("MZ", "onHiddenChanged: "+"NOhidden");

//			isMapViewGone = false;
//			mapFragment.setUserVisibleHint(true);

			new Handler().postDelayed(new Runnable(){
				public void run() {
					//execute the task
					isMapViewGone = false;
					mapFragment.setUserVisibleHint(true);
				}
			}, 100);

		}

	}


	/**
	 * 获取 AMap 对象
	 */
	private void setUpMapIfNeeded() {
		if (mMap == null) {
			mapFragment = (SupportMapFragment) getChildFragmentManager()
					.findFragmentById(R.id.map);
			mMap = ((SupportMapFragment) getChildFragmentManager()
					.findFragmentById(R.id.map)).getMap();
		}
	}

}
