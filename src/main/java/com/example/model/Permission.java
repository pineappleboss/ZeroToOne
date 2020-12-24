package com.example.model;

import lombok.Data;

import java.util.List;

@Data
public class Permission {
private int id;
private String title;
private boolean expand;
private boolean checked;
private String url;
private int pid;
private List<Permission> children;
}
