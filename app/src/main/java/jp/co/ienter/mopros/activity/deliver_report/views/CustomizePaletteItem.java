package jp.co.ienter.mopros.activity.deliver_report.views;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.compoundedittextspinner.EditSpinner;

import jp.co.ienter.mopros.R;

public class CustomizePaletteItem extends LinearLayout {

    private LinearLayout layoutParent;
    private TextView tvLabel;
    private Context mContext;
    private EditSpinner spinnerDelivery;
    private EditSpinner spinnerReturn;
    private String palletteNo;
    private OnItemDeliveryPaletteCallback DeliveryPaletteItemCallback;
    private  OnItemReturnPaletteCallback ReturnPaletteItemCallback;

    public CustomizePaletteItem(Context context, final String palletNo, final OnItemDeliveryPaletteCallback DeliveryPaletteItemCallback, final OnItemReturnPaletteCallback ReturnPaletteItemCallback) {
        super(context);
        mContext = context;
        this.palletteNo=palletNo;
        this.DeliveryPaletteItemCallback=DeliveryPaletteItemCallback;
        this.ReturnPaletteItemCallback=ReturnPaletteItemCallback;
        inflate(context, R.layout.item_palette_customize, this);
        initUI();
        spinnerDelivery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DeliveryPaletteItemCallback.onItemDeliveryClick(i,tvLabel.getText().toString(),spinnerDelivery.getText().toString(),palletNo);
            }
        });

        spinnerReturn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ReturnPaletteItemCallback.onItemReturnClick(i,tvLabel.getText().toString(),spinnerReturn.getText().toString(),palletNo);
            }
        });
    }


    private void initUI(){
        layoutParent = (LinearLayout) findViewById(R.id.layout_parrent);
        tvLabel = (TextView) findViewById(R.id.tv_label_pallet_item);
        spinnerDelivery = (EditSpinner) findViewById(R.id.spinner_delivery);
        spinnerReturn = (EditSpinner) findViewById(R.id.spinner_return);
    }

    public void setDeliveryAdapter(ArrayAdapter adapter){
        spinnerDelivery.setAdapter(adapter);
    }

    public void setReturnAdapter(ArrayAdapter adapter){
        spinnerReturn.setAdapter(adapter);
    }

    public void setLabel(String label){
        tvLabel.setText(label);
    }

    public String getDeliveryText(){
        return spinnerDelivery.getText().toString();
    }

    public String getReturnText(){
        return spinnerReturn.getText().toString();
    }



    public interface OnItemDeliveryPaletteCallback {
        void onItemDeliveryClick(int position,String lblLabel,String strDelivery,String palletteNo);
    }

    public interface OnItemReturnPaletteCallback {
        void onItemReturnClick(int position,String lblLabel,String strDelivery,String palletNo);
    }

    public interface OnTextboxPaletteItemCallback {
        void onItemClick(int position,String lblLabel,String strDelivery,String palletteNo);
    }


}
