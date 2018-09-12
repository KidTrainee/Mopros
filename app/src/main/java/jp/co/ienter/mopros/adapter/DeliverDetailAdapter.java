package jp.co.ienter.mopros.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.adapter.viewholder.DeliverDetailViewHolder;
import jp.co.ienter.mopros.adapter.viewholder.PickupViewHolder;
import jp.co.ienter.mopros.models.MoprosDelivery;
import jp.co.ienter.mopros.activity.deliver_report.TopActivity;

/**
 * Control the list in {@link TopActivity}
 */
public class DeliverDetailAdapter extends DeliveryAdapter<MoprosDelivery> {

    public DeliverDetailAdapter(Context context) {
        super(context);
    }

    /**----------------------------**/
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        switch (viewType) {
            case TYPE_DESTINATION_PICK_UP:
                itemView = mInflater.inflate(R.layout.item_pickup_list, parent, false);
                return new PickupViewHolder(itemView);
            default:
                itemView = mInflater.inflate(R.layout.item_destination_list, parent, false);
                return new DeliverDetailViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case TYPE_DESTINATION_PICK_UP:
                ((PickupViewHolder) holder).setupView(mContext, mDeliveryList.get(position), position);
                break;
            default:
                ((DeliverDetailViewHolder) holder).setupView(mContext, mDeliveryList.get(position), position);
                break;
        }
    }
}
