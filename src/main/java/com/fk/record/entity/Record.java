package com.fk.record.entity;

import lombok.Data;


@Data
public class Record extends Common{

    private String systemName;

    private String taskDetail;

    private String taskDate;

    private String period;

}
