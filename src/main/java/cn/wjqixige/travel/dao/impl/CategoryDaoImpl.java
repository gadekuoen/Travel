package cn.wjqixige.travel.dao.impl;

import cn.wjqixige.travel.dao.CategoryDao;
import cn.wjqixige.travel.domain.Category;
import cn.wjqixige.travel.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Category> findAll() {
        String sql = "select * from tab_category";
        return template.query(sql, new BeanPropertyRowMapper<Category>(Category.class));
    }
}
