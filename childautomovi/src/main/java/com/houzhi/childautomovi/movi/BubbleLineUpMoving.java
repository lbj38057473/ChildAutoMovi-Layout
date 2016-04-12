package com.houzhi.childautomovi.movi;

import android.view.View;
import android.widget.RelativeLayout;
import android.util.Log;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by houzhi on 15-5-16.
 * line up bubble
 */
public class BubbleLineUpMoving implements ViewMovingInterface{




    public int getPerMaxVerticalMovingSteps() {
        return perMaxVerticalMovingSteps;
    }

    /**
     * set each max step size
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
     * set init max vertical interval for each view
     * @param initMaxVerticalDistance
     */
    public void setInitMaxVerticalDistance(int initMaxVerticalDistance) {
        this.initMaxVerticalDistance = initMaxVerticalDistance;
    }

    /**
     * moving random
     */
    private Random movingRandom = new Random();

    /**
     *
     * @return a value between 0 and SINGLE_MOVING_STEPS
     */
    private int nextRandomSteps(){
        return movingRandom.nextInt() % perMaxVerticalMovingSteps;
    }



    /**
     * sign for same moving direct
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
     * bubble move
     * @param view
     */
    @Override
    public void move(View view,int width,int height) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();

        if( leftIncSignMap.get(getViewIndependentKey(view))==null ){
            initSign(view);
        }

        int next = Math.abs(nextRandomSteps());
        params.bottomMargin =  next + params.bottomMargin ;


        if(params.bottomMargin > height ){
            // back to bottom
            params.bottomMargin =  - Math.abs(nextRandomSteps() );
        }

        int sign = leftIncSignMap.get(getViewIndependentKey(view));
        params.leftMargin = nextPositiveHorizontalSteps() * sign + params.leftMargin ;
        if(params.leftMargin < 0 ){
            params.leftMargin = -params.leftMargin ;
            //revert direction
            leftIncSignMap.put(getViewIndependentKey(view),-sign);
        }
        if(params.leftMargin > width - view.getWidth() ){
            params.leftMargin = width - view.getWidth() ;
            //revert direction
            leftIncSignMap.put(getViewIndependentKey(view),-sign);
        }

        Log.i("", "tag:" + params.topMargin + "," + params.leftMargin + "");
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
     * random set the position of view at screen bottom
     * @param view
     */
    @Override
    public void init(View view,int width,int height){
        if(! (view.getLayoutParams() instanceof  RelativeLayout.LayoutParams)){
            throw new IllegalArgumentException("child view params must be RelativeLayout.LayoutParams");
        }

        initSign(view);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)view.getLayoutParams();


        //only two margin, it can make sure the view position.
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
