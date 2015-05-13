package com.houzhi.childautomovi.view;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by houzhi on 15-5-11.
 * 作为一个随机飘动View,通过margin的参数来设置view的位置，所以不要给子View设置margin
 */
public class TagRandomView extends RelativeLayout {

    public static interface TagClickListener{
        void onTagClickListener( View view, int position, long id);
    }


    private TagClickListener mOnTagClickListener ;

    public TagClickListener getOnTagClickListener() {
        return mOnTagClickListener;
    }

    public void setOnTagClickListener(TagClickListener mOnTagClickListener) {
        this.mOnTagClickListener = mOnTagClickListener;
    }

    public static final int MESSAGE_MOVING = 1;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MESSAGE_MOVING:
                    randomMovingAllChilds();
                    break;
            }
        }
    };
    /**
     * 定时器
     */
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            Message.obtain(handler, MESSAGE_MOVING).sendToTarget();
        }
    };


    /**
     * 移动所有的子View
     */
    private void randomMovingAllChilds() {
        if (movingInterface != null) {
            for (int i = 0; i != getChildCount(); ++i) {
                movingInterface.move(getChildAt(i), getWidth(), getHeight());
            }
        }
    }

    private boolean checkViewTouchInside(View view,MotionEvent ev){
        LayoutParams params = (LayoutParams)view.getLayoutParams();
        float x = ev.getX() ;
        float y = ev.getY() ;

        if( params.topMargin <  y && params.topMargin + view.getHeight() > y
                && params.leftMargin < x && params.leftMargin + view.getWidth() > x ){
            return true ;
        }else {
            return false;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean handle = super.dispatchTouchEvent(ev);
        if(handle){
            //如果已经处理了，则返回
            return handle ;
        }else{
            if(ev.getAction() == MotionEvent.ACTION_DOWN ) {
                for (int i = 0; i != getChildCount(); ++i) {
                    View childView = getChildAt(i);


                    boolean visible = checkViewTouchInside(childView, ev);
                    Log.d(VIEW_LOG_TAG, "childView " + i + " " + visible);
                    if (visible) {
//                    childView.dispatch
                        if (mOnTagClickListener != null)
                            mOnTagClickListener.onTagClickListener(childView, i, adapter.getItemId(i));
                        return true;
                    }
                }
            }
            return false ;
        }
    }

    /**
     * 设置标签之能够朝着一个方向移动
     */
    private ArrayList<Integer> topIncSign = new ArrayList<>();
    private ArrayList<Integer> leftIncSign = new ArrayList<>();


    private void randInitChilds() {
        if (movingInterface != null) {

            for (int i = 0; i != getChildCount(); ++i) {

                movingInterface.init(getChildAt(i), getWidth(), getHeight());
            }
        }
    }


    private ViewMovingInterface movingInterface;

    public void setMovingInterface(ViewMovingInterface movingInterface) {
        this.movingInterface = movingInterface;
    }

    public TagRandomView(Context context) {
        super(context);
    }

    public TagRandomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private BaseAdapter adapter;



    public void setAdapter(BaseAdapter adapter,ViewMovingInterface movingInterface){
        this.movingInterface = movingInterface ;

        setAdapter(adapter);
    }



    DataSetObserver dataSetObserver = new DataSetObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            //TODO 修改数据
        }

        @Override
        public void onInvalidated() {
            super.onInvalidated();
        }
    };
    /**
     * 该Adapter不会像ListView一样有重用机制
     *
     * @param adapter
     */
    public void setAdapter(BaseAdapter adapter) {

        for (int index = 0; index != adapter.getCount(); ++index) {
            View childView = adapter.getView(index, null, this);

            addView(childView);

        }

        this.adapter = adapter;

        adapter.registerDataSetObserver(dataSetObserver);

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            private int counts = 0;

            @Override
            public void onGlobalLayout() {
                counts++;
                if (counts < 2) {
                    Log.i("", "counts init tag initSign");
                    randInitChilds();
                }
            }
        });
    }


    private final Timer timer = new Timer();

    public void startMoving() {
        //init childView position
//        randInitChilds();
//getChildVisibleRect()

        timer.scheduleAtFixedRate(timerTask, 0, 100);
    }

    public TagRandomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
