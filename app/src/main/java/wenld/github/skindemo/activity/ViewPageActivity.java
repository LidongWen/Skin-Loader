package wenld.github.skindemo.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.hc.skin1.R;

import wenld.github.skindemo.fragment.TestFragment;
import wenld.github.skinloader.SkinAppCompatActivity;
import wenld.github.skinloader.support.SkinResource;

public class ViewPageActivity extends SkinAppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    String TAG = "ViewPageActivity";
    public static int testCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        long i = System.currentTimeMillis();

        Log.e(TAG, "开始：testCount" + testCount + "  " + System.currentTimeMillis());
        setContentView(R.layout.activity_view_page);
        Log.e(TAG, "结束：testCount" + testCount + "  " + (System.currentTimeMillis() - i));

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOffscreenPageLimit(8);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

//        if (testCount <50 ) {
//            Intent intent = new Intent(this, ViewPageActivity.class);
//            startActivity(intent);
//            testCount++;
//        }
    }

    @Override
    public void changeSkin(SkinResource skinResource) {

    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new TestFragment();
        }

        @Override
        public int getCount() {
            return 7;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "标签0";
                case 1:
                    return "标签1";
                case 2:
                    return "标签2";
                case 3:
                    return "标签3";
                case 4:
                    return "标签4";
                case 5:
                    return "标签5";
                case 6:
                    return "标签6";
                case 7:
                    return "标签7";
            }
            return null;
        }
    }
}
