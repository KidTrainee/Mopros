package jp.co.ienter.mopros.adapter.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import butterknife.BindView;
import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.models.SelectableDelivery;
import jp.co.ienter.mopros.activity.deliver_report.FragmentChooseDestination;
import jp.co.ienter.mopros.utils.AppConstants;
import jp.co.ienter.mopros.utils.Const;

/**
 * Responsible for inflate deliver views in list in {@link FragmentChooseDestination}
 */
public class SelectableDeliveryViewHolder extends DeliveryViewHolder {
    @BindView(R.id.item_destination_list_container)
    LinearLayout llContainer;
    public SelectableDeliveryViewHolder(View itemView) {
        super(itemView);
    }

    public void setupView(Context context, SelectableDelivery delivery, int position) {
        super.setupView(context, delivery, position);
        if (delivery.isDone()) {
            llContainer.setBackground(context.getResources().getDrawable(R.drawable.bg_deliver_done));
        } else if (delivery.isPending()) {
            llContainer.setBackground(context.getResources().getDrawable(R.drawable.bg_deliver_pending));
        } else {
            llContainer.setBackground(context.getResources().getDrawable(R.drawable.bg_selectable));
        }

        llContainer.setSelected(delivery.isSelected());
    }
}