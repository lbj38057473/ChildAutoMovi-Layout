package com.houzhi.childautomovi.movi;

import android.view.View;
import android.widget.RelativeLayout;
import android.util.Log;

import java.util.Random;

/**
 * Created by houzhi on 15-5-16.
 * vertical up bubble
 */
public class BubbleDirectUpMoving implements ViewMovingInterface{



    public static final int SINGLE_MOVING_STEPS = 4 ;


    public int getPerMaxMovingSteps() {
        return perMaxMovingSteps;
    }

    /**
     * set each max step size for moving.
     * @param perMaxMovingSteps
     */
    public void setPerMaxMovingSteps(int perMaxMovingSteps) {
        this.perMaxMovingSteps = perMaxMovingSteps;
    }

    private int perMaxMovingSteps = SINGLE_MOVING_STEPS ;



    /**
     * 移动的随机产生器
     */
    private Random movingRandom = new Random();

    /**
     *
     * @return 一个正数在 0 与SINGLE_MOVING_STEPS之间的数
     */
    private int nextRandomSteps(){

        return Math.abs(movingRandom.nextInt() )% perMaxMovingSteps;
    }


    /**
     * 不断向上冒泡
     * @param view
     */
    @Override
    public void move(View view,int width,int height) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();


        //只需要两个margin 就可以确定位置了

        int next = nextRandomSteps() ;
        params.bottomMargin =  next + params.bottomMargin ;

        if(params.bottomMargin > height ){
            //回到最下面
            params.bottomMargin =  - nextRandomSteps() ;
        }

        Log.i(ViewMovingInterface_TAG, "tag:" + params.bottomMargin + "," + params.leftMargin + "");

        view.setLayoutParams(params);
    }


    /**
     * 随机设置view 的位置,位置在底部
     * @param view
     */
    @Override
    public void init(View view,int width,int height){
        if(! (view.getLayoutParams() instanceof  RelativeLayout.LayoutParams)){
            throw new IllegalArgumentException("child view params must be RelativeLayout.LayoutParams");
        }

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)view.getLayoutParams();


        //只需要两个margin 就可以确定位置了
        params.bottomMargin = Math.abs(movingRandom.nextInt()) % 10 ;
        params.leftMargin = Math.abs(movingRandom.nextInt()) % (width - view.getWidth() ) ;

        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

//        for( int rule : params.getRules()){
//            if(rule == ALIGN_PARENT_BOTTOM || rule == ALIGN_PARENT_END
//                    || rule == ALIGN_PARENT_START || rule == ALIGN_PARENT_RIGHT){
//
//                params.removeRule(rule);
//            }
//        }

        view.setLayoutParams(params);
    }


}
