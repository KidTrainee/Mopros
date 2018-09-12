package jp.co.ienter.mopros.activity.deliver_chart.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("nonyu_name")
    @Expose
    private String nonyuName;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("zip")
    @Expose
    private String zip;
    @SerializedName("shop_type")
    @Expose
    private String shopType;
    @SerializedName("nohin_kind")
    @Expose
    private String nohinKind;
    @SerializedName("enter_kind")
    @Expose
    private String enterKind;
    @SerializedName("sejo_kasho")
    @Expose
    private String sejoKasho;
    @SerializedName("niuke_genkai")
    @Expose
    private String niukeGenkai;
    @SerializedName("shuhen_map")
    @Expose
    private String shuhenMap;
    @SerializedName("sharyo_kisei")
    @Expose
    private String sharyoKisei;
    @SerializedName("security_kbn")
    @Expose
    private Integer securityKbn;
    @SerializedName("detail_item")
    @Expose
    private List<DetailItem> detailItem = null;

    public String getNonyuName() {
        return nonyuName;
    }

    public void setNonyuName(String nonyuName) {
        this.nonyuName = nonyuName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getShopType() {
        return shopType;
    }

    public void setShopType(String shopType) {
        this.shopType = shopType;
    }

    public String getNohinKind() {
        return nohinKind;
    }

    public void setNohinKind(String nohinKind) {
        this.nohinKind = nohinKind;
    }

    public String getEnterKind() {
        return enterKind;
    }

    public void setEnterKind(String enterKind) {
        this.enterKind = enterKind;
    }

    public String getSejoKasho() {
        return sejoKasho;
    }

    public void setSejoKasho(String sejoKasho) {
        this.sejoKasho = sejoKasho;
    }

    public String getNiukeGenkai() {
        return niukeGenkai;
    }

    public void setNiukeGenkai(String niukeGenkai) {
        this.niukeGenkai = niukeGenkai;
    }

    public String getShuhenMap() {
        return shuhenMap;
    }

    public void setShuhenMap(String shuhenMap) {
        this.shuhenMap = shuhenMap;
    }

    public List<DetailItem> getDetailItem() {
        return detailItem;
    }

    public void setDetailItem(List<DetailItem> detailItem) {
        this.detailItem = detailItem;
    }

    public String getSharyoKisei() {
        return sharyoKisei;
    }

    public void setSharyoKisei(String sharyoKisei) {
        this.sharyoKisei = sharyoKisei;
    }

    public Integer getSecurityKbn() {
        return securityKbn;
    }

    public void setSecurityKbn(Integer securityKbn) {
        this.securityKbn = securityKbn;
    }
}