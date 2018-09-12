package jp.co.ienter.mopros.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.adapter.viewholder.SelectableDeliveryViewHolder;
import jp.co.ienter.mopros.adapter.viewholder.SelectablePickupViewHolder;
import jp.co.ienter.mopros.interfaces.ConfirmListener;
import jp.co.ienter.mopros.models.SelectableDelivery;

public class SelectableDeliveryAdapter extends DeliveryAdapter<SelectableDelivery> {
    private Context mContext;
    private OnDestinationClickListener mListener;

    public SelectableDeliveryAdapter(Context context, OnDestinationClickListener listener) {
        super(context);
        mContext = context;
        mDeliveryList = new ArrayList<>();
        mListener = listener;
    }

    @Override
    public void updateData(List<SelectableDelivery> destinations) {
        mDeliveryList.clear();
        mDeliveryList.addAll(filterNotDeliverDestinations(destinations));
        notifyDataSetChanged();
    }

    private List<SelectableDelivery> filterNotDeliverDestinations(List<SelectableDelivery> destinations) {
        List<SelectableDelivery> filteredList = new ArrayList<>();
        for (SelectableDelivery selectableDelivery : destinations) {
            if (!selectableDelivery.isNotDeliver()) {
                filteredList.add(selectableDelivery);
            }
        }
        return filteredList;
    }

    public void deselectAll(ArrayList<SelectableDelivery> selectedDeliveryList) {
        for (SelectableDelivery selectableDelivery : selectedDeliveryList) {
            if (selectableDelivery.isSelected()) selectableDelivery.setSelected(false);
            notifyItemChanged(mDeliveryList.indexOf(selectableDelivery));
        }
    }

    /**
     * -----------------------------------
     **/
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        switch (viewType) {
            case TYPE_DESTINATION_PICK_UP:
                itemView = mInflater.inflate(R.layout.item_selectable_pickup_list, parent, false);
                return new SelectablePickupViewHolder(itemView);
            default:
                itemView = mInflater.inflate(R.layout.item_selectable_destination_list, parent, false);
                return new SelectableDeliveryViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final SelectableDelivery selectableDelivery = mDeliveryList.get(holder.getAdapterPosition());
        final int pos = holder.getAdapterPosition();

        switch (holder.getItemViewType()) {
            case TYPE_DESTINATION_PICK_UP:
                ((SelectablePickupViewHolder) holder).setupView(mContext, mDeliveryList.get(pos), pos);

                holder.itemView.setOnClickListener(new OnDeliverClickListener(holder, selectableDelivery) {
                    @Override
                    void onFinishDeliverDoneBehavior() {
                        mListener.onSingleItemClick(mDeliveryList.get(pos), pos);
//                        notifyItemChanged(pos);
                        notifyDataSetChanged();
                    }
                });
                break;
            default:
                ((SelectableDeliveryViewHolder) holder).setupView(mContext, mDeliveryList.get(pos), pos);
                holder.itemView.setOnClickListener(new OnDeliverClickListener(holder, selectableDelivery) {
                    @Override
                    void onFinishDeliverDoneBehavior() {
                        mListener.onItemClick(selectableDelivery, pos);
//                        notifyItemChanged(pos);
                        notifyDataSetChanged();
                    }
                });
                break;
        }
    }

    public interface OnDestinationClickListener {
        void onItemClick(SelectableDelivery delivery, int position);

        void onSingleItemClick(SelectableDelivery pickup, int position);

        void onDoneItemClick(SelectableDelivery delivery, int position, ConfirmListener listener);
    }

    private abstract class OnDeliverClickListener implements View.OnClickListener {

        private RecyclerView.ViewHolder holder;
        private SelectableDelivery selectableDelivery;

        OnDeliverClickListener(RecyclerView.ViewHolder holder, SelectableDelivery selectableDelivery) {
            this.holder = holder;
            this.selectableDelivery = selectableDelivery;
        }

        @Override
        public void onClick(View view) {
            final int pos = holder.getAdapterPosition();
            if (selectableDelivery.isDone()
                    && !selectableDelivery.isSelected()) {
                mListener.onDoneItemClick(selectableDelivery, pos, new ConfirmListener() {
                    @Override
                    public void onAgree() {
                        onFinishDeliverDoneBehavior();
                    }

                    @Override
                    public void onCancel() {

                    }
                });
            } else {
                onFinishDeliverDoneBehavior();
            }
        }

        abstract void onFinishDeliverDoneBehavior();

    }
}
