package com.explem.smalllemonade.bean;

/**
 * Created by asus on 2017/1/4.
 */

public class Home_Fragment_ZhuGong {

    /**
     * code : 1
     * data : {"festivalId":7,"festivalTime":1485532800000,"holidayDetails":"http://www.yulin520.com/a2a/h/i/app/next_festival","img":"http://img1.yulin520.com/news/JULIM57SWHT0OA56ZQ2N.png#210_210","name":"春节-  1.28","remark":"http://www.yulin520.com/a2a/h/i/yulin/h5_festival"}
     * height : 0
     * success : true
     * width : 0
     */

    private int code;
    /**
     * festivalId : 7
     * festivalTime : 1485532800000
     * holidayDetails : http://www.yulin520.com/a2a/h/i/app/next_festival
     * img : http://img1.yulin520.com/news/JULIM57SWHT0OA56ZQ2N.png#210_210
     * name : 春节-  1.28
     * remark : http://www.yulin520.com/a2a/h/i/yulin/h5_festival
     */

    private DataBean data;
    private int height;
    private boolean success;
    private int width;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
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

    public static class DataBean {
        private int festivalId;
        private long festivalTime;
        private String holidayDetails;
        private String img;
        private String name;
        private String remark;

        public int getFestivalId() {
            return festivalId;
        }

        public void setFestivalId(int festivalId) {
            this.festivalId = festivalId;
        }

        public long getFestivalTime() {
            return festivalTime;
        }

        public void setFestivalTime(long festivalTime) {
            this.festivalTime = festivalTime;
        }

        public String getHolidayDetails() {
            return holidayDetails;
        }

        public void setHolidayDetails(String holidayDetails) {
            this.holidayDetails = holidayDetails;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}
