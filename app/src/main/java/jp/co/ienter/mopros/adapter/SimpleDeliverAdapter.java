package jp.co.ienter.mopros.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.models.MoprosDelivery;
import jp.co.ienter.mopros.utils.Const;

public class SimpleDeliverAdapter extends RecyclerView.Adapter<SimpleDeliverAdapter.ViewHolder> {

    private final Context context;
    private final List<MoprosDelivery> deliveries;

    public SimpleDeliverAdapter(List<MoprosDelivery> deliveries, Context context) {
        this.deliveries = deliveries;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.simple_deliver_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MoprosDelivery delivery = deliveries.get(position);
        if (delivery.getData_type().equals(Const.FLAG_DESTINATION_TYPE_DELIVER))
            holder.nameTV.setText(String.format(context.getString(R.string.deliver_store_name_format), delivery.getNonyu_name()));
        else
            holder.nameTV.setText(String.format(context.getString(R.string.pickup_store_name_format), delivery.getNonyu_name()));
    }

    @Override
    public int getItemCount() {
        return deliveries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.simple_deliver_item_tv)
        TextView nameTV;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
