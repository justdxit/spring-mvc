package com.moodeon.beans;

public class PageBean {

    //최소 페이지 번호
    private int min;
    //최대 페이지 번호
    private int max;
    //이전 버튼 페이지 번호
    private int prevPage;
    //다음 버튼 페이지 번호
    private int nextPage;
    //전체 페이지 수
    private int pageCnt;
    //현재 페이지 번호
    private int currentPage;

    //contentCnt: 전체글 수, currentPage: 현재 글 번호,
    //contentPageCnt: 페이지당 글 수, paginationCnt: 페이지 버튼 수
    public PageBean(int contentCnt, int currentPage,
                    int contentPageCnt, int paginationCnt) {
        //현재 페이지 번호
        this.currentPage = currentPage;

        //전체 페이지 수(전체 글의 개수 / 페이지당 글 개수)
        pageCnt = contentCnt/contentPageCnt;

        if (contentCnt % contentPageCnt > 0) {
            pageCnt++; //나머지 글 때문에 한 페이지 더 필요
        }
        /*
        0~9: 1
        10~19: 11
        20~29: 21
         */
        min = ((currentPage-1) / contentPageCnt) * contentPageCnt+1;
        max = min + paginationCnt-1 ;

        //버튼의 최대값이 실제 페이지 개수보다 많을 시
        if (max > pageCnt) {
            max = pageCnt;
        }

        //첫 페이지면 prevPage는 0 -> 비활성화
        prevPage = min-1;
        nextPage = max+1;
        if (nextPage > pageCnt) {
            nextPage = pageCnt;
        }
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getPrevPage() {
        return prevPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public int getPageCnt() {
        return pageCnt;
    }

    public int getCurrentPage() {
        return currentPage;
    }
}
