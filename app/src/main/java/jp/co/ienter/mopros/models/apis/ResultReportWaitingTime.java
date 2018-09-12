package jp.co.ienter.mopros.models.apis;

public class ResultReportWaitingTime {

    ResultWaitingTimeItem[] result;

    public ResultReportWaitingTime(ResultWaitingTimeItem[] result) {
        this.result = result;
    }

    public class ResultWaitingTimeItem {
        int is_reached;
        String reach_time;

        public ResultWaitingTimeItem(int is_reached, String reach_time) {
            this.is_reached = is_reached;
            this.reach_time = reach_time;
        }
    }

    // Mock

    public ResultReportWaitingTime() {
        this.result = new ResultWaitingTimeItem[]{new ResultWaitingTimeItem(0, "08:08:08")};
    }
}
