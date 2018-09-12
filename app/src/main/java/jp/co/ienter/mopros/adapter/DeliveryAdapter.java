package jp.co.ienter.mopros.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.adapter.viewholder.DeliveryViewHolder;
import jp.co.ienter.mopros.adapter.viewholder.PickupViewHolder;
import jp.co.ienter.mopros.models.MoprosDelivery;

import static jp.co.ienter.mopros.utils.Const.FLAG_DESTINATION_TYPE_DELIVER;

public class DeliveryAdapter<T extends MoprosDelivery> extends RecyclerView.Adapter {

    protected static final int TYPE_DESTINATION_DELIVER = 0;
    protected static final int TYPE_DESTINATION_PICK_UP = 1;

    protected Context mContext;
    protected ArrayList<T> mDeliveryList;
    protected final LayoutInflater mInflater;

    public DeliveryAdapter(Context context) {
        mContext = context;
        mDeliveryList = new ArrayList<>();

        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemViewType(int position) {
        if (mDeliveryList.get(position).getData_type().equals(FLAG_DESTINATION_TYPE_DELIVER))
            return TYPE_DESTINATION_DELIVER;
        else
            return TYPE_DESTINATION_PICK_UP;
    }

    @Override
    public int getItemCount() {
        return mDeliveryList.size();
    }

    public void updateData(List<T> destinations) {
        this.mDeliveryList.clear();
        this.mDeliveryList.addAll(destinations);
        notifyDataSetChanged();
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
                return new DeliveryViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case TYPE_DESTINATION_PICK_UP:
                ((PickupViewHolder) holder).setupView(mContext, mDeliveryList.get(position), position);
                break;
            default:
                ((DeliveryViewHolder) holder).setupView(mContext, mDeliveryList.get(position), position);
                break;
        }
    }

}
