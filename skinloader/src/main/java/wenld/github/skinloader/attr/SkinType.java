package wenld.github.skinloader.attr;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import wenld.github.skinloader.SkinManager;
import wenld.github.skinloader.support.SkinResource;

/**
 * Author: wenld on 2017/4/6 10:37.
 * blog: http://www.jianshu.com/u/99f514ea81b3
 * github: https://github.com/LidongWen
 */
public enum SkinType {
    TEXT_COLOR("textColor") {
        @Override
        public void skin(View view, String resName) {
            SkinResource skinResource = getSkinResource();
            ColorStateList color = skinResource.getColorByName(resName);
            if (color == null) {
                return;
            }
            TextView textView = (TextView) view;
            textView.setTextColor(color);
        }
    }, BACKGROUND("background") {
        @Override
        public void skin(View view, String resName) {
            SkinResource skinResource = getSkinResource();
            Drawable drawable = skinResource.getDrawableByName(resName);
            if (drawable != null) {
                ImageView imageView = (ImageView) view;
                imageView.setBackgroundDrawable(drawable);
                return;
            }

            ColorStateList color = skinResource.getColorByName(resName);
            if (color != null) {
                view.setBackgroundColor(color.getDefaultColor());
            }
        }
    }, SRC("src") {
        @Override
        public void skin(View view, String resName) {

            SkinResource skinResource = getSkinResource();
            Drawable drawable = skinResource.getDrawableByName(resName);
            if (drawable != null) {
                ImageView imageView = (ImageView) view;
                imageView.setImageDrawable(drawable);
                return;
            }
        }
    }, cardBackgroundColor("cardBackgroundColor") {
        @Override
        public void skin(View view, String resName) {
            SkinResource skinResource = getSkinResource();
            ColorStateList color = skinResource.getColorByName(resName);
            if (color != null) {
                CardView cardView = (CardView) view;
                cardView.setCardBackgroundColor(color);
            }
        }
    }, subtitleTextColor("subtitleTextColor") {
        @Override
        public void skin(View view, String resName) {
            SkinResource skinResource = getSkinResource();
            int color = skinResource.getColorIdByName(resName);
            if (color != -1) {
                Toolbar toolbar = (Toolbar) view;
                toolbar.setSubtitleTextColor(color);
            }
        }
    }, titleTextColor("titleTextColor") {
        @Override
        public void skin(View view, String resName) {
            SkinResource skinResource = getSkinResource();
            int color = skinResource.getColorIdByName(resName);
            if (color != -1) {
                Toolbar toolbar = (Toolbar) view;
                toolbar.setTitleTextColor(color);
            }
        }
    }, popupTheme("popupTheme") {
        @Override
        public void skin(View view, String resName) {
            SkinResource skinResource = getSkinResource();
            int styleId = skinResource.getStyleIdByName(resName);
            if (styleId != -1) {
                Toolbar toolbar = (Toolbar) view;
                toolbar.setPopupTheme(styleId);
            }
        }
    }, rippleColor("rippleColor") {
        @Override
        public void skin(View view, String resName) {
            SkinResource skinResource = getSkinResource();
            int color = skinResource.getColorIdByName(resName);
            if (color != -1) {
                FloatingActionButton fab = (FloatingActionButton) view;
                fab.setRippleColor(color);
            }
        }
    }, backgroundTint("backgroundTint") {
        @Override
        public void skin(View view, String resName) {

            SkinResource skinResource = getSkinResource();
            ColorStateList color = skinResource.getColorByName(resName);
            if (color == null) {
                return;
            }
            FloatingActionButton imageView = (FloatingActionButton) view;
            imageView.setBackgroundTintList(color);
            return;
        }
    }/*, tabIndicatorColor("tabIndicatorColor") {
        @Override
        public void skin(View view, String resName) {
            SkinResource skinResource = getSkinResource();
            int color = skinResource.getColorIdByName(resName);
            if (color != -1) {
                TabLayout fab = (TabLayout) view;
                fab.setSelectedTabIndicatorColor(color);
            }
        }
    }, tabSelectedTextColor("tabSelectedTextColor") {
        @Override
        public void skin(View view, String resName) {
            SkinResource skinResource = getSkinResource();
            int color = skinResource.getColorIdByName(resName);
            if (color != -1) {
                TabLayout fab = (TabLayout) view;
                fab.setTabTextColors(color);
            }
        }
    }, tabTextColor("tabTextColor") {
        @Override
        public void skin(View view, String resName) {
            SkinResource skinResource = getSkinResource();
            ColorStateList color = skinResource.getColorByName(resName);
            if (color != null) {
                TabLayout fab = (TabLayout) view;
                fab.setTabTextColors(color);
            }
        }
    }*/;

    // 会根据名字调对应的方法
    private String mResName;

    SkinType(String resName) {
        this.mResName = resName;
    }

    // 抽象的
    public abstract void skin(View view, String resName);

    public String getResName() {
        return mResName;
    }

    public SkinResource getSkinResource() {
        return SkinManager.getInstance().getSkinResource();
    }
}
