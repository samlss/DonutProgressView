package com.iigo.library;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author SamLeung
 * @e-mail 729717222@qq.com
 * @github https://github.com/samlss
 * @csdn https://blog.csdn.net/Samlss
 * @description A progress view.
 */
public class DonutProgressView extends View {
    private final static int DEFAULT_COLOR = Color.WHITE;
    private int mColor = DEFAULT_COLOR;
    private int mProgress = 0;
    private float mStokeWidth;

    private float mCenterX;
    private float mCenterY;

    private Path mPath;
    private Paint mPaint;
    private Paint mCornerPaint;
    private Path mDstPath;
    private PathMeasure mPathMeasure;

    private float[] mCalculatePos = new float[2];

    public DonutProgressView(Context context) {
        this(context, null);
    }

    public DonutProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DonutProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DonutProgressView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        parseAttrs(attrs);
        init();
    }

    private void parseAttrs(AttributeSet attrs){
        if (attrs == null){
            return;
        }

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.DonutProgressView);
        mColor    = typedArray.getColor(R.styleable.DonutProgressView_progressColor, DEFAULT_COLOR);
        mProgress = typedArray.getInt(R.styleable.DonutProgressView_progress, 0);
        typedArray.recycle();

        checkProgress();
    }

    private void init(){
        mPath    = new Path();
        mDstPath = new Path();
        mPathMeasure = new PathMeasure();

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(mColor);

        mCornerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCornerPaint.setStyle(Paint.Style.FILL);
        mCornerPaint.setColor(mColor);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mPath.reset();

        mCenterX = w / 2;
        mCenterY = h / 2;

        int minSize = Math.min(w, h);
        mStokeWidth = minSize / 12f;

        mPaint.setStrokeWidth(mStokeWidth);

        float radius = (minSize - mStokeWidth * 2) / 2;
        mPath.addCircle(mCenterX, mCenterY, radius, Path.Direction.CW);
        mPathMeasure.setPath(mPath, false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mDstPath.reset();
        mPaint.setPathEffect(new CornerPathEffect(mStokeWidth));

        float distance = mProgress * 1.0f / 100 * mPathMeasure.getLength();

        mPathMeasure.getSegment(0, distance, mDstPath, true);
        canvas.drawPath(mDstPath, mPaint);

        mPathMeasure.getPosTan(0, mCalculatePos, null);
        canvas.drawCircle(mCalculatePos[0], mCalculatePos[1], mStokeWidth / 2, mCornerPaint);

        mPathMeasure.getPosTan(distance, mCalculatePos, null);
        canvas.drawCircle(mCalculatePos[0], mCalculatePos[1], mStokeWidth / 2, mCornerPaint);
    }

    /**
     * Check if the progress is between 0-100.
     * */
    private void checkProgress(){
        if (mProgress < 0){
            mProgress = 0;
        }else if (mProgress > 100){
            mProgress = 100;
        }
    }

    /**
     * Set the progress,the range is 0-100.
     * */
    public void setProgress(int progress) {
        this.mProgress = progress;
        invalidate();
    }

    /**
     * Get current progress.
     * */
    public int getProgress() {
        return mProgress;
    }

    /**
     * Set the progress color.
     * */
    public void setColor(int color) {
        this.mColor = color;
        mPaint.setColor(mColor);
        mCornerPaint.setColor(mColor);
        invalidate();
    }

    /**
     * Get current progress color.
     * */
    public int getColor() {
        return mColor;
    }
}

