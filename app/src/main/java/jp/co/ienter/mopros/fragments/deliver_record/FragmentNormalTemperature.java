package jp.co.ienter.mopros.fragments.deliver_record;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alexvasilkov.gestures.views.GestureImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.co.ienter.mopros.R;
import jp.co.ienter.mopros.activity.deliver_chart.model.DeliverChartModel;
import jp.co.ienter.mopros.activity.deliver_chart.view.ExpandView;
import jp.co.ienter.mopros.fragments.base.BaseFragment;
import jp.co.ienter.mopros.utils.TableHelper;

public class FragmentNormalTemperature extends BaseFragment {

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

    public static FragmentNormalTemperature newInstance() {

        Bundle args = new Bundle();

        FragmentNormalTemperature fragment = new FragmentNormalTemperature();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_normal_temperature, container, false);
        ButterKnife.bind(this, fragmentView);
        init();
        return fragmentView;
    }

    private void init() {
        evWorkGenerate.setHeader(getResources().getString(R.string.table_work_generate_title));
        evPositionDeliver.setHeader(getResources().getString(R.string.table_deliver_position_title));
        evImportantNote.setHeader(getResources().getString(R.string.table_deliver_important_note_title));
        evPositionMap.setHeader(getResources().getString(R.string.table_deliver_position_map_title));
        evInnerDesign.setHeader(getResources().getString(R.string.table_deliver_inner_design_map_title));
    }

    @Override
    public void notifyDataChanged(DeliverChartModel object) {
        super.notifyDataChanged(object);
        inflateWorkGenerate(object);
        inflateDeliverPosition(object);
        inflateImportantNote(object);
        inflatePositionMap(object);
        inflateInnerDesignMap(object);
    }

    private void inflateWorkGenerate(DeliverChartModel object) {
        TableHelper mTableHelper = new TableHelper(getContext());
        TableHelper.ItemRow[] items;
        TableHelper.ItemRow key = mTableHelper.new ItemRow();
        TableHelper.ItemRow value = mTableHelper.new ItemRow();
        String textValue = "";

        evWorkGenerate.clearContent();

        //add firt line to tablex
        evWorkGenerate.addRowToTableLayout(mTableHelper
                .createRowLine(getResources().getColor(R.color.table_colurm_color)));

        //add DeliverWorkGenerateModel.nohinWaitKbn
        /**
         * 0:なし　1：ありの表記
         */
        try {
            key.setContent(getResources().getString(R.string.table_work_generate_item_nohinwaitkbn));
            key.setBgColor(getResources().getColor(R.color.table_row_key_bg_color));
            key.setTextColor(getResources().getColor(R.color.table_text_color));
            if (object.getResult().get(0).getDetailItem().get(0).getNohinWaitKbn() == 1) {
                textValue = getResources().getString(R.string.security_system_yes);
            } else if (object.getResult().get(0).getDetailItem().get(0).getNohinWaitKbn() == 0) {
                textValue = getResources().getString(R.string.security_system_no);
            }
            value.setContent(textValue);
            value.setBgColor(getResources().getColor(R.color.table_row_value_bg_color));
            value.setTextColor(getResources().getColor(R.color.table_text_color));
            items = new TableHelper.ItemRow[]{key, value};
            evWorkGenerate.addRowToTableLayout(mTableHelper.createRow(items,
                    getResources().getColor(R.color.table_colurm_color)));
            evWorkGenerate.addRowToTableLayout(mTableHelper
                    .createRowLine(getResources().getColor(R.color.table_colurm_color)));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //add DeliverWorkGenerateModel.nohinShiteiKbn
        /**
         * 0:なし　1：ありの表記
         */
        try {
            key.setContent(getResources().getString(R.string.table_work_generate_item_nohinshiteikbn));
            key.setBgColor(getResources().getColor(R.color.table_row_key_bg_color));
            key.setTextColor(getResources().getColor(R.color.table_text_color));
            if (object.getResult().get(0).getDetailItem().get(0).getNohinShiteiKbn() == 1) {
                textValue = getResources().getString(R.string.security_system_yes);
            } else if (object.getResult().get(0).getDetailItem().get(0).getNohinShiteiKbn() == 0) {
                textValue = getResources().getString(R.string.security_system_no);
            } else {
                textValue = "";
            }
            value.setContent(textValue);
            value.setBgColor(getResources().getColor(R.color.table_row_value_bg_color));
            value.setTextColor(getResources().getColor(R.color.table_text_color));
            items = new TableHelper.ItemRow[]{key, value};
            evWorkGenerate.addRowToTableLayout(mTableHelper.createRow(items,
                    getResources().getColor(R.color.table_colurm_color)));
            evWorkGenerate.addRowToTableLayout(mTableHelper
                    .createRowLine(getResources().getColor(R.color.table_colurm_color)));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //add DeliverWorkGenerateModel.pLShiteiKbn
        /**
         * 0:なし　1：ありの表記
         */
//        key.setContent(getResources().getString(R.string.table_work_generate_item_plshiteikbn));
//        key.setBgColor(getResources().getColor(R.color.table_row_key_bg_color));
//        key.setTextColor(getResources().getColor(R.color.table_text_color));
//        if(object.getpLShiteiKbn() == 1){
//            textValue = getResources().getString(R.string.security_system_yes);
//        } else if(object.getpLShiteiKbn() == 0) {
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

        //add DeliverWorkGenerateModel.tanaSagyoKbn
        /**
         * 0:なし　1：ありの表記
         */
        try {
            key.setContent(getResources().getString(R.string.table_work_generate_item_tanasagyokbn));
            key.setBgColor(getResources().getColor(R.color.table_row_key_bg_color));
            key.setTextColor(getResources().getColor(R.color.table_text_color));
            if (object.getResult().get(0).getDetailItem().get(0).getTanaSagyoKbn() == 1) {
                textValue = getResources().getString(R.string.security_system_yes);
            } else if (object.getResult().get(0).getDetailItem().get(0).getTanaSagyoKbn() == 0) {
                textValue = getResources().getString(R.string.security_system_no);
            } else {
                textValue = "";
            }
            value.setContent(textValue);
            value.setBgColor(getResources().getColor(R.color.table_row_value_bg_color));
            value.setTextColor(getResources().getColor(R.color.table_text_color));
            items = new TableHelper.ItemRow[]{key, value};
            evWorkGenerate.addRowToTableLayout(mTableHelper.createRow(items,
                    getResources().getColor(R.color.table_colurm_color)));
            evWorkGenerate.addRowToTableLayout(mTableHelper
                    .createRowLine(getResources().getColor(R.color.table_colurm_color)));
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        //add DeliverWorkGenerateModel.liftSagyoKbn
        /**
         * 0:なし　1：ありの表記
         */
        try {
            key.setContent(getResources().getString(R.string.table_work_generate_item_liftsagyokbn));
            key.setBgColor(getResources().getColor(R.color.table_row_key_bg_color));
            key.setTextColor(getResources().getColor(R.color.table_text_color));
            if (object.getResult().get(0).getDetailItem().get(0).getLiftSagyoKbn() == 1) {
                textValue = getResources().getString(R.string.security_system_yes);
            } else if (object.getResult().get(0).getDetailItem().get(0).getLiftSagyoKbn() == 0) {
                textValue = getResources().getString(R.string.security_system_no);
            } else {
                textValue = "";
            }
            value.setContent(textValue);
            value.setBgColor(getResources().getColor(R.color.table_row_value_bg_color));
            value.setTextColor(getResources().getColor(R.color.table_text_color));
            items = new TableHelper.ItemRow[]{key, value};
            evWorkGenerate.addRowToTableLayout(mTableHelper.createRow(items,
                    getResources().getColor(R.color.table_colurm_color)));
            evWorkGenerate.addRowToTableLayout(mTableHelper
                    .createRowLine(getResources().getColor(R.color.table_colurm_color)));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        /**
         * add DeliverWorkGenerateModel.bandSagyoKbn
         * 0:なし　1：ありの表記
         */
        try {
            key.setContent(getResources().getString(R.string.table_work_generate_item_bandsagyokbn));
            key.setBgColor(getResources().getColor(R.color.table_row_key_bg_color));
            key.setTextColor(getResources().getColor(R.color.table_text_color));
            if (object.getResult().get(0).getDetailItem().get(0).getBandSagyoKbn() == 1) {
                textValue = getResources().getString(R.string.security_system_yes);
            } else if (object.getResult().get(0).getDetailItem().get(0).getBandSagyoKbn() == 0) {
                textValue = getResources().getString(R.string.security_system_no);
            } else {
                textValue = "";
            }
            value.setContent(textValue);
            value.setBgColor(getResources().getColor(R.color.table_row_value_bg_color));
            value.setTextColor(getResources().getColor(R.color.table_text_color));
            items = new TableHelper.ItemRow[]{key, value};
            evWorkGenerate.addRowToTableLayout(mTableHelper.createRow(items,
                    getResources().getColor(R.color.table_colurm_color)));
            evWorkGenerate.addRowToTableLayout(mTableHelper
                    .createRowLine(getResources().getColor(R.color.table_colurm_color)));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        /**
         * add DeliverWorkGenerateModel.coinParkKbn
         * 0:なし　1：ありの表記
         */
        try {
            key.setContent(getResources().getString(R.string.table_work_generate_item_coinparkkbn));
            key.setBgColor(getResources().getColor(R.color.table_row_key_bg_color));
            key.setTextColor(getResources().getColor(R.color.table_text_color));
            if (object.getResult().get(0).getDetailItem().get(0).getCoinParkKbn() == 1) {
                textValue = getResources().getString(R.string.security_system_yes);
            } else if (object.getResult().get(0).getDetailItem().get(0).getCoinParkKbn() == 0) {
                textValue = getResources().getString(R.string.security_system_no);
            } else {
                textValue = "";
            }
            value.setContent(textValue);
            value.setBgColor(getResources().getColor(R.color.table_row_value_bg_color));
            value.setTextColor(getResources().getColor(R.color.table_text_color));
            items = new TableHelper.ItemRow[]{key, value};
            evWorkGenerate.addRowToTableLayout(mTableHelper.createRow(items,
                    getResources().getColor(R.color.table_colurm_color)));
            evWorkGenerate.addRowToTableLayout(mTableHelper
                    .createRowLine(getResources().getColor(R.color.table_colurm_color)));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void inflateDeliverPosition(DeliverChartModel object) {

        try {
            ArrayList<String> nohinPlanArr = object.getResult().get(0).getDetailItem().get(0).getNohinPlanArr();
            if (nohinPlanArr != null && nohinPlanArr.size() > 0) {
                TableHelper mTableHelper = new TableHelper(getContext());
                TableHelper.ItemRow[] items;
                TableHelper.ItemRow key = mTableHelper.new ItemRow();
                TableHelper.ItemRow value = mTableHelper.new ItemRow();

                evPositionDeliver.clearContent();
                //add firt line to tablex
                evPositionDeliver.addRowToTableLayout(mTableHelper
                        .createRowLine(getResources().getColor(R.color.table_colurm_color)));

                int index = 1;
                for (String content : nohinPlanArr) {
                    key.setContent(mContext.getResources().getString(R.string.table_deliver_position_title) + index);
                    key.setBgColor(getResources().getColor(R.color.table_row_key_bg_color));
                    key.setTextColor(getResources().getColor(R.color.table_text_color));
                    value.setContent(content);
                    value.setBgColor(getResources().getColor(R.color.table_row_value_bg_color));
                    value.setTextColor(getResources().getColor(R.color.table_text_color));
                    items = new TableHelper.ItemRow[]{key, value};
                    evPositionDeliver.addRowToTableLayout(mTableHelper.createRow(items,
                            getResources().getColor(R.color.table_colurm_color)));
                    evPositionDeliver.addRowToTableLayout(mTableHelper
                            .createRowLine(getResources().getColor(R.color.table_colurm_color)));
                    index++;
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void inflateImportantNote(DeliverChartModel object) {
        try {
            ArrayList<String> chuiJikoArr = object.getResult().get(0).getDetailItem().get(0).getChuiJikoArr();
            if (chuiJikoArr != null && chuiJikoArr.size() > 0) {
                TableHelper mTableHelper = new TableHelper(getContext());
                TableHelper.ItemRow[] items;
                TableHelper.ItemRow key = mTableHelper.new ItemRow();

                evImportantNote.clearContent();
                //add firt line to tablex
                evImportantNote.addRowToTableLayout(mTableHelper
                        .createRowLine(getResources().getColor(R.color.table_colurm_color)));

                int index = 1;
                for (String content : chuiJikoArr) {
                    key.setContent(content);
                    key.setBgColor(getResources().getColor(R.color.table_row_value_bg_color));
                    key.setTextColor(getResources().getColor(R.color.table_text_color));
                    items = new TableHelper.ItemRow[]{key};
                    evImportantNote.addRowToTableLayout(mTableHelper.createRow(items,
                            getResources().getColor(R.color.table_colurm_color)));
                    evImportantNote.addRowToTableLayout(mTableHelper
                            .createRowLine(getResources().getColor(R.color.table_colurm_color)));
                    index++;
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void inflatePositionMap(DeliverChartModel object) {
        try {
            ArrayList<String> nohinPlanPicArr = object.getResult().get(0).getDetailItem().get(0).getNoihinPlanPicArr();
            if (nohinPlanPicArr != null && nohinPlanPicArr.size() > 0) {
                TableHelper mTableHelper = new TableHelper(getContext());
                evPositionMap.clearContent();
                //add firt line to tablex
                evPositionMap.addRowToTableLayout(mTableHelper
                        .createRowLine(getResources().getColor(R.color.table_colurm_color)));
                int index = 1;
                for (String imgUrl : nohinPlanPicArr) {
                    String key = getResources().getString(R.string.table_deliver_position_map_key_position_map) + index;
                    evPositionMap.addRowToTableLayout(mTableHelper.createRow(key, imgUrl,
                            getResources().getColor(R.color.table_text_color),
                            getResources().getColor(R.color.table_row_value_bg_color),
                            getResources().getColor(R.color.table_colurm_color)));
                    evPositionMap.addRowToTableLayout(mTableHelper
                            .createRowLine(getResources().getColor(R.color.table_colurm_color)));
                    index++;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void inflateInnerDesignMap(DeliverChartModel object) {
        try {
            if (object.getResult().get(0).getDetailItem().get(0).getKonaiMap().isEmpty()) {
                return;
            }
            evInnerDesign.clearContent();
            GestureImageView innerDesignMap = new GestureImageView(getContext());
            innerDesignMap.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            innerDesignMap.getController().getSettings()
                    .setMaxZoom(5f);
            Picasso.get().load(object.getResult().get(0).getDetailItem().get(0).getKonaiMap()).into(innerDesignMap);
            evInnerDesign.addRowToTableLayout(innerDesignMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
