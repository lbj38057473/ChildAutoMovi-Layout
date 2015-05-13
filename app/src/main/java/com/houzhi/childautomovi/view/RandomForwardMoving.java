package com.houzhi.childautomovi.view;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by houzhi on 15-5-13.
 */
public class RandomForwardMoving implements ViewMovingInterface{
    public static final int SINGLE_MOVING_STEPS = 20 ;


    public RandomForwardMoving(){
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
        int perMovingSteps = SINGLE_MOVING_STEPS ;
        return Math.abs(movingRandom.nextInt() )% perMovingSteps ;
    }



    /**
     * 设置标签之能够朝着一个方向移动
     */
    private HashMap<String,Integer> topIncSignMap = new HashMap<>();
    private HashMap<String,Integer> leftIncSignMap = new HashMap<>();



    /**
     * 随机设置下一个位置来移动View,通过设置margin值来移动View
     * @param view
     */
    @Override
    public void move(View view,int width,int height) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();


        //只需要两个margin 就可以确定位置了

        if(topIncSignMap.get(getViewIndependentKey(view)) == null ||leftIncSignMap.get(getViewIndependentKey(view))==null ){
            initSign(view);
        }
        int next = nextRandomSteps() ;
        int sign = topIncSignMap.get(getViewIndependentKey(view));
        Log.i("", "tag sign top :" +sign +" next is " + next);
        assert  sign != 0 ;
        params.topMargin =  next * sign+params.topMargin ;
        if(params.topMargin < 0 ){
            params.topMargin = -params.topMargin ;
            //逆转方向
            topIncSignMap.put(getViewIndependentKey(view),-sign);
        }

        if(params.topMargin > height ){
            params.topMargin = height - view.getHeight() ;
            //逆转方向
            topIncSignMap.put(getViewIndependentKey(view),-sign);
        }

        next = nextRandomSteps() ;
        sign = leftIncSignMap.get(getViewIndependentKey(view));
        Log.i("", "tag sign left:" +sign);
        assert  sign != 0 ;
        params.leftMargin = next * sign+ params.leftMargin ;
        if(params.leftMargin < 0 ){
            params.leftMargin = -params.leftMargin ;
            //逆转方向
            leftIncSignMap.put(getViewIndependentKey(view),-sign);
        }
        if(params.leftMargin > width ){
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
    }

    private String getViewIndependentKey(View view){
        return view.hashCode()+""+view.getId()+"";
    }

    private void initSign(View view){
        Log.i("", "tag initSign:");

        Random random = new Random();
        if(random.nextInt() > 0 ) {
            topIncSignMap.put(getViewIndependentKey(view),1);
        }else{
            topIncSignMap.put(getViewIndependentKey(view),-1);
        }

        if(random.nextInt() > 0 ) {
            leftIncSignMap.put(getViewIndependentKey(view),1);
        }else{
            leftIncSignMap.put(getViewIndependentKey(view),-1);
        }


    }

    /**
     * 随机设置view 的位置
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
        params.topMargin = Math.abs(movingRandom.nextInt()) % height ;
        params.leftMargin = Math.abs(movingRandom.nextInt()) % width ;

        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
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
