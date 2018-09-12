package jp.co.ienter.mopros.fragments.deliver_record;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alexvasilkov.gestures.views.GestureImageView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.activity.deliver_chart.model.DeliverChartModel;
import jp.co.ienter.mopros.activity.deliver_chart.view.ExpandView;
import jp.co.ienter.mopros.fragments.base.BaseFragment;

public class FragmentSurroundingMap extends BaseFragment {

    @BindView(R.id.expandview_surround_map)
    ExpandView evSurroundMap;

    private GestureImageView imgMap;

    public static FragmentSurroundingMap newInstance() {

        Bundle args = new Bundle();
        FragmentSurroundingMap fragment = new FragmentSurroundingMap();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_surrounding_map, container, false);
        ButterKnife.bind(this, fragmentView);
        init();
        return fragmentView;
    }

    private void init() {
        evSurroundMap.setHeader(getResources().getString(R.string.surrounding_map));
        evSurroundMap.disableExpandButton(true);

    }

    @Override
    public void notifyDataChanged(DeliverChartModel object) {
        super.notifyDataChanged(object);
        inflateDataToView(object);
    }

    private void inflateDataToView(DeliverChartModel object) {
        if (object.getResult().get(0).getShuhenMap().isEmpty()) {
            return;
        }
        try {
            evSurroundMap.clearContent();
            imgMap = new GestureImageView(getContext());
            imgMap.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            imgMap.getController().getSettings()
                    .setMaxZoom(5f);
            Picasso.get().load(object.getResult().get(0).getShuhenMap()).into(imgMap);
            evSurroundMap.addRowToTableLayout(imgMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
