package com.explem.smalllemonade.bean;

import java.util.List;

/**
 * Created by ${薛亚南}
 * on 2017/1/9 12：58.
 */

public class FmcommentBean {

    /**
     * code : 1
     * height : 0
     * success : true
     * width : 0
     * data : [{"broadcastReplyMutualPO":[],"headImg":null,"hxKey":"29826af95d5316b64d6dd8809715dd08","img":"http://photo-60481.oss-cn-shenzhen.aliyuncs.com/%E6%99%93%E4%B8%BD%E5%A4%B4%E5%83%8F3/113.jpg","yulin":"93193","name":"偏心你i","id":87,"content":"我之前就是这样，发的朋友圈就特别希望他评论。。。","time":1483927105000},{"broadcastReplyMutualPO":[],"headImg":null,"hxKey":"45ed67c194ed403f2dcfc9dc5ad180e4","img":"http://wx.qlogo.cn/mmopen/XOJ0SOWibYcw66ofzSuLPBHs7p8AckibmFrrGgYhA2ZAJ5xLdbecRtG19RbQOphu7WiaZBK1ZJ4NhbD1urhpLM9HSf1icqfmSQsu/0","yulin":"88468","name":"Jacky","id":81,"content":"感同身受咯","time":1483608285000},{"broadcastReplyMutualPO":[],"headImg":null,"hxKey":"45ed67c194ed403f2dcfc9dc5ad180e4","img":"http://wx.qlogo.cn/mmopen/XOJ0SOWibYcw66ofzSuLPBHs7p8AckibmFrrGgYhA2ZAJ5xLdbecRtG19RbQOphu7WiaZBK1ZJ4NhbD1urhpLM9HSf1icqfmSQsu/0","yulin":"88468","name":"Jacky","id":82,"content":"感同身受咯","time":1483608285000},{"broadcastReplyMutualPO":[],"headImg":null,"hxKey":"f9bd2eb2224e23a355371a899ea7d1f6","img":"http://img1.yulin520.com/head/BW0QPIS0DIK0O8QSJOKF.jpg","yulin":"97582","name":"啦啦","id":76,"content":"哈哈","time":1483509940000}]
     * message : null
     */

    private int code;
    private int height;
    private boolean success;
    private int width;
    private Object message;
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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
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
        /**
         * broadcastReplyMutualPO : []
         * headImg : null
         * hxKey : 29826af95d5316b64d6dd8809715dd08
         * img : http://photo-60481.oss-cn-shenzhen.aliyuncs.com/%E6%99%93%E4%B8%BD%E5%A4%B4%E5%83%8F3/113.jpg
         * yulin : 93193
         * name : 偏心你i
         * id : 87
         * content : 我之前就是这样，发的朋友圈就特别希望他评论。。。
         * time : 1483927105000
         */

        private Object headImg;
        private String hxKey;
        private String img;
        private String yulin;
        private String name;
        private int id;
        private String content;
        private long time;
        private List<?> broadcastReplyMutualPO;

        public Object getHeadImg() {
            return headImg;
        }

        public void setHeadImg(Object headImg) {
            this.headImg = headImg;
        }

        public String getHxKey() {
            return hxKey;
        }

        public void setHxKey(String hxKey) {
            this.hxKey = hxKey;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getYulin() {
            return yulin;
        }

        public void setYulin(String yulin) {
            this.yulin = yulin;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public List<?> getBroadcastReplyMutualPO() {
            return broadcastReplyMutualPO;
        }

        public void setBroadcastReplyMutualPO(List<?> broadcastReplyMutualPO) {
            this.broadcastReplyMutualPO = broadcastReplyMutualPO;
        }
    }
}
