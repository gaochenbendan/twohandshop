package com.orange.admin.commons.enums;

/**
 * 是否删除
 */
public enum ISDELETE {
    //    1 是存在 0 是删除
    NO_DELETE("存在", 1), HAVA_DELETE("已经删除", 0);
    // 成员变量
    private String name;
    private int type;
    // 构造方法

    ISDELETE(String name, int type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
