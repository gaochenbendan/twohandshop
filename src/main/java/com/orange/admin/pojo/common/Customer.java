package com.orange.admin.pojo.common;

import com.orange.admin.annotion.ValidateAnnotion;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @author 高晨
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Customer {

    public static final int STUDENT_STATUS_ENABLE = 1;//状态可用
    public static final int STUDENT_STATUS_UNABLE = 0;//状态不可用


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,length = 11,unique = true)
    private Long id;

    /**
     * 账号
     */
    @ValidateAnnotion(required=true,requiredLength=true,minLength=6,maxLength=18,errorRequiredMsg="名称不能为空!",errorMinlenthMsg="名称长度需大于6!",errorMaxlenthMsg="名称长度不能大于18!")
    @Column(nullable = true,length = 18,unique = true)
    private String sn;

    /**
     * 头像
     */
    @ValidateAnnotion(required=false)
    @Column(length=128)
    private String headPic;

    /**
     * 密码
     */
    @ValidateAnnotion(required=true,requiredLength=true,minLength=6,maxLength=18,errorRequiredMsg="密码不能为空!",errorMinlenthMsg="密码长度需大于6!",errorMaxlenthMsg="密码长度不能大于18!")
    @Column(length = 18,nullable=false)
    private String password;

    /**
     * 昵称
     */
    @ValidateAnnotion(required=false)
    @Column(length=32)
    private String nickname;//昵称

    /**
     * 手机
     */
    @ValidateAnnotion(required=false)
    @Column(length = 15)
    private String mobile;

    /**
     * qq
     */
    @ValidateAnnotion(required=true,minLength=5,maxLength=12,errorMinlenthMsg="qq号最小5位",errorMaxlenthMsg="qq号长度不能大于12")
    @Column(length = 15)
    private String qq;

    /**
     * 学校
     */
    @ValidateAnnotion(required=false)
    @Column(length = 15)
    private String school;

    /**
     * 年级
     */
    @ValidateAnnotion(required=false)
    @Column(length = 15)
    private String grade;

    /**
     * 学院/系别
     */
    @ValidateAnnotion(required=false)
    @Column(length = 15)
    private String academy;



    @ValidateAnnotion(required=false)
    @Column(length=1)
    private int status = STUDENT_STATUS_ENABLE;


    @Column(nullable=false)
    @CreatedDate
    private Date createTime;//创建时间


    public Customer() {
    }

    public static int getStudentStatusEnable() {
        return STUDENT_STATUS_ENABLE;
    }

    public static int getStudentStatusUnable() {
        return STUDENT_STATUS_UNABLE;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", sn='" + sn + '\'' +
                ", headPic='" + headPic + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", mobil='" + mobile + '\'' +
                ", qq='" + qq + '\'' +
                ", school='" + school + '\'' +
                ", grade='" + grade + '\'' +
                ", academy='" + academy + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }
}
