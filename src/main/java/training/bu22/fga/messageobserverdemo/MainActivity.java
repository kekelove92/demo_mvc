package training.bu22.fga.messageobserverdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import training.bu22.fga.messageobserverdemo.controller.CalculatorController;
import training.bu22.fga.messageobserverdemo.model.CalculatorModel;

public class MainActivity extends Activity {

    public static final String TAG = MainActivity.class.getName();

    public static final String SUM = "+";

    public static final String MUL = "*";
    
    public static final int EVENT_ON_ITEM_CHANGE = 0;

    public static final int EVENT_ONCLICK = 1;



    private Spinner mOperationSpn;

    private EditText mNumberAEdt;

    private EditText mNumberBEdt;

    private Button mResultBtn;

    private TextView mCurrenStateTxt;
    
    private CalculatorModel mModel;

    private CalculatorController mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        registerViewListenner();
        
        initModel();
        initController();
    }

    /**
     * Init view of screen from xml.
     */
    private void initView() {
        mOperationSpn = (Spinner) findViewById(R.id.operation_spn);
        BaseAdapter operationAdt = ArrayAdapter
                .createFromResource(this, R.array.operation, android.R.layout.simple_spinner_item);
        mOperationSpn.setAdapter(operationAdt);
        mOperationSpn.setSelection(0);

        mNumberAEdt = (EditText) findViewById(R.id.number_a_edt);
        mNumberBEdt = (EditText) findViewById(R.id.number_b_edt);

        mResultBtn = (Button) findViewById(R.id.resultbtn);

        mCurrenStateTxt = (TextView) findViewById(R.id.cur_state_txt);
    }

    /**
     * Register listenner for view in screen.
     */
    private void registerViewListenner() {
        mResultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // Do onclick
                Log.d(TAG, "mResultBtn: onClick");
                Message msg = new Message();
                msg.what = EVENT_ONCLICK;
                msg.obj = null;
                mController.sendMessage(msg);
            }
        });
        mOperationSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> adapterView, final View view, final int i, final long l) {
                Message msg = new Message();
                msg.what = EVENT_ON_ITEM_CHANGE;
                msg.obj = mOperationSpn.getSelectedItem().toString();
                mController.sendMessage(msg);
            }

            @Override
            public void onNothingSelected(final AdapterView<?> adapterView) {

            }
        });
    }

    public float getNumberA() {
        if (mNumberAEdt == null || TextUtils.isEmpty(mNumberAEdt.getText())) {
            return 0;
        }
        float a = 0;
        try {
            a = Float.parseFloat(mNumberAEdt.getText().toString());
        } catch (NumberFormatException e) {
            Log.e(TAG, "getNumberA: NumberFormatException");
            a = 0;
        }

        return a;
    }

    public float getNumberB() {
        if (mNumberBEdt == null || TextUtils.isEmpty(mNumberBEdt.getText())) {
            return 0;
        }
        float a = 0;
        try {
            a = Float.parseFloat(mNumberBEdt.getText().toString());
        } catch (NumberFormatException e) {
            Log.e(TAG, "getNumberB: NumberFormatException");
            a = 0;
        }

        return a;
    }

    public String getOperation() {
        if (mOperationSpn == null || TextUtils.isEmpty(mOperationSpn.getSelectedItem().toString())) {
            return null;
        }
        return mOperationSpn.getSelectedItem().toString();
    }

    public CalculatorModel getmModel() {
        return mModel;
    }

    private void initModel(){
        mModel = new CalculatorModel();
        mModel.setPropertyChangeSupportListenner(new PropertyChangeListener() {
            @Override
            public void propertyChange(final PropertyChangeEvent event) {
                onUpdateModel(event);
            }
        });
    }
    
    private void onUpdateModel(PropertyChangeEvent event){
        Log.d(TAG, "onUpdateModel ");
        switch (event.getPropertyName()){
            case CalculatorModel.RESULT:
                if(mResultBtn!=null){
                    mResultBtn.setText(mModel.getResult());
                }
                break;
            default:
                break;
        }
    }

    private void initController(){
        mController = new CalculatorController(this);
    }


}
