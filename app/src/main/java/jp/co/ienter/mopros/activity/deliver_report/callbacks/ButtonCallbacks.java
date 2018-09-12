package jp.co.ienter.mopros.activity.deliver_report.callbacks;

public interface ButtonCallbacks  {

    interface DepartureCallback {
        void gotoFragmentDeliverArrival();
    }

    interface ArrivalCallback {
        void onBtnDeliverArrivalClick();
    }

    interface DeliverStartCallback {
        void gotoDeliverEndFragment();
    }

//    interface DeliverFinishCallback extends BaseButtonClickCallback {
//        void onDeliverFinishClick();
//    }

//    interface BreakStartBtnClickCallback extends BaseButtonClickCallback {
//        void onBtnBreakStartProcessed();
//    }
//
//    interface BreakFinishBtnClickCallback extends BaseButtonClickCallback {
//        void onBtnBreakFinishClick();
//    }
//
//    interface ReturnDepartureCallback extends BaseButtonClickCallback {
//        void onBtnReturnDepartureClick();
//    }
//
//    interface ReturnArrivalCallback extends BaseButtonClickCallback {
//        void onBtnReturnArrivalClick();
//    }
//
//    interface PickupStartCallback extends BaseButtonClickCallback {
//        void onBtnPickupStartClick();
//    }

//    interface PickupFinishCallback extends BaseButtonClickCallback {
//        void onBtnPickupFinishClick();
//    }
//
//    interface ExtraReportCallback extends BaseButtonClickCallback {
//        void onExtraExtraReportFragment();
//        void onBtnReportClick();
//        void onBtnNoReportClick();
//    }
}
