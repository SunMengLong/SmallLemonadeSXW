package com.explem.smalllemonade.bean;

/**
 * Created by johpo on 2017/1/4 0004.
 */
/*{
        "status": "ok",
        "data": {
        "userId": "10124",
        "name": "godboy",
        "telNum": "18500704988",
        "password": "",
        "image_url": "http://119.255.40.206:8089/pictures/headimg/userhead/null"
        }
        }*/

public class Baocun {
   public String status;
    public BaBean data;
       public class BaBean{
          public String userId;
          public String  name;
          public String  telNum;
          public String  password;
          public String  image_url;
    }

}
