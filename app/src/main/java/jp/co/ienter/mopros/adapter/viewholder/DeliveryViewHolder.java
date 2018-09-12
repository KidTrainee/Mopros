package jp.co.ienter.mopros.adapter.viewholder;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.models.MoprosDelivery;
import jp.co.ienter.mopros.utils.Const;


public class DeliveryViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tvDestinationName) protected TextView tvStoreName;
    @BindView(R.id.tvDesignation) protected TextView tvDesignation;
    @BindView(R.id.tvCS) protected TextView tvCS;

    public DeliveryViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setupView(Context context, MoprosDelivery delivery, int position) {
        // TODO: 8/6/18 don't have nonyu_address
        if (delivery.getData_type().equals(Const.FLAG_DESTINATION_TYPE_DELIVER)) {
            tvStoreName.setText(String.format(context.getString(R.string.deliver_store_name_format), delivery.getNonyu_name()));
        }

        String shiteiTime = delivery.getShitei_time();
        if(!TextUtils.isEmpty(shiteiTime)) {
            tvDesignation.setText(context.getString(R.string.txt_designation, delivery.getShitei_time()));
            tvDesignation.setVisibility(View.VISIBLE);
        } else {
            tvDesignation.setVisibility(View.INVISIBLE);
        }
        tvCS.setText(context.getString(R.string.txt_cs_format, delivery.getCase_cnt()));
    }
}
