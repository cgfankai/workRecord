package com.fk.record.config;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.List;

public class Util {

    public static final ObjectMapper JSON_OBJ_MAPPER = new ObjectMapper();

    public static String HumpToUnderline(String para) {
        StringBuilder sb = new StringBuilder(para);
        int temp = 0;//定位
        if (!para.contains("_")) {
            for (int i = 0; i < para.length(); i++) {
                if (Character.isUpperCase(para.charAt(i))) {
                    sb.insert(i + temp, "_");
                    temp += 1;
                }
            }
        }
        return sb.toString().toLowerCase();
    }

    public static void updateQueryWrapperForFilters(AbstractWrapper queryWrapper, String filterStr) {
        List<FilterQuery> filterQueries = null;
        if (filterStr != null && !filterStr.trim().isEmpty()) {
            try {
                filterQueries = JSON_OBJ_MAPPER.readValue(filterStr, new TypeReference<List<FilterQuery>>() {
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (filterQueries == null || filterQueries.isEmpty()) {
            return;
        } else {
            for (FilterQuery filterQuery : filterQueries) {
                if (filterQuery.getValue() != null && !filterQuery.getValue().trim().isEmpty()) {
                    String value = filterQuery.getValue().trim();
                    if (filterQuery.isEqual()) {
                        queryWrapper.eq(HumpToUnderline(filterQuery.getField()), value);
                    }
                    if (filterQuery.isContain()) {
                        queryWrapper.like(
                                HumpToUnderline(filterQuery.getField()),
                                value.replaceAll("\\[", "[[]")
                                        .replaceAll("%", "[%]")

                        );
                    }
                    if (filterQuery.isIn()) {
                        queryWrapper.in(HumpToUnderline(filterQuery.getField()), value.split(","));
                    }
                    if (filterQuery.isGreaterTo()) {
                        queryWrapper.gt(HumpToUnderline(filterQuery.getField()), value);
                    }
                    if (filterQuery.isGreaterOrEqualTo()) {
                        queryWrapper.ge(HumpToUnderline(filterQuery.getField()), value);
                    }
                    if (filterQuery.isLessTo()) {
                        queryWrapper.lt(HumpToUnderline(filterQuery.getField()), value);
                    }
                    if (filterQuery.isLessOrEqualTo()) {
                        queryWrapper.le(HumpToUnderline(filterQuery.getField()), value);
                    }
                }
            }
        }
    }

}
