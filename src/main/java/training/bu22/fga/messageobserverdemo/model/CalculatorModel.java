package training.bu22.fga.messageobserverdemo.model;

import android.util.Log;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Created by public on 3/13/2018.
 */
public class CalculatorModel {

    public static final String TAG = CalculatorModel.class.getName();

    private String result;

    public static final String RESULT = "RESULT";

    private PropertyChangeSupport mPropertyChangeSupport;

    public CalculatorModel() {
        this.mPropertyChangeSupport = new PropertyChangeSupport(this);
    }

    /**
     * Register PropertyChangeListenner for model.
     * @param listenner
     */
    public void setPropertyChangeSupportListenner(PropertyChangeListener listenner) {
        mPropertyChangeSupport.addPropertyChangeListener(listenner);
    }

    public String getResult() {
        return result;
    }

    public void setResult(final String result) {
        Log.d(TAG, "setResult() called with " + "result = [" + result + "]");
        String oldResult = this.result;
        this.result = result;
        mPropertyChangeSupport.firePropertyChange(RESULT,oldResult, result);
    }
}
