package com.moodeon.dao;

import com.moodeon.beans.BoardInfoBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TopMenuDao {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public List<BoardInfoBean> getTopMenuList() {
        List<BoardInfoBean> topMenuList = sqlSessionTemplate.selectList("topmenu.get_topmenu_list");
        return topMenuList;
    }

}
