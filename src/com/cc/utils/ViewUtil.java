package com.cc.utils;

import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class ViewUtil {
	/**
	 * Set text to TextView
	 * @param view Layout
	 * @param id TextView's id
	 * @param text string
	 * @return if TextView is null return false,else return true
	 */
    public static boolean setText(View view, int id, String text) {
        TextView textView = (TextView) view.findViewById(id);
        if (textView == null)
            return false;

        textView.setText(text);
        return true;
    }
    /**
     * Set text to TextView
     * @param view Layout
     * @param id TextView's id
     * @param text int
     * @return if TextView is null return false,else return true
     */
    public static boolean setText(View view, int id, int text) {
        TextView textView = (TextView) view.findViewById(id);
        if (textView == null)
            return false;

        textView.setText(text);
        return true;
    }
    /**
     * 滚动到ScrollView底部
     * @param scroll
     * @param inner
     */
    public static void scrollToBottom(final View scroll, final View inner) {
    	Handler mHandler = new Handler();
    	mHandler.post(new Runnable() {
	    	public void run() {
		    	if (scroll == null || inner == null) {
		    		return;
		    	}
		    	int offset = inner.getMeasuredHeight() - scroll.getHeight();
		    	if (offset < 0) {
		    		offset = 0;
		    	}
		    	scroll.scrollTo(0, offset);
	    	}
    	});
	}
}
