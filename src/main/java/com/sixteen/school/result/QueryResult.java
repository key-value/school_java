package com.sixteen.school.result;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class QueryResult<T> {
    private final static Collection empty = new ArrayList<>();
    private int count;
    private Collection<T> items = empty;

    public QueryResult(int count, Collection<T> items) {
        this.count = count;
        this.items = items;
    }

    public QueryResult(Long count, Collection<T> items) {
        this.count = count.intValue();
        this.items = items;
    }


    public QueryResult(Page<T> teacherPage) {
        this.count = (int)teacherPage.getTotalElements();
        this.items = teacherPage.getContent();
    }

    public QueryResult(int count) {
        this.count = count;
    }

    public QueryResult(Collection<T> items) {
        this.items = items;
    }
}
