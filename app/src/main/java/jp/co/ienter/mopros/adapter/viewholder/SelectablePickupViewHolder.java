package jp.co.ienter.mopros.adapter.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import butterknife.BindView;
import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.models.SelectableDelivery;
import jp.co.ienter.mopros.activity.deliver_report.FragmentChooseDestination;
/**
 * Responsible for inflate pick-up views in list in {@link FragmentChooseDestination}
 */
public class SelectablePickupViewHolder extends PickupViewHolder {
    @BindView(R.id.item_selectable_pickup_list_ll_container)
    LinearLayout mContainerLL;

    public SelectablePickupViewHolder(View itemView) {
        super(itemView);
    }

    public void setupView(Context context, SelectableDelivery pickup, int position) {
        super.setupView(context, pickup, position);
        if (pickup.isDone()) {
            mContainerLL.setBackground(context.getResources().getDrawable(R.drawable.bg_deliver_done));
        } else if (pickup.isPending()) {
            mContainerLL.setBackground(context.getResources().getDrawable(R.drawable.bg_deliver_pending));
        } else {
            mContainerLL.setBackground(context.getResources().getDrawable(R.drawable.bg_selectable));
        }

        mContainerLL.setSelected(pickup.isSelected());
    }
}
