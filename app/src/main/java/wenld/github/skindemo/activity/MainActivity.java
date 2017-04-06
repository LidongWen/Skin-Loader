package wenld.github.skindemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.hc.skin1.R;

import java.io.File;
import java.io.IOException;

import wenld.github.skindemo.base.CopyAssetsUtil;
import wenld.github.skinloader.SkinActivity;
import wenld.github.skinloader.SkinManager;
import wenld.github.skinloader.support.SkinResource;

/**
 * Created by wenld on 2017/4/3.
 */

public class MainActivity extends SkinActivity implements View.OnClickListener {
    private Button btn_huanfu;
    private Button btn_moren;
    private Button btn_tiaozhuan;
    private LinearLayout activity_main;
    private Button btn_tiaozhuan_f;
    private Button btn_tiaozhuan_c;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            CopyAssetsUtil.copyAssets(this, "skin", Environment.getExternalStorageDirectory().getAbsolutePath() + "/skin");
        } catch (IOException e) {
            e.printStackTrace();
        }
        initView();
//        findViewById()
//        LayoutInflater
    }

    private void initView() {
        btn_huanfu = (Button) findViewById(R.id.btn_huanfu);
        btn_moren = (Button) findViewById(R.id.btn_moren);
        btn_tiaozhuan = (Button) findViewById(R.id.btn_tiaozhuan);

        btn_huanfu.setOnClickListener(this);
        btn_moren.setOnClickListener(this);
        btn_tiaozhuan.setOnClickListener(this);
        btn_tiaozhuan_f = (Button) findViewById(R.id.btn_tiaozhuan_f);
        btn_tiaozhuan_f.setOnClickListener(this);
        btn_tiaozhuan_c = (Button) findViewById(R.id.btn_tiaozhuan_c);
        btn_tiaozhuan_c.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_huanfu:
                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "skin/red.skin";
                SkinManager.getInstance().loadSkin(path);
                break;
            case R.id.btn_moren:
                SkinManager.getInstance().restoreDefault();
                break;
            case R.id.btn_tiaozhuan:
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_tiaozhuan_f:
                Intent intent1 = new Intent(MainActivity.this, ViewPageActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_tiaozhuan_c:
                Intent intent2 = new Intent(MainActivity.this, CollapsingActivity.class);
                startActivity(intent2);
                break;
        }
    }

    @Override
    public void changeSkin(SkinResource skinResource) {

    }
}
