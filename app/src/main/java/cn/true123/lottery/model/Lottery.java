package cn.true123.lottery.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by feimeng0530 on 2016/3/17.
 */
public class Lottery implements ILottery{

    public List getAllEntities(){
        List ret = new ArrayList();
        ret.add(getValue220051());
        ret.add(getValue120029());
        ret.add(getValue220028());
        ret.add(getValue110022());
        ret.add(getValue210053());
        ret.add(getValue110033());
        ret.add(getValue110035());
        ret.add(getValue166406());
        ret.add(getValue168009());
        ret.add(getValue258001());
        ret.add(getValue255401());
        ret.add(getValue258203());
        ret.add(getValue130011());
        ret.add(getValue165707());
        ret.add(getValue257503());
        ret.add(getValue255903());
        ret.add(getValue255803());
        ret.add(getValue257703());
        ret.add(getValue223515());
        ret.add(getValue166407());
        ret.add(getValue166507());
        ret.add(getValue165207());
        ret.add(getValue166907());
        ret.add(getValue265108());
        ret.add(getValue167607());
        return ret;
    }
    /**
     * issue : 2016029
     * lotName : 双色球
     * balls : 12 15 18 20 21 27+15
     * date : 2016-03-15
     * index : 11
     */

    @SerializedName("220051")
    private Entity value220051;
    /**
     * issue : 2016030
     * lotName : 大乐透
     * balls : 10 19 21 29 33+04 06
     * date : 2016-03-16
     * index : 12
     */

    @SerializedName("120029")
    private Entity value120029;
    /**
     * issue : 2016030
     * lotName : 七乐彩
     * balls : 07 16 19 21 23 29 30+10
     * date : 2016-03-16
     * index : -2
     */

    @SerializedName("220028")
    private Entity value220028;
    /**
     * issue : 2016030
     * lotName : 七星彩
     * balls : 4411721
     * date : 2016-03-15
     * index : -3
     */

    @SerializedName("110022")
    private Entity value110022;
    /**
     * issue : 2016070
     * lotName : 福彩3D
     * balls : 896+926
     * date : 2016-03-17
     * index : 17
     */

    @SerializedName("210053")
    private Entity value210053;
    /**
     * issue : 2016070
     * lotName : 排列三
     * balls : 789
     * date : 2016-03-17
     * index : 20
     */

    @SerializedName("110033")
    private Entity value110033;
    /**
     * issue : 2016070
     * lotName : 排列五
     * balls : 78931
     * date : 2016-03-17
     * index : -4
     */

    @SerializedName("110035")
    private Entity value110035;
    /**
     * issue : 2016031772
     * lotName : 11选5
     * balls : 08 02 07 10 04
     * date : 2016-03-17
     * index : 32
     */

    @SerializedName("166406")
    private Entity value166406;
    /**
     * issue : 2016031772
     * lotName : 新11选5
     * balls : 01 09 03 11 02
     * date : 2016-03-17
     * index : 33
     */

    @SerializedName("168009")
    private Entity value168009;
    /**
     * issue : 160222084
     * lotName : 新时时彩
     * balls : 31668
     * date : 2016-02-22
     * index : 15
     */

    @SerializedName("258001")
    private Entity value258001;
    /**
     * issue : 160317090
     * lotName : 老时时彩
     * balls : 12605
     * date : 2016-03-17
     * index : 19
     */

    @SerializedName("255401")
    private Entity value255401;
    /**
     * issue : 2016031775
     * lotName : 新快3
     * balls : 346
     * date : 2016-03-17
     * index : 107
     */

    @SerializedName("258203")
    private Entity value258203;
    /**
     * issue : 2016042
     * lotName : 胜负彩
     * balls : 10030113311131
     * date : 2016-03-15
     * index : 23
     */

    @SerializedName("130011")
    private Entity value130011;
    /**
     * issue : 2016031772
     * lotName : 粤11选5
     * balls : 09 04 10 08 01
     * date : 2016-03-17
     * index : 24
     */

    @SerializedName("165707")
    private Entity value165707;
    /**
     * issue : 2016031767
     * lotName : 快3
     * balls : 346
     * date : 2016-03-17
     * index : 31
     */

    @SerializedName("257503")
    private Entity value257503;
    /**
     * issue : 2016031775
     * lotName : 老快3
     * balls : 246
     * date : 2016-03-17
     * index : 33
     */

    @SerializedName("255903")
    private Entity value255903;
    /**
     * issue : 2016031769
     * lotName : 好运快3
     * balls : 146
     * date : 2016-03-17
     * index : 18
     */

    @SerializedName("255803")
    private Entity value255803;
    /**
     * issue : 2016031772
     * lotName : 湖北快3
     * balls : 233
     * date : 2016-03-17
     * index : 27
     */

    @SerializedName("257703")
    private Entity value257703;
    /**
     * issue : 2016069
     * lotName : 15选5
     * balls : 02 04 06 14 15
     * date : 2016-03-16
     * index : -1
     */

