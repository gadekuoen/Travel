package cn.wjqixige.travel.dao;

import cn.wjqixige.travel.domain.User;

public interface UserDao {

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 存储用户信息
     * @param user
     */
    void save(User user);

    /**
     * 根据激活码查询用户对象
     * @param code
     * @return
     */
    User findByCode(String code);

    /**
     * 修改执行用户激活状态
     * @param user
     */
    void updateStatus(User user);

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    User findByUsernameAndPassword(String username, String password);
}
