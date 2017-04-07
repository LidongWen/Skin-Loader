package wenld.github.skindemo.fragment;

import android.content.Intent;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import com.hc.skin1.R;

import java.io.File;

import wenld.github.skindemo.activity.ViewPageActivity;
import wenld.github.skindemo.base.BaseLazyFragment;
import wenld.github.skinloader.SkinManager;

/**
 * <p/>
 * Author: 温利东 on 2017/4/6 10:49.
 * blog: http://blog.csdn.net/sinat_15877283
 * github: https://github.com/LidongWen
 */

public class TestFragment extends BaseLazyFragment implements View.OnClickListener {
    private Button btn_huanfu;
    private Button btn_moren;
    private Button btn_tiaozhuan;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_test;
    }

    @Override
    protected void onFirstUserVisible() {

    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onUserInvisible() {

    }

    @Override
    protected void initViewsAndEvents(View view) {
        btn_huanfu = (Button) view.findViewById(R.id.btn_huanfu);
        btn_moren = (Button) view.findViewById(R.id.btn_moren);
        btn_tiaozhuan = (Button) view.findViewById(R.id.btn_tiaozhuan);

        btn_huanfu.setOnClickListener(this);
        btn_moren.setOnClickListener(this);
        btn_tiaozhuan.setOnClickListener(this);
    }

    @Override
    protected void DetoryViewAndThing() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_huanfu:
                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "skin/day.skin";
                SkinManager.getInstance().loadSkin(path);
                break;
            case R.id.btn_moren:
                SkinManager.getInstance().restoreDefault();
                break;
            case R.id.btn_tiaozhuan:
                Intent intent = new Intent(getContext(), ViewPageActivity.class);
                startActivity(intent);
                break;
        }
    }
}
