package jp.co.ienter.mopros.fragments.deliver_record;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.activity.deliver_chart.view.ExpandView;
import jp.co.ienter.mopros.fragments.base.BaseFragment;

public class FragmentSpecialWork extends BaseFragment {

    @BindView(R.id.expandview_deliver_generate)
     ExpandView evWorkGenerate;
    @BindView(R.id.expandview_deliver_position)
     ExpandView evPositionDeliver;
    @BindView(R.id.expandview_inner_design_map)
     ExpandView evInnerDesign;
    @BindView(R.id.expandview_important_note)
     ExpandView evImportantNote;
    @BindView(R.id.expandview_deliver_position_map)
     ExpandView evPositionMap;

    public static FragmentSpecialWork newInstance() {
        
        Bundle args = new Bundle();
        
        FragmentSpecialWork fragment = new FragmentSpecialWork();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_special_work, container, false);
        ButterKnife.bind(this, fragmentView);
        init();
        return fragmentView;
    }

    private void init(){
        evWorkGenerate.setHeader(getResources().getString(R.string.table_work_generate_title));
        evPositionDeliver.setHeader(getResources().getString(R.string.table_deliver_position_title));
        evImportantNote.setHeader(getResources().getString(R.string.table_deliver_important_note_title));
        evPositionMap.setHeader(getResources().getString(R.string.table_deliver_position_map_title));
        evInnerDesign.setHeader(getResources().getString(R.string.table_deliver_inner_design_map_title));
    }


}
