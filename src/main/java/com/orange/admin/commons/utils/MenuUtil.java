package com.orange.admin.commons.utils;

import com.orange.admin.pojo.admin.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MenuUtil {

    /**
     * 获取所有的顶级菜单类
     * @param menus
     * @return
     */
    public static List<Menu> getTopMenus(List<Menu> menus)
    {

        List<Menu> topmenus = new ArrayList<>();
        for (Menu menu : menus) {
            if(menu.getParent() == null)
            {
                topmenus.add(menu);
            }
        }
        return topmenus;

    }

    /**
     * 获取二级分类菜单
     * @param menus
     * @return
     */
    public static List<Menu> getSecondMenus(List<Menu> menus)
    {

        List<Menu> Secondmenus = new ArrayList<>();
        for (Menu menu : menus) {
            if(menu.getParent() != null  && menu.getParent().getParent() == null)
            {
                Secondmenus.add(menu);
            }
        }
        return Secondmenus;

    }

    public static List<Menu> getThirdMenus(List<Menu> menus)
    {

        List<Menu> Thirdmenus = new ArrayList<>();
        for (Menu menu : menus) {
            if(menu.getParent() != null  && menu.getParent().getParent() != null  )
            {
                Thirdmenus.add(menu);
            }
        }
        return Thirdmenus;

    }

    /**
     * 根据菜单url获取菜单id
     * @param url
     * @param menus
     * @return
     */
    public static Long getMenuIdByUrl(String url,List<Menu> menus){
        if(url == null)return null;
        for(Menu menu : menus){
            if(url.equals(menu.getUrl())){
                return menu.getMenuId();
            }
        }
        return null;
    }
    /**
     * 获取某个菜单id的所有子分类
     * @param parentId
     * @param menus
     * @return
     */
    public static List<Menu> getChildren(Long parentId,List<Menu> menus){
        List<Menu> children = new ArrayList<Menu>();
        if(parentId != null){
            for(Menu menu : menus){
                if(menu.getParent() != null && menu.getParent().getMenuId().longValue() == parentId.longValue()){
                    children.add(menu);
                }
            }
        }
        return children;
    }

    /**
     * 判断给定的url是否存在于指定的列表
     * @param url
     * @param menus
     * @return
     */
    public static boolean isExistUrl(String url,List<Menu> menus){
        if(url != null){
            for(Menu menu : menus){
                if(menu.getUrl() != null){
                    if(menu.getUrl().contains(url)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
