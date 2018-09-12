package jp.co.ienter.mopros.models;

/**
 * Created by thanhnv on 8/2/18.
 */

public class MainMenuInfor {
    private String   in_loading;
    private String     completed_unso_cnt;
    private  String     unso_cnt_all;
    private String     work_cnt;

    public MainMenuInfor(String tsumikomi_cnt, String completed_unso_cnt, String unso_cnt_all, String work_cnt) {
        this. in_loading = tsumikomi_cnt;
        this.completed_unso_cnt = completed_unso_cnt;
        this.unso_cnt_all = unso_cnt_all;
        this.work_cnt = work_cnt;
    }

    public String getIn_loading() {
        return in_loading;
    }

    public void setIn_loading(String in_loading) {
        this.in_loading = in_loading;
    }

    public String getWork_cnt() {
        return work_cnt;
    }

    public void setWork_cnt(String work_cnt) {
        this.work_cnt = work_cnt;
    }

    public String getCompleted_unso_cnt() {
        return completed_unso_cnt;
    }

    public void setCompleted_unso_cnt(String completed_unso_cnt) {
        this.completed_unso_cnt = completed_unso_cnt;
    }

    public String getUnso_cnt_all() {
        return unso_cnt_all;
    }

    public void setUnso_cnt_all(String unso_cnt_all) {
        this.unso_cnt_all = unso_cnt_all;
    }

    @Override
    public String toString() {
        return "MainMenuInfor{" +
                "tsumikomi_cnt='" +  in_loading + '\'' +
                ", haiso_cnt='" + completed_unso_cnt + '\'' +
                ", haiso_cnt_all='" + unso_cnt_all + '\'' +
                ", work_cnt='" + work_cnt + '\'' +
                '}';
    }
}
