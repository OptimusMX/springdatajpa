package com.ming.springbootjpatest.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

//使用该注解，代表初始化springboot工程时，不会创建该接口的实例。所以当作为basedao时，可以使用该注解。
@NoRepositoryBean
public interface IBaseDao<T,ID extends Serializable> extends JpaRepository<T,ID>, JpaSpecificationExecutor<T> {
}
