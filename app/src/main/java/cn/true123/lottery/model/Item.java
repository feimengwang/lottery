package cn.true123.lottery.model;

/**
 * Created by feimeng0530 on 2016/3/24.
 */
public class Item implements ILottery{
    private String lotName;
    private String lotId;
    private String flg;

    public Item() {
    }

    public Item(String lotName, String lotId, String flg) {
        this.lotName = lotName;
        this.lotId = lotId;
        this.flg = flg;
    }

    public String getFlg() {
        return flg;
    }

    public void setFlg(String flg) {
        this.flg = flg;
    }

    public String getLotName() {
        return lotName;
    }

    public void setLotName(String lotName) {
        this.lotName = lotName;
    }

    public String getLotId() {
        return lotId;
    }

    public void setLotId(String lotId) {
        this.lotId = lotId;
    }
}
