package com.seohan1010.ch8_4.to;

import java.util.Objects;

public class SearchCondition {

   private String writer;
   private String keyword;


    @Override
    public String toString() {
        return "SearchCondition{" +
                "writer='" + writer + '\'' +
                ", keyword='" + keyword + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchCondition that = (SearchCondition) o;
        return Objects.equals(writer, that.writer) && Objects.equals(keyword, that.keyword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(writer, keyword);
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
