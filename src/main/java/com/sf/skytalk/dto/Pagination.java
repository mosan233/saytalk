package com.sf.skytalk.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Pagination<T> {
    private List<T> data;
    private int totalCount;
    private int page;
    private int size;
    private int totalPage;

    private boolean showPrevious;
    private boolean showNext;
    private boolean showFirstPage;
    private boolean showEndPage;
    private List<Integer> pages = new ArrayList<>();


    public void setPagination(int totalCount, int page,int size){
        this.totalPage = totalCount%size == 0 ? totalCount/size : (totalCount/size+1);
        this.page = page < 1 ? 1 : page;
        this.page = this.page > totalPage ? totalPage : this.page;
        this.size = size;
        this.totalCount = totalCount;
        this.pages.add(this.page);
        //当前页前三个页码 当前页 当前页后三个页面
        for(int i=1;i<=3;i++){
            if(this.page-i >= 1){//页码大于1
                this.pages.add(0,this.page-i);
            }
            if(this.page+i <= totalPage){//页码不大于总页码
                this.pages.add(this.page+i);
            }
        }

        this.showPrevious = this.page != 1 ? true : false;
        this.showNext = this.page != totalPage ? true : false;
        this.showFirstPage = !pages.contains(1) ? true : false;
        this.showEndPage = !pages.contains(totalPage) ? true : false;

    }
}
