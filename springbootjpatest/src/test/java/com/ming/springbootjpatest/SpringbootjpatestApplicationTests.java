package com.ming.springbootjpatest;

import com.ming.springbootjpatest.dao.IUserDao;
import com.ming.springbootjpatest.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootjpatestApplicationTests {
    @Autowired
    private IUserDao userDao;

    /**
     * 增加方法
     */
    @Test
    public void testAdd(){
        User user=new User();
        user.setUserName("zhangsan1");
        user.setPassword("12345");
        userDao.save(user);
    }

    /**
     * 批量增加
     */
    @Test
    public void testAddList(){
        List<User> userList=new ArrayList<>();
        for(int i=0;i<10;i++){
            User user=new User();
            user.setUserName("zhangsan_"+i);
            user.setPassword("12345_"+i);
            userList.add(user);
        }
        userDao.saveAll(userList);
    }
    /**
     * 通过主键查找
     */
    @Test
    public void testFindById(){
        Optional<User> optionalUser = userDao.findById(1);
        User user = optionalUser.get();
        System.out.println(user);
    }

    /**
     * 通用查找方法，基本上按照哪个字段查都可以，写起来稍微麻烦一点点。
     * 下边是按照userName="zhangsan"去查，如果不用=换作like，就将下边的criteriaBuilder.equal
     * 换成criteriaBuilder.like。
     */
    @Test
    public void testCommonFind(){
        Specification<User> specification=new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> userName = root.get("userName");
                Predicate predicate = criteriaBuilder.equal(userName, "zhangsan");
                return predicate;
            }
        };
        Optional<User> optionalUser = userDao.findOne(specification);
        User user = optionalUser.get();
        System.out.println(user);
    }

    /**
     * 查询所有，如果想通过条件查询，可以根据上边的方式查询。
     */
    @Test
    public void testFindAll(){
        List<User> userList = userDao.findAll();
        for(User item:userList){
            System.out.println(item);
        }
    }
    @Test
    public void testPage(){
        //排序
        Sort sort=new Sort(Sort.Direction.DESC,"id");
        //分页
        Pageable pageable=PageRequest.of(0,2,sort);
        Page<User> page = userDao.findAll(pageable);
        //当前页结果，（0）页
        List<User> userList = page.getContent();
        //按照每页2条数据，一共有的页数
        int totalPages = page.getTotalPages();
        //总共有多少条数据
        long totalElements = page.getTotalElements();
    }

    /**
     * 通用修改方法，先根据主键查找，然后在修改提交
     * 也可以通过sql直接修改.需要在IUserDao接口中定义，这里不再演示。
     */
    @Test
    public void testUpdate(){
        Optional<User> userOptional = userDao.findById(1);
        User user = userOptional.get();
        user.setUserName("张三");
        //主键存在为修改，不存在为新增
        userDao.save(user);
    }

    /**
     * 通过主键删除
     * 也可以通过sql直接删除.需要在IUserDao接口中定义，这里不再演示
     */
    @Test
    public void testDelete(){
        userDao.deleteById(1);
    }
    @Test
    public void testDeleteAll(){
        userDao.deleteAll();
    }
    @Test
    public void testDeleteBySql(){
        userDao.deleteBySql("zhangsan%");
    }
}
