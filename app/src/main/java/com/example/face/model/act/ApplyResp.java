package com.example.face.model.act;

import lombok.Data;

@Data
public class ApplyResp {
    private int id;
    private int uid;
    private Integer status;
    /************活动属性 start**************/
    private long aid;
    private String title;
    private String address;
    private String picture;
    /************活动属性 end**************/

}

