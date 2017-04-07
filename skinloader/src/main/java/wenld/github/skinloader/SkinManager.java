package wenld.github.skinloader;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import wenld.github.skinloader.attr.SkinView;
import wenld.github.skinloader.callback.ISkinChangeListener;
import wenld.github.skinloader.config.SkinConfig;
import wenld.github.skinloader.config.SkinPreUtils;
import wenld.github.skinloader.support.SkinResource;

/**
 * Author: wenld on 2017/4/6 10:37.
 * blog: http://www.jianshu.com/u/99f514ea81b3
 * github: https://github.com/LidongWen
 */
public class SkinManager {

    private static SkinManager mInstance;
    private Context mContext;
    private SkinResource mSkinResource;

    private Map<ISkinChangeListener, List<SkinView>> mSkinViews = new HashMap<>();

    static {
        mInstance = new SkinManager();
    }

    public static SkinManager getInstance() {
        return mInstance;
    }

    public void init(Context context) {
        this.mContext = context.getApplicationContext();

        String currentSkinPath = SkinPreUtils.getInstance(context).getSkinPath();

        File file = new File(currentSkinPath);

        if (!file.exists()) {
            SkinPreUtils.getInstance(context).clearSkinInfo();
            return;
        }

        String packageName = context.getPackageManager().getPackageArchiveInfo(
                currentSkinPath, PackageManager.GET_ACTIVITIES).packageName;

        if (TextUtils.isEmpty(packageName)) {
            SkinPreUtils.getInstance(context).clearSkinInfo();
            return;
        }

        // 最好校验签名  增量更新再说

        mSkinResource = new SkinResource(mContext, currentSkinPath);
    }


    /**
     * 加载皮肤
     *
     * @param skinPath
     * @return
     */
    public synchronized int loadSkin(String skinPath) {
        File file = new File(skinPath);
        if (!file.exists()) {
            return SkinConfig.SKIN_FILE_NOEXSIST;
        }

        String packageName = mContext.getPackageManager().getPackageArchiveInfo(
                skinPath, PackageManager.GET_ACTIVITIES).packageName;

        if (TextUtils.isEmpty(packageName)) {
            return SkinConfig.SKIN_FILE_ERROR;
        }

        String currentSkinPath = SkinPreUtils.getInstance(mContext).getSkinPath();
        if (skinPath.equals(currentSkinPath)) {
            return SkinConfig.SKIN_CHANGE_NOTHING;
        }

        // 校验签名  增量更新再说

        // 初始化资源管理
        mSkinResource = new SkinResource(mContext, skinPath);

        changeSkin();

        // 保存皮肤的状态
        saveSkinStatus(skinPath);
        return SkinConfig.SKIN_CHANGE_SUCCESS;
    }

    /**
     * 改变皮肤
     */
    private synchronized void changeSkin() {
        Set<ISkinChangeListener> keys = mSkinViews.keySet();
        for (ISkinChangeListener key : keys) {
            List<SkinView> skinViews = mSkinViews.get(key);
            for (SkinView skinView : skinViews) {
                skinView.skin();
            }

            // 通知Activity
            key.changeSkin(mSkinResource);
        }
    }

    private void saveSkinStatus(String skinPath) {
        SkinPreUtils.getInstance(mContext).saveSkinPath(skinPath);
    }

    /**
     * 恢复默认
     *
     * @return
     */
    public int restoreDefault() {
        String currentSkinPath = SkinPreUtils.getInstance(mContext).getSkinPath();

        if (TextUtils.isEmpty(currentSkinPath)) {
            return SkinConfig.SKIN_CHANGE_NOTHING;
        }

        String skinPath = mContext.getPackageResourcePath();
        mSkinResource = new SkinResource(mContext, skinPath);

        changeSkin();

        SkinPreUtils.getInstance(mContext).clearSkinInfo();

        return SkinConfig.SKIN_CHANGE_SUCCESS;
    }

    /**
     * 获取SkinView通过activity
     *
     * @param activity
     * @return
     */
    public List<SkinView> getSkinViews(Activity activity) {
        return mSkinViews.get(activity);
    }

    /**
     * 注册
     *
     * @param skinChangeListener
     * @param skinViews
     */
    public void register(ISkinChangeListener skinChangeListener, List<SkinView> skinViews) {
        mSkinViews.put(skinChangeListener, skinViews);
    }

    /**
     * 获取当前的皮肤资源
     *
     * @return
     */
    public SkinResource getSkinResource() {
        return mSkinResource;
    }

    /**
     * 检测要不要换肤
     *
     * @param skinView
     */
    public void checkChangeSkin(SkinView skinView) {
        String currentSkinPath = SkinPreUtils.getInstance(mContext).getSkinPath();

        if (!TextUtils.isEmpty(currentSkinPath)) {
            skinView.skin();
        }
    }

    /**
     * 防止内存泄露
     */
    public void unregister(ISkinChangeListener skinChangeListener) {
        mSkinViews.remove(skinChangeListener);
    }
}
