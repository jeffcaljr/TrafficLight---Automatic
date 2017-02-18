package com.jefferycalhoun.hw3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ControllerFragment.LightChangerListener, SequenceTimerFragment.SequenceListener {

    private static final String EXTRA_TRAFFIC_LIGHT_STATE = "EXTRA_TRAFFIC_LIGHT_STATE";
    private static final String SEQUENCE_TIMER_FRAG_TAG = "SequenceTimerFragment";


    private TrafficLight mTrafficLightModel;

    TrafficLightFragment trafficLightFragment;

    private SequenceTimerFragment sequenceTimerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTrafficLightModel = new TrafficLight();

        sequenceTimerFragment = (SequenceTimerFragment) getSupportFragmentManager().findFragmentByTag(SEQUENCE_TIMER_FRAG_TAG);

        trafficLightFragment = (TrafficLightFragment) getSupportFragmentManager().findFragmentById(R.id.traffic_light_fragment);

        if(sequenceTimerFragment == null){
            sequenceTimerFragment = new SequenceTimerFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(sequenceTimerFragment, SEQUENCE_TIMER_FRAG_TAG)
                    .commit();
        }
        sequenceTimerFragment.setSequenceListener(this);

        if(savedInstanceState != null){
            String savedTrafficLightStateString = savedInstanceState.getString(EXTRA_TRAFFIC_LIGHT_STATE);

            if(savedTrafficLightStateString!= null && !savedTrafficLightStateString.equals("")){
                TrafficLight.TrafficLightState savedState = TrafficLight.TrafficLightState.valueOf(savedTrafficLightStateString);
                setTrafficLightState(savedState);
            }
        }
        else{
            startTrafficLight();
        }




    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(EXTRA_TRAFFIC_LIGHT_STATE, mTrafficLightModel.getState().toString());
    }

    public void setTrafficLightState(TrafficLight.TrafficLightState state){

        if(trafficLightFragment != null){

            mTrafficLightModel.setState(state);
            trafficLightFragment.changeLight(state);


        }
        else{
            Log.e("TrafficLight", "Failed to retrieve TrafficLightFragment from support fragment manager");
        }
    }


    //MARK: ControllerFragment.LightChangeListener methods

    public void startTrafficLight(){
//        mTrafficLightModel.setState(TrafficLight.TrafficLightState.GREEN);
//        setTrafficLightState(TrafficLight.TrafficLightState.GREEN);
        sequenceTimerFragment.startSequence();


    }

    @Override
    public void stopTrafficLight() {
        sequenceTimerFragment.stopSequence();
        mTrafficLightModel.setState(TrafficLight.TrafficLightState.RED);
        setTrafficLightState(TrafficLight.TrafficLightState.RED);
    }


    //MARK: SequenceTimerFragment methods


    @Override
    public TrafficLight.TrafficLightState sequenceWasTriggered() {
//        Toast.makeText(this, "it's working", Toast.LENGTH_SHORT).show();

        TrafficLight.TrafficLightState newState;
//        TrafficLightFragment trafficLightFragment;
//        trafficLightFragment = (TrafficLightFragment) getSupportFragmentManager().findFragmentById(R.id.traffic_light_fragment);

        if(trafficLightFragment != null){

            newState = mTrafficLightModel.changeTrafficLight();
            trafficLightFragment.changeLight(newState);

            return newState;
        }
        else{
            Log.e("TrafficLight", "Failed to retrieve TrafficLightFragment from support fragment manager");
            return null;
        }

    }
}
