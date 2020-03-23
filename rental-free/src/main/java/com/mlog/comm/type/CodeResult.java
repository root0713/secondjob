package com.mlog.comm.type;

import java.io.Serializable;

public class CodeResult<T> implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1871389131198576885L;
    public static final String SUCCESS = "00";
    private String code;
    private String message;
    private int totalCnt;
    private long sDate;    // 시작시간 ( 로그처리 )
    private long eDate;    // 종료시간 ( 로그처리 )
    private String callUrl;    // 호출 URL ( 로그처리 )
    private T data;

    
    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

	public String getCallUrl() {
		return callUrl;
	}

	public void setCallUrl(String callUrl) {
		this.callUrl = callUrl;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public CodeResult() {
    }

    public CodeResult(T data) {
        this.data = data;
    }

    public CodeResult(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public CodeResult(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return SUCCESS.equals(code);
    }

    /*
     *  data 제외한 항목 복사 처리
     */
    public void copyExceptData(CodeResult<?> from) {
        this.setCode(from.getCode());
        this.setMessage(from.getMessage());
        this.setTotalCnt(from.getTotalCnt());
    }

    public void setCodeMsg(String message) {
        if (!message.isEmpty())
            this.message = message;
    }

    public long getsDate() {
        return sDate;
    }

    public long geteDate() {
        return eDate;
    }

    public void setsDate(long sDate) {
        this.sDate = sDate;
    }

    public void seteDate(long eDate) {
        this.eDate = eDate;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CodeResult [code=");
        builder.append(code);
        builder.append(", message=");
        builder.append(message);
        builder.append(", totalCnt=");
        builder.append(totalCnt);
        if (data != null) {
            builder.append(", data=");
            builder.append(data.toString());
        }
        builder.append("]");
        return builder.toString();
    }
}
