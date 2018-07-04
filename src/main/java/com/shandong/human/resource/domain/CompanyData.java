package com.shandong.human.resource.domain;

import com.shandong.human.resource.util.RegExpUtil;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Pattern;
import java.util.Date;
/**
 * 公司上报数据实体类
 * <p>
 * Author: constantine <1194479264@qq.com>
 * Date: 16/3/11 上午10:04
 */
public class CompanyData {
    private int id;                 //主键
    private int pid;                //父id，默认为0，表示该数据没有被修改，若不为0，则可以通过字段查找到原始数据
    private int company_id;         //company中的id
    private int survey_time_id;     //调查时间id

    //@Range( min = 0 ,max = 214783647)
    private Integer init_people;        //建档期就业人数,填写初次将档时监测点就业人数

   // @Range( min = 0 ,max = 214783647)
    private Integer cur_people;         //调查期就业人数,填写本次调查期当时的监测点就业人数

    @Pattern(regexp = "(.){1,255}")
    private String other_reason;    //其他原因

    @Pattern(regexp = "(.){1,255}")
    private String reduce_type;     //减少原因

    @Pattern(regexp = "(.){1,255}")
    private String reason1;         //原因1

    @Pattern(regexp = "(.){1,255}")
    private String reason1_explain; //原因1解释

    @Pattern(regexp = "(.){1,255}")
    private String reason2;         //原因2

    @Pattern(regexp = "(.){1,255}")
    private String reason2_explain; //原因2解释

    @Pattern(regexp = "(.){1,255}")
    private String reason3;         //原因3

    @Pattern(regexp = "(.){1,255}")
    private String reason3_explain; //原因3解释
    private int status;             //状态:-1:审核不通过,0:等待审核,1:审核通过

    private String not_pass_reason; //审核不通过原因
    private Date create_time;       //创建时间
    private Date update_time;       //更新时间


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public int getSurvey_time_id() {
        return survey_time_id;
    }

    public void setSurvey_time_id(int survey_time_id) {
        this.survey_time_id = survey_time_id;
    }

    public Integer getCur_people() {
        return cur_people;
    }

    public Integer getInit_people() {
        return init_people;
    }

    public void setCur_people(Integer cur_people) {
        this.cur_people = cur_people;
    }

    public void setInit_people(Integer init_people) {
        this.init_people = init_people;
    }

    public String getOther_reason() {
        return other_reason;
    }

    public void setOther_reason(String other_reason) {
        this.other_reason = other_reason;
    }

    public String getReduce_type() {
        return reduce_type;
    }

    public void setReduce_type(String reduce_type) {
        this.reduce_type = reduce_type;
    }

    public String getReason1() {
        return reason1;
    }

    public void setReason1(String reason1) {
        this.reason1 = reason1;
    }

    public String getReason1_explain() {
        return reason1_explain;
    }

    public void setReason1_explain(String reason1_explain) {
        this.reason1_explain = reason1_explain;
    }

    public String getReason2() {
        return reason2;
    }

    public void setReason2(String reason2) {
        this.reason2 = reason2;
    }

    public String getReason2_explain() {
        return reason2_explain;
    }

    public void setReason2_explain(String reason2_explain) {
        this.reason2_explain = reason2_explain;
    }

    public String getReason3() {
        return reason3;
    }

    public void setReason3(String reason3) {
        this.reason3 = reason3;
    }

    public String getReason3_explain() {
        return reason3_explain;
    }

    public void setReason3_explain(String reason3_explain) {
        this.reason3_explain = reason3_explain;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNot_pass_reason() {
        return not_pass_reason;
    }

    public void setNot_pass_reason(String not_pass_reason) {
        this.not_pass_reason = not_pass_reason;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompanyData that = (CompanyData) o;

        if (id != that.id) return false;
        if (pid != that.pid) return false;
        if (company_id != that.company_id) return false;
        if (survey_time_id != that.survey_time_id) return false;
        if (init_people != that.init_people) return false;
        if (cur_people != that.cur_people) return false;
        if (status != that.status) return false;
        if (!other_reason.equals(that.other_reason)) return false;
        if (!reduce_type.equals(that.reduce_type)) return false;
        if (!reason1.equals(that.reason1)) return false;
        if (!reason1_explain.equals(that.reason1_explain)) return false;
        if (!reason2.equals(that.reason2)) return false;
        if (!reason2_explain.equals(that.reason2_explain)) return false;
        if (!reason3.equals(that.reason3)) return false;
        if (!reason3_explain.equals(that.reason3_explain)) return false;
        if (!not_pass_reason.equals(that.not_pass_reason)) return false;
        if (!create_time.equals(that.create_time)) return false;
        return update_time.equals(that.update_time);

    }

}
