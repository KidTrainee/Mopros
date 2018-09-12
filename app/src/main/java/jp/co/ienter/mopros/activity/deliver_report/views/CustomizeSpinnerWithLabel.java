package jp.co.ienter.mopros.activity.deliver_report.views;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Objects;

import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.models.ReportLoading;

public class CustomizeSpinnerWithLabel extends LinearLayout {

    private Context mContext;
    private LinearLayout layoutParrent;
    private TextView tvLabel;
    private Spinner spinner;

    public CustomizeSpinnerWithLabel(Context context) {
        super(context);
        this.mContext = context;
        inflate(mContext, R.layout.spinner_with_label, this);
        initUI();
    }

    private void initUI(){
        layoutParrent = (LinearLayout) findViewById(R.id.layout_parrent);
        tvLabel = (TextView) findViewById(R.id.tv_label_spinner);
        spinner = (Spinner) findViewById(R.id.spinner);
    }

    /**
     * get item selected
     * @param position
     * @return
     */
    public Object getItemSelected(int position){
        return spinner.getSelectedItem();
    }


    /**
     * set spinner select item with position
     * @param position
     */
    public void setSelection(int position){
        spinner.setSelection(position);
    }

    /**
     * set spinner select item with position
     * @param position
     * @param isListener true: spinner will listen action selected
     */
    public void setSelection(final int position, final boolean isListener){
        final AdapterView.OnItemSelectedListener listener = spinner.getOnItemSelectedListener();
        if(listener == null){
            setSelection(position);
        } else {
            if(!isListener){
                 setOnItemSelectedListener(null);
            }
            spinner.post(new Runnable() {
                @Override
                public void run() {
                    setSelection(position);
                    spinner.post(new Runnable() {
                        @Override
                        public void run() {
                            if(!isListener) {
                                setOnItemSelectedListener(listener);
                            }
                        }
                    });
                }
            });

        }

    }
    /**
     * listener when spinner selected item
     * @param listener
     */
    public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener listener){
        spinner.setOnItemSelectedListener(listener);


    }

    public void setLabel(String label){
        if(label == null)
            return;
        tvLabel.setText(label);
    }

    public void setSpinnerData(ArrayAdapter adapter){
        spinner.setAdapter(adapter);
    }

    public Object getSelectedItem(){
        try {
            Object item = spinner.getSelectedItem();
            return item;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public String getLabel(){
        return tvLabel.getText().toString();
    }

}
