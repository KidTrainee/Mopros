package jp.co.ienter.mopros.activity.deliver_report.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import jp.co.ienter.mopros.R;

public class CustomizeRadioButton extends LinearLayout {

    private Context mContext;
    private LinearLayout layoutParent;
    private RadioButton btnYes;
    private RadioButton btnNo;
    private RadioGroup radioGroup;
    private TextView tvLabel;

    public CustomizeRadioButton(Context context) {
        super(context);
        inflate(context, R.layout.radio_button_customize, this);
        this.mContext = context;
        initUI();
    }

    @SuppressLint("ResourceType")
    private void initUI() {
        layoutParent = (LinearLayout) findViewById(R.id.layout_parrent);
        btnYes = (RadioButton) findViewById(R.id.rb_yes);
        btnNo = (RadioButton) findViewById(R.id.rb_no);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        tvLabel = (TextView) findViewById(R.id.tv_label_radio);
        btnYes.setId(1);
        btnNo.setId(0);
    }

    public void setLabel(String label){
        tvLabel.setText(label);
    }

    public void setRadioButtonTitle(String title1, String title2){
        btnYes.setText(title1);
        btnNo.setText(title2);
    }
    public void setChecked(String kbn){
        switch (kbn) {
            case "1":
                btnYes.setChecked(true);
            case "0":
                btnNo.setChecked(true);
            case "-1":
            default:
        }
    }
    public int getSelected(){
        return radioGroup.getCheckedRadioButtonId();
    }
    public boolean isButtonYesSelected(){
        return btnYes.isSelected();
    }

    public boolean isButtonNoSelected(){
        return btnNo.isSelected();
    }

}
