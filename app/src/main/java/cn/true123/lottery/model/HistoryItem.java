package cn.true123.lottery.model;

/**
 * Created by feimeng0530 on 2016/3/24.
 */
public class HistoryItem implements ILottery{
    private String lotId;
    private String time;
    private String issue;
    private String red;
    private String blue;

    public HistoryItem() {
    }

    public HistoryItem(String lotId, String time, String issue, String red, String blue) {
        this.lotId = lotId;
        this.time = time;
        this.issue = issue;
        this.red = red;
        this.blue = blue;
    }

    public String getLotId() {
        return lotId;
    }

    public void setLotId(String lotId) {
        this.lotId = lotId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getRed() {
        return red;
    }

    public void setRed(String red) {
        this.red = red;
    }

    public String getBlue() {
        return blue;
    }

    public void setBlue(String blue) {
        this.blue = blue;
    }

    @Override
    public String toString() {
        return "HistoryItem{" +
                "lotId='" + lotId + '\'' +
                ", time='" + time + '\'' +
                ", issue='" + issue + '\'' +
                ", red='" + red + '\'' +
                ", blue='" + blue + '\'' +
                '}';
    }
}
