package com.example.model;

import lombok.Data;

import java.util.List;

@Data
public class AdminMenu {
    private int id;
    private  String name;
    private String path;
    private String nameZh;
    private String iconCls;
    private String component;
    private int parentId;
    private List<AdminMenu> children;
}
