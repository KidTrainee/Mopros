package jp.co.ienter.mopros.models.helpers;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import jp.co.ienter.mopros.models.MoprosCargo;
import jp.co.ienter.mopros.models.MoprosDelivery;
import jp.co.ienter.mopros.models.SelectableDelivery;
import jp.co.ienter.mopros.models.apis.BaseApi;
import jp.co.ienter.mopros.models.apis.CargoApi;
import jp.co.ienter.mopros.models.apis.DeliverApi;
import jp.co.ienter.mopros.models.apis.DeliverArrApi;
import jp.co.ienter.mopros.models.apis.SimpleDeliverApi;
import jp.co.ienter.mopros.utils.PreferencesHelper;

public class DeliverParser implements IDeliveryHelper {

    @Override
    public List<SelectableDelivery> convertToSelectableList(List<MoprosDelivery> deliveryList) {
        List<SelectableDelivery> output = new ArrayList<>();
        for (MoprosDelivery delivery : deliveryList) {
            output.add(new SelectableDelivery(delivery, false));
        }
        return output;
    }

    @Override
    public ArrayList<SelectableDelivery> compareSelectDelivery(List<MoprosDelivery> deliveryList,
                                                               ArrayList<SelectableDelivery> selectableDeliveries) {
        ArrayList<SelectableDelivery> output = new ArrayList<>();

        for (MoprosDelivery delivery : deliveryList) {
            output.add(new SelectableDelivery(delivery, selectableDeliveries.contains(delivery)));
        }
        return output;
    }

    @Override
    public ArrayList<SelectableDelivery> convertFullDelivery(List<MoprosDelivery> deliveryList, ArrayList<SelectableDelivery> selectableDeliveries) {
        ArrayList<SelectableDelivery> output = new ArrayList<>();

        for (MoprosDelivery delivery : deliveryList) {
            if (selectableDeliveries.contains(delivery)) {
                output.add(new SelectableDelivery(delivery, true));
            }
        }
        return output;
    }

    @Override
    public ArrayList<SelectableDelivery> convertToSelectableListTrue(ArrayList<MoprosDelivery> deliveryList) {
        ArrayList<SelectableDelivery> output = new ArrayList<>();
        for (MoprosDelivery delivery : deliveryList) {
            output.add(new SelectableDelivery(delivery, true));
        }
        return output;
    }

    @Override
    public DeliverApi[] convertToDeliverApiArr(List<MoprosDelivery> deliveryList) {
        DeliverApi[] arr = new DeliverApi[deliveryList.size()];
        for (int i = 0; i < deliveryList.size(); i++) {
            MoprosDelivery delivery = deliveryList.get(i);
            arr[i] = new DeliverApi(delivery.getCourse_code_1(), delivery.getCourse_code_2(), delivery.getHaiso_order_no(),
                    delivery.getTrip(), (!TextUtils.isEmpty(delivery.getShuka_code())
                    ? delivery.getShuka_code() : delivery.getNonyu_code()), delivery.getGosha());
            //"303"
//            // TODO: 9/5/18 fake data
//            arr[i] = new DeliverApi(delivery.getCourse_code_1(), delivery.getCourse_code_2(), delivery.getHaiso_order_no(),
//                    delivery.getTrip(), "303");
        }
        return arr;
    }

    @Override
    public ArrayList<MoprosDelivery> convertToDeliverList(List<SelectableDelivery> selectedList) {
        ArrayList<MoprosDelivery> output = new ArrayList<>();
        output.addAll(selectedList);
        return output;
    }

    @Override
    public ArrayList<MoprosDelivery> convertToArrayList(List<MoprosDelivery> deliveryList) {
        return new ArrayList<>(deliveryList);
    }

    @Override
    public CargoApi[] convertCargoListToArr(ArrayList<MoprosCargo> cargoList) {
        CargoApi[] cargoApis = new CargoApi[cargoList.size()];
        for (int i = 0; i < cargoList.size(); i++) {
            cargoApis[i] = new CargoApi(cargoList.get(i).getShuka_code());
        }
        return cargoApis;
    }

    public DeliverArrApi createMultiDeliverApi(String id, String haiso_date, List<MoprosDelivery> mDeliveryList) {
        return new DeliverArrApi(id, haiso_date,
                mDeliveryList.get(0).getData_type(),
                convertToDeliverApiArr(mDeliveryList));
    }

    public SimpleDeliverApi createUpdateDeliverApi(String id, String haiso_date, List<MoprosDelivery> deliverList) {
        SimpleDeliverApi simpleDeliverApi = null;
        if (deliverList != null && !deliverList.isEmpty()) {
            MoprosDelivery deliver = deliverList.get(0);
            String transport_code = (deliver.getNonyu_code().isEmpty())
                    ? deliver.getShuka_code()
                    : deliver.getNonyu_code();

            simpleDeliverApi = new SimpleDeliverApi(id, haiso_date,
                    deliverList.get(0).getData_type(),
                    deliver.getCourse_code_1(), deliver.getCourse_code_2(), deliver.getHaiso_order_no(),
                    deliver.getTrip(), transport_code,
                    deliver.getGosha());
        }
        return simpleDeliverApi;
    }

    public BaseApi createBaseApi(PreferencesHelper prefsHelper) {
        return new BaseApi(prefsHelper.getUserID(), prefsHelper.getHaiSoDate());
    }

//    public ReportDataAPI createReportApi(String id, String haiSoDate, List<MoprosDelivery> selectedDeliveryList) {
//        ReportDataAPI reportApi =
//                new ReportDataAPI(id, haiSoDate,
//                        selectedDeliveryList.get(0).getData_type(),
//                        convertToReportApiArr(selectedDeliveryList));
//
//        return reportApi;
//    }

//    private TransportData[] convertToReportApiArr(List<MoprosDelivery> deliveryList) {
//        TransportData[] arr = new TransportData[deliveryList.size()];
//        for (int i = 0; i < deliveryList.size(); i++) {
//            MoprosDelivery delivery = deliveryList.get(i);
//            arr[i] = new TransportData(delivery.getCourse_code_1(),
//                    delivery.getCourse_code_2(), delivery.getHaiso_order_no(),
//                    delivery.getTrip(), delivery.getNonyu_code());
//        }
//        return arr;
//
//    }
}
