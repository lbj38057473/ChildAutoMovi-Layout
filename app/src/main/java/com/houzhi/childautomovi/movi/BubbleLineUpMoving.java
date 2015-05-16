package com.houzhi.childautomovi.movi;

import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by houzhi on 15-5-16.
 * 直接垂直向上冒泡
 */
public class BubbleLineUpMoving implements ViewMovingInterface{




    public int getPerMaxVerticalMovingSteps() {
        return perMaxVerticalMovingSteps;
    }

    /**
     * 设置每一步最大的移动大小，这是水平跟垂直分开的 默认4
     * @param perMaxVerticalMovingSteps
     */
    public void setPerMaxVerticalMovingSteps(int perMaxVerticalMovingSteps) {
        this.perMaxVerticalMovingSteps = perMaxVerticalMovingSteps;
    }

    private int perMaxVerticalMovingSteps = 4 ;

    private int initMaxVerticalDistance = 60 ;

    public int getInitMaxVerticalDistance() {
        return initMaxVerticalDistance;
    }

    /**
     * 设置初始化的时候，每个view垂直位置相差的最大值.默认60
     * @param initMaxVerticalDistance
     */
    public void setInitMaxVerticalDistance(int initMaxVerticalDistance) {
        this.initMaxVerticalDistance = initMaxVerticalDistance;
    }

    /**
     * 移动的随机产生器
     */
    private Random movingRandom = new Random();

    /**
     *
     * @return 一个正数在 0 与SINGLE_MOVING_STEPS之间的数
     */
    private int nextRandomSteps(){
        return movingRandom.nextInt() % perMaxVerticalMovingSteps;
    }



    /**
     * 设置标签之能够朝着一个方向移动
     */
    private HashMap<String,Integer> leftIncSignMap = new HashMap<>();


    private Random randomHorizontal = new Random();

    public int getPerMaxHorizontalSteps() {
        return perMaxHorizontalSteps;
    }

    public void setPerMaxHorizontalSteps(int perMaxHorizontalSteps) {
        this.perMaxHorizontalSteps = perMaxHorizontalSteps;
    }

    int perMaxHorizontalSteps = 5 ;
    private int nextPositiveHorizontalSteps(){
        return Math.abs(randomHorizontal.nextInt()) % perMaxHorizontalSteps ;
    }

    /**
     * 不断向上冒泡
     * @param view
     */
    @Override
    public void move(View view,int width,int height) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();

        if( leftIncSignMap.get(getViewIndependentKey(view))==null ){
            initSign(view);
        }
        //只需要两个margin 就可以确定位置了

        int next = Math.abs(nextRandomSteps());
        params.bottomMargin =  next + params.bottomMargin ;


        if(params.bottomMargin > height ){
            //回到最下面
            params.bottomMargin =  - Math.abs(nextRandomSteps() );
        }

        int sign = leftIncSignMap.get(getViewIndependentKey(view));
        params.leftMargin = nextPositiveHorizontalSteps() * sign + params.leftMargin ;
        if(params.leftMargin < 0 ){
            params.leftMargin = -params.leftMargin ;
            //逆转方向
            leftIncSignMap.put(getViewIndependentKey(view),-sign);
        }
        if(params.leftMargin > width - view.getWidth() ){
            params.leftMargin = width - view.getWidth() ;
            //逆转方向
            leftIncSignMap.put(getViewIndependentKey(view),-sign);
        }

        Log.i("", "tag:" + params.topMargin + "," + params.leftMargin +"");
//        for( int rule : params.getRules()){
//            if(rule == ALIGN_PARENT_BOTTOM || rule == ALIGN_PARENT_END
//                    || rule == ALIGN_PARENT_START || rule == ALIGN_PARENT_RIGHT){
//
//                params.removeRule(rule);
//            }
//        }

        view.setLayoutParams(params);
        view.invalidate();
    }

    private String getViewIndependentKey(View view){
        return view.hashCode()+""+view.getId()+"";
    }


    private void initSign(View view){
        Log.i("", "tag initSign:");

        Random random = new Random();

        if(random.nextInt() > 0 ) {
            leftIncSignMap.put(getViewIndependentKey(view),1);
        }else{
            leftIncSignMap.put(getViewIndependentKey(view),-1);
        }


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

        initSign(view);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)view.getLayoutParams();


        //只需要两个margin 就可以确定位置了
        params.bottomMargin = Math.abs(movingRandom.nextInt()) % initMaxVerticalDistance;
        params.leftMargin = Math.abs(movingRandom.nextInt()) % (width - view.getWidth() )  ;

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
