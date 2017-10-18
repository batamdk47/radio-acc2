package radio.elenahudson.uk.ulti.timer;

import java.util.ArrayList;

import radio.elenahudson.uk.TimerObject;


public class ArrayTimer {
    public  static ArrayList<TimerObject> ArrayTimer(){
        ArrayList<TimerObject> mTimerObject = new ArrayList<>();

        for(int i = 1;i<10;i++){
            TimerObject timerObject=new TimerObject();
            timerObject.timer =" "+i*10 +" Minutes";
            timerObject.time=i*10;
            mTimerObject.add(timerObject);
        }
        return mTimerObject;
    }
}
