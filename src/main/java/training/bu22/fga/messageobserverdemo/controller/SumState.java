package training.bu22.fga.messageobserverdemo.controller;

import android.os.Message;
import android.util.Log;

/**
 * Created by public on 3/13/2018.
 */
public class SumState extends BaseState {

    public static final String TAG = SumState.class.getName();

    public SumState(final CalculatorController controller) {
        super(controller);
    }

    @Override
    public void handleMsg(Message msg){
        //......
        float a = mController.getMainActivity().getNumberA();
        float b = mController.getMainActivity().getNumberB();
        float result;
        Log.d(TAG, "a =  " + a + "; b = " + b);
        result = a + b;
        mController.getMainActivity().getmModel().setResult(String.valueOf(result));
    }
}
