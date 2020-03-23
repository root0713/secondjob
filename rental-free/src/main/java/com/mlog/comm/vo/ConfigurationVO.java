package com.mlog.comm.vo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfigurationVO implements Serializable {
    private static final long serialVersionUID = -5724617471320577541L;
    public String flag;
    public String message;
    public int total_count;
    public List<ConfigurationInfoVO> recordset;
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getTotal_count() {
		return total_count;
	}
	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}
	public List<ConfigurationInfoVO> getRecordset() {
		return recordset;
	}
	public void setRecordset(List<ConfigurationInfoVO> recordset) {
		this.recordset = recordset;
	}
    
}
