package com.ming.springjpatest.dao;

import com.ming.springjpatest.base.IBaseDao;
import com.ming.springjpatest.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface IUserDao extends IBaseDao<User,Integer> {
    @Transactional
    @Modifying
    @Query("delete from User where userName like ?1")
    public void deleteBySql(String userName);
}
