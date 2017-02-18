package com.jefferycalhoun.hw3;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Jeff on 2/8/17.
 */

public class ControllerFragment extends Fragment {

    Button mStartLightChangerButton;
    Button mStopLightChangerButton;

    public interface LightChangerListener{
        void startTrafficLight();
        void stopTrafficLight();
    }

    private LightChangerListener mListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.light_changer_fragment, container, false);

        mStartLightChangerButton = (Button) view.findViewById(R.id.start_traffic_light_button);
        mStopLightChangerButton = (Button) view.findViewById(R.id.stop_traffic_light_button);


        if(mListener != null){
            if(mStartLightChangerButton != null){
                mStartLightChangerButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mListener.startTrafficLight();
                    }
                });
            }

            if(mStopLightChangerButton != null){

                mStopLightChangerButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mListener.stopTrafficLight();
                    }
                });

            }
        }



        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try{
            mListener = (LightChangerListener) activity;
        }
        catch (ClassCastException e){
            e.printStackTrace();
        }
    }
}
