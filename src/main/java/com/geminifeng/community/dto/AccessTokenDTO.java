package com.geminifeng.community.dto;/**
 * description: AccessTokenDTO <br>
 * date: 2020/5/24 18:11 <br>
 * author: lenovo <br>
 * version: 1.0 <br>
 */

import lombok.Data;

/**
 * @Description
 * @ClassName AccessTokenDTO
 * @Author SunBaoFeng
 * @date 2020.05.24 18:11
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
