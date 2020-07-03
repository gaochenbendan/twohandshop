package com.orange.admin.pojo.admin;

import com.orange.admin.annotion.ValidateAnnotion;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Role {

    public static final int ADMIN_ROLE_STATUS_ENABLE = 1;//角色状态正常可用
    public static final int ADMIN_ROLE_STATUS_UNABLE = 0;//角色状态不可用

    @Column(nullable=false,length=11)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long id;

    @ValidateAnnotion(required=true,requiredLength=true,minLength=1,maxLength=18,errorRequiredMsg="角色名称不能为空!",errorMinlenthMsg="角色名称长度需大于1!",errorMaxlenthMsg="角色名称长度不能大于18!")
    @Column(nullable=false,length=18)
    private String name;//角色名称

    @ValidateAnnotion(required=false)
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Menu> authorities;//角色所对应的权限（菜单）列表

    @ValidateAnnotion(required=false)
    @Column(length=1)
    private int status = ADMIN_ROLE_STATUS_ENABLE;//角色状态,默认可用

    @ValidateAnnotion(required=false)
    @Column(length=128)
    private String remark;//角色备注

    @Column(nullable=false)
    @CreatedDate
    private Date createTime;//创建时间

    @Column(nullable=false)
    @LastModifiedDate
    private Date updateTime;//更新时间

    public Role() {
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", authorities=" + authorities +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public static int getAdminRoleStatusEnable() {
        return ADMIN_ROLE_STATUS_ENABLE;
    }

    public static int getAdminRoleStatusUnable() {
        return ADMIN_ROLE_STATUS_UNABLE;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Menu> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Menu> authorities) {
        this.authorities = authorities;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
