package com.ming.springbootjpatest.dao;

import com.ming.springbootjpatest.base.IBaseDao;
import com.ming.springbootjpatest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.net.UnknownServiceException;

//public interface IUserDao extends JpaRepository<User,Integer>,JpaSpecificationExecutor<User> {
public interface IUserDao extends IBaseDao<User,Integer> {
      public User findByUserName(String userName);

      @Query("from User where userName=?1")
      public User findBySql(String userName);

      @Transactional
      @Modifying
      @Query("update User set userName=?1 where id=?2")
      public int updateBySql(String userName,Integer id);

      @Transactional
      @Modifying
      @Query("delete from User where userName like ?1")
      public void deleteBySql(String userName);
}
