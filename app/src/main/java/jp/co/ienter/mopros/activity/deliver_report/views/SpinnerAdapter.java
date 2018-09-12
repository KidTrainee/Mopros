package jp.co.ienter.mopros.activity.deliver_report.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.models.ReportLoading;
import jp.co.ienter.mopros.models.ReportMasterData;

public class SpinnerAdapter<T> extends ArrayAdapter<T> {

    private Context mContent;
    private List<T> arrReportLoading;
    private LayoutInflater mInflater;


    public SpinnerAdapter(@NonNull Context context, int resource, @NonNull List<T> objects) {
        super(context, resource, objects);
        this.mContent = context;
        this.arrReportLoading = objects;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = mInflater.inflate( android.R.layout.simple_spinner_dropdown_item, parent, false);
        }
        T rowItem = getItem(position);
        if(rowItem instanceof ReportLoading){
            ReportLoading item = (ReportLoading) rowItem;
            TextView tvContent = (TextView) convertView.findViewById(android.R.id.text1);
            tvContent.setText(item.getItem_name());
        } else if(rowItem instanceof ReportMasterData.Sub_item_list){
            ReportMasterData.Sub_item_list item = (ReportMasterData.Sub_item_list) rowItem;
            TextView tvContent = (TextView) convertView.findViewById(android.R.id.text1);
            tvContent.setText(item.getSub_item_name());
        }
        return convertView;
    }


    /**
     * update array data for spinner
     * @param data
     */
    public void updateListData(List<T> data){
        clear();
        if(data == null){
            notifyDataSetChanged();
            return;
        }
        addAll(data);
        notifyDataSetChanged();;
    }

    public List<T> getArrReportLoading() {
        return arrReportLoading;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = mInflater.inflate( android.R.layout.simple_spinner_dropdown_item, parent, false);
        }
        Object rowItem = getItem(position);
        if(rowItem instanceof ReportLoading) {
            ReportLoading item = (ReportLoading) rowItem;
            TextView tvContent = (TextView) convertView.findViewById(android.R.id.text1);
            tvContent.setText(item.getItem_name());
        } else if(rowItem instanceof ReportMasterData.Sub_item_list){
            ReportMasterData.Sub_item_list item = (ReportMasterData.Sub_item_list) rowItem;
            TextView tvContent = (TextView) convertView.findViewById(android.R.id.text1);
            tvContent.setText(item.getSub_item_name());
        }
        return convertView;
    }
}
