package com.orange.admin.service.adminservice.Impl;

import com.orange.admin.commons.utils.PageUtil;
import com.orange.admin.dao.admin.GoodsCategoryDao;
import com.orange.admin.pojo.common.GoodsCategory;
import com.orange.admin.service.adminservice.GoodsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 高晨
 */
@Service
public class GoodsCategoryServiceImpl implements GoodsCategoryService {

    @Autowired
    private GoodsCategoryDao goodsCategoryDao;

    @Override
    public GoodsCategory addGoodsCategory(GoodsCategory goodsCategory) {
        return goodsCategoryDao.save(goodsCategory);
    }

    @Override
    public List<GoodsCategory> findTopCategory() {
        return goodsCategoryDao.findByParentIsNull();
    }

    @Override
    public PageUtil<GoodsCategory> findList(PageUtil<GoodsCategory> pageUtil, GoodsCategory goodsCategory) {

        ExampleMatcher withMatcher = ExampleMatcher.matching().withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
        ExampleMatcher sort1 = withMatcher.withIgnorePaths("sort");
        Example<GoodsCategory> example = Example.of(goodsCategory, sort1);
        Sort sort = Sort.by(Sort.Direction.ASC, "sort");
        Pageable pageable = PageRequest.of(pageUtil.getCurrentPage()-1, pageUtil.getPageSize(),sort);
        Page<GoodsCategory> findAll = goodsCategoryDao.findAll(example, pageable);
        pageUtil.setContent(findAll.getContent());
        pageUtil.setTotal(findAll.getTotalElements());
        pageUtil.setTotalPage(findAll.getTotalPages());

        return pageUtil;
    }

    @Override
    public GoodsCategory finfById(Long id) {
        return goodsCategoryDao.findById(id).orElse(null);
    }

    @Override
    public void delteById(Long id) {
        goodsCategoryDao.deleteById(id);
    }

    @Override
    public List<GoodsCategory> findAll() {
        return goodsCategoryDao.findAll();
    }

}
