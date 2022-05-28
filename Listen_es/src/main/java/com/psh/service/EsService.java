package com.psh.service;


import com.psh.entity.Users;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface EsService extends ElasticsearchRepository<Users,Long>  {

    /**
     * 查询用户名为username的用户
     * @param username
     * @return
     */
    List<Users> findByUsername(String username);

    /**
     * 查询用户名不是username的所有用户
     * @param username
     * @return
     */
    List<Users> findByUsernameNot(String username);


    /**
     * 查询年龄段为ageFrom到ageTo的用户
     * @param ageFrom
     * @param ageTo
     * @return
     */
    List<Users> findByAgeBetween(Integer ageFrom, Integer ageTo);


    /**
     * 查询年龄小于或等于ageTo的用户
     */
    List<Users> findByAgeBefore(Integer ageTo);

    /**
     * 查询年龄大于或等于ageFrom的用户
     * @param ageFrom
     * @return
     */
    List<Users> findByAgeAfter(Integer ageFrom);

    /**
     * 用户名模糊查询
     * @param username
     * @return
     */
    List<Users> findByUsernameLike(String username);


    /**
     * 查询以start开头的用户
     * @param start
     * @return
     */
    List<Users> findByUsernameStartingWith(String start);

    /**
     * 查询以end结尾的用户
     * @return
     */
    List<Users> findByUsernameEndingWith(String end);

    /**
     * 查询用户名包含word的用户
     * @param word
     * @return
     */
    List<Users> findByUsernameContaining(String word);

    /**
     * 查询名字属于usernames中的用户
     * @param usernames
     * @return
     */
    List<Users> findByUsernameIn(Collection<String> usernames);

    /**
     * 查询名字不属于usernames中的用户
     * @param usernames
     * @return
     */
    List<Users> findByUsernameNotIn(Collection<String> usernames);




}
