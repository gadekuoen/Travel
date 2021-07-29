package cn.wjqixige.travel.service.impl;

import cn.wjqixige.travel.dao.UserDao;
import cn.wjqixige.travel.dao.impl.UserDaoImpl;
import cn.wjqixige.travel.domain.User;
import cn.wjqixige.travel.service.UserService;
import cn.wjqixige.travel.until.MailUtils;
import cn.wjqixige.travel.until.UuidUtil;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    /**
     * 注册用户
     * @param user
     * @return
     */
    @Override
    public boolean regist(User user) {
        //1. 根据用户名查询用户对象
        User u = userDao.findByUsername(user.getUsername());
        if (u != null){
            //用户名存在，注册失败
            return false;
        }
        //2.保存用户信息
        //2.1 设置激活码
        user.setCode(UuidUtil.getUuid());
        //2.2 设置激活状态
        user.setStatus("N");
        userDao.save(user);

        //3. 激活邮件发送
        String content = "<a href='http://localhost/travel/activeUserServlet?code=" + user.getCode() + "'>点击激活【旅游网】</a>";
        MailUtils.sendMail(user.getEmail(),content,"激活邮件");
        return true;
    }

    @Override
    public boolean active(String code) {
        return false;
    }

    @Override
    public User login(User user) {
        return null;
    }
}
