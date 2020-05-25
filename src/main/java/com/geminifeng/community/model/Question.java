package com.geminifeng.community.model;
/**
 * @Author: SunBaoFeng
 * @Description: Question
 * @Date: Created in 2020/5/26 0:03
 */

import lombok.Data;

/**
 * @Description
 * @ClassName Question
 * @Author SunBaoFeng
 * @date 2020.05.26 00:03
 */
@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
}
