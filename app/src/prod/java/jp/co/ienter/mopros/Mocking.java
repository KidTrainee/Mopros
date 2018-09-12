package jp.co.ienter.mopros;

import jp.co.ienter.mopros.activity.deliver_report.FragmentChooseDestination;
import jp.co.ienter.mopros.utils.ScreenTransition;

import static jp.co.ienter.mopros.utils.PreConditions.checkNotNull;

public class Mocking {

    public static void checkServerCurrentDeliverStateMock(ScreenTransition screenTransition) {
        checkNotNull(screenTransition);
        screenTransition.replaceFragment(FragmentChooseDestination.newInstance());
    }

//    public static void arrivalDeliver(final ButtonCallbacks.ArrivalCallback mCallback) {
//        mCallback.onLoading();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mCallback.onLoadingFinished();
//                mCallback.onBtnDeliverArrivalClick();
//            }
//        }, 1000);
//    }
//
//    public static void departureDeliver(final ButtonCallbacks.DepartureCallback mCallback) {
//        mCallback.onLoading();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mCallback.onLoadingFinished();
//                mCallback.gotoFragmentDeliverArrival();
//            }
//        }, 1000);
//    }
//
//    public static void startDeliver(final ButtonCallbacks.DeliverStartCallback mCallback) {
//        mCallback.onLoading();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mCallback.onLoadingFinished();
//                mCallback.onBtnDeliverStartClick();
//            }
//        }, 1000);
//
//    }
//
//    public static void endDeliver(final ButtonCallbacks.DeliverFinishCallback mCallback) {
//        mCallback.onLoading();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mCallback.onLoadingFinished();
//                mCallback.onDeliverFinishClick();
//            }
//        }, 1000);
//    }
}
