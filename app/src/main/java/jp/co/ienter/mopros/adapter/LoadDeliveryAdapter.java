package jp.co.ienter.mopros.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.interfaces.ConfirmListener;
import jp.co.ienter.mopros.interfaces.helper.ItemTouchHelperViewHolder;
import jp.co.ienter.mopros.models.MoprosDelivery;
import jp.co.ienter.mopros.models.SelectableDelivery;
import jp.co.ienter.mopros.utils.Const;

/**
 * Created by donghv on 9/6/18.
 */

public class LoadDeliveryAdapter extends RecyclerView.Adapter<LoadDeliveryAdapter.ItemViewHolder> {
    private ArrayList<SelectableDelivery> listDelivery;
    private OnDestinationClickListener mListener;
    private Context mContext;

    public LoadDeliveryAdapter(ArrayList<SelectableDelivery> listDelivery,OnDestinationClickListener mListener, Context mContext) {
        this.listDelivery = listDelivery;
        this.mListener = mListener;
        this.mContext = mContext;
    }

    public void updateData(@NonNull ArrayList<SelectableDelivery> listSortDelivery) {
        listDelivery=new ArrayList<>();
        listDelivery.addAll(filterListDelivery(listSortDelivery));
        notifyDataSetChanged();
    }
    private ArrayList<SelectableDelivery> filterListDelivery(ArrayList<SelectableDelivery> destinations) {
        ArrayList<SelectableDelivery> filteredList = new ArrayList<>();
        for (SelectableDelivery selectableDelivery : destinations) {
            if (!selectableDelivery.isDelivery()) {
                filteredList.add(selectableDelivery);
            }
        }
        return filteredList;
    }
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from
                (parent.getContext()).inflate(R.layout.item_selectable_ship_list, parent, false);
        ItemViewHolder viewHolder = new ItemViewHolder(rowView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder,final int position) {
        final SelectableDelivery objDelivery = listDelivery.get(position);
        holder.setupView(objDelivery, position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onItemClick(objDelivery,position);
                notifyItemChanged(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listDelivery.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
        @BindView(R.id.tvDestinationName)
        TextView tvStoreName;
        @BindView(R.id.tvDesignation) TextView tvDesignation;
        @BindView(R.id.tvCS) TextView tvCS;
        @BindView(R.id.tvLoad) TextView tvLoad;
        @BindView(R.id.item_destination_list_container)
        LinearLayout mContainerLL;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        public void setupView(SelectableDelivery objDelivery, int position) {
            if(Const.FLAG_DESTINATION_TYPE_DELIVER.equalsIgnoreCase(objDelivery.getData_type())) {
                tvStoreName.setText("[配]" + objDelivery.getNonyu_name());
            }else{
                tvStoreName.setText("[集]" + objDelivery.getNonyu_name());
            }

            mContainerLL.setBackgroundResource(R.drawable.bg_selectable);

            if(!"".equalsIgnoreCase(objDelivery.getShitei_time())) {
                tvDesignation.setText(objDelivery.getShitei_time() + "指定");
            } else {
                tvDesignation.setText("");
            }

            if(objDelivery.getCase_cnt() != null){
                tvCS.setText(objDelivery.getCase_cnt()+"CS");
            } else {
                tvCS.setText("");
            }
            mContainerLL.setSelected(objDelivery.isSelected());
        }
        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.TRANSPARENT);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }


    }
    public interface OnDestinationClickListener {
        void onItemClick(SelectableDelivery delivery, int position);
    }
}

