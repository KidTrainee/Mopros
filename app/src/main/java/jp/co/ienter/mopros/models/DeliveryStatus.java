package jp.co.ienter.mopros.models;

/**
 * Created by thanhnv on 7/26/18.
 */

public class DeliveryStatus {
    private String name;
    private String shitei_time;
    private String reach_time;
    private int kanryo_flg;
    private int horyu_flg;
    private String sagyo_kbn;

    public DeliveryStatus(String name, String shitei_time, String reach_time, int kanryo_flg, int horyu_flg, String sagyo_kbn) {
        this.name = name;
        this.shitei_time = shitei_time;
        this.reach_time = reach_time;
        this.kanryo_flg = kanryo_flg;
        this.horyu_flg = horyu_flg;
        this.sagyo_kbn = sagyo_kbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShitei_time() {
        return shitei_time;
    }

    public void setShitei_time(String shitei_time) {
        this.shitei_time = shitei_time;
    }

    public String getReach_time() {
        return reach_time;
    }

    public void setReach_time(String reach_time) {
        this.reach_time = reach_time;
    }

    public int getKanryo_flg() {
        return kanryo_flg;
    }

    public void setKanryo_flg(int kanryo_flg) {
        this.kanryo_flg = kanryo_flg;
    }

    public int getHoryu_flg() {
        return horyu_flg;
    }

    public void setHoryu_flg(int horyu_flg) {
        this.horyu_flg = horyu_flg;
    }

    public String getSagyo_kbn() {
        return sagyo_kbn;
    }

    public void setSagyo_kbn(String sagyo_kbn) {
        this.sagyo_kbn = sagyo_kbn;
    }
}
