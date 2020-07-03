package com.orange.admin.pojo.admin;

import com.orange.admin.annotion.ValidateAnnotion;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 菜单实体类
 * @author 高晨
 */

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,length = 11,unique = true)
    private Long menuId;

    @ValidateAnnotion(required=true,requiredLength=true,minLength=1,maxLength=18,errorRequiredMsg="菜单名称不能为空!",errorMinlenthMsg="菜单名称长度需大于1!",errorMaxlenthMsg="菜单名称长度不能大于18!")
    @Column(nullable=false,length=18)
    private String name;//菜单名称

    @ManyToOne
    @JoinColumn(name="parent_id")
    private Menu parent;//菜单父分类


    @ValidateAnnotion(required=false)
    @Column(length=128)
    private String url;//菜单url

    @ValidateAnnotion(required=false)
    @Column(length=32)
    private String icon;//菜单图标icon

    @Column(nullable=false,length=4)
    private Integer sort = 0;//菜单顺序，默认升序排列,默认是0

    @Column(nullable=false)
    private boolean isButton = false;//是否是按钮

    @Column(nullable=false)
    private boolean isShow = true;//是否显示

    @Column(nullable = false,length = 128,updatable = false)
    @CreatedDate
    private Date createTime;

    public Menu() {
    }



    public Long getMenuId() {
        return menuId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public boolean isButton() {
        return isButton;
    }

    public void setButton(boolean button) {
        isButton = button;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }


    @Override
    public String toString() {
        return "Menu{" +
                "menuId=" + menuId +
                ", name='" + name + '\'' +
                ", parent=" + parent +
                ", url='" + url + '\'' +
                ", icon='" + icon + '\'' +
                ", sort=" + sort +
                ", isButton=" + isButton +
                ", isShow=" + isShow +
                ", createTime=" + createTime +
                '}';
    }
}
