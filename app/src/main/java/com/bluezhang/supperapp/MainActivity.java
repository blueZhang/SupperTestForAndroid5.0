package com.bluezhang.supperapp;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import com.bluezhang.supperapp.fragments.HistoryListFragment;
import com.bluezhang.supperapp.providers.DataContract;

/**
 * 四大组件的使用
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 添加历史
     * @param view
     */
    public void btnAddHistory(View view) {
        ContentResolver resolver= getContentResolver();
        ContentValues values = new ContentValues();
        values.put(DataContract.History.URL,"http://www.baidu.com");
        //添加纪录并且返回记录的Uri
        Uri rowUri = resolver.insert(
                DataContract.History.CONTENT_URI,
                values
        );

        if (rowUri != null) {
            //Toast.makeText(this,rowUri.toString(),Toast.LENGTH_SHORT).show();
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            Fragment fragmentById = supportFragmentManager.findFragmentById(R.id.fragment_history);
            if(fragmentById instanceof HistoryListFragment){
                HistoryListFragment historyListFragment = (HistoryListFragment)fragmentById;
                ((HistoryListFragment) fragmentById).refreshHistory();
            }

        }

    }

    /**
     * 在Activity中将Activity切换成横屏并且锁死切换不回来了
     * 切换为横屏显示
     * @param view
     */
    public void btnSwitchLandScreen(View view) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d("onConfigChanged","orentation: "+newConfig.orientation);
        //屏幕的方向
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){

        }else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){

        }
    }
}
