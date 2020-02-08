package com.link.fan.data.bean;

import java.util.List;

public class JuHeMenuResult {

    /**
     * id : 8
     * classid : 2
     * name : 醋溜白菜
     * peoplenum : 1-2人
     * preparetime : 10-20分钟
     * cookingtime : 10-20分钟
     * content : 醋溜白菜，是北方人经常吃的一道菜，尤其是在多年前的冬天。那时，没有大棚菜，冬天，家家每天佐餐的基本上都是冬储大白菜，聪明的家庭主妇总是想方设法将这单调的菜变成多种菜式，于是，醋溜白菜被频繁的端上餐桌。<br>美食不分贵贱，用最平凡的原料、最简单的调料和最普通的手法做出美味的菜肴来才是美食的真谛。 <br>这次，我做的醋溜白菜，近似鲁菜的做法，使个这道菜酸甜浓郁、开胃下饭、老少咸宜。
     * pic : http://api.jisuapi.com/recipe/upload/20160719/115138_46688.jpg
     * tag : 减肥,家常菜,排毒,补钙
     * material : [{"mname":"油","type":"0","amount":"适量"},{"mname":"盐","type":"0","amount":"适量"},{"mname":"花椒","type":"0","amount":"适量"},{"mname":"干红椒","type":"0","amount":"适量"},{"mname":"葱","type":"0","amount":"适量"},{"mname":"姜","type":"0","amount":"适量"},{"mname":"蒜","type":"0","amount":"适量"},{"mname":"醋","type":"0","amount":"适量"},{"mname":"酱油","type":"0","amount":"适量"},{"mname":"糖","type":"0","amount":"适量"},{"mname":"淀粉","type":"0","amount":"适量"},{"mname":"白菜","type":"1","amount":"380g"}]
     * process : [{"pcontent":"准备食材。","pic":"http://api.jisuapi.com/recipe/upload/20160719/162550_84583.jpg"},{"pcontent":"将白菜斜刀片成薄片。","pic":"http://api.jisuapi.com/recipe/upload/20160719/162551_90620.jpg"},{"pcontent":"片切好的白菜帮与菜叶分别入好。","pic":"http://api.jisuapi.com/recipe/upload/20160719/162551_20925.jpg"},{"pcontent":"盐、糖、生抽、醋淀粉加少许水调匀备用。","pic":"http://api.jisuapi.com/recipe/upload/20160719/162552_23125.jpg"},{"pcontent":"锅中油烧热，先入花椒炒香后捞出。再加入干红椒段略炒。","pic":"http://api.jisuapi.com/recipe/upload/20160719/162552_57046.jpg"},{"pcontent":"加入葱姜蒜煸炒香，然后入白菜帮翻炒。","pic":"http://api.jisuapi.com/recipe/upload/20160719/162553_89090.jpg"},{"pcontent":"炒至菜帮变软时，加入白菜叶。","pic":"http://api.jisuapi.com/recipe/upload/20160719/162553_40445.jpg"},{"pcontent":"快速翻炒至菜软，勾入碗汁","pic":"http://api.jisuapi.com/recipe/upload/20160719/162554_92210.jpg"},{"pcontent":"使汤汁均匀的包裹在菜帮上即可","pic":"http://api.jisuapi.com/recipe/upload/20160719/162554_29522.jpg"}]
     */

    private String id;
    private String classid;
    private String name;
    private String peoplenum;
    private String preparetime;
    private String cookingtime;
    private String content;
    private String pic;
    private String tag;
    private List<MaterialBean> material;
    private List<ProcessBean> process;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPeoplenum() {
        return peoplenum;
    }

    public void setPeoplenum(String peoplenum) {
        this.peoplenum = peoplenum;
    }

    public String getPreparetime() {
        return preparetime;
    }

    public void setPreparetime(String preparetime) {
        this.preparetime = preparetime;
    }

    public String getCookingtime() {
        return cookingtime;
    }

    public void setCookingtime(String cookingtime) {
        this.cookingtime = cookingtime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<MaterialBean> getMaterial() {
        return material;
    }

    public void setMaterial(List<MaterialBean> material) {
        this.material = material;
    }

    public List<ProcessBean> getProcess() {
        return process;
    }

    public void setProcess(List<ProcessBean> process) {
        this.process = process;
    }

    public static class MaterialBean {
        /**
         * mname : 油
         * type : 0
         * amount : 适量
         */

        private String mname;
        private String type;
        private String amount;

        public String getMname() {
            return mname;
        }

        public void setMname(String mname) {
            this.mname = mname;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
    }

    public static class ProcessBean {
        /**
         * pcontent : 准备食材。
         * pic : http://api.jisuapi.com/recipe/upload/20160719/162550_84583.jpg
         */

        private String pcontent;
        private String pic;

        public String getPcontent() {
            return pcontent;
        }

        public void setPcontent(String pcontent) {
            this.pcontent = pcontent;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }
    }
}

