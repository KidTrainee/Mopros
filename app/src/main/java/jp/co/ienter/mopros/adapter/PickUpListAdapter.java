package jp.co.ienter.mopros.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.models.MoprosCargo;

/**
 * Created by thanhnv on 7/30/18.
 */

public class PickUpListAdapter extends RecyclerView.Adapter<PickUpListAdapter.ItemViewHolder> {

    private List<MoprosCargo> listCargo  = new ArrayList<>();
    private Context mContext;
    private CargoItemClickListener mListener;
    private int mCurrentSelectPosition = -1;

    public PickUpListAdapter(List<MoprosCargo> listCargo, Context context){
//        this.mCustomers =  new ArrayList<>();
        this.mContext = context;
        this.listCargo=listCargo;
        mListener = (CargoItemClickListener) context;
    }

    public void updateData(@NonNull List<MoprosCargo> customers) {
        listCargo.clear();
        listCargo.addAll(customers);
        notifyDataSetChanged();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from
                (parent.getContext()).inflate(R.layout.item_selectable_ship_list, parent, false);
        ItemViewHolder viewHolder = new ItemViewHolder(rowView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {
        final MoprosCargo objCargo = listCargo.get(position);
        holder.setupView(objCargo, position,mCurrentSelectPosition);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tempSelect = mCurrentSelectPosition;
                if (mCurrentSelectPosition != -1) { // if there is already selected item
                    if (mCurrentSelectPosition != holder.getAdapterPosition()) {
                        deselectItem(tempSelect);
                        selectItem(holder.getAdapterPosition(), objCargo); // select current item

                    } else {
                        deselectItem(holder.getAdapterPosition());
                    }
                } else {
                    selectItem(holder.getAdapterPosition(), objCargo);
                }
            }
        });
    }

    private void selectItem(int position, MoprosCargo destination) {
        mCurrentSelectPosition = position;
        mListener.onItemClick(destination,position);
        notifyItemChanged(position);
    }

    private void deselectItem(int pos) {
        mCurrentSelectPosition = -1;
        notifyItemChanged(pos);
    }


    @Override
    public int getItemCount() {
        return listCargo.size();
    }


    public static class ItemViewHolder extends RecyclerView.ViewHolder  {
        @BindView(R.id.tvDestinationName) TextView tvStoreName;
        @BindView(R.id.tvDesignation) TextView tvDesignation;
        @BindView(R.id.tvCS) TextView tvCS;
        @BindView(R.id.tvLoad) TextView tvLoad;
        @BindView(R.id.item_destination_list_container)
        LinearLayout mContainerLL;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        public void setupView( MoprosCargo destination, int position, int currentPos) {
            tvStoreName.setText(destination.getShuka_name());
//            tvDesignation.setText(destination.getMessage());
//            tvDesignation.setText(destination.getShuka_code());
//            tvCS.setText(destination.getSyaryo_code());
//            tvLoad.setText(destination.getWeight_kg());
            mContainerLL.setSelected(currentPos == position);
        }

    }

    public interface CargoItemClickListener {
        void onItemClick(MoprosCargo delivery, int position);
    }
}
