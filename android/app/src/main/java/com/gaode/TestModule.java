package com.gaode;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.support.v4.app.ActivityManagerCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.Toast;

import com.amap.api.im.data.IMDataManager;
import com.amap.api.im.listener.DownloadStatusCode;
import com.amap.api.im.listener.IMDataDownloadListener;
import com.amap.api.im.listener.IMMapEventListener;
import com.amap.api.im.listener.IMMapLoadListener;
import com.amap.api.im.listener.MapLoadStatus;
import com.amap.api.im.mapcore.IMPoint;
import com.amap.api.im.mapcore.IMSearchResult;
import com.amap.api.im.util.IMLog;
import com.amap.api.im.view.IMIndoorMapFragment;
import com.amap.api.im.view.IMJniWrapper;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.modules.toast.ToastModule;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ijoy on 16-7-19.
 */
public class TestModule extends ReactContextBaseJavaModule {
    public Context context;
    String defaultBuilding = "B023B173VP";

    public TestModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.context = reactContext;

    }

    @Override
    public String getName() {
        return "Indoor";
    }

    private IMIndoorMapFragment mIndoorMapFragment;
    @ReactMethod
    public void open() {
        Activity activity = getCurrentActivity();
        FragmentManager fragmentManager = ((android.support.v4.app.FragmentActivity) activity).getSupportFragmentManager();

       mIndoorMapFragment= (IMIndoorMapFragment) fragmentManager.findFragmentByTag("hellocomponent");
        Log.println(Log.ERROR,"fRAGEMTN","mapFragment=  "+mIndoorMapFragment);


        IMDataManager mDataManager = IMDataManager.getInstance();
        IMDataManager.setRequestTimeOut(5000);
        mIndoorMapFragment.loadMap(defaultBuilding, mMapLoadListener);//B023B173VP
    }
    private IMMapLoadListener mMapLoadListener = new IMMapLoadListener() {

        @Override
        public void onMapLoadSuccess() {
            Log.println(Log.ERROR,"LoadSuccess","地图加载 is success");
        }
        @Override
        public void onMapLoadFailure(MapLoadStatus mapLoadStatus) {
            Log.println(Log.ERROR,"LoadFailure","地图加载 is false");
        }
    };
}
