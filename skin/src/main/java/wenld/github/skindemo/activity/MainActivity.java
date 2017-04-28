package wenld.github.skindemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.hc.skin1.R;

import java.io.IOException;

import wenld.github.skindemo.base.CopyAssetsUtil;
import wenld.github.skinloader.SkinActivity;
import wenld.github.skinloader.support.SkinResource;

/**
 * Created by wenld on 2017/4/3.
 */

public class MainActivity extends SkinActivity implements View.OnClickListener {
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
    }

    private void initView() {
        btn_tiaozhuan_f = (Button) findViewById(R.id.btn_tiaozhuan_f);
        btn_tiaozhuan_f.setOnClickListener(this);
        btn_tiaozhuan_c = (Button) findViewById(R.id.btn_tiaozhuan_c);
        btn_tiaozhuan_c.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
