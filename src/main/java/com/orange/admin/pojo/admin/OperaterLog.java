package com.orange.admin.pojo.admin;


import com.orange.admin.commons.enums.ISDELETE;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;


/**
 * 后台操作日志记录表
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
public class OperaterLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,length = 11,unique = true)
    private Long id;

    @Column(nullable = false,length = 18)
    private String operator;
    @Column(nullable = false,length = 128)
    private String content;
    @Column(nullable = false,length = 128,updatable = false)
    @CreatedDate
    private Date createTime;
    @Column(nullable = false)
    @LastModifiedDate
    private Date updataTime;
    @Column(nullable = true)
    private String siteName;
//    1 是存在 0 是删除
    @Column(nullable = false)
    private Integer isDelete = ISDELETE.NO_DELETE.getType();


    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdataTime() {
        return updataTime;
    }

    public void setUpdataTime(Date updataTime) {
        this.updataTime = updataTime;
    }

    @Override
    public String toString() {
        return "OperaterLog{" +

                ", operator='" + operator + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", updataTime=" + updataTime +
                '}';
    }
}
