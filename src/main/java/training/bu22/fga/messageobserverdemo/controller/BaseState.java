package training.bu22.fga.messageobserverdemo.controller;

import android.os.Message;

/**
 * Created by public on 3/13/2018.
 */
public class BaseState {

    protected CalculatorController mController;

    public BaseState(CalculatorController controller) {
        mController = controller;
    }

    public void handleMsg(Message msg){
        //......
    }

}
