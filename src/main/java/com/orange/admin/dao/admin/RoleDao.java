package com.orange.admin.dao.admin;

import com.orange.admin.pojo.admin.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role,Long> {
}
