package com.monkey.t.clnote.Widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.monkey.t.clnote.R;


/**
 * TODO: document your custom view class.
 */
public class CustomTitleView extends View {
    private String mExampleString; // TODO: use a default from R.string...
    private int mExampleColor = Color.RED; // TODO: use a default from R.color...
    private float mExampleDimension = 0; // TODO: use a default from R.dimen...
    private Drawable mExampleDrawable;

    private TextPaint mTextPaint;
    private float mTextWidth;
    private float mTextHeight;
    private float mTextHeightReal;

    Rect mBound;

    public CustomTitleView(Context context) {
        super(context);
        init(null, 0);
    }

    public CustomTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public CustomTitleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.CustomTitleView, defStyle, 0);

        mExampleString = a.getString(
                R.styleable.CustomTitleView_titleString);
        mExampleColor = a.getColor(
                R.styleable.CustomTitleView_titleColor,
                mExampleColor);
        // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
        // values that should fall on pixel boundaries.
        mExampleDimension = a.getDimension(
                R.styleable.CustomTitleView_titleDimension,
                mExampleDimension);

        if (a.hasValue(R.styleable.CustomTitleView_titleDrawable)) {
            mExampleDrawable = a.getDrawable(
                    R.styleable.CustomTitleView_titleDrawable);
            mExampleDrawable.setCallback(this);
        }

        a.recycle();

        // Set up a default TextPaint object
        mTextPaint = new TextPaint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.LEFT);

        mTextPaint.setTextSize(mExampleDimension);
        mBound = new Rect();
        mTextPaint.getTextBounds(mExampleString, 0, mExampleString.length(), mBound);

        // Update TextPaint and text measurements from attributes
        invalidateTextPaintAndMeasurements();
    }

    private void invalidateTextPaintAndMeasurements() {
        mTextPaint.setTextSize(mExampleDimension);
        mTextPaint.setColor(mExampleColor);
        mTextWidth = mTextPaint.measureText(mExampleString);

        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        mTextHeight = fontMetrics.bottom;
        mTextHeightReal = fontMetrics.bottom - fontMetrics.top;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;

        Rect mBounds = new Rect();
        mTextPaint.setTextSize(mExampleDimension);

        mTextPaint.getTextBounds(mExampleString, 0, mExampleString.length(), mBounds);

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {

            int textWidth = mBounds.width();
            int desired = getPaddingLeft() + textWidth + getPaddingRight();
            width = desired;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {

            int textHeight = mBounds.height();

            int desireds = getPaddingTop() + textHeight + getPaddingBottom();
            height = desireds;
        }


        Log.v("tttt", width + ":" + height);
        setMeasuredDimension(width, height);
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // TODO: consider storing these as member variables to reduce
        // allocations per draw cycle.
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;

        Paint mPaint = new Paint();
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        // Draw the text.
        canvas.drawText(mExampleString,
                paddingLeft + (contentWidth - mTextWidth) / 2,
                paddingTop + (contentHeight + mTextHeightReal) / 2 - mTextHeight - 1,
                mTextPaint);
        Log.v("ttt", contentHeight + ":" + mTextHeight);

//    canvas.drawText(mExampleString, getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2 , mTextPaint);
//    Log.v("ttt",getHeight() + ":" + mBound.height());

        // Draw the example drawable on top of the text.
        if (mExampleDrawable != null) {
            mExampleDrawable.setBounds(paddingLeft, paddingTop,
                    paddingLeft + contentWidth, paddingTop + contentHeight);
            mExampleDrawable.draw(canvas);
        }


//        Paint textPaint = new Paint( Paint.ANTI_ALIAS_FLAG);
//        textPaint.setTextSize( 35);
//        textPaint.setColor( Color.BLACK);
//
//        // FontMetrics对象
//        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
//        String text = "abcdefghijklmnopqrstu";
//
//        // 计算每一个坐标
//        float baseX = 0;
//        float baseY = 100;
//        float topY = baseY + fontMetrics.top;
//        float ascentY = baseY + fontMetrics.ascent;
//        float descentY = baseY + fontMetrics.descent;
//        float bottomY = baseY + fontMetrics.bottom;
//
//        // 绘制文本
//        canvas.drawText( text, baseX, baseY, textPaint);
//
//        // BaseLine描画
//        Paint baseLinePaint = new Paint( Paint.ANTI_ALIAS_FLAG);
//        baseLinePaint.setColor( Color.RED);
//        canvas.drawLine(0, baseY, getWidth(), baseY, baseLinePaint);
//
//        // Base描画
//        canvas.drawCircle( baseX, baseY, 5, baseLinePaint);
//
//        // TopLine描画
//        Paint topLinePaint = new Paint( Paint.ANTI_ALIAS_FLAG);
//        topLinePaint.setColor( Color.LTGRAY);
//        canvas.drawLine(0, topY, getWidth(), topY, topLinePaint);
//
//        // AscentLine描画
//        Paint ascentLinePaint = new Paint( Paint.ANTI_ALIAS_FLAG);
//        ascentLinePaint.setColor( Color.GREEN);
//        canvas.drawLine(0, ascentY, getWidth(), ascentY, ascentLinePaint);
//
//        // DescentLine描画
//        Paint descentLinePaint = new Paint( Paint.ANTI_ALIAS_FLAG);
//        descentLinePaint.setColor( Color.YELLOW);
//        canvas.drawLine(0, descentY, getWidth(), descentY, descentLinePaint);
//
//        // ButtomLine描画
//        Paint bottomLinePaint = new Paint( Paint.ANTI_ALIAS_FLAG);
//        bottomLinePaint.setColor( Color.MAGENTA);
//        canvas.drawLine(0, bottomY, getWidth(), bottomY, bottomLinePaint);

    }


    /**
     * Gets the example string attribute value.
     *
     * @return The example string attribute value.
     */
    public String getExampleString() {
        return mExampleString;
    }

    /**
     * Sets the view's example string attribute value. In the example view, this string
     * is the text to draw.
     *
     * @param exampleString The example string attribute value to use.
     */
    public void setExampleString(String exampleString) {
        mExampleString = exampleString;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * Gets the example color attribute value.
     *
     * @return The example color attribute value.
     */
    public int getExampleColor() {
        return mExampleColor;
    }

    /**
     * Sets the view's example color attribute value. In the example view, this color
     * is the font color.
     *
     * @param exampleColor The example color attribute value to use.
     */
    public void setExampleColor(int exampleColor) {
        mExampleColor = exampleColor;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * Gets the example dimension attribute value.
     *
     * @return The example dimension attribute value.
     */
    public float getExampleDimension() {
        return mExampleDimension;
    }

    /**
     * Sets the view's example dimension attribute value. In the example view, this dimension
     * is the font size.
     *
     * @param exampleDimension The example dimension attribute value to use.
     */
    public void setExampleDimension(float exampleDimension) {
        mExampleDimension = exampleDimension;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * Gets the example drawable attribute value.
     *
     * @return The example drawable attribute value.
     */
    public Drawable getExampleDrawable() {
        return mExampleDrawable;
    }

    /**
     * Sets the view's example drawable attribute value. In the example view, this drawable is
     * drawn above the text.
     *
     * @param exampleDrawable The example drawable attribute value to use.
     */
    public void setExampleDrawable(Drawable exampleDrawable) {
        mExampleDrawable = exampleDrawable;
    }
}
