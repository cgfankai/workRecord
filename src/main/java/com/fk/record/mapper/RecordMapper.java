package com.fk.record.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.fk.record.entity.Record;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Collection;
import java.util.List;

public interface RecordMapper extends BaseMapper<Record> {


    @Select("select distinct ${field} from record where is_deleted = 0 order by ${field}")
    Collection<String> queryDistinctField(@Param("field") String field);




}
