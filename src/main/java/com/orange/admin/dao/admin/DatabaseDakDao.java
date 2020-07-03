package com.orange.admin.dao.admin;

import com.orange.admin.pojo.admin.DatabaseBak;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseDakDao extends JpaRepository<DatabaseBak,Long> {



}
