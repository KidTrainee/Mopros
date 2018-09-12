package jp.co.ienter.mopros.activity.deliver_report;

interface ISynchronizer {
    ISynchronizer loadATask();

    ISynchronizer finishATask();

    boolean isDone();
}
