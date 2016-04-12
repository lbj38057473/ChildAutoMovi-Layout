package com.houzhi.childautomovi.movi;

import android.view.View;

/**
 * Created by houzhi on 15-5-13.
 */
public interface ViewMovingInterface {
    /**
     * viewMovingInterface
     */
    public static final String ViewMovingInterface_TAG = "ViewMoving";
    /**
     * move
     * @param view
     * @param height move height
     * @param width move width
     */
    void move(View view,int width,int height);


    /**
     * init position
     * @param view
     * @param height move height
     * @param width move width
     */
    void init(View view,int width,int height);

}
