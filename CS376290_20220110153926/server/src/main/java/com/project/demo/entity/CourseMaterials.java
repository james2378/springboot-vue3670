package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *课程资料：(CourseMaterials)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "CourseMaterials")
public class CourseMaterials implements Serializable {

    //CourseMaterials编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_materials_id")
    private Integer course_materials_id;
   // 课程编号
   @Basic
    private String course_number;
   // 课程名称
   @Basic
    private String course_name;
   // 任课教师
   @Basic
    private Integer teacher;
   // 资料名称
   @Basic
    private String data_name;
   // 资料类型
   @Basic
    private String data_type;
   // 附件
   @Basic
    private String enclosure;
   // 课程视频
   @Basic
    private String course_video;
   // 封面
   @Basic
    private String cover;
   // 资料介绍
   @Basic
    private String information_introduction;
    // 点击数
    @Basic
    private Integer hits;
    // 点赞数
    @Basic
    private Integer praise_len;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
