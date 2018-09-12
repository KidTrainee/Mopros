package jp.co.ienter.mopros.adapter.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.models.MoprosDelivery;

public class PickupViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.item_selectable_pickup_list_tv_pickup_name)
    TextView mPickupNameTV;

    public PickupViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
    public void setupView(Context context, MoprosDelivery pickup, int position) {
        mPickupNameTV.setText(String.format(context.getString(R.string.pickup_store_name_format), pickup.getNonyu_name()));
    }
}
