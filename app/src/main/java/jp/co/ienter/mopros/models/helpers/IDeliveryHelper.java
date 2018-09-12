package jp.co.ienter.mopros.models.helpers;

import java.util.ArrayList;
import java.util.List;

import jp.co.ienter.mopros.models.MoprosCargo;
import jp.co.ienter.mopros.models.SelectableDelivery;
import jp.co.ienter.mopros.models.MoprosDelivery;
import jp.co.ienter.mopros.models.apis.BaseApi;
import jp.co.ienter.mopros.models.apis.CargoApi;
import jp.co.ienter.mopros.models.apis.DeliverApi;
import jp.co.ienter.mopros.utils.PreferencesHelper;

interface IDeliveryHelper {
    List<SelectableDelivery> convertToSelectableList(List<MoprosDelivery> deliveryList);
    ArrayList<SelectableDelivery> convertToSelectableListTrue(ArrayList<MoprosDelivery> deliveryList);
    ArrayList<SelectableDelivery> compareSelectDelivery(List<MoprosDelivery> deliveryList,ArrayList<SelectableDelivery> selectableDeliveries);
    DeliverApi[] convertToDeliverApiArr(List<MoprosDelivery> deliveryList);
    ArrayList<SelectableDelivery> convertFullDelivery(List<MoprosDelivery> deliveryList,ArrayList<SelectableDelivery> selectableDeliveries);

    ArrayList<MoprosDelivery> convertToDeliverList(List<SelectableDelivery> mSelectedDeliveryList);

    ArrayList<MoprosDelivery> convertToArrayList(List<MoprosDelivery> deliveryList);

    CargoApi[] convertCargoListToArr(ArrayList<MoprosCargo> cargoList);


}
