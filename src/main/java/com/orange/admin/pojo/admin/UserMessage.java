package com.orange.admin.pojo.admin;

import com.orange.admin.annotion.ValidateAnnotion;
import com.orange.admin.commons.enums.ISDELETE;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class UserMessage {


    private static final int SEX_MAN = 1;
    private static final int SEX_WOMAN = 2;
    private static final int SEX_NO = 0;
    public static final int ADMIN_USER_STATUS_ENABLE = 1;//角色状态正常可用
    public static final int ADMIN_USER_STATUS_UNABLE = 0;//角色状态不可用


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,length = 11,unique = true)
    private Long id;
    @Column(nullable = false,length = 18,unique = true)
    @ValidateAnnotion(
            required=true,
            requiredLength=true,
            minLength=4,
            maxLength=18,
            errorRequiredMsg="亲，用户名不能为空哦~~",
            errorMinlenthMsg="亲，用户名长度需大于4哦~~!",
            errorMaxlenthMsg="亲，用户名长度不能大于18哦~~!")
    private String userName;
    @Column(nullable = false,length = 20)
    @ValidateAnnotion(
            required=true,
            requiredLength=true,
            minLength=4,
            maxLength=18,
            errorRequiredMsg="亲，性感网名不能为空哦~~",
            errorMinlenthMsg="亲，性感网名长度需大于4哦~~!",
            errorMaxlenthMsg="亲，性感网名长度不能大于18哦~~!")
    private String netName;
    @Column(nullable = false,length = 18)
    @ValidateAnnotion(
            required=true,
            requiredLength=true,
            minLength=6,
            maxLength=18,
            errorRequiredMsg="亲，密码不能为空哦~~!",
            errorMinlenthMsg="亲，密码长度需大于6哦~~!",
            errorMaxlenthMsg="亲，密码长度不能大于18o~~!")
    private String password;
    @Column(nullable = false,length = 128,updatable = false)
    @CreatedDate
    private Date creatTime;
    @Column(nullable = false)
    @LastModifiedDate
    private Date updataTime;
    @Column(nullable = false)
    private Integer isDelete = ISDELETE.NO_DELETE.getType();
    @Column(length = 128)
    @ValidateAnnotion(required=false)
    private String headPic;
    @Column(length = 12)
    @ValidateAnnotion(required=false)
    private String phone;
    @Column(length = 32)
    @ValidateAnnotion(required=false)
    private String email;
    @Column(length = 32)
    private int sex = SEX_NO;
    @ValidateAnnotion(required=false)
    @Column(length=1)
    private int status = ADMIN_USER_STATUS_ENABLE;//角色状态,默认可用

    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    private Role role;



    public UserMessage() {
    }




    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserMessage{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", netName='" + netName + '\'' +
                ", password='" + password + '\'' +
                ", creatTime=" + creatTime +
                ", updataTime=" + updataTime +
                ", isDelete=" + isDelete +
                ", headPic='" + headPic + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", sex=" + sex +
                ", status=" + status +
                ", role=" + role +
                '}';
    }

    public String getNetName() {
        return netName;
    }

    public void setNetName(String netName) {
        this.netName = netName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getUpdataTime() {
        return updataTime;
    }

    public void setUpdataTime(Date updataTime) {
        this.updataTime = updataTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
