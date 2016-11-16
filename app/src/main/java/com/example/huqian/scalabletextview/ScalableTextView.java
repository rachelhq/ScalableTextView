package com.example.huqian.scalabletextview;

import android.content.Context;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

/**
 * 根据textview宽度可自适应改变textsize
 * <p>
 * Created by huqian on 16/11/14.
 */

public class ScalableTextView extends TextView {
    private int mTextViewWidth = 0;

    public ScalableTextView(Context context) {
        super(context);
    }

    public ScalableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScalableTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTextViewWidth = w;
        refreshScalableText();
    }

    private void refreshScalableText() {
        CharSequence text = getText();
        if (text == null || text.length() == 0)
            return;

        //这种paint的获取方法会直接改变此textview的paint的size，导致settextsize时不会重新渲染
//         TextPaint paint = getPaint();
        TextPaint paint = new TextPaint(getPaint());
        float size = paint.getTextSize();
        int contentWidth = mTextViewWidth - getPaddingLeft() - getPaddingRight();
        if (contentWidth > 0) {
            while (paint.measureText(text.toString()) > contentWidth) {
                size = size - 1;
                paint.setTextSize(size);
            }
            setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
        }
    }

}
