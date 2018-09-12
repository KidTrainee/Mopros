package jp.co.ienter.mopros.activity.deliver_report;

public class ServerSynchronizer implements ISynchronizer {

    private static int isWorking;

    public static void reset() {
        isWorking = 0;
    }

    @Override
    public ISynchronizer loadATask() {
        isWorking++;
        return this;
    }

    @Override
    public ISynchronizer finishATask() {
        isWorking--;
        return this;
    }

    @Override
    public boolean isDone() {
        return isWorking == 0;
    }
}
