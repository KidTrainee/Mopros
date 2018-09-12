package jp.co.ienter.mopros.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.interfaces.DeliveryItemClickListener;
import jp.co.ienter.mopros.interfaces.OnClickChooseListener;
import jp.co.ienter.mopros.interfaces.OnItemChoosedListener;
import jp.co.ienter.mopros.interfaces.OnSortListChangedListener;
import jp.co.ienter.mopros.interfaces.helper.ItemTouchHelperAdapter;
import jp.co.ienter.mopros.interfaces.helper.ItemTouchHelperViewHolder;
import jp.co.ienter.mopros.interfaces.helper.OnStartDragListener;
import jp.co.ienter.mopros.models.MoprosDelivery;
import jp.co.ienter.mopros.utils.Const;

/**
 * Created by thanhnv on 7/30/18.
 */

public class SortListAdapter extends RecyclerView.Adapter<SortListAdapter.ItemViewHolder> implements ItemTouchHelperAdapter{

    private ArrayList<MoprosDelivery> listSort  = new ArrayList<>();
    private Context mContext;
    private OnStartDragListener mDragStartListener;
    private DeliveryItemClickListener mListener;
    private int mCurrentSelectPosition = -1;
    private OnSortListChangedListener mListChangedListener;
    private  OnClickChooseListener mChooseListener;
    private  OnItemChoosedListener onItemChoosedListener;

    public SortListAdapter(ArrayList<MoprosDelivery> listSort, Context context,
                           OnStartDragListener dragLlistener,
                           OnSortListChangedListener listChangedListener,
                           OnClickChooseListener chooseListener, OnItemChoosedListener onItemChoosedListener){
//        this.mCustomers =  new ArrayList<>();
        this.mContext = context;
        this.listSort=listSort;
        this.mDragStartListener = dragLlistener;
        this.mListener = (DeliveryItemClickListener) context;
        this.mListChangedListener=listChangedListener;
        this.mChooseListener=chooseListener;
        this.onItemChoosedListener=onItemChoosedListener;
    }


    public void updateData(@NonNull ArrayList<MoprosDelivery> listSortDelivery) {
        listSort=new ArrayList<>();
        listSort.addAll(listSortDelivery);
        deselectItem(mCurrentSelectPosition);
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
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        final MoprosDelivery objSort = listSort.get(position);
        holder.setupView(objSort, position, mCurrentSelectPosition);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onItemClick(objSort,position);
                int tempSelect = mCurrentSelectPosition;
                if (mCurrentSelectPosition != -1) { // if there is already selected item
                    if (mCurrentSelectPosition != holder.getAdapterPosition()) {
                        deselectItem(tempSelect);
                        selectItem(holder.getAdapterPosition(),objSort.getKanryo_flg()); // select current item
                    } else {
                        deselectItem(holder.getAdapterPosition());
                    }
                } else {
                    selectItem(holder.getAdapterPosition(),objSort.getKanryo_flg());
                }
            }
        });

//        holder.tvDesignation.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                if (MotionEventCompat.getActionMasked(motionEvent) == MotionEvent.ACTION_DOWN) {
//                    mDragStartListener.onStartDrag(holder);
//                }
//                return false;
//            }
//        });
    }

    public ArrayList<MoprosDelivery> getListSort() {
        return listSort;
    }

    private void selectItem(int position, String kanryoFlag) {
        mChooseListener.onChooseItem(position);
        mCurrentSelectPosition = position;
        onItemChoosedListener.onIsChooseItem(true,position,"0",listSort.get(position));
        notifyItemChanged(position);
    }

    private void deselectItem(int pos) {
        mCurrentSelectPosition = -1;
        mChooseListener.onChooseItem(-1);
        notifyItemChanged(pos);
        onItemChoosedListener.onIsChooseItem(false,pos,"0",null);
    }


    @Override
    public int getItemCount() {
        return listSort.size();
    }

    @Override
    public void onItemDismiss(int position) {
        listSort.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(listSort, fromPosition, toPosition);
        mListChangedListener.onNoteListChanged(listSort);
        notifyItemMoved(fromPosition, toPosition);

        if (mCurrentSelectPosition == fromPosition) mCurrentSelectPosition = toPosition;
        else if (mCurrentSelectPosition == toPosition) mCurrentSelectPosition = fromPosition;
        return true;
    }

//    @Override
//    public boolean onItemMove(int fromPosition, int toPosition) {
//        if (fromPosition < toPosition) {
//            for (int i = fromPosition; i < toPosition; i++) {
//                Collections.swap(mCustomers, i, i + 1);
//            }
//        } else {
//            for (int i = fromPosition; i > toPosition; i--) {
//                Collections.swap(mCustomers, i, i - 1);
//            }
//        }
//        notifyItemMoved(fromPosition, toPosition);
//        return true;
//    }
//
//    @Override
//    public void onItemDismiss(int position) {
//        mCustomers.remove(position);
//        notifyItemRemoved(position);
//    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
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


        public void setupView(MoprosDelivery objSort, int position, int currentPos) {
            if(Const.FLAG_DESTINATION_TYPE_DELIVER.equalsIgnoreCase(objSort.getData_type())) {
                tvStoreName.setText("[配]" + objSort.getNonyu_name());
            }else{
                tvStoreName.setText("[集]" + objSort.getNonyu_name());
            }

            if(objSort.getKanryo_flg().equals("2")){
                mContainerLL.setBackgroundResource(R.drawable.bg_selectabletwo);
            } else {
                mContainerLL.setBackgroundResource(R.drawable.bg_selectable);
            }

            if(!"".equalsIgnoreCase(objSort.getShitei_time())) {
                tvDesignation.setText(objSort.getShitei_time() + "指定");
            } else {
                tvDesignation.setText("");
            }

            if(objSort.getCase_cnt() != null){
                tvCS.setText(objSort.getCase_cnt()+"CS");
            } else {
                tvCS.setText("");
            }
            mContainerLL.setSelected(currentPos == position);
//            tvLoad.setText(objSort.getWeight_kg());
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
}
