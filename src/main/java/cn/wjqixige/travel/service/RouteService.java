package cn.wjqixige.travel.service;

import cn.wjqixige.travel.domain.PageBean;
import cn.wjqixige.travel.domain.Route;

public interface RouteService {

    /**
     * 根据类别进行分页查询
     * @param cid
     * @param currentPage
     * @param pageSize
     * @param rname
     * @return
     */
    PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname);
}
