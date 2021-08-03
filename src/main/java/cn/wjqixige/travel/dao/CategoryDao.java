package cn.wjqixige.travel.dao;

import cn.wjqixige.travel.domain.Category;

import java.util.List;

public interface CategoryDao {

    List<Category> findAll();
}
