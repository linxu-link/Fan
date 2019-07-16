package com.link.component_search.data.entity;

import java.util.List;

public class MenuDetail {


    /**
     * id : 4323
     * title : 香甜草莓可可卷
     * tags : 早餐;烘焙;甜品;甜;可可粉;蛋糕;烤箱;普通;卷;蛋卷;可可卷
     * imtro : 奶油可可卷总是十分受人欢迎，但是现在卖的都是一些食品添加剂，而且原料都有卖的，因此还不如自己在家里做比较好。
     * ingredients : 大号鸡蛋,4个;低粉,55g
     * burden : 可可粉,25g;砂糖,85g;色拉油,50ml;热开水,60ml;淡奶油,150g;浓奶油,100g
     * albums : ["http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/5/4323_224573.jpg"]
     * steps : [{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/44/4323_3adcc38469c97069.jpg","step":"1.用烤纸叠好28cmX28cm的方盒，还可以叠的再深一点哦!后面会比较方便.低粉和可可粉混合过筛。蛋黄和蛋白分开"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/44/4323_ee10e56d09611723.jpg","step":"2.蛋白打散出泡后，砂糖一半的量分3次加入蛋白里，直到把蛋白打到硬性发泡"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/44/4323_4b915736e78e9d97.jpg","step":"3.把余下的砂糖加到蛋黄里打到稍微有点发白.再把加入色拉油用打蛋器搅匀后，加入热水搅匀."},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/44/4323_ceab91948709ec31.jpg","step":"4.把面粉和可可粉筛入..搅拌均匀."},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/44/4323_d28ae9483bb9f509.jpg","step":"5.加入1/3打发的蛋白用打蛋器搅匀,然后再加入另外1/3的蛋白继续搅匀."},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/44/4323_4c7287f1e18def35.jpg","step":"6.再加入剩余的1/3蛋白.用刮刀搅拌均匀!"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/44/4323_742c67ff267575ea.jpg","step":"7.把搅拌均匀的液体倒入烤盘里，用平铲刮平表面抬起烤盘顿几下排掉气泡后，180度烤15分钟左右。"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/44/4323_26472a2367b74247.jpg","step":"8.烤完出炉以后用保鲜膜包裹住..保持蛋糕湿润."},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/44/4323_01ac5a39ccabd893.jpg","step":"9.准备奶油,我用的是35%鲜奶油150克和48%double cream100克混合."},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/44/4323_61ac5b449b4ed2b8.jpg","step":"10.先用一部分35%的奶油搅匀48%的重奶油后，再倒入余下的35%的奶油和糖25克搅匀后打发到八分程度（能挤花的程度)"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/44/4323_e986e2330c590139.jpg","step":"11.面板上铺一层蜡纸或烤纸，把之前烤好的蛋糕上的保鲜膜揭下后倒扣在纸上，再揭下上面的烤纸。把7的奶油铺到蛋糕上用抹刀抹平，注意两边不要铺太满.然后排上草莓.蛋糕片的起始端用刀隔适当距离切1厘米的刀口.方便等下卷."},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/44/4323_73228729db603499.jpg","step":"12.然后就把蛋糕慢慢卷起来..看图片很明显我卷失败了- -这个根据我失败的经验,卷的时候卷大一点第一层的时候就把第一排草莓卷进去.我卷的太小都挤破了..不过还是一样好吃!哈哈"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/44/4323_8c32e7986f13764d.jpg","step":"13.就是右边的那颗草莓应该在蛋糕皮的下面而不是上面!"}]
     */

    private String id;
    private String title;
    private String tags;
    private String imtro;
    private String ingredients;
    private String burden;
    private List<String> albums;
    private List<StepsBean> steps;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getImtro() {
        return imtro;
    }

    public void setImtro(String imtro) {
        this.imtro = imtro;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getBurden() {
        return burden;
    }

    public void setBurden(String burden) {
        this.burden = burden;
    }

    public List<String> getAlbums() {
        return albums;
    }

    public void setAlbums(List<String> albums) {
        this.albums = albums;
    }

    public List<StepsBean> getSteps() {
        return steps;
    }

    public void setSteps(List<StepsBean> steps) {
        this.steps = steps;
    }

    public static class StepsBean {
        /**
         * img : http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/44/4323_3adcc38469c97069.jpg
         * step : 1.用烤纸叠好28cmX28cm的方盒，还可以叠的再深一点哦!后面会比较方便.低粉和可可粉混合过筛。蛋黄和蛋白分开
         */

        private String img;
        private String step;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getStep() {
            return step;
        }

        public void setStep(String step) {
            this.step = step;
        }
    }
}
