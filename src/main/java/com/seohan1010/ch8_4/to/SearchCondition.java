package com.seohan1010.ch8_4.to;

import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

public class SearchCondition {

    private String option = "";
    private String keyword = "";
    private Integer page = 1;
    private Integer pageSize = 10;
    private Integer offset = 0;


    @Override
    public String toString() {
        return "SearchCondition{" +
                "option='" + option + '\'' +
                ", keyword='" + keyword + '\'' +
                ", page=" + page +
                ", pageSize=" + pageSize +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchCondition that = (SearchCondition) o;
        return Objects.equals(option, that.option) && Objects.equals(keyword, that.keyword) && Objects.equals(page, that.page) && Objects.equals(pageSize, that.pageSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(option, keyword, page, pageSize);
    }



    public SearchCondition() {
    }

    public SearchCondition(String option, String keyword, Integer page, Integer pageSize) {
        this.option = option;
        this.keyword = keyword;
        this.page = page;
        this.pageSize = pageSize;
    }




    public String getQueryString(Integer page){

        return  UriComponentsBuilder.newInstance()
                .queryParam("page",page)
                .queryParam("pageSize",pageSize)
                .queryParam("option",option)
                .queryParam("keyword",keyword)
                .build().toString();

    }


    //페이지를 지정하지 않으면 아래의 메서드를 호출하게 하고
    public String getQueryString(){
        return getQueryString(page);
    }


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
