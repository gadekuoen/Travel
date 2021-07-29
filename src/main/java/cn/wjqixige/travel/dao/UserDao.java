package cn.wjqixige.travel.dao;

import cn.wjqixige.travel.domain.User;

public interface UserDao {

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    public User findByUsername(String username);

    void save(User user);
}
