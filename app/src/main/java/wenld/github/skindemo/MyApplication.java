package wenld.github.skindemo;

import android.app.Application;

import wenld.github.skinloader.SkinManager;

/**
 * Created by wenld on 2017/4/3.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SkinManager.getInstance().init(getApplicationContext());
    }
}
