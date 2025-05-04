package com.ttknp.abcmodelsservice.models.mysql;


public class DetailMYSQL {

    private Long did;
    private String code;

    public DetailMYSQL(Long did, String code) {
        this.did = did;
        this.code = code;
    }

    public Long getDid() {
        return did;
    }

    public void setDid(Long did) {
        this.did = did;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
