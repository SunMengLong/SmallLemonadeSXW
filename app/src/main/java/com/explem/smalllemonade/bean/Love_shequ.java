package com.explem.smalllemonade.bean;

import java.util.List;

/**
 * Created by asus on 2017/1/3.
 */

public class Love_shequ {

    /**
     * code : 1
     * height : 0
     * width : 0
     * success : true
     * data : [{"headImg":"http://photo-60481.oss-cn-shenzhen.aliyuncs.com/%E6%99%93%E4%B8%BD%E5%A4%B4%E5%83%8F3/70.png","userRole":null,"forumId":10459,"floor":"1楼","floorReplyTimes":0,"createTime":1482896623000,"yulin":"93595","userId":7828,"phone":"","frmList":[],"imgs":[],"userName":"奥特蛋@","id":26346,"host":0,"content":"长篇大论的我是看了。\n\n不做点评。\n\n我就直接告诉你，\n\n妄图控制男人思想的女人，都是大傻X。"},{"headImg":"http://photo-60481.oss-cn-shenzhen.aliyuncs.com/%E5%B0%8F%E5%AE%87%E7%9A%84%E5%A4%B4%E5%83%8F%E7%AC%AC%E4%BA%8C%E6%B3%A2/109.jpg","userRole":null,"forumId":10459,"floor":"2楼","floorReplyTimes":0,"createTime":1482902786000,"yulin":"93337","userId":7742,"phone":"","frmList":[],"imgs":[],"userName":"九泉家的小熊","id":26347,"host":0,"content":"心里有朵放不下的白莲花的男人千万别碰！\n男人这么多，还有好多纯真的小处男呢，干嘛要在这种注定得不到爱的人身上期待爱呢？\n\n同遇到过这种硬件无可挑剔但是心里还有白莲花的完美王老五，感觉是一块蛋糕上面沾了一坨呕吐物。再诱人也没法吃。"},{"headImg":"http://photo-60481.oss-cn-shenzhen.aliyuncs.com/%E5%B0%8F%E5%AE%87%E7%9A%84%E5%A4%B4%E5%83%8F%E7%AC%AC%E4%BA%8C%E6%B3%A2/113.jpg","userRole":null,"forumId":10459,"floor":"3楼","floorReplyTimes":0,"createTime":1482917430000,"yulin":"94645","userId":8178,"phone":"","frmList":[],"imgs":[],"userName":"-Ethereally","id":26348,"host":0,"content":"我是男生,如果我真喜欢你,注意力就只会放在你身上,以前的恋人只会回忆.\n\n1.如果我只是偶尔想想,没有和前任聊,没有撩,无出格举动你却介意的话,你有病.\n\n2.我和前任有联系,有聊有撩,尽早分.男生出轨迟早的事."},{"headImg":"http://photo-60481.oss-cn-shenzhen.aliyuncs.com/photo/42.gif","userRole":null,"forumId":10459,"floor":"4楼","floorReplyTimes":0,"createTime":1482920577000,"yulin":"95815","userId":8508,"phone":"","frmList":[],"imgs":[],"userName":"julietevening","id":26349,"host":0,"content":"他没那么喜欢你。按照这样的趋势发展下去很难有好结果。"}]
     * message : null
     */

    private int code;
    private int height;
    private int width;
    private boolean success;
    private Object message;
    /**
     * headImg : http://photo-60481.oss-cn-shenzhen.aliyuncs.com/%E6%99%93%E4%B8%BD%E5%A4%B4%E5%83%8F3/70.png
     * userRole : null
     * forumId : 10459
     * floor : 1楼
     * floorReplyTimes : 0
     * createTime : 1482896623000
     * yulin : 93595
     * userId : 7828
     * phone :
     * frmList : []
     * imgs : []
     * userName : 奥特蛋@
     * id : 26346
     * host : 0
     * content : 长篇大论的我是看了。

     不做点评。

     我就直接告诉你，

     妄图控制男人思想的女人，都是大傻X。
     */

    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String headImg;
        private Object userRole;
        private int forumId;
        private String floor;
        private int floorReplyTimes;
        private long createTime;
        private String yulin;
        private int userId;
        private String phone;
        private String userName;
        private int id;
        private int host;
        private String content;
        private List<?> frmList;
        private List<?> imgs;

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public Object getUserRole() {
            return userRole;
        }

        public void setUserRole(Object userRole) {
            this.userRole = userRole;
        }

        public int getForumId() {
            return forumId;
        }

        public void setForumId(int forumId) {
            this.forumId = forumId;
        }

        public String getFloor() {
            return floor;
        }

        public void setFloor(String floor) {
            this.floor = floor;
        }

        public int getFloorReplyTimes() {
            return floorReplyTimes;
        }

        public void setFloorReplyTimes(int floorReplyTimes) {
            this.floorReplyTimes = floorReplyTimes;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getYulin() {
            return yulin;
        }

        public void setYulin(String yulin) {
            this.yulin = yulin;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getHost() {
            return host;
        }

        public void setHost(int host) {
            this.host = host;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public List<?> getFrmList() {
            return frmList;
        }

        public void setFrmList(List<?> frmList) {
            this.frmList = frmList;
        }

        public List<?> getImgs() {
            return imgs;
        }

        public void setImgs(List<?> imgs) {
            this.imgs = imgs;
        }
    }
}
