package com.example.dealerapp.component;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import util.BMAConstants;


public class BMAFontViewField  extends AppCompatTextView {
    public BMAFontViewField(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public BMAFontViewField(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BMAFontViewField(Context context) {
        super(context);
        init();
    }

    private void init() {

        //Font name should not contain "/".
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                BMAConstants.ICON_FILE);
        setTypeface(tf);
    }

}
