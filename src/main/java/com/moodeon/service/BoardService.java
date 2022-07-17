package com.moodeon.service;

import com.moodeon.beans.ContentBean;
import com.moodeon.beans.PageBean;
import com.moodeon.beans.UserBean;
import com.moodeon.dao.BoardDao;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

@Service
public class BoardService {
    //실제 파일이 업로드 되는 경로 가져오기
    @Value("${path.upload}")
    private String path_upload;

    //페이징을 위한 프로퍼티 값 주입
    @Value("${page.listcnt}")
    private int page_listcnt;

    //페이지 숫자 10개씩
    @Value("${page.paginationcnt}")
    private int page_paginationcnt;

    @Autowired
    private BoardDao boardDao;

    @Resource(name = "loginUserBean")
    @Lazy
    private UserBean loginUserBean;


    private String saveUploadFile(MultipartFile upload_file) {
        //저장될 파일명 설정 (시스템시간_원본파일명)
        String file_name = System.currentTimeMillis() + "_" + upload_file.getOriginalFilename();

        try {
            upload_file.transferTo(new File(path_upload + "/" + file_name));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file_name;
    }


//        파라미터로 넘어오는 데이터 확인
//        System.out.println(writeContentBean.getContent_subject());
//        System.out.println(writeContentBean.getContent_text());
//        System.out.println(writeContentBean.getUpload_file().getSize());
    public void addContentInfo(ContentBean writeContentBean) {

        MultipartFile upload_file = writeContentBean.getUpload_file();

        if (upload_file.getSize() > 0) {
            //업로드 할 파일이 있으면 Bean의 파일 이름 지정
            String file_name = saveUploadFile(upload_file);
            writeContentBean.setContent_file(file_name);
        }
        //loginUserBean을 주입받아 작성자 idx 값 셋팅
        writeContentBean.setContent_writer_idx(loginUserBean.getUser_idx());

        boardDao.addContentInfo(writeContentBean);
    }

    public String getBoardInfoName(int board_info_idx) {
        return boardDao.getBoardInfoName(board_info_idx);
    }

    //글 목록 조회, 페이지 조회
    public List<ContentBean> getContentList(int board_info_idx, int page) {
//        페이지 당 글의 개수
//        page_listcnt = 10

//        1->0
//        2->10
//        3->20
        int start = (page - 1) * page_listcnt;
        RowBounds rowBounds = new RowBounds(start, page_listcnt);

        return boardDao.getContentList(board_info_idx, rowBounds);
    }

    //글 하나의 정보 가져오기
    public ContentBean getContentInfo(int content_idx) {
        return boardDao.getContentInfo(content_idx);
    }

    public void modifyContentInfo(ContentBean modifyContentBean) {

        //첨부 파일 가져오기
        MultipartFile upload_file = modifyContentBean.getUpload_file();

        if (upload_file.getSize() > 0) {
            String file_name = saveUploadFile(upload_file);
            modifyContentBean.setContent_file(file_name);
        }
        boardDao.modifyContentInfo(modifyContentBean);
    }

    public void deleteContentInfo(int content_idx) {
        boardDao.deleteContentInfo(content_idx);
    }

    public PageBean getContentCnt(int content_board_idx, int currentPage) {

        int content_cnt = boardDao.getContentCnt(content_board_idx);

        PageBean pageBean = new PageBean(content_cnt, currentPage, page_listcnt, page_paginationcnt);

        return pageBean;
    }
}
