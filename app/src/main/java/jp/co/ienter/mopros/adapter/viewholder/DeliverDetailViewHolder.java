package jp.co.ienter.mopros.adapter.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.models.MoprosDelivery;

/**
 * Take control list in {@link jp.co.ienter.mopros.activity.deliver_report.DeliverReportActivity}
 */
public class DeliverDetailViewHolder extends DeliveryViewHolder {

    @BindView(R.id.tvAddress) protected TextView tvAddress;
    @BindView(R.id.tvLoad) protected  TextView tvLoad;

    public DeliverDetailViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setupView(Context context, MoprosDelivery delivery, int position) {
        super.setupView(context, delivery, position);
        tvAddress.setText(delivery.getNonyu_address());
        if (tvAddress.getVisibility()!=View.VISIBLE) tvAddress.setVisibility(View.VISIBLE);
//        tvLoad.setText(context.getString(R.string.txt_weight_format, Math.round(Float.parseFloat(delivery.getWeight_kg()))));
    }
}