    @SerializedName("223515")
    private Entity value223515;
    /**
     * issue : 2016031772
     * lotName : 快乐扑克
     * balls : 22 44 4Q
     * date : 2016-03-17
     * index : 108
     */

    @SerializedName("166407")
    private Entity value166407;
    /**
     * issue : 2016031775
     * lotName : 幸运11选5
     * balls : 04 08 06 09 10
     * date : 2016-03-17
     * index : 26
     */

    @SerializedName("166507")
    private Entity value166507;
    /**
     * issue : 2016031773
     * lotName : 上海11选5
     * balls : 02 09 03 04 10
     * date : 2016-03-17
     * index : 25
     */

    @SerializedName("165207")
    private Entity value165207;
    /**
     * issue : 2016031774
     * lotName : 辽宁11选5
     * balls : 02 10 09 06 07
     * date : 2016-03-17
     * index : 30
     */

    @SerializedName("166907")
    private Entity value166907;
    /**
     * issue : 748824
     * lotName : 快乐8
     * balls : 04 06 07 09 22 23 24 30 36 37 40 44 51 58 60 61 64 67 69 76+01
     * date : 2016-03-17
     * index : 28
     */

    @SerializedName("265108")
    private Entity value265108;
    /**
     * issue : 2016031767
     * lotName : 快乐11选5
     * balls : 10 05 08 07 01
     * date : 2016-03-17
     * index : 18
     */

    @SerializedName("167607")
    private Entity value167607;
    /**
     * home : 拜仁
     * away : 尤文图斯
     * full : 2:2
     * half : 0:2
     * lotLose : -1
     * date : 2016-03-17
     * lotName : 竞彩足球
     * index : 6
     */

    @SerializedName("130042")
    private Entity2 value130042;
    /**
     * home : 武装体育
     * away : 纳萨夫
     * full : 1:0
     * half : 0:0
     * lotLose : -1
     * date : 2016-03-16
     * lotName : 北京单场
     * index : 105
     */

    @SerializedName("130041")
    private Entity2 value130041;
    /**
     * home : 勇士
     * away : 尼克斯
     * full : 121:85
     * lotLose : -15.50
     * code : 1
     * date : 2016-03-17
     * lotName : 竞彩篮球
     * index : 9
     */

    @SerializedName("130043")
    private Entity2 value130043;

    public Entity getValue220051() {
        return value220051;
    }

    public void setValue220051(Entity value220051) {
        this.value220051 = value220051;
    }

    public Entity getValue120029() {
        return value120029;
    }

    public void setValue120029(Entity value120029) {
        this.value120029 = value120029;
    }

    public Entity getValue220028() {
        return value220028;
    }

    public void setValue220028(Entity value220028) {
        this.value220028 = value220028;
    }

    public Entity getValue110022() {
        return value110022;
    }

    public void setValue110022(Entity value110022) {
        this.value110022 = value110022;
    }

    public Entity getValue210053() {
        return value210053;
    }

    public void setValue210053(Entity value210053) {
        this.value210053 = value210053;
    }

    public Entity getValue110033() {
        return value110033;
    }

    public void setValue110033(Entity value110033) {
        this.value110033 = value110033;
    }

    public Entity getValue110035() {
        return value110035;
    }

    public void setValue110035(Entity value110035) {
        this.value110035 = value110035;
    }

    public Entity getValue166406() {
        return value166406;
    }

    public void setValue166406(Entity value166406) {
        this.value166406 = value166406;
    }

    public Entity getValue168009() {
        return value168009;
    }

    public void setValue168009(Entity value168009) {
        this.value168009 = value168009;
    }

    public Entity getValue258001() {
        return value258001;
    }

    public void setValue258001(Entity value258001) {
        this.value258001 = value258001;
    }

    public Entity getValue255401() {
        return value255401;
    }

    public void setValue255401(Entity value255401) {
        this.value255401 = value255401;
    }

    public Entity getValue258203() {
        return value258203;
    }

    public void setValue258203(Entity value258203) {
        this.value258203 = value258203;
    }

    public Entity getValue130011() {
        return value130011;
    }

    public void setValue130011(Entity value130011) {
        this.value130011 = value130011;
    }

    public Entity getValue165707() {
        return value165707;
    }

    public void setValue165707(Entity value165707) {
        this.value165707 = value165707;
    }

    public Entity getValue257503() {
        return value257503;
    }

    public void setValue257503(Entity value257503) {
        this.value257503 = value257503;
    }

    public Entity getValue255903() {
        return value255903;
    }

    public void setValue255903(Entity value255903) {
        this.value255903 = value255903;
    }

    public Entity getValue255803() {
        return value255803;
    }

    public void setValue255803(Entity value255803) {
        this.value255803 = value255803;
    }

    public Entity getValue257703() {
        return value257703;
    }

    public void setValue257703(Entity value257703) {
        this.value257703 = value257703;
    }

    public Entity getValue223515() {
        return value223515;
    }

    public void setValue223515(Entity value223515) {
        this.value223515 = value223515;
    }

    public Entity getValue166407() {
        return value166407;
    }

    public void setValue166407(Entity value166407) {
        this.value166407 = value166407;
    }

