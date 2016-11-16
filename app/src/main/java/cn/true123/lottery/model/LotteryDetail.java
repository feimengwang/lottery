package cn.true123.lottery.model;

import java.util.List;

/**
 * Created by feimeng0530 on 2016/3/18.
 */
public class LotteryDetail implements ILottery{

    /**
     * lotId : 220051
     * lotName : 双色球
     * issue : 2016030
     * code : 10 14 19 22 25 29+12
     * date : 2016-03-17
     * time : 21:20
     * sale : 351985986
     * money : 202797966
     * level : [{"name":"一等奖","count":"98","fund":"5069845"},{"name":"二等奖","count":"552","fund":"15500"},{"name":"三等奖","count":"7016","fund":"3000"},{"name":"四等奖","count":"188032","fund":"200"},{"name":"五等奖","count":"2196561","fund":"10"},{"name":"六等奖","count":"11525793","fund":"5"}]
     */

    private String lotId;
    private String lotName;
    private String issue;
    private String code;
    private String date;
    private String time;
    private String sale;
    private String money;
    /**
     * name : 一等奖
     * count : 98
     * fund : 5069845
     */

    private List<LevelEntity> level;

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

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public List<LevelEntity> getLevel() {
        return level;
    }

    public void setLevel(List<LevelEntity> level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "LotteryDetail{" +
                "lotId='" + lotId + '\'' +
                ", lotName='" + lotName + '\'' +
                ", issue='" + issue + '\'' +
                ", code='" + code + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", sale='" + sale + '\'' +
                ", money='" + money + '\'' +
                ", level=" + level +
                '}';
    }

    public static class LevelEntity {
        private String name;
        private String count;
        private String fund;

        public LevelEntity() {
        }

        public LevelEntity(String name, String count, String fund) {
            this.name = name;
            this.count = count;
            this.fund = fund;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getFund() {
            return fund;
        }

        public void setFund(String fund) {
            this.fund = fund;
        }

        @Override
        public String toString() {
            return "LevelEntity{" +
                    "name='" + name + '\'' +
                    ", count='" + count + '\'' +
                    ", fund='" + fund + '\'' +
                    '}';
        }
    }
}
