package cn.wjqixige.travel.dao.impl;

import cn.wjqixige.travel.dao.UserDao;
import cn.wjqixige.travel.domain.User;
import cn.wjqixige.travel.until.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User findByUsername(String username) {
        User user = null;
        //1. 定义sql
        String sql = "select * from tab_user where username = ?";
        //2. 执行sql
        try{
            user = template.queryForObject(sql,new BeanPropertyRowMapper<User>(), username);
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void save(User user) {
        String sql = "insert into tab_user(username,password,name,birthday,sex,telephone,email) values(?,?,?,?,?,?,?)";

        template.update(sql,user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail());
    }
}
