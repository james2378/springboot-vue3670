package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *资料分类：(DataClassification)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "DataClassification")
public class DataClassification implements Serializable {

    //DataClassification编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "data_classification_id")
    private Integer data_classification_id;
   // 资料类型
   @Basic
    private String data_type;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
