package com.geminifeng.community.model;
/**
 * @Author: SunBaoFeng
 * @Description: User
 * @Date: Created in 2020/5/24 23:18
 */

import lombok.Data;

/**
 * @Description
 * @ClassName User
 * @Author SunBaoFeng
 * @date 2020.05.24 23:18
 */
@Data
public class User {
    private Integer id;
    private String accountId;
    private String name;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
}
