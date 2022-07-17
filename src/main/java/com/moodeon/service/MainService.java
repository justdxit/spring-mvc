package com.moodeon.service;

import com.moodeon.beans.ContentBean;
import com.moodeon.dao.BoardDao;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainService {

    @Autowired
    private BoardDao boardDao;

    public List<ContentBean> getMainList(int board_info_idx) {
        RowBounds rowBounds = new RowBounds(0, 5);
        return boardDao.getContentList(board_info_idx, rowBounds);
    }

}
