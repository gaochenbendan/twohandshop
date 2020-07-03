package com.orange.admin.dao.admin;


import com.orange.admin.pojo.admin.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuDao extends JpaRepository<Menu,Long> {




}
