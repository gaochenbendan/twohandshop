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
public class GoodsCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,length = 11,unique = true)
    private Long id;

    /**
     * 分类名称
     */
    @ValidateAnnotion(required=true,requiredLength=true,minLength=1,maxLength=18,errorRequiredMsg="菜单名称不能为空!",errorMinlenthMsg="菜单名称长度需大于1!",errorMaxlenthMsg="菜单名称长度不能大于18!")
    @Column(nullable=false,length=18)
    private String name;

    @ManyToOne
    @JoinColumn(name="parent_id")
    private GoodsCategory parent;//菜单父分类

    /**
     * 分类图标icon
     */
    @ValidateAnnotion(required=false,errorRequiredMsg="请选择ICON")
    @Column(length=32)
    private String icon;

    @Column(nullable=false)
    @CreatedDate
    private Date createTime;//创建时间

    @Column(nullable=false,length=4)
    private Integer sort = 0;

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

    public GoodsCategory getParent() {
        return parent;
    }

    public void setParent(GoodsCategory parent) {
        this.parent = parent;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public GoodsCategory() {
    }

    @Override
    public String toString() {
        return "GoodsCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parent=" + parent +
                ", icon='" + icon + '\'' +
                ", createTime=" + createTime +
                ", sort=" + sort +
                '}';
    }
}
