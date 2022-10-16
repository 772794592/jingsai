package com.example.jingsai.systemresource.utils;

import lombok.Data;

import	java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class PageUtil<T> {

    private Integer pageSize = 1;

    private Integer pageNum = 10;

    private Integer currPage = 1;

    private Integer topPage = 0;

    private Integer nextPage = 0;

    private Integer countPage;

    private Integer count;

    private boolean hasNextPage = false;

    private boolean hasTopPage = false;

    private List<T> list = new ArrayList<> ();

    public PageUtil(){
        super();
    }

    public PageUtil(List<T> list){
        new PageUtil(pageSize, pageNum, list);
    }

    public PageUtil(Integer pageSize, List<T> list){
        new PageUtil(pageSize, pageNum, list);
    }

    public PageUtil(Integer pageSize, Integer pageNum, List<T> list) {
        this.pageSize = pageSize;
        this.currPage = pageSize;
        this.pageNum = pageNum;
        this.count = list.size();
        if (this.count % pageNum == 0) {
            this.countPage = this.count / pageNum;
        }else{
            this.countPage = this.count / pageNum + 1;
        }
        if(pageSize > 1){
            this.hasTopPage = true;
            this.topPage = pageSize - 1;
        }
        Integer start = (pageSize - 1) * pageNum;
        if(pageSize < this.countPage){
            this.hasNextPage = true;
            this.nextPage = pageSize + 1;
            this.list = list.stream().filter(t -> (start <= list.indexOf(t)
                    && list.indexOf(t) <= (pageSize * pageNum - 1))).collect(Collectors.toList());
        }else if(pageSize == this.countPage){
            this.list = list.stream().filter(t -> (start <= list.indexOf(t)
                    && list.indexOf(t) <= (this.count - 1))).collect(Collectors.toList());
        }
    }


}
