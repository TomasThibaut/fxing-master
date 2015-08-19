package cn.gc.customerview;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by GXAAA on 15/8/19 上午10:55.
 * 本类相当于ViewGroup,还有两个子View,一个是内容ContentView,一个是删除控件DeleteView
 * 三者同时使用
 */
public class SlideDeleteView extends FrameLayout {

    private static final String TAG = SlideDeleteView.class.getSimpleName();
    private ViewDragHelper helper;

    public SlideDeleteView(Context context, AttributeSet attrs) {
        super(context, attrs);
        helper = ViewDragHelper.create(this, new MyCallback());
    }

    private View content_view;
    private View delete_view;

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        content_view = getChildAt(0);
        delete_view = getChildAt(1);
    }

    private int content_width;
    private int delete_width;
    private int view_height;

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        //测量子View的大小
        measureView();
        //确定子View的位置
        delete_view.layout(content_width, 0, content_width + delete_width, view_height);
    }

    private void measureView() {
        content_width = content_view.getMeasuredWidth();
        delete_width = delete_view.getMeasuredWidth();
        view_height = content_view.getMeasuredHeight();
        Log.i(TAG, "delete_width = " + delete_width);
        Log.i(TAG, "content_width = " + content_width);
        Log.i(TAG, "view_height = " + view_height);
    }

    private class MyCallback extends ViewDragHelper.Callback {
        /**
         * 返回可以被捕获的子View
         */
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child == content_view || child == delete_view;
        }

        /**
         * Called when the captured view's position changes as the result of a drag or settle.
         */
        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {

            if (changedView == content_view)
            /**不能使用另个一子View来决定自己的位置,因为滑动的时候有一个惯性动画,会导致另一个子View的位置不正确*/
//                delete_view.layout(content_view.getRight() + dx, 0, content_view.getRight() + dx + delete_width, view_height);
                delete_view.layout(delete_view.getLeft() + dx, 0, delete_view.getRight() + dx, view_height);
            if (changedView == delete_view)
                content_view.layout(content_view.getLeft() + dx, 0, content_view.getRight() + dx, view_height);

        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            return delete_width;
        }

        /**
         * The default implementation does not allow horizontal motion(return 0)
         */
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {

            if (child == content_view) {
                if (left < -delete_width) left = -delete_width;
                if (left > 0) left = 0;
            } else if (child == delete_view) {
                if (left < content_width - delete_width) left = content_width - delete_width;
                if (left > content_width) left = content_width;
            }
            return left;
        }
    }

    private float startX;
    private float startY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        helper.processTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float distanceX = Math.abs(event.getX() - startX);
                float distanceY = Math.abs(event.getY() - startY);
                if (distanceX >= distanceY) {
                    getParent().requestDisallowInterceptTouchEvent(false);//不让父View拦截,可以滑动自己
                } else {
                    getParent().requestDisallowInterceptTouchEvent(true);//让ListView拦截掉,可以滑动ListView
                }
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return helper.shouldInterceptTouchEvent(ev);
    }
    /**TODO 确定何时开启,何时关闭*/
}
