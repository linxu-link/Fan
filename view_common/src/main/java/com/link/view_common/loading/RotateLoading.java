package com.link.view_common.loading;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

import com.link.view_common.R;

/**
 * @author WJ
 * @date 2019-08-19
 * <p>
 * 描述：旋转的view
 */
public class RotateLoading extends View {

    private static final int DEFAULT_WIDTH = 6;
    //阴影的偏移量
    private static final int DEFAULT_SHADOW_POSITION = 5;
    private static final int DEFAULT_SPEED_OF_DEGREE = 10;

    private Paint mPaint;
    private int mColor;

    //圆弧所在矩形
    private RectF mCircleRect;
    private RectF mCircleShadowRect;

    //圆弧的宽度
    private int mWidth;
    private int mShadowPosition;

    //
    private int mTopDegree = 10;
    private int mBottomDegree = 190;
    private int mSpeedOfDegree;
    private int mSpeedArc;
    //弧长
    private int mArc;

    private boolean mIsStart;
    private boolean mChange = true;


    public RotateLoading(Context context) {
        super(context);
        initView(context, null);
    }

    public RotateLoading(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public RotateLoading(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        mColor = Color.BLUE;
        mWidth = dpToPx(context, DEFAULT_WIDTH);
        mShadowPosition = dpToPx(context, DEFAULT_SHADOW_POSITION);
        mSpeedOfDegree = dpToPx(context, DEFAULT_SPEED_OF_DEGREE);
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RotateLoading);
            mColor = typedArray.getColor(R.styleable.RotateLoading_loading_color, Color.BLUE);
            mShadowPosition = typedArray.getInt(R.styleable.RotateLoading_shadow_position, DEFAULT_SHADOW_POSITION);
            mSpeedOfDegree = typedArray.getInt(R.styleable.RotateLoading_loading_speed, DEFAULT_SPEED_OF_DEGREE);
            mWidth = typedArray.getDimensionPixelSize(R.styleable.RotateLoading_loading_width, DEFAULT_WIDTH);
            typedArray.recycle();
        }
        mPaint = new Paint();
        mPaint.setColor(mColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(mWidth);

        mSpeedArc = mSpeedOfDegree / 4;

        start();

    }

    public static final String TAG = "View";

    //获取控件的改变前和改变后的大小
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mArc = mSpeedOfDegree;
        mCircleRect = new RectF(2 * mWidth, 2 * mWidth, w - 2 * mWidth, h - 2 * mWidth);
        mCircleShadowRect = new RectF(2 * mWidth + mShadowPosition, 2 * mWidth + mShadowPosition, w - 2 * mWidth + mShadowPosition, h - 2 * mWidth + mShadowPosition);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!mIsStart) {
            return;
        }

        //绘制圆弧和阴影
        mPaint.setColor(Color.parseColor("#1a000000"));
        canvas.drawArc(mCircleShadowRect, mTopDegree, mArc, false, mPaint);
        canvas.drawArc(mCircleShadowRect, mBottomDegree, mArc, false, mPaint);

        mPaint.setColor(mColor);
        canvas.drawArc(mCircleRect, mTopDegree, mArc, false, mPaint);
        canvas.drawArc(mCircleRect, mBottomDegree, mArc, false, mPaint);

        mTopDegree += mSpeedOfDegree;
        mBottomDegree += mSpeedOfDegree;
        if (mTopDegree > 360) {
            mTopDegree = mTopDegree - 360;
        }
        if (mBottomDegree > 360) {
            mBottomDegree = mBottomDegree - 360;
        }

        if (mChange) {
            if (mArc < 160) {
                mArc += mSpeedArc;
                invalidate();
            }
        } else {
            if (mArc > mSpeedOfDegree) {
                mArc -= 2 * mSpeedArc;
                invalidate();
            }
        }


        if (mArc >= 160 || mArc <= mSpeedOfDegree) {
            mChange = !mChange;
            invalidate();
        }


//        Log.e(TAG, "onDraw: " + mArc + "," + mBottomDegree + "," + mTopDegree + "," + mChange);
    }

    public void start() {
        startAnimator();
        mIsStart = true;
        invalidate();
    }

    public void stop() {
        stopAnimator();
        invalidate();
    }

    public boolean isStart() {
        return mIsStart;
    }

    private void stopAnimator() {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(this, "ScaleX", 1f, 0f);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(this, "ScaleY", 1f, 0f);
        animatorX.setDuration(300);
        animatorY.setDuration(300);
        animatorX.setInterpolator(new LinearInterpolator());
        animatorY.setInterpolator(new LinearInterpolator());
        AnimatorSet set = new AnimatorSet();
        set.playTogether(animatorX, animatorY);
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mIsStart = false;
            }
        });
        set.start();
    }

    private void startAnimator() {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(this, "ScaleX", 0f, 1f);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(this, "ScaleY", 0f, 1f);
        animatorX.setDuration(300);
        animatorY.setDuration(300);
        animatorX.setInterpolator(new LinearInterpolator());
        animatorY.setInterpolator(new LinearInterpolator());
        AnimatorSet set = new AnimatorSet();
        set.playTogether(animatorX, animatorY);
        set.start();
    }

    private int dpToPx(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, context.getResources().getDisplayMetrics());
    }
}
