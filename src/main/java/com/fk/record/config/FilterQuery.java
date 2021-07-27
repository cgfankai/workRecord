package com.fk.record.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)

public class FilterQuery {

    private String field;

    private String op;

    private String value;


    public boolean isEqual() {
        if (this.op.equals("equal") || this.op.equals("eq")) {
            return true;
        }
        return false;
    }

    public boolean isContain() {
        if (this.op.equals("contain") || this.op.equals("contains") || this.op.equals("like")) {
            return true;
        }
        return false;
    }

    public boolean isIn() {
        if (this.op.equals("in")) {
            return true;
        }
        return false;
    }

    public boolean isGreaterTo() {
        if (this.op.equals("gt")) {
            return true;
        }
        return false;
    }

    public boolean isGreaterOrEqualTo() {
        if (this.op.equals("ge")) {
            return true;
        }
        return false;
    }

    public boolean isLessTo() {
        if (this.op.equals("lt")) {
            return true;
        }
        return false;
    }

    public boolean isLessOrEqualTo() {
        if (this.op.equals("le")) {
            return true;
        }
        return false;
    }


}