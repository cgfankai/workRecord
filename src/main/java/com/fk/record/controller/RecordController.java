package com.fk.record.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fk.record.entity.Record;
import com.fk.record.mapper.RecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/record")
@CrossOrigin
public class RecordController implements CommonController<Record> {

    private final RecordMapper recordMapper;

    public RecordController(RecordMapper recordMapper) {
        this.recordMapper = recordMapper;
    }


    @RequestMapping("/distinct/{field}")
    public List<Map<String, String>> queryDistinctField(@PathVariable("field") String field) {
        Collection<String> collection = recordMapper.queryDistinctField(field);
        if (collection != null) {
            List<Map<String, String>> rlt = new ArrayList<>(collection.size());
            collection.forEach(item -> {
                Map<String, String> map = new HashMap<>();
                map.put("id", item);
                map.put("text", item);
                rlt.add(map);
            });
            return rlt;
        }
        return new ArrayList<>();
    }


    @RequestMapping("/export/range/date")
    public Object exportByRangeDate(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().le(Record::getTaskDate, endDate)
                .ge(Record::getTaskDate, startDate)
                .orderByAsc(Record::getTaskDate);
        List<Record> records = this.recordMapper.selectList(queryWrapper);
        Map<String, List<Record>> mapBySysName = records.stream().collect(Collectors.groupingBy(Record::getSystemName));
        StringBuilder stringBuilder = new StringBuilder();
        mapBySysName.forEach((key, res) -> {
            stringBuilder.append("【" + key + "】\n");
            for (int i = 0; i < res.size(); i++) {
                stringBuilder.append((i + 1) + "、" + res.get(i).getTaskDetail() + "\n");
            }
        });
        return stringBuilder.toString();
    }


    @Override
    public BaseMapper getMapper() {
        return recordMapper;
    }
}
