package com.wmm.antman.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by ming on 2015/11/4.
 */
public class BaseActivity extends AppCompatActivity {

    private GestureDetector mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RightSlidingFinishActivity();
    }

    /**
     * 右滑销毁Activity
     */
    private void RightSlidingFinishActivity() {
        mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                /**
                 * 当手势滑动的时候，关闭页面的效果，具体需求具体对待
                 * 向下滑动，向上滑动，向右滑动（常用使用该方式）
                 */
                // 手势向下 down
                if ((e2.getRawY() - e1.getRawY()) > 200) {
                    //finish();//在此处控制关闭
                    return true;
                }
                // 手势向上 up
                if ((e1.getRawY() - e2.getRawY()) > 200) {
                    //finish();//在此处控制关闭
                    return true;
                }
                // 控制只右滑
                if (e2.getX() - e1.getX() > 0 && (e1.getX() >= 0 && e1.getX() <= 500)) {
                    if (Math.abs(e2.getX() - e1.getX()) > Math.abs(e2.getY() - e1.getY()) && Math.abs(velocityX) > 100) {
                        finish();
                        //onBackPressed();
                    }
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
