package com.jefferycalhoun.hw3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * Created by Jeff on 2/8/17.
 */

public class TrafficLight {

    enum TrafficLightState{
        RED, YELLOW, GREEN;
    }

    private TrafficLightState currentState;
    private ArrayList<TrafficLightState> states;
    private ListIterator<TrafficLightState> itr;

    public TrafficLight(){

        this.states = new ArrayList<>();
        this.states.add(TrafficLightState.GREEN);
        this.states.add(TrafficLightState.YELLOW);
        this.states.add(TrafficLightState.RED);



        this.itr = states.listIterator();

    }


    public TrafficLightState getState(){
        return this.currentState;
    }

    /**
     * Reset the iterator to the beginning, and loop through until the iterator points to the state specified by the argument
     * @param state The state to set the iterator to
     */
    public void setState(TrafficLightState state){
        this.itr = this.states.listIterator();
        while(this.itr.hasNext()){
            TrafficLightState tempState = this.itr.next();
            if(tempState == state){
                this.currentState = tempState;
                break;
            }
        }
    }

    public TrafficLightState changeTrafficLight(){
        if(this.itr.hasNext()){
            this.currentState = this.itr.next();
            return this.currentState;
        }
        else{
            this.itr = this.states.listIterator();
            this.currentState =  this.itr.next();
            return this.currentState;
        }
    }
}
