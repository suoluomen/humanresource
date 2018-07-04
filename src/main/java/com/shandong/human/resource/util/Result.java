package com.shandong.human.resource.util;

import com.shandong.human.resource.util.config.CompositeFactory;
import org.apache.log4j.Logger;

/**
 * 处理结果类
 *
 * @author tyee.noprom@qq.com
 * @time 2/5/16 9:16 AM.
 */
public class Result {

    public enum Status {
        ERROR, SUCCESS
    }

    private static final Logger logger = Logger.getLogger(Result.class);

    // 操作结果
    private Status status;

    // 错误信息
    private String info;

    // 附属对象
    private Object object;

    public Result(Status status, String info) {
        super();
        this.status = status;
        this.info = CompositeFactory.getInstance().getString(info);
    }

    public Result(Status status, String info, Object object) {
        super();
        this.status = status;
        this.info = CompositeFactory.getInstance().getString(info);
        this.object = object;
    }

    public static Logger getLogger() {
        return logger;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
