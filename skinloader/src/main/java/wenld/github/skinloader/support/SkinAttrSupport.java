package wenld.github.skinloader.support;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

import wenld.github.skinloader.attr.SkinAttr;
import wenld.github.skinloader.attr.SkinType;

/**
 * Author: wenld on 2017/4/6 10:37.
 * blog: http://www.jianshu.com/u/99f514ea81b3
 * github: https://github.com/LidongWen
 */
public class SkinAttrSupport {

    private static final String TAG = "SkinAttrSupport";

    /**
     * 获取SkinAttr的属性
     *
     * @param context
     * @param attrs
     * @return
     */
    public static List<SkinAttr> getSkinAttrs(Context context, AttributeSet attrs) {
        // background   src  textColor
        List<SkinAttr> skinAttrs = new ArrayList<>();

        int attrLength = attrs.getAttributeCount();
        String attrName, attrValue;
        for (int index = 0; index < attrLength; index++) {
            // 获取名称 , 值
            attrName = attrs.getAttributeName(index);
            attrValue = attrs.getAttributeValue(index);
//            Log.e(TAG, "attrName -> " + attrName + " ; attrValue -> " + attrValue);
            if (attrName.equals("enable_skin")) {
                if (attrValue.equals("false")) {
                    skinAttrs.clear();
                    return skinAttrs;
                }
            }
            // 只获取重要的
            SkinType skinType = getSkinType(attrName);
            if (skinType != null) {
                String resName = getResName(context, attrValue);
                if (TextUtils.isEmpty(resName)) {
                    continue;
                }
                SkinAttr skinAttr = new SkinAttr(resName, skinType);
                skinAttrs.add(skinAttr);
            }
        }
        return skinAttrs;
    }

    /**
     * 获取资源的名称
     *
     * @param context
     * @param attrValue
     * @return
     */
    private static String getResName(Context context, String attrValue) {

        if (attrValue.startsWith("@")) {
            attrValue = attrValue.substring(1);

            int resId = Integer.parseInt(attrValue);
            try {
                return context.getResources().getResourceEntryName(resId);
            } catch (Exception e) {
                return null;
            }
        }

        return null;
    }

    /**
     * 通过名称获取SkinType
     *
     * @param attrName
     * @return
     */
    private static SkinType getSkinType(String attrName) {
        if (attrName.equals("layout_") || "id".equals(attrName) || attrName.contains("padding") || "text".equals(attrName)) {
            return null;
        }
        SkinType[] skinTypes = SkinType.values();
        for (SkinType skinType : skinTypes) {
            if (skinType.getResName().equals(attrName)) {
                return skinType;
            }
        }
        return null;
    }
}
