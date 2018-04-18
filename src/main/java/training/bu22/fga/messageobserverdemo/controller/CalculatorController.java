package training.bu22.fga.messageobserverdemo.controller;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.SparseArray;

import java.util.ArrayList;

import training.bu22.fga.messageobserverdemo.MainActivity;

/**
 * Created by public on 3/13/2018.
 */
public class CalculatorController {

    public static final String TAG = CalculatorController.class.getName();

    public static final int SUM_STATE = 1;

    public static final int MUL_STATE = 2;

    private MsgHandler msgHandler;

    private MainActivity mainActivity;

    private SparseArray<BaseState> mStates;

    private BaseState currentState;


    public CalculatorController(MainActivity activity) {
        msgHandler = new MsgHandler(this);
        mainActivity = activity;
        mStates = initState();
        currentState = mStates.get(SUM_STATE);
    }

    public void sendMessage(Message msg){
        //........
        msgHandler.sendMessage(msg);
    }

    private void handleMsg(Message msg){
        Log.d(TAG, "handleMsg() called with " + "msg = [" + msg + "]");
        //........
        switch (msg.what){
            case MainActivity.EVENT_ONCLICK:
                Log.d(TAG, "EVENT_ONCLICK");
                if(currentState!=null){
                    currentState.handleMsg(msg);
                }
                break;
            case MainActivity.EVENT_ON_ITEM_CHANGE:
                String operation = mainActivity.getOperation();
                Log.d(TAG, "EVENT_ON_ITEM_CHANGE: operation = " + operation);
                if (operation.equals(MainActivity.SUM)) {
                    currentState = mStates.get(SUM_STATE);
                } else {
                    currentState = mStates.get(MUL_STATE);
                }
                break;
            default:
                break;
        }
    }

    private SparseArray<BaseState> initState(){
        SparseArray<BaseState> states = new SparseArray<>();
        states.put(SUM_STATE,new SumState(this));
        states.put(MUL_STATE,new MulState(this));

        return states;
    }

    public MainActivity getMainActivity() {
        return mainActivity;
    }

    /**
     * Ultility provides send message and handle message.
     */
    private static class MsgHandler extends Handler {

        private CalculatorController mController;

        public MsgHandler(CalculatorController controller) {
            super();
            mController = controller;
        }

        @Override
        public void handleMessage(final Message msg) {
            mController.handleMsg(msg);
        }
    }

}
