package com.link.librarymodule.widgets.likes;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LikesView extends PopupWindow {

    private int mDuration = 800;

    private int mDistance = 50;

    private int mTextSize = 16;

    private int mTextColor = 0xFF04AD84;

    private String mText;

    private TextView mHint;


    public int getDuration() {
        return mDuration;
    }

    public void setDuration(int duration) {
        mDuration = duration;
    }

    public int getDistance() {
        return mDistance;
    }

    public void setDistance(int distance) {
        mDistance = distance;
    }

    public int getTextSize() {
        return mTextSize;
    }

    public void setTextSize(int textSize) {
        mTextSize = textSize;
    }

    public int getTextColor() {
        return mTextColor;
    }

    public void setTextColor(int textColor) {
        mTextColor = textColor;
    }

    public TextView getHint() {
        return mHint;
    }

    public String getText() {
        return mText;
    }

    private Context mContext;

    public LikesView(Context context) {
        super(context);
        mContext = context;
        RelativeLayout layout = new RelativeLayout(context);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        mHint = new TextView(context);
        mHint.setText(mText);
        mHint.setTextColor(mTextColor);
        mHint.setTextSize(TypedValue.COMPLEX_UNIT_DIP, mTextSize);
        mHint.setLayoutParams(layoutParams);
        layout.addView(mHint);
        setContentView(layout);

        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        mHint.measure(w, h);

        setWidth(mHint.getMeasuredWidth());
        setHeight(mDistance + mHint.getMeasuredHeight());
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setFocusable(false);
        setTouchable(false);
        setOutsideTouchable(false);
        mAnimationSet = createAnimation();
    }

    /**
     * 设置文本
     *
     * @param text
     */
    public void setText(String text) {
        if (TextUtils.isEmpty(text)) {
            throw new IllegalArgumentException("text cannot be null.");
        }
        mText = text;
        mHint.setText(text);
        mHint.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        int w = (int) mHint.getPaint().measureText(text);
        setWidth(w);
        setHeight(mDistance + getTextViewHeight(mHint, w));
    }

    private static int getTextViewHeight(TextView textView, int width) {
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.AT_MOST);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        textView.measure(widthMeasureSpec, heightMeasureSpec);
        return textView.getMeasuredHeight();
    }

    /**
     * 设置文本信息
     *
     * @param text
     * @param textColor
     * @param textSize
     */
    public void setTextInfo(String text, int textColor, int textSize) {
        setTextColor(textColor);
        setTextSize(textSize);
        setText(text);
    }

    /**
     * 设置图片
     *
     * @param resId
     */
    public void setImage(int resId) {
        setImage(mContext.getResources().getDrawable(resId));
    }

    /**
     * 设置图片
     *
     * @param drawable
     */
    public void setImage(Drawable drawable) {
        if (drawable == null) {
            throw new IllegalArgumentException("drawable cannot be null.");
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mHint.setBackground(drawable);
        } else {
            mHint.setBackgroundDrawable(drawable);
        }
        mHint.setText("");
        setWidth(drawable.getIntrinsicWidth());
        setHeight(mDistance + drawable.getIntrinsicHeight());
    }

    private AnimationSet mAnimationSet;
    private boolean mChanged = false;

    public void show(View v) {
        if (!isShowing()) {
            int offsetY = -v.getHeight() - getHeight();
            showAsDropDown(v, v.getWidth() / 2 - getWidth() / 2, offsetY);
            if (mAnimationSet == null || mChanged) {
                mAnimationSet = createAnimation();
                mChanged = false;
            }
            mHint.startAnimation(mAnimationSet);
        }
    }

    private AnimationSet createAnimation() {
        mAnimationSet = new AnimationSet(true);
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 0, -getDistance());
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.01f);
        mAnimationSet.addAnimation(translateAnimation);
        mAnimationSet.addAnimation(alphaAnimation);
        mAnimationSet.setDuration(getDuration());
        mAnimationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (isShowing()) {
                    new Handler().post(new Runnable() {
                        @Override
                        public void run() {
                            dismiss();
                        }
                    });
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        return mAnimationSet;
    }
}
