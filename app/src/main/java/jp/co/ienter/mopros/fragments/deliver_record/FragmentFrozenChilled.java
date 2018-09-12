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

public class FragmentFrozenChilled extends BaseFragment {

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

    public static FragmentFrozenChilled newInstance() {
        
        Bundle args = new Bundle();
        
        FragmentFrozenChilled fragment = new FragmentFrozenChilled();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_frozen_chilled, container, false);
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

//    private void inflateDataToView(ImportantWorkModel importantWorkModel){
//        inflateWorkGenerate(importantWorkModel.getDeliverWorkGenerateModel());
//        inflateDeliverPosition(importantWorkModel.getPositionDeliverModel());
//        inflateImportantNote(importantWorkModel.getImportantNoteModel());
//        inflatePositionMap(importantWorkModel.getDeliverPositionMapModel());
//        inflateInnerDesignMap(importantWorkModel.getInnerDesignMapModel());
//    }
//
//    private void inflateWorkGenerate(DeliverWorkGenerateModel object){
//        TableHelper mTableHelper = new TableHelper(getContext());
//        TableHelper.ItemRow [] items;
//        TableHelper.ItemRow key = mTableHelper.new ItemRow();
//        TableHelper.ItemRow value = mTableHelper.new ItemRow();
//        String textValue = "";
//
//        evWorkGenerate.clearContent();
//
//        //add firt line to tablex
//        evWorkGenerate.addRowToTableLayout(mTableHelper
//                .createRowLine(getResources().getColor(R.color.table_colurm_color)));
//
//        //add DeliverWorkGenerateModel.nohinWaitKbn
//        /**
//         * 0:なし　1：ありの表記
//         */
//        key.setContent(getResources().getString(R.string.table_work_generate_item_nohinwaitkbn));
//        key.setBgColor(getResources().getColor(R.color.table_row_key_bg_color));
//        key.setTextColor(getResources().getColor(R.color.table_text_color));
//        if(object.getNohinWaitKbn() == 1){
//            textValue = getResources().getString(R.string.security_system_yes);
//        } else if(object.getNohinWaitKbn() == 0) {
//            textValue = getResources().getString(R.string.security_system_no);
//        }
//        value.setContent(textValue);
//        value.setBgColor(getResources().getColor(R.color.table_row_value_bg_color));
//        value.setTextColor(getResources().getColor(R.color.table_text_color));
//        items = new TableHelper.ItemRow[] {key, value};
//        evWorkGenerate.addRowToTableLayout(mTableHelper.createRow(items,
//                getResources().getColor(R.color.table_colurm_color)));
//        evWorkGenerate.addRowToTableLayout(mTableHelper
//                .createRowLine(getResources().getColor(R.color.table_colurm_color)));
//
//        //add DeliverWorkGenerateModel.nohinShiteiKbn
//        /**
//         * 0:なし　1：ありの表記
//         */
//        key.setContent(getResources().getString(R.string.table_work_generate_item_nohinshiteikbn));
//        key.setBgColor(getResources().getColor(R.color.table_row_key_bg_color));
//        key.setTextColor(getResources().getColor(R.color.table_text_color));
//        if(object.getNohinShiteiKbn() == 1){
//            textValue = getResources().getString(R.string.security_system_yes);
//        } else if(object.getNohinShiteiKbn() == 0) {
//            textValue = getResources().getString(R.string.security_system_no);
//        } else{
//            textValue = "";
//        }
//        value.setContent(textValue);
//        value.setBgColor(getResources().getColor(R.color.table_row_value_bg_color));
//        value.setTextColor(getResources().getColor(R.color.table_text_color));
//        items = new TableHelper.ItemRow[] {key, value};
//        evWorkGenerate.addRowToTableLayout(mTableHelper.createRow(items,
//                getResources().getColor(R.color.table_colurm_color)));
//        evWorkGenerate.addRowToTableLayout(mTableHelper
//                .createRowLine(getResources().getColor(R.color.table_colurm_color)));
//
//        //add DeliverWorkGenerateModel.pLShiteiKbn
//        /**
//         * 0:なし　1：ありの表記
//         */
////        key.setContent(getResources().getString(R.string.table_work_generate_item_plshiteikbn));
////        key.setBgColor(getResources().getColor(R.color.table_row_key_bg_color));
////        key.setTextColor(getResources().getColor(R.color.table_text_color));
////        if(object.getpLShiteiKbn() == 1){
////            textValue = getResources().getString(R.string.security_system_yes);
////        } else if(object.getpLShiteiKbn() == 0) {
////            textValue = getResources().getString(R.string.security_system_no);
////        } else{
////            textValue = "";
////        }
////        value.setContent(textValue);
////        value.setBgColor(getResources().getColor(R.color.table_row_value_bg_color));
////        value.setTextColor(getResources().getColor(R.color.table_text_color));
////        items = new TableHelper.ItemRow[] {key, value};
////        evWorkGenerate.addRowToTableLayout(mTableHelper.createRow(items,
////                getResources().getColor(R.color.table_colurm_color)));
////        evWorkGenerate.addRowToTableLayout(mTableHelper
////                .createRowLine(getResources().getColor(R.color.table_colurm_color)));
//
//        //add DeliverWorkGenerateModel.tanaSagyoKbn
//        /**
//         * 0:なし　1：ありの表記
//         */
//        key.setContent(getResources().getString(R.string.table_work_generate_item_tanasagyokbn));
//        key.setBgColor(getResources().getColor(R.color.table_row_key_bg_color));
//        key.setTextColor(getResources().getColor(R.color.table_text_color));
//        if(object.getTanaSagyoKbn() == 1){
//            textValue = getResources().getString(R.string.security_system_yes);
//        } else if(object.getTanaSagyoKbn() == 0) {
//            textValue = getResources().getString(R.string.security_system_no);
//        } else{
//            textValue = "";
//        }
//        value.setContent(textValue);
//        value.setBgColor(getResources().getColor(R.color.table_row_value_bg_color));
//        value.setTextColor(getResources().getColor(R.color.table_text_color));
//        items = new TableHelper.ItemRow[] {key, value};
//        evWorkGenerate.addRowToTableLayout(mTableHelper.createRow(items,
//                getResources().getColor(R.color.table_colurm_color)));
//        evWorkGenerate.addRowToTableLayout(mTableHelper
//                .createRowLine(getResources().getColor(R.color.table_colurm_color)));
//
//
//        //add DeliverWorkGenerateModel.liftSagyoKbn
//        /**
//         * 0:なし　1：ありの表記
//         */
//        key.setContent(getResources().getString(R.string.table_work_generate_item_liftsagyokbn));
//        key.setBgColor(getResources().getColor(R.color.table_row_key_bg_color));
//        key.setTextColor(getResources().getColor(R.color.table_text_color));
//        if(object.getLiftSagyoKbn() == 1){
//            textValue = getResources().getString(R.string.security_system_yes);
//        } else if(object.getLiftSagyoKbn() == 0) {
//            textValue = getResources().getString(R.string.security_system_no);
//        } else{
//            textValue = "";
//        }
//        value.setContent(textValue);
//        value.setBgColor(getResources().getColor(R.color.table_row_value_bg_color));
//        value.setTextColor(getResources().getColor(R.color.table_text_color));
//        items = new TableHelper.ItemRow[] {key, value};
//        evWorkGenerate.addRowToTableLayout(mTableHelper.createRow(items,
//                getResources().getColor(R.color.table_colurm_color)));
//        evWorkGenerate.addRowToTableLayout(mTableHelper
//                .createRowLine(getResources().getColor(R.color.table_colurm_color)));
//
//        /**
//         * add DeliverWorkGenerateModel.bandSagyoKbn
//         * 0:なし　1：ありの表記
//         */
//        key.setContent(getResources().getString(R.string.table_work_generate_item_bandsagyokbn));
//        key.setBgColor(getResources().getColor(R.color.table_row_key_bg_color));
//        key.setTextColor(getResources().getColor(R.color.table_text_color));
//        if(object.getBandSagyoKbn() == 1){
//            textValue = getResources().getString(R.string.security_system_yes);
//        } else if(object.getBandSagyoKbn() == 0) {
//            textValue = getResources().getString(R.string.security_system_no);
//        } else{
//            textValue = "";
//        }
//        value.setContent(textValue);
//        value.setBgColor(getResources().getColor(R.color.table_row_value_bg_color));
//        value.setTextColor(getResources().getColor(R.color.table_text_color));
//        items = new TableHelper.ItemRow[] {key, value};
//        evWorkGenerate.addRowToTableLayout(mTableHelper.createRow(items,
//                getResources().getColor(R.color.table_colurm_color)));
//        evWorkGenerate.addRowToTableLayout(mTableHelper
//                .createRowLine(getResources().getColor(R.color.table_colurm_color)));
//
//        /**
//         * add DeliverWorkGenerateModel.coinParkKbn
//         * 0:なし　1：ありの表記
//         */
//        key.setContent(getResources().getString(R.string.table_work_generate_item_coinparkkbn));
//        key.setBgColor(getResources().getColor(R.color.table_row_key_bg_color));
//        key.setTextColor(getResources().getColor(R.color.table_text_color));
//        if(object.getCoinParkKbn() == 1){
//            textValue = getResources().getString(R.string.security_system_yes);
//        } else if(object.getCoinParkKbn() == 0) {
//            textValue = getResources().getString(R.string.security_system_no);
//        } else{
//            textValue = "";
//        }
//        value.setContent(textValue);
//        value.setBgColor(getResources().getColor(R.color.table_row_value_bg_color));
//        value.setTextColor(getResources().getColor(R.color.table_text_color));
//        items = new TableHelper.ItemRow[] {key, value};
//        evWorkGenerate.addRowToTableLayout(mTableHelper.createRow(items,
//                getResources().getColor(R.color.table_colurm_color)));
//        evWorkGenerate.addRowToTableLayout(mTableHelper
//                .createRowLine(getResources().getColor(R.color.table_colurm_color)));
//    }
//
//    private void inflateDeliverPosition(DeliverPositionModel object){
//
//        ArrayList<String> nohinPlanArr = object.getNohinPlanArr();
//        if(nohinPlanArr != null && nohinPlanArr.size() > 0){
//            TableHelper mTableHelper = new TableHelper(getContext());
//            TableHelper.ItemRow [] items;
//            TableHelper.ItemRow key = mTableHelper.new ItemRow();
//            TableHelper.ItemRow value = mTableHelper.new ItemRow();
//
//            evPositionDeliver.clearContent();
//            //add firt line to tablex
//            evPositionDeliver.addRowToTableLayout(mTableHelper
//                    .createRowLine(getResources().getColor(R.color.table_colurm_color)));
//
//            int index = 1;
//            for (String content: nohinPlanArr) {
//                key.setContent("" + index);
//                key.setBgColor(getResources().getColor(R.color.table_row_key_bg_color));
//                key.setTextColor(getResources().getColor(R.color.table_text_color));
//                value.setContent(content);
//                value.setBgColor(getResources().getColor(R.color.table_row_value_bg_color));
//                value.setTextColor(getResources().getColor(R.color.table_text_color));
//                items = new TableHelper.ItemRow[] {key, value};
//                evPositionDeliver.addRowToTableLayout(mTableHelper.createRow(items,
//                        getResources().getColor(R.color.table_colurm_color)));
//                evPositionDeliver.addRowToTableLayout(mTableHelper
//                        .createRowLine(getResources().getColor(R.color.table_colurm_color)));
//                index++;
//            }
//
//        }
//
//    }
//
//    private void inflateImportantNote(ImportantNoteModel object){
//        ArrayList<String> chuiJikoArr = object.getChuiJikoArr();
//        if(chuiJikoArr != null && chuiJikoArr.size() > 0){
//            TableHelper mTableHelper = new TableHelper(getContext());
//            TableHelper.ItemRow [] items;
//            TableHelper.ItemRow key = mTableHelper.new ItemRow();
//
//            evImportantNote.clearContent();
//            //add firt line to tablex
//            evImportantNote.addRowToTableLayout(mTableHelper
//                    .createRowLine(getResources().getColor(R.color.table_colurm_color)));
//
//            int index = 1;
//            for (String content: chuiJikoArr) {
//                key.setContent(content);
//                key.setBgColor(getResources().getColor(R.color.table_row_value_bg_color));
//                key.setTextColor(getResources().getColor(R.color.table_text_color));
//                items = new TableHelper.ItemRow[] {key};
//                evImportantNote.addRowToTableLayout(mTableHelper.createRow(items,
//                        getResources().getColor(R.color.table_colurm_color)));
//                evImportantNote.addRowToTableLayout(mTableHelper
//                        .createRowLine(getResources().getColor(R.color.table_colurm_color)));
//                index++;
//            }
//
//        }
//    }
//
//    private void inflatePositionMap(DeliverPositionMapModel object){
//        ArrayList<String> nohinPlanPicArr = object.getNohinPlanPicArr();
//        if(nohinPlanPicArr != null && nohinPlanPicArr.size() > 0){
//            TableHelper mTableHelper = new TableHelper(getContext());
//            evPositionMap.clearContent();
//            //add firt line to tablex
//            evPositionMap.addRowToTableLayout(mTableHelper
//                    .createRowLine(getResources().getColor(R.color.table_colurm_color)));
//            int index = 1;
//            for(String imgUrl : nohinPlanPicArr){
//                String key = getResources().getString(R.string.table_deliver_position_map_key_position_map) + index;
//                evPositionMap.addRowToTableLayout(mTableHelper.createRow(key,imgUrl,
//                        getResources().getColor(R.color.table_text_color),
//                        getResources().getColor(R.color.table_row_value_bg_color),
//                        getResources().getColor(R.color.table_colurm_color)));
//                evPositionMap.addRowToTableLayout(mTableHelper
//                        .createRowLine(getResources().getColor(R.color.table_colurm_color)));
//                index++;
//            }
//        }
//    }
//
//    private void inflateInnerDesignMap(InnerDesignMapModel object){
//        evInnerDesign.clearContent();
//        ImageView innerDesignMap = new ImageView(getContext());
//        innerDesignMap.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT));
//        Picasso.get().load(object.getKonaiMap()).into(innerDesignMap);
//        evInnerDesign.addRowToTableLayout(innerDesignMap);
//    }

}
