package wenld.github.skindemo.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wenld- on 2015/12/17.
 */
public class SecondaryAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragments = new ArrayList<>();
    private final List<String> mFragmentTitles = new ArrayList<>();
    private Context mContext;

    public SecondaryAdapter(FragmentManager fm, Context mContext) {
        super(fm);
        this.mContext = mContext;
    }

    public void addFragment(Fragment fragment, String title) {
        mFragments.add(fragment);
        mFragmentTitles.add(title);
    }

    public View getTabView(int paramInt) {
        //View localView = LayoutInflater.from(this.mContext).inflate(R.layout.fragment_userinfo, null);
        //return localView;
        return null;
    }
    public String getTitle(int position) {
        return mFragmentTitles.get(position);
    }
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    /**
     * 覆盖destroyItem方法可阻止销毁Fragment
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitles.get(position);
    }
}