    public Entity getValue166507() {
        return value166507;
    }

    public void setValue166507(Entity value166507) {
        this.value166507 = value166507;
    }

    public Entity getValue165207() {
        return value165207;
    }

    public void setValue165207(Entity value165207) {
        this.value165207 = value165207;
    }

    public Entity getValue166907() {
        return value166907;
    }

    public void setValue166907(Entity value166907) {
        this.value166907 = value166907;
    }

    public Entity getValue265108() {
        return value265108;
    }

    public void setValue265108(Entity value265108) {
        this.value265108 = value265108;
    }

    public Entity getValue167607() {
        return value167607;
    }

    public void setValue167607(Entity value167607) {
        this.value167607 = value167607;
    }

    public Entity2 getValue130042() {
        return value130042;
    }

    public void setValue130042(Entity2 value130042) {
        this.value130042 = value130042;
    }

    public Entity2 getValue130041() {
        return value130041;
    }

    public void setValue130041(Entity2 value130041) {
        this.value130041 = value130041;
    }

    public Entity2 getValue130043() {
        return value130043;
    }

    public void setValue130043(Entity2 value130043) {
        this.value130043 = value130043;
    }

     public static class Entity implements IEntity{
        private String issue;
        private String lotName;
        private String balls;
        private String date;
        private String index;

         public Entity() {
         }

         public Entity(String issue, String lotName, String balls, String date, String index) {
             this.issue = issue;
             this.lotName = lotName;
             this.balls = balls;
             this.date = date;
             this.index = index;
         }

         public String getIssue() {
            return issue;
        }

        public void setIssue(String issue) {
            this.issue = issue;
        }

        public String getLotName() {
            return lotName;
        }

        public void setLotName(String lotName) {
            this.lotName = lotName;
        }

        public String getBalls() {
            return balls;
        }

        public void setBalls(String balls) {
            this.balls = balls;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getIndex() {
            return index;
        }

        public void setIndex(String index) {
            this.index = index;
        }

         @Override
         public String toString() {
             return "Entity{" +
                     "issue='" + issue + '\'' +
                     ", lotName='" + lotName + '\'' +
                     ", balls='" + balls + '\'' +
                     ", date='" + date + '\'' +
                     ", index='" + index + '\'' +
                     '}';
         }
     }



    public static class Entity2 implements IEntity{
        private String home;
        private String away;
        private String full;
        private String half;
        private String lotLose;
        private String date;
        private String lotName;
        private String index;

        public String getHome() {
            return home;
        }

        public void setHome(String home) {
            this.home = home;
        }

        public String getAway() {
            return away;
        }

        public void setAway(String away) {
            this.away = away;
        }

        public String getFull() {
            return full;
        }

        public void setFull(String full) {
            this.full = full;
        }

        public String getHalf() {
            return half;
        }

        public void setHalf(String half) {
            this.half = half;
        }

        public String getLotLose() {
            return lotLose;
        }

        public void setLotLose(String lotLose) {
            this.lotLose = lotLose;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getLotName() {
            return lotName;
        }

        @Override
        public String getIssue() {
            return null;
        }

        @Override
        public String getBalls() {
            return null;
        }

        public void setLotName(String lotName) {
            this.lotName = lotName;
        }

        public String getIndex() {
            return index;
        }

        public void setIndex(String index) {
            this.index = index;
        }

        @Override
        public String toString() {
            return "Entity2{" +
                    "home='" + home + '\'' +
                    ", away='" + away + '\'' +
                    ", full='" + full + '\'' +
                    ", half='" + half + '\'' +
                    ", lotLose='" + lotLose + '\'' +
                    ", date='" + date + '\'' +
                    ", lotName='" + lotName + '\'' +
                    ", index='" + index + '\'' +
                    '}';
        }


    }

    public  interface IEntity{
        public String getLotName();
        public String getIssue();
        public String getBalls();
        public String getDate();
    }

    @Override
    public String toString() {
        return "Lottery{" +
                "value220051=" + value220051 +
                ", value120029=" + value120029 +
                ", value220028=" + value220028 +
                ", value110022=" + value110022 +
                ", value210053=" + value210053 +
                ", value110033=" + value110033 +
                ", value110035=" + value110035 +
                ", value166406=" + value166406 +
                ", value168009=" + value168009 +
                ", value258001=" + value258001 +
                ", value255401=" + value255401 +
                ", value258203=" + value258203 +
                ", value130011=" + value130011 +
                ", value165707=" + value165707 +
                ", value257503=" + value257503 +
                ", value255903=" + value255903 +
                ", value255803=" + value255803 +
                ", value257703=" + value257703 +
                ", value223515=" + value223515 +
                ", value166407=" + value166407 +
                ", value166507=" + value166507 +
                ", value165207=" + value165207 +
                ", value166907=" + value166907 +
                ", value265108=" + value265108 +
                ", value167607=" + value167607 +
                ", value130042=" + value130042 +
                ", value130041=" + value130041 +
                ", value130043=" + value130043 +
                '}';
    }
}
