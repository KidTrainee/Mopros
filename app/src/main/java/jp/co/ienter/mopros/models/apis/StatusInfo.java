package jp.co.ienter.mopros.models.apis;

public class StatusInfo {

    //    transport_status	0: 未作業、1: 運送中、2: 到着、3: 納品中、4: 納品完了、5: 報告なし、6: 報告済み…①
    //    pending_status	0: 保留なし、1: 保留中（到着処理）、2: 保留中（納品先選択）…②
    //    break_status		0: 未休憩、1: 休憩中、2: 休憩終了…③
    //    return_status		0: 未帰着、1: 帰着中、2: 帰着済み…④

    //    transport_status  0: Not working, 1: in transit, 2: arrival, 3: delivery,
    //                      4: delivery completed, 5: no report, 6: reported ... ①
    //    pending_status	0: No holding, 1: Pending (arrival processing),
    //                      2: Pending (selecting delivery destination) ②
    //    break_status		0: no break, 1: break, 2: break end ... ③
    //    return_status		0: Unresolved, 1: Returning, 2: Returned ... ④

    private int transport_status, pending_status, break_status, return_status;

    public StatusInfo(int transport_status, int pending_status, int break_status, int return_status) {
        this.transport_status = transport_status;
        this.pending_status = pending_status;
        this.break_status = break_status;
        this.return_status = return_status;
    }

    public boolean isChoosingDestination() {
        return  isPending() ||
                    (!isOnWorking() && !isOnBreak() && !isOnReturn());
                    // not working AND not on break AND not on return
    }

    public boolean isOnWorking() {
        return transport_status != 0 && transport_status != 5 && transport_status != 6;
    }

    public boolean isOnBreak() {
        return break_status == 1;
    }

    public boolean isOnReturn() {
        return return_status == 1;
    }

    public boolean isPending() {
        return pending_status != 0;
    }

    public boolean isInTransport() {
        return (!isOnBreak() && !isOnReturn() && !isPending() && transport_status == 1);
    }

    public boolean isStartDeliver() {
        return (!isOnBreak() && !isOnReturn() && !isPending() && transport_status == 2);
    }

    public boolean isOnDeliver() {
        return (!isOnBreak() && !isOnReturn() && !isPending() && transport_status == 3);
    }

    public boolean isAtIncidentalWork() {
        return (!isOnBreak() && !isOnReturn() && !isPending() && transport_status == 4);
    }
}
