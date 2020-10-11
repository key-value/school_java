package com.sixteen.school.result;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Data
public class QueryResult<T> {
//    private final static Collection empty = new ArrayList<>();
    private int count;
    private Collection<T> items = new ArrayList<>();
    @Getter(AccessLevel.PRIVATE)
    private Map<String, Object> extend = new HashMap<>();

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


    @JsonAnyGetter
    public Map<String, Object> getProperties() {
        return extend;
    }

    public <T extends Object> void add(String key,T value){
        extend.put(key,value);
    }

    public QueryResult(int count) {
        this.count = count;
    }

    public QueryResult(Collection<T> items) {
        this.items = items;
    }
}
