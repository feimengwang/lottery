package cn.true123.lottery.model;

import java.util.List;

/**
 * Created by feimeng0530 on 2016/3/18.
 */
public class LotteryHistory implements ILottery{

    /**
     * list : [{"Issue":"2016030","WinNumber":"07 16 19 21 23 29 30+10","BallNumber":"","EndTime":"2016-03-16 20:00:00"},{"Issue":"2016029","WinNumber":"01 03 05 07 15 17 25+09","BallNumber":"","EndTime":"2016-03-14 20:00:00"},{"Issue":"2016028","WinNumber":"01 02 09 15 16 24 26+21","BallNumber":"","EndTime":"2016-03-11 20:00:00"},{"Issue":"2016027","WinNumber":"02 03 06 07 08 09 28+04","BallNumber":"","EndTime":"2016-03-09 20:00:00"},{"Issue":"2016026","WinNumber":"02 06 12 17 22 25 28+05","BallNumber":"","EndTime":"2016-03-07 20:00:00"},{"Issue":"2016025","WinNumber":"03 04 06 19 21 27 30+28","BallNumber":"","EndTime":"2016-03-04 20:00:00"},{"Issue":"2016024","WinNumber":"02 07 09 17 18 22 28+29","BallNumber":"","EndTime":"2016-03-02 20:00:00"},{"Issue":"2016023","WinNumber":"01 06 13 14 25 26 28+10","BallNumber":"","EndTime":"2016-02-29 20:00:00"},{"Issue":"2016022","WinNumber":"02 09 11 13 15 18 28+06","BallNumber":"","EndTime":"2016-02-26 20:00:00"},{"Issue":"2016021","WinNumber":"01 02 03 07 08 10 14+18","BallNumber":"","EndTime":"2016-02-24 20:00:00"}]
     * lotId : 220028
     * lotName : 七乐彩
     * openTime : 21:20
     * pageCount : 5
     */

    private String lotId;
    private String lotName;
    private String openTime;
    private String pageCount;
    /**
     * Issue : 2016030
     * WinNumber : 07 16 19 21 23 29 30+10
     * BallNumber :
     * EndTime : 2016-03-16 20:00:00
     */

    private List<ListEntity> list;

    public String getLotId() {
        return lotId;
    }

    public void setLotId(String lotId) {
        this.lotId = lotId;
    }

    public String getLotName() {
        return lotName;
    }

    public void setLotName(String lotName) {
        this.lotName = lotName;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    public List<ListEntity> getList() {
        return list;
    }

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public static class ListEntity {
        private String Issue;
        private String WinNumber;
        private String BallNumber;
        private String EndTime;

        public String getIssue() {
            return Issue;
        }

        public void setIssue(String Issue) {
            this.Issue = Issue;
        }

        public String getWinNumber() {
            return WinNumber;
        }

        public void setWinNumber(String WinNumber) {
            this.WinNumber = WinNumber;
        }

        public String getBallNumber() {
            return BallNumber;
        }

        public void setBallNumber(String BallNumber) {
            this.BallNumber = BallNumber;
        }

        public String getEndTime() {
            return EndTime;
        }

        public void setEndTime(String EndTime) {
            this.EndTime = EndTime;
        }

        @Override
        public String toString() {
            return "ListEntity{" +
                    "Issue='" + Issue + '\'' +
                    ", WinNumber='" + WinNumber + '\'' +
                    ", BallNumber='" + BallNumber + '\'' +
                    ", EndTime='" + EndTime + '\'' +
                    '}';
        }
    }
}
