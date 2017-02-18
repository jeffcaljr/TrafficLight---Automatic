package com.jefferycalhoun.hw3;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * Created by Jeff on 2/17/17.
 */

public class SequenceTimerFragment extends Fragment {

    public static final long RED_DELAY = 5000;
    public static final long GREEN_DELAY = 3000;
    public static final long YELLOW_DELAY = 2000;

    private Handler mHandler;

    private SequenceListener mListener;

    public void setSequenceListener(SequenceListener listener){
        this.mListener = listener;
    }

    interface SequenceListener{
        TrafficLight.TrafficLightState sequenceWasTriggered();
    }

    public void startSequence(){
        if(mHandler == null){
            mHandler = new Handler((Looper.getMainLooper()));
            mHandler.post(mRunnable);
        }

    }

    public void stopSequence(){
        if(mHandler != null){
            mHandler.removeCallbacks(mRunnable);
            mHandler = null;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            TrafficLight.TrafficLightState newState = null;
            if(mListener != null){
                newState = mListener.sequenceWasTriggered();
            }
            switch(newState){
                case RED:
                    mHandler.postDelayed(mRunnable, RED_DELAY);
                    break;
                case GREEN:
                    mHandler.postDelayed(mRunnable, GREEN_DELAY);
                    break;
                case YELLOW:
                    mHandler.postDelayed(mRunnable, YELLOW_DELAY);
                    break;
                default:
                    Log.e("SequenceTimerFragment", "Invalid traffic light state!");
            }
        }

    };
}
