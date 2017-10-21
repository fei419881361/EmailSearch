package com.query.utils;

/**
 * Created by exphuhong on 17-10-16.
 * Start
 *
 */
public class EmailRow {

    private String email;
    private int resultCount;
    private String status;  //邮箱格式不正确=-1 异常=0　正常=1

    public EmailRow() {
    }

    public EmailRow(String email) {
        this.email = email;
    }
    public EmailRow(String email, int resultCount, String status) {
        this.email = email;
        this.resultCount = resultCount;
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
