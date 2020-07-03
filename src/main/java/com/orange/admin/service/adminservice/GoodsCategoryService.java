package com.orange.admin.service.adminservice;

import com.orange.admin.commons.utils.PageUtil;
import com.orange.admin.pojo.common.GoodsCategory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 高晨
 */
@Service
public interface GoodsCategoryService {

    /**
     * 添加/编辑 商品
     *
     * @param goodsCategory
     */
    public GoodsCategory addGoodsCategory(GoodsCategory goodsCategory);


    /**
     * 获取所有的一级分类
     * @return List<GoodsCategory>
     */
    public List<GoodsCategory> findTopCategory();

    /**
     * 搜索列表
     * @param pageUtil
     * @param goodsCategory
     * @return
     */
    public PageUtil<GoodsCategory> findList(PageUtil<GoodsCategory> pageUtil, GoodsCategory goodsCategory);

    /**
     * 通过ID查找
     * @param id
     * @return
     */
    public GoodsCategory finfById(Long id);

    /**
     * 通过Id删除
     * @param id
     */
    public void delteById(Long id);

    /**
     * 查找全部
     * @return
     */
    public List<GoodsCategory> findAll();

}
