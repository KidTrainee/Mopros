package jp.co.ienter.mopros.activity.deliver_report.views;

import android.content.Context;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import jp.co.ienter.mopros.R;

public class CustomizePalettePickUpItem extends LinearLayout {

    private Context mContext;
    private LinearLayout layoutParent;
    private TextView tvLabel;
    private EditText editText;

    public CustomizePalettePickUpItem(Context context) {
        super(context);
        inflate(context, R.layout.item_palette_pickup_customize, this);
        this.mContext = context;
        initUI();
    }

    private void initUI(){
        layoutParent = (LinearLayout) findViewById(R.id.layout_parrent);
        tvLabel = (TextView) findViewById(R.id.tv_label_pallet_item);
        editText = (EditText) findViewById(R.id.edt_palette_pickup);
    }

    public void setLabel(String label){
        tvLabel.setText(label);
    }

    public void setEditext(String value){
        editText.setText(value);
    }

    public String getValue(){
        return editText.getText().toString();
    }
}
