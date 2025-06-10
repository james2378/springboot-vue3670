package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *学习统计：(LearningStatistics)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "LearningStatistics")
public class LearningStatistics implements Serializable {

    //LearningStatistics编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "learning_statistics_id")
    private Integer learning_statistics_id;
   // 班级名称
   @Basic
    private String class_name;
   // 学习次数
   @Basic
    private Integer learning_times;
   // 讨论次数
   @Basic
    private Integer number_of_discussions;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
