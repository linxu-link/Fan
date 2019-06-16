package com.link.librarymodule.widgets.tabs;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.link.librarymodule.R;

public class TabItem extends View {
    public final CharSequence text;
    public final Drawable icon;
    public final int customLayout;

    public TabItem(Context context) {
        this(context, (AttributeSet)null);
    }

    public TabItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes( attrs, R.styleable.TabItem);
        this.text = typedArray.getText(R.styleable.TabItem_android_text);
        this.icon = typedArray.getDrawable(R.styleable.TabItem_android_icon);
        this.customLayout = typedArray.getResourceId(R.styleable.TabItem_android_layout, 0);
        typedArray.recycle();
    }
}
