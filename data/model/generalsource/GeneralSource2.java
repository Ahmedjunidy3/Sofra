
package com.example.sofra.data.model.generalsource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeneralSource2 {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private GeneralSourceData data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public GeneralSourceData getData() {
        return data;
    }

    public void setData(GeneralSourceData data) {
        this.data = data;
    }

}
