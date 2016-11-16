package cn.true123.lottery.model;

/**
 * Created by junbo on 7/11/2016.
 */

public class LotteryItem implements ILottery{
    private String id;
    private String name;
    private boolean isValid;

    public LotteryItem() {
    }

    public LotteryItem(String id, String name, boolean isValid) {
        this.id = id;
        this.name = name;
        this.isValid = isValid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }
}
