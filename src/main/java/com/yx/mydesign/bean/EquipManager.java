package com.yx.mydesign.bean;

public class EquipManager {
    private Integer id;

    private String equipmanagername;

    private String equipmanagerpassword;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEquipmanagername() {
        return equipmanagername;
    }

    public void setEquipmanagername(String equipmanagername) {
        this.equipmanagername = equipmanagername == null ? null : equipmanagername.trim();
    }

    public String getEquipmanagerpassword() {
        return equipmanagerpassword;
    }

    public void setEquipmanagerpassword(String equipmanagerpassword) {
        this.equipmanagerpassword = equipmanagerpassword == null ? null : equipmanagerpassword.trim();
    }
}