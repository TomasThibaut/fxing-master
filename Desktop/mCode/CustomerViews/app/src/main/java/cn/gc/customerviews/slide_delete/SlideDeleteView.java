package cn.gc.customerviews.slide_delete;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by 宫成 on 15/8/10.
 */
public class SlideDeleteView extends FrameLayout {
    private final String TAG = "T";
    private View view_content;
    private View view_delete;
    private ViewDragHelper helper;

    public SlideDeleteView(Context context, AttributeSet attrs) {
        super(context, attrs);

        ViewDragHelper.Callback mCallback = new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View view, int i) {
                return view == view_content || view == view_delete;
            }


            /**
             * 回调 视图位置移动,可以用来绑定联动效果
             */
            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                if (changedView == view_content) {
                    view_delete.layout(view_delete.getLeft() + dx, 0, view_delete.getRight() + dx, height_view);
                } else if (changedView == view_delete) {
                    view_content.layout(view_content.getLeft() + dx, 0, view_content.getRight() + dx, height_view);
                }
            }

            /**
             * clamp 视图水平方向的位置
             * @param child 子view
             * @param left 相对于屏幕左上角的坐标,全程计算同时也是视图的预期坐标,滑动速度越快,值越大;
             * @param dx  每次移动的具体像素,手势停下来一次就计算一次;
             * @return 结束后, 视图相对于起点的具体位置,所以返回left即可, 默认返回0, 所以不能移动视图
             */
            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                Log.e(TAG, "left = " + left);
                Log.e(TAG, "-width_delete = " + -width_delete);
                Log.e(TAG, "width_content = " + width_content);
                if (child == view_content) {
                    if (left > 0) {
                        left = 0;
                    } else if (left < -width_delete) {
                        left = -width_delete;
                    }
                } else if (child == view_delete) {
                    if (left > width_content) {
                        left = width_content;
                    } else if (left < width_content - width_delete) {
                        left = width_content - width_delete;
                    }
                }
                return left;
            }

            /**
             * 控制子view的移动范围
             * @param child
             * @return 默认返回值为0, 所以子view不能移动
             */
            @Override
            public int getViewHorizontalDragRange(View child) {
                //最大移动距离就是delete控件的宽度
                return width_delete;
//                return 0;
            }
        };
        helper = ViewDragHelper.create(this, mCallback);
        Log.e(TAG, "constructor helper = " + helper);
    }

    /**
     * 绑定到Window对象时候调用
     */
    @Override
    protected void onAttachedToWindow() {
        Log.e(TAG, "onAttachedToWindow");
        super.onAttachedToWindow();

    }

    /**
     * 当xml文件加载完成时候调用
     */
    @Override
    protected void onFinishInflate() {
        Log.e(TAG, "onFinishInflate");
        view_content = this.getChildAt(0);
        view_delete = this.getChildAt(1);
        super.onFinishInflate();
    }

    int width_content;
    int height_view;
    int width_delete;

    /**
     * 测量所有子view时候调用
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.e(TAG, "onMeasure");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        width_content = view_content.getMeasuredWidth();
        width_delete = view_delete.getMeasuredWidth();
        height_view = view_content.getMeasuredHeight();
    }

    /**
     * 指定子view的位置,after onMeasure()方法
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.e(TAG, "onLayout");
        super.onLayout(changed, left, top, right, bottom);
        view_delete.layout(width_content, 0, width_content + width_delete, height_view);
    }

    private float x;
    private float y;

    /**
     * 处理手势判断是否拦截父View && 判断是否需要open或者close横栏;
     *
     * @return true代表消费掉, false代表不能处理, 需要向上传递,此时只能触发DOWN事件
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = event.getRawX();
                y = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                float distanceX = event.getRawX() - x;
                float distanceY = event.getRawY() - y;
                if (Math.abs(distanceX) >= Math.abs(distanceY)) {
                    /**横向滑动,设置ListView为不可以拦截事件,必须由子view来处理*/
                    getParent().requestDisallowInterceptTouchEvent(true);
                } else {
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                x = event.getRawX();
                y = event.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                if (view_content.getLeft() < -width_delete / 2) {
                    open();
                } else if (view_content.getLeft() >= -width_delete / 2) {
                    close();
                }
                break;
        }
        helper.processTouchEvent(event);
        return true;
    }

    /**
     * @param ev 事件对象
     * @return 如果返回true, 就拦截掉事件, 并且通过自己的onTouchEvent()消费掉, 他的子view
     * 会得到一个MotionEvent.CANCEL,表示没有任何消息将会传递,默认返回false;
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        /**
         * 点击事件必须down-->up才会触发,如果返回true,拦截掉事件,不再向下分发,只会触发触摸事件
         * (onTouch()方法),后续发送MotionEvent.CANCEL,来让深层控件知道没有消息会传递过来,
         * 只有不拦截,才会继续传递到更深层次的 onClick()方法;
         * 也就是说ViewDragHelper类封装了一个判断(有可能是down到up之间的时间长度),如果判断出用户想点击,
         * 那么返回false不拦截,如果判断用户想要滑动,那么返回true,拦截掉事件传递;
         */
        boolean flag = helper.shouldInterceptTouchEvent(ev);
        return flag;
        /**
         * 事件机制:
         *
         * 两个判断条件:
         * ①是否拦截事件
         * ②是否能消费掉事件
         *
         * onInterceptTouchEvent(MotionEvent ev)是ViewGroup类的方法,用来决定是否拦截子view拿到事件
         * onTouchEvent(MotionEvent ev)是View类的方法,只用来判断手势,
         * 手势分为三种:DOWN,MOVE,UP,CANCEL,onTouchEvent()返回值为true,会执行到UP手势终止,
         * 如果返回值为false,执行DOWN手势,之后执行CANCEL,来取消剩下的手势执行
         * ViewGroup中,onTouchEvent虽然被改为返回true,但是拦截事件默认返回的是false,
         * DOWN事件会向下传递到子View,触摸事件默认返回的是false,会传递到最底层,也就是onClick方法消费掉
         * 这是返回true所以会执行到UP;
         * 所以自己并不消费,而是传递了;
         */
    }

    /**
     * 关闭画出来的条目,如何得到关闭的时机?
     */
    public void close() {
        view_content.layout(0, 0, width_content, height_view);
        view_delete.layout(width_content, 0, width_content + width_delete, height_view);
    }

    public void open() {
        view_content.layout(-width_delete, 0, width_content - width_delete, height_view);
        view_delete.layout(width_content - width_delete, 0, width_content, height_view);
    }

}
