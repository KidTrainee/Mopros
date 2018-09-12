package jp.co.ienter.mopros.models.helpers;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import jp.co.ienter.mopros.activity.deliver_report.FragmentChooseDestination;
import jp.co.ienter.mopros.activity.deliver_report.FragmentDeliverDeparture;
import jp.co.ienter.mopros.activity.deliver_report.FragmentDeliverReport;
import jp.co.ienter.mopros.activity.deliver_report.FragmentBreakEnd;
import jp.co.ienter.mopros.activity.deliver_report.FragmentDeliverStart;
import jp.co.ienter.mopros.activity.deliver_report.FragmentExtraReportDeliver;
import jp.co.ienter.mopros.activity.deliver_report.FragmentReturnArrival;
import jp.co.ienter.mopros.activity.deliver_report.FragmentDeliverArrival;
import jp.co.ienter.mopros.activity.deliver_report.FragmentDeliverEnd;
import jp.co.ienter.mopros.fragments.base.BaseFragment;
import jp.co.ienter.mopros.models.apis.StatusInfo;

import static jp.co.ienter.mopros.models.helpers.DeliverStatus.*;
import static jp.co.ienter.mopros.models.helpers.WorkerStatus.*;

public class StatusHelper {

    private @Nullable
    StatusInfo mStatusInfo;

    public StatusHelper(@Nullable StatusInfo statusInfo) {
        mStatusInfo = statusInfo;
    }

    private WorkerStatus getWorkerStatus() {
        WorkerStatus workerStatus;
        if (mStatusInfo == null) {
            workerStatus = STATUS_CHOOSE_DESTINATION;
        } else {
            if (mStatusInfo.isOnReturn()) {
                workerStatus = STATUS_ON_RETURN;
            } else if (mStatusInfo.isOnBreak()) {
                workerStatus = STATUS_ON_BREAK;
            } else if (mStatusInfo.isChoosingDestination()) {
                workerStatus = STATUS_CHOOSE_DESTINATION;
            } else if (mStatusInfo.isOnWorking()) {
                workerStatus = STATUS_ON_WORKING;
            } else {
                throw new IllegalStateException("State not determined: " + mStatusInfo.toString());
            }
        }

        return workerStatus;
    }

    private DeliverStatus getDeliverStatus() {
        DeliverStatus deliverStatus;
        if (mStatusInfo == null)
            deliverStatus = STATUS_NEW_PROGRESS;
        else {
            if (mStatusInfo.isInTransport()) {
                deliverStatus = STATUS_ON_TRANSPORT;
            } else if (mStatusInfo.isStartDeliver()) {
                deliverStatus = STATUS_START_DELIVER;
            } else if (mStatusInfo.isOnDeliver()) {
                deliverStatus = STATUS_ON_DELIVER;
            } else if (mStatusInfo.isAtIncidentalWork()) {
                deliverStatus = STATUS_AT_INCIDENTAL_WORK;
            } else {
                deliverStatus = STATUS_NEW_PROGRESS;
            }
        }

        return deliverStatus;
    }

    public String TAG = getClass().getSimpleName();

    public void gotoDeliverReportFragment(FragmentDeliverReport parentFrag) {

        BaseFragment fragment;
        switch (getDeliverStatus()) {
            case STATUS_ON_TRANSPORT:
                parentFrag.gotoFragmentDeliverArrival();
                break;
            case STATUS_START_DELIVER:
                parentFrag.replaceProgressFragment(FragmentDeliverStart.newInstance());
                break;
            case STATUS_ON_DELIVER:
                parentFrag.gotoDeliverEndFragment();
                break;
            case STATUS_AT_INCIDENTAL_WORK:
                parentFrag.gotoExtraDeliverReport();
                break;
            case STATUS_NEW_PROGRESS:
                parentFrag.replaceProgressFragment(FragmentDeliverDeparture.newInstance());
                break;
            default:
                throw new IllegalStateException(getDeliverStatus() + " is not match.\n" + mStatusInfo.toString());
        }
    }

    @NonNull
    public BaseFragment getTopFragment() {
        BaseFragment fragment;
        switch (getWorkerStatus()) {
            case STATUS_CHOOSE_DESTINATION:
                fragment = FragmentChooseDestination.newInstance();
                break;
            case STATUS_ON_BREAK:
                fragment = FragmentBreakEnd.newInstance();
                break;
            case STATUS_ON_RETURN:
                fragment = FragmentReturnArrival.newInstance();
                break;
            case STATUS_ON_WORKING:
                fragment = FragmentDeliverReport.newInstance();
                break;
            default:
                throw new IllegalStateException(getWorkerStatus() + " is not match.\n" + mStatusInfo.toString());
        }
        return fragment;
    }

}
