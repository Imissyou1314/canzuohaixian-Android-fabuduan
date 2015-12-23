/**
 * 
 */
package com.zhanjixun.views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author Imissyou
 * @Time  2015年11月23日
 *
 */
public class MissMyViewPager extends ViewPager {

	/**
	 * @param context
	 */
	public MissMyViewPager(Context context) {
		super(context);
		// TODO 自动生成的构造函数存根
	}
	public MissMyViewPager(Context context, AttributeSet attrs) {  
        super(context, attrs);  
    }  
  
    @Override  
    public boolean dispatchTouchEvent(MotionEvent ev) {  
        getParent().requestDisallowInterceptTouchEvent(true);//这句话的作用 告诉父view，我的单击事件我自行处理，不要阻碍我。    
        return super.dispatchTouchEvent(ev);  
    }  

}
