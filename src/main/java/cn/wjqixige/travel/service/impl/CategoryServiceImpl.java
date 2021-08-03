package cn.wjqixige.travel.service.impl;

import cn.wjqixige.travel.dao.CategoryDao;
import cn.wjqixige.travel.dao.impl.CategoryDaoImpl;
import cn.wjqixige.travel.domain.Category;
import cn.wjqixige.travel.service.CategoryService;
import cn.wjqixige.travel.utils.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<Category> findAll() {

        //1.从redis中查询
        Jedis jedis = JedisUtil.getJedis();
        Set<Tuple> categorys = jedis.zrangeWithScores("category", 0, -1);

        List<Category> cs = null;
        //2.判断查询的集合是否为空
        if (categorys == null || categorys.size() == 0){
            //从数据库中查询
            System.out.println("从数据库中查询...");
            cs = categoryDao.findAll();
            for (int i = 0; i < cs.size(); i++) {
                jedis.zadd("category", cs.get(i).getCid(), cs.get(i).getCname());
            }
        } else {
            //从redis中查询
            System.out.println("从redis中查询...");
            cs = new ArrayList<Category>();
            for (Tuple tuple : categorys){
                Category category = new Category();
                category.setCid((int)tuple.getScore());
                category.setCname(tuple.getElement());
                cs.add(category);
            }
        }

        return cs;
    }
}
