package com.moodeon.service;

import com.moodeon.beans.BoardInfoBean;
import com.moodeon.dao.TopMenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopMenuService {

    @Autowired
    private TopMenuDao topMenuDao;

    public List<BoardInfoBean> getTopMenuList() {
        List<BoardInfoBean> topMenuList = topMenuDao.getTopMenuList();
        return topMenuList;
    }
}
