package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *课程管理：(CourseManagement)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "CourseManagement")
public class CourseManagement implements Serializable {

    //CourseManagement编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_management_id")
    private Integer course_management_id;
   // 课程编号
   @Basic
    private String course_number;
   // 课程名称
   @Basic
    private String course_name;
   // 学时
   @Basic
    private String class_hours;
   // 任课教师
   @Basic
    private Integer teacher;
   // 学分
   @Basic
    private String credit;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
