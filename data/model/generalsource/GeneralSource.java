
package com.example.sofra.data.model.generalsource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeneralSource {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private GeneralSourceDataPagination data;

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

    public GeneralSourceDataPagination getData() {
        return data;
    }

    public void setCategoryData(GeneralSourceDataPagination data) {
        this.data = data;
    }

}
