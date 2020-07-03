package com.orange.admin.dao.admin;


import com.orange.admin.pojo.admin.OperaterLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperaterLogDao extends JpaRepository<OperaterLog,Long> {


    /**
     * 获取最近的指定条数的操作日志
     * @param size
     * @return
     */
    @Query(value = "SELECT * FROM operater_log ol ORDER BY create_time DESC LIMIT 0,:size  ",nativeQuery = true)
        public List<OperaterLog> findLastData(@Param("size") int size);


}
