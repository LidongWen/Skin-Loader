package wenld.github.skinloader.support;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StyleRes;

import java.lang.reflect.Method;

/**
 * Author: wenld on 2017/4/6 10:37.
 * blog: http://blog.csdn.net/sinat_15877283
 * github: https://github.com/LidongWen
 * <p>
 * Description:
 */
public class SkinResource {

    private Resources mSkinResource;
    private String mPackageName;
    private static final String TAG = "SkinResource";

    public SkinResource(Context context, String skinPath) {
        try {
            Resources superRes = context.getResources();
            AssetManager asset = AssetManager.class.newInstance();
            Method method = AssetManager.class.getDeclaredMethod("addAssetPath", String.class);
            method.invoke(asset, skinPath);

            mSkinResource = new Resources(asset, superRes.getDisplayMetrics(),
                    superRes.getConfiguration());


            // 获取skinPath包名
            mPackageName = context.getPackageManager().getPackageArchiveInfo(
                    skinPath, PackageManager.GET_ACTIVITIES).packageName;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * get Drawable by  resource name
     *
     * @param resName
     * @return
     */
    public Drawable getDrawableByName(String resName) {
        try {
            int resId = mSkinResource.getIdentifier(resName, "drawable", mPackageName);
//            Log.e(TAG,"resId -> "+resId+" mPackageName -> "+mPackageName +" resName -> "+resName);
            Drawable drawable = mSkinResource.getDrawable(resId);
            return drawable;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @ColorInt
    public int getColorIdByName(String resName) {
        try {
            int resId = mSkinResource.getIdentifier(resName, "color", mPackageName);
            return resId;
        } catch (Exception e) {
            return -1;
        }
    }

    @StyleRes
    public int getStyleIdByName(String resName) {
        try {
            int resId = mSkinResource.getIdentifier(resName, "style", mPackageName);
            return resId;
        } catch (Exception e) {
            return -1;
        }
    }

    public ColorStateList getColorByName(String resName) {
        try {
            int resId = mSkinResource.getIdentifier(resName, "color", mPackageName);
            ColorStateList color = mSkinResource.getColorStateList(resId);
            return color;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Deprecated
    public Drawable getDrawable(Context context, @DrawableRes int resId) throws Resources.NotFoundException {
        String resName = context.getResources().getResourceEntryName(resId);
        final Drawable d = getDrawableByName(resName);
        return d;
    }

    @Deprecated
    public ColorStateList getColorStateList(Context context, @ColorRes int resId) throws Resources.NotFoundException {
        String resName = context.getResources().getResourceEntryName(resId);
        final ColorStateList csl = getColorByName(resName);
        return csl;
    }
}
