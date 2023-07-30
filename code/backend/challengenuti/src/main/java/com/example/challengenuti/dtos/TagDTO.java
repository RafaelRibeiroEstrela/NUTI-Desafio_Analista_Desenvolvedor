package com.example.challengenuti.dtos;

import com.example.challengenuti.models.Tag;

import java.io.Serializable;

public class TagDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private int count;

    public TagDTO(){

    }

    public TagDTO(Tag tag){
        this.name = tag.getName();
        this.count = tag.getCount();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "TagDTO{" +
                ", name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}
