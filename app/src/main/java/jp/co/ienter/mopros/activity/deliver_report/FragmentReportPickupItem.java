package jp.co.ienter.mopros.activity.deliver_report;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.activity.deliver_report.views.CustomizePalettePickUpItem;

public class FragmentReportPickupItem extends FragmentReportPalette {

    //@BindView(R.id.layout_item_palette)
    LinearLayout layoutPalettes;
    private Button btnDone;


    public static FragmentReportPickupItem newInstance() {

        Bundle args = new Bundle();

        FragmentReportPickupItem fragment = new FragmentReportPickupItem();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_report_pick_up_item, container, false);
        layoutPalettes = view.findViewById(R.id.layout_pickup_item);
        btnDone = view.findViewById(R.id.activity_report_palette_btn_done);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFragmentDeliverReport.gotoPreviousFragment();
            }
        });

        setupUI();
        return view;
    }

    private void setupUI() {
        loadPaletteData("2");
    }

    @Override
    protected  void onAddShukaData(String label){
        CustomizePalettePickUpItem item = new CustomizePalettePickUpItem(mContext);
        item.setLabel(label);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutPalettes.addView(item, layoutParams);
    }

//    @OnClick(R.id.activity_report_palette_btn_done)
    public void onClick() {
    }

}
