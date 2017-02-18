package com.jefferycalhoun.hw3;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Jeff on 2/8/17.
 */

public class TrafficLightFragment extends Fragment{

    ImageView mRedLight;
    ImageView mGreenLight;
    ImageView mYellowLight;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.traffic_light_fragment, container, false);

        mRedLight = (ImageView) view.findViewById(R.id.red_light);

        mYellowLight = (ImageView) view.findViewById(R.id.yellow_light);

        mGreenLight = (ImageView) view.findViewById(R.id.green_light);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void changeLight(TrafficLight.TrafficLightState state){
        switch(state){
            case RED:
                //If the traffic light state is red, make the green and yellow lights inactive, and the red light active
                mYellowLight.getDrawable().setColorFilter(ResourcesCompat.getColor(getResources(), R.color.inactive_light, null), PorterDuff.Mode.ADD);
                mGreenLight.getDrawable().setColorFilter(ResourcesCompat.getColor(getResources(), R.color.inactive_light, null), PorterDuff.Mode.ADD);
                mRedLight.getDrawable().setColorFilter(ResourcesCompat.getColor(getResources(), R.color.red_light, null), PorterDuff.Mode.ADD);

                break;

            case YELLOW:
                //If the traffic light state is yellow, make the green and red lights inactive, and the red light active
                mGreenLight.getDrawable().setColorFilter(ResourcesCompat.getColor(getResources(), R.color.inactive_light, null), PorterDuff.Mode.ADD);
                mRedLight.getDrawable().setColorFilter(ResourcesCompat.getColor(getResources(), R.color.inactive_light, null), PorterDuff.Mode.ADD);
                mYellowLight.getDrawable().setColorFilter(ResourcesCompat.getColor(getResources(), R.color.yellow_light, null), PorterDuff.Mode.ADD);
                break;

            case GREEN:
                //If the traffic light state is green, make the red and yellow lights inactive, and the red light active
                mRedLight.getDrawable().setColorFilter(ResourcesCompat.getColor(getResources(), R.color.inactive_light, null), PorterDuff.Mode.ADD);
                mYellowLight.getDrawable().setColorFilter(ResourcesCompat.getColor(getResources(), R.color.inactive_light, null), PorterDuff.Mode.ADD);
                mGreenLight.getDrawable().setColorFilter(ResourcesCompat.getColor(getResources(), R.color.green_light, null), PorterDuff.Mode.ADD);

                break;

            default:
                Log.e("TrafficLightFragment", "Invalid TrafficLightState");
        }
    }
}
