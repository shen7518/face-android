package com.example.face.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class LoginVo {
   private String token;
   private int uid;
}
