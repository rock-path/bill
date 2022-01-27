package com.psh.hik.domain;

import java.io.Serializable;

/**
 * @author softstone71.hik.
 * @DATE 2021/9/24.
 * @time 10:46.
 */
public class BaseResultModel<T> implements Serializable {

    public static final String NAME = "resultModel";
    public static final Boolean SUCCESS = true;
    public static final String SUCCESS_CODE = "0";
    public static final String SUCCESS_MSG = "操作成功";
    public static final Boolean ERROR = false;
    public static final String ERROR_CODE = "-1";
    public String message = "";
    private String code = "0";
    private int httpStatus;
    private T data;
    private Long count = -1L;

    public BaseResultModel(String code, String message, T data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public BaseResultModel() {
    }

    public BaseResultModel(T data) {
        this.data = data;
    }

    public static <T> BaseResultModel<T> success() {
        return new BaseResultModel("0", "操作成功", (Object) null);
    }

    public static <T> BaseResultModel<T> success(T data) {
        return new BaseResultModel("0", "操作成功", data);
    }

    public static <T> BaseResultModel<T> fail(String code, String message) {
        BaseResultModel<T> result = new BaseResultModel();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static <T> BaseResultModel<T> fail(String message) {
        return fail("-1", message);
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getHttpStatus() {
        return this.httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Long getCount() {
        return this.count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof BaseResultModel)) {
            return false;
        } else {
            BaseResultModel<?> other = (BaseResultModel) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label63:
                {
                    Object this$message = this.getMessage();
                    Object other$message = other.getMessage();
                    if (this$message == null) {
                        if (other$message == null) {
                            break label63;
                        }
                    } else if (this$message.equals(other$message)) {
                        break label63;
                    }

                    return false;
                }

                Object this$code = this.getCode();
                Object other$code = other.getCode();
                if (this$code == null) {
                    if (other$code != null) {
                        return false;
                    }
                } else if (!this$code.equals(other$code)) {
                    return false;
                }

                if (this.getHttpStatus() != other.getHttpStatus()) {
                    return false;
                } else {
                    Object this$data = this.getData();
                    Object other$data = other.getData();
                    if (this$data == null) {
                        if (other$data != null) {
                            return false;
                        }
                    } else if (!this$data.equals(other$data)) {
                        return false;
                    }

                    Object this$count = this.getCount();
                    Object other$count = other.getCount();
                    if (this$count == null) {
                        if (other$count != null) {
                            return false;
                        }
                    } else if (!this$count.equals(other$count)) {
                        return false;
                    }

                    return true;
                }
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof BaseResultModel;
    }

    @Override
    public int hashCode() {
        Boolean PRIME = true;
        int result = 1;
        Object $message = this.getMessage();
        result = result * 59 + ($message == null ? 43 : $message.hashCode());
        Object $code = this.getCode();
        result = result * 59 + ($code == null ? 43 : $code.hashCode());
        result = result * 59 + this.getHttpStatus();
        Object $data = this.getData();
        result = result * 59 + ($data == null ? 43 : $data.hashCode());
        Object $count = this.getCount();
        result = result * 59 + ($count == null ? 43 : $count.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "BaseResultModel(message=" + this.getMessage() + ", code=" + this.getCode() + ", httpStatus=" + this.getHttpStatus() + ", data=" + this.getData() + ", count=" + this.getCount() + ")";
    }

}
