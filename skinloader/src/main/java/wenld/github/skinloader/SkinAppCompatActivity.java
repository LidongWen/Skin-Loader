package wenld.github.skinloader;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import wenld.github.skinloader.attr.SkinAttr;
import wenld.github.skinloader.attr.SkinView;
import wenld.github.skinloader.callback.ISkinChangeListener;
import wenld.github.skinloader.support.SkinAppCompatViewInflater;
import wenld.github.skinloader.support.SkinAttrSupport;

public abstract class SkinAppCompatActivity extends AppCompatActivity implements LayoutInflaterFactory, ISkinChangeListener {
    private SkinAppCompatViewInflater mAppCompatViewInflater;
    private String TAG = "SkinAppCompatActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory(getLayoutInflater(), this);
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        final View view = createView(parent, name, context, attrs);

        if (view != null) {
            List<SkinAttr> skinAttrs = SkinAttrSupport.getSkinAttrs(context, attrs);
            if (skinAttrs.size() > 0) {
                SkinView skinView = new SkinView(view, skinAttrs);
                //交给 SkinManager 管理
                managerSkinView(skinView);
                SkinManager.getInstance().checkChangeSkin(skinView);
            }

        }

        return view;
    }

    private void managerSkinView(SkinView skinView) {
        List<SkinView> skinViews = SkinManager.getInstance().getSkinViews(this);
        if (skinViews == null) {
            skinViews = new CopyOnWriteArrayList();
        }
        skinViews.add(skinView);
        SkinManager.getInstance().register(this, skinViews);
    }

    public View createView(View parent, final String name, @NonNull Context context,
                           @NonNull AttributeSet attrs) {
        final boolean isPre21 = Build.VERSION.SDK_INT < 21;
        if (mAppCompatViewInflater == null) {
            mAppCompatViewInflater = new SkinAppCompatViewInflater();
        }
        // We only want the View to inherit its context if we're running pre-v21
        final boolean inheritContext = isPre21 && shouldInheritContext((ViewParent) parent);
        return mAppCompatViewInflater.createView(parent, name, context, attrs, inheritContext,
                isPre21, /* Only read android:theme pre-L (L+ handles this anyway) */
                true
        );
    }


    private boolean shouldInheritContext(ViewParent parent) {
        if (parent == null) {
            // The initial parent is null so just return false
            return false;
        }
        final View windowDecor = getWindow().getDecorView();
        while (true) {
            if (parent == null) {
                // Bingo. We've hit a view which has a null parent before being terminated from
                // the loop. This is (most probably) because it's the root view in an inflation
                // call, therefore we should inherit. This works as the inflated layout is only
                // added to the hierarchy at the end of the inflate() call.
                return true;
            } else if (parent == windowDecor || !(parent instanceof View)
                    || ViewCompat.isAttachedToWindow((View) parent)) {
                // We have either hit the window's decor view, a parent which isn't a View
                // (i.e. ViewRootImpl), or an attached view, so we know that the original parent
                // is currently added to the view hierarchy. This means that it has not be
                // inflated in the current inflate() call and we should not inherit the context.
                return false;
            }
            parent = parent.getParent();
        }
    }

    @Override
    protected void onDestroy() {
        SkinManager.getInstance().unregister(this);
        super.onDestroy();
    }


}
