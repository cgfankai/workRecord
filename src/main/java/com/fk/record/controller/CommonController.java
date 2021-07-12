package com.fk.record.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.override.MybatisMapperProxy;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fk.record.entity.Common;
import com.fk.record.entity.Record;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public interface CommonController<T extends Common> {


    public BaseMapper<T> getMapper();

    @RequestMapping("/query")
    public default Map<String, Object> list(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "rows", defaultValue = "20") Integer rows) {
        Map<String, Object> rlt = new HashMap<>();
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        if (this.getClass().getName().contains("Record")) {
            queryWrapper.orderByDesc(new ArrayList<String>(2) {
                {
                    this.add("task_date");
                    this.add("system_name");
                } });

        }
        IPage<T> iPage = this.getMapper().selectPage(new Page<T>(page, rows), queryWrapper);
        rlt.put("total", iPage.getTotal());
        rlt.put("rows", iPage.getRecords());
        return rlt;
    }

    @RequestMapping("/query/{id}")
    public default T getOneById(@PathVariable("id") Integer id) {
        return this.getMapper().selectById(id);
    }

    @RequestMapping("/save")
    public default T updateById(T t) {
        if (t.getId() == null) {
            this.getMapper().insert(t);
        } else {
            this.getMapper().updateById(t);
        }
        return t;
    }

    @RequestMapping("/delete/{id}")
    public default Object deleteById(@PathVariable("id") Integer id) {
        return this.getMapper().deleteById(id);
    }


}
