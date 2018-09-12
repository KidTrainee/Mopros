package jp.co.ienter.mopros.fragments.deliver_record;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.activity.deliver_chart.model.DeliverChartModel;
import jp.co.ienter.mopros.activity.deliver_chart.view.ExpandView;
import jp.co.ienter.mopros.fragments.base.BaseFragment;
import jp.co.ienter.mopros.utils.TableHelper;


public class FragmentBasicInfo extends BaseFragment {

    @BindView(R.id.expandview_basic_info)
    ExpandView evBasicInfo;

    public static FragmentBasicInfo newInstance() {

        Bundle args = new Bundle();
        FragmentBasicInfo fragment = new FragmentBasicInfo();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_basic_info, container, false);
        ButterKnife.bind(this, fragmentView);
        initUI();
        return fragmentView;
    }

    private void initUI() {
        evBasicInfo.setHeader(getResources().getString(R.string.basic_info));
        evBasicInfo.disableExpandButton(true);
    }

    @Override
    public void notifyDataChanged(DeliverChartModel object) {
        super.notifyDataChanged(object);
        inflateData(object);
    }

    private void inflateData(DeliverChartModel object) {
        TableHelper mTableHelper = new TableHelper(getContext());
        TableHelper.ItemRow[] items;
        TableHelper.ItemRow key = mTableHelper.new ItemRow();
        TableHelper.ItemRow value = mTableHelper.new ItemRow();
        evBasicInfo.clearContent();

        //add firt line to tablex
        evBasicInfo.addRowToTableLayout(mTableHelper
                .createRowLine(getResources().getColor(R.color.table_colurm_color)));

        //add basicmodel.shopType
        try {
            key.setContent(getResources().getString(R.string.shop_type));
            key.setBgColor(getResources().getColor(R.color.table_row_key_bg_color));
            key.setTextColor(getResources().getColor(R.color.table_text_color));
            value.setContent(object.getResult().get(0).getShopType());
            value.setBgColor(getResources().getColor(R.color.table_row_value_bg_color));
            value.setTextColor(getResources().getColor(R.color.table_text_color));
            items = new TableHelper.ItemRow[]{key, value};
            evBasicInfo.addRowToTableLayout(mTableHelper.createRow(items,
                    getResources().getColor(R.color.table_colurm_color)));
            evBasicInfo.addRowToTableLayout(mTableHelper
                    .createRowLine(getResources().getColor(R.color.table_colurm_color)));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //add basicmodel.noHinKind
        try {
            key.setContent(getResources().getString(R.string.nohin_kind));
            key.setBgColor(getResources().getColor(R.color.table_row_key_bg_color));
            key.setTextColor(getResources().getColor(R.color.table_text_color));
            value.setContent(object.getResult().get(0).getNohinKind());
            value.setBgColor(getResources().getColor(R.color.table_row_value_bg_color));
            value.setTextColor(getResources().getColor(R.color.table_text_color));
            items = new TableHelper.ItemRow[]{key, value};
            evBasicInfo.addRowToTableLayout(mTableHelper.createRow(items,
                    getResources().getColor(R.color.table_colurm_color)));
            evBasicInfo.addRowToTableLayout(mTableHelper
                    .createRowLine(getResources().getColor(R.color.table_colurm_color)));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //add basicmodel.enterKind
        try {
            key.setContent(getResources().getString(R.string.enter_kind));
            key.setBgColor(getResources().getColor(R.color.table_row_key_bg_color));
            key.setTextColor(getResources().getColor(R.color.table_text_color));
            value.setContent(object.getResult().get(0).getEnterKind());
            value.setBgColor(getResources().getColor(R.color.table_row_value_bg_color));
            value.setTextColor(getResources().getColor(R.color.table_text_color));
            items = new TableHelper.ItemRow[]{key, value};
            evBasicInfo.addRowToTableLayout(mTableHelper.createRow(items,
                    getResources().getColor(R.color.table_colurm_color)));
            evBasicInfo.addRowToTableLayout(mTableHelper
                    .createRowLine(getResources().getColor(R.color.table_colurm_color)));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //add basicmodel.SejoKasho
        try {
            key.setContent(getResources().getString(R.string.sejo_kasho));
            key.setBgColor(getResources().getColor(R.color.table_row_key_bg_color));
            key.setTextColor(getResources().getColor(R.color.table_text_color));
            value.setContent(object.getResult().get(0).getSejoKasho());
            value.setBgColor(getResources().getColor(R.color.table_row_value_bg_color));
            value.setTextColor(getResources().getColor(R.color.table_text_color));
            items = new TableHelper.ItemRow[]{key, value};
            evBasicInfo.addRowToTableLayout(mTableHelper.createRow(items,
                    getResources().getColor(R.color.table_colurm_color)));
            evBasicInfo.addRowToTableLayout(mTableHelper
                    .createRowLine(getResources().getColor(R.color.table_colurm_color)));
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        //add basicmodel.niukeKigen
        try {
            key.setContent(getResources().getString(R.string.niuke_kigen));
            key.setBgColor(getResources().getColor(R.color.table_row_key_bg_color));
            key.setTextColor(getResources().getColor(R.color.table_text_color));
            value.setContent(object.getResult().get(0).getNiukeGenkai());
            value.setBgColor(getResources().getColor(R.color.table_row_value_bg_color));
            value.setTextColor(getResources().getColor(R.color.table_text_color));
            items = new TableHelper.ItemRow[]{key, value};
            evBasicInfo.addRowToTableLayout(mTableHelper.createRow(items,
                    getResources().getColor(R.color.table_colurm_color)));
            evBasicInfo.addRowToTableLayout(mTableHelper
                    .createRowLine(getResources().getColor(R.color.table_colurm_color)));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //add basicmodel.SharyoKisei
        try {
            key.setContent(getResources().getString(R.string.sharyo_kisei));
            key.setBgColor(getResources().getColor(R.color.table_row_key_bg_color));
            key.setTextColor(getResources().getColor(R.color.table_text_color));
            value.setContent(object.getResult().get(0).getSharyoKisei());
            value.setBgColor(getResources().getColor(R.color.table_row_value_bg_color));
            value.setTextColor(getResources().getColor(R.color.table_text_color));
            items = new TableHelper.ItemRow[]{key, value};
            evBasicInfo.addRowToTableLayout(mTableHelper.createRow(items,
                    getResources().getColor(R.color.table_colurm_color)));
            evBasicInfo.addRowToTableLayout(mTableHelper
                    .createRowLine(getResources().getColor(R.color.table_colurm_color)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * add basicmodel.securitySystem
         * 0:なし　1：ありの表記
         */
        try {
            key.setContent(getResources().getString(R.string.security_system));
            key.setBgColor(getResources().getColor(R.color.table_row_key_bg_color));
            key.setTextColor(getResources().getColor(R.color.table_text_color));
            String textValue = "";
            if (object.getResult().get(0).getSecurityKbn() == 1) {
                textValue = getResources().getString(R.string.security_system_yes);
            } else if (object.getResult().get(0).getSecurityKbn() == 0) {
                textValue = getResources().getString(R.string.security_system_no);
            }
            value.setContent(textValue);
            value.setBgColor(getResources().getColor(R.color.table_row_value_bg_color));
            value.setTextColor(getResources().getColor(R.color.table_text_color));
            items = new TableHelper.ItemRow[]{key, value};
            evBasicInfo.addRowToTableLayout(mTableHelper.createRow(items,
                    getResources().getColor(R.color.table_colurm_color)));
            evBasicInfo.addRowToTableLayout(mTableHelper
                    .createRowLine(getResources().getColor(R.color.table_colurm_color)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
