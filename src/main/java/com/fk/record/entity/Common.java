package com.fk.record.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Common {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer isDeleted;

}
