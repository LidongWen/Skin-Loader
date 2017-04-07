package wenld.github.skindemo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.hc.skin1.R;

import java.io.File;

import wenld.github.skinloader.SkinAppCompatActivity;
import wenld.github.skinloader.SkinManager;
import wenld.github.skinloader.support.SkinResource;

public class CollapsingActivity extends SkinAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setSubtitleTextColor(Color.parseColor("#b76666"));
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "skin/day.skin";
                SkinManager.getInstance().loadSkin(path);
                Snackbar.make(view, "换肤...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void changeSkin(SkinResource skinResource) {

    }
}
