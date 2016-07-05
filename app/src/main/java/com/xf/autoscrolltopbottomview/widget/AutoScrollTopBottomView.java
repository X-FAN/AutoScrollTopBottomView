package com.xf.autoscrolltopbottomview.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.RelativeLayout;
import android.widget.Scroller;

/**
 * Created by X-FAN on 2016/7/5.
 */
public class AutoScrollTopBottomView extends RelativeLayout {
    private final int ANI_TIME = 800;
    private float mLastActionDownY;

    private View mBottomView;
    private ViewGroup mTopView;
    private Scroller mScroller;
    private MotionEvent mLastMoveEvent;

    public AutoScrollTopBottomView(Context context) {
        super(context);
        mScroller = new Scroller(context);

    }

    public AutoScrollTopBottomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);

    }

    public AutoScrollTopBottomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() != 2) {
            throw new IllegalStateException("only and should contain two child view");
        }
        mBottomView = getChildAt(0);
        if (!(getChildAt(1) instanceof ViewGroup)) {
            throw new IllegalStateException("top view should be contained by a viewgroup");
        }
        mTopView = (ViewGroup) getChildAt(1);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        View view = null;
        if (mTopView.getChildCount() > 0) {
            view = mTopView.getChildAt(0);
        }

        if (view != null) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mLastActionDownY = ev.getRawY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    float distance = ev.getRawY() - mLastActionDownY;
                    mLastActionDownY = ev.getRawY();
                    mLastMoveEvent = ev;
                    if (!mScroller.computeScrollOffset()) {
                        if (distance > 0 && isViewAtTop(view)) {//pull down
                            if (Math.abs(mTopView.getScrollY() - distance) > mBottomView.getMeasuredHeight()) {//avoid out of bottom boundary
                                mTopView.scrollBy(0, -mTopView.getScrollY() - mBottomView.getMeasuredHeight());
                            } else {
                                mTopView.scrollBy(0, (int) -distance);
                            }
                            sendCancelEvent();
                            return true;
                        } else if (distance < 0 && !isViewAtTop(mTopView)) {//pull up
                            if ((distance - mTopView.getScrollY()) < 0) {//avoid out of top boundary
                                mTopView.scrollBy(0, -mTopView.getScrollY());
                            } else {
                                mTopView.scrollBy(0, (int) -distance);

                            }
                            sendCancelEvent();
                            return true;
                        }
                    } else {
                        sendCancelEvent();
                    }
                    break;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    if (isInUp()) {//prepare scroll to top
                        mScroller.startScroll(mTopView.getScrollX(), mTopView.getScrollY(), 0, -mTopView.getScrollY(), ANI_TIME);
                    } else if (isInDown()) {//prepare scroll to bottom
                        mScroller.startScroll(mTopView.getScrollX(), mTopView.getScrollY(), 0, -mTopView.getScrollY() - mBottomView.getMeasuredHeight(), ANI_TIME);
                    }
                    invalidate();
                    break;
                default:
                    break;
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            mTopView.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }


    /**
     * detect top view in top half of bottom view
     *
     * @return
     */
    private boolean isInUp() {//在上半部分内
        int y = -mTopView.getScrollY();
        if (y > 0 && y < mBottomView.getMeasuredHeight() / 2) {
            return true;
        }
        return false;
    }

    /**
     * detect top view in bottom half of bottom view
     *
     * @return
     */
    private boolean isInDown() {//在下半部分内
        int y = -mTopView.getScrollY();
        if (y >= mBottomView.getMeasuredHeight() / 2 && y < mBottomView.getMeasuredHeight()) {
            return true;
        }
        return false;
    }

    private boolean isViewAtTop(View view) {
        if (view instanceof AbsListView) {//这里可以自己更改代码,判断listview等在什么情况下为拉到顶部,默认为第一个item可见的时候
            final AbsListView absListView = (AbsListView) view;
            return absListView.getChildCount() > 0 && (absListView.getFirstVisiblePosition() == 0 && absListView.getChildAt(0).getTop() >= absListView.getPaddingTop());
        } else {
            return view.getScrollY() == 0;
        }
    }

    /**
     * 滑动过程调用,解决滑动与其他事件冲突
     * solve conflict move event between other event
     */
    private void sendCancelEvent() {
        if (mLastMoveEvent == null) {
            return;
        }
        MotionEvent last = mLastMoveEvent;
        MotionEvent e = MotionEvent.obtain(last.getDownTime(), last.getEventTime() + ViewConfiguration.getLongPressTimeout(), MotionEvent.ACTION_CANCEL, last.getX(), last.getY(), last.getMetaState());
        super.dispatchTouchEvent(e);
    }

}
