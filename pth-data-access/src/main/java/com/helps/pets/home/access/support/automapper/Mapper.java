package com.helps.pets.home.access.support.automapper;

import java.util.Map;

public class Mapper {
    
    private String tableName;
    private String model;
    private Map<String, String> columns;
    private Map<String, String> insertMap;
    private Map<String, String> updateMap;
    private String prikey;
    private boolean page;
    private Map<String, String> likes;

    public Map<String, String> getLikes() {
        return likes;
    }

    public void setLikes(Map<String, String> likeColumns) {
        this.likes = likeColumns;
    }

    public Mapper() {
    }
    
    public Mapper(String tableName, String model, Map<String, String> columns, Map<String, String> inserts, Map<String, String> updates) {
        super();
        this.tableName = tableName;
        this.model = model;
        this.columns = columns;
        this.insertMap = inserts;
        this.updateMap = updates;
    }
    
    public Map<String, String> getInsertMap() {
        return insertMap;
    }
    
    public void setInsertMap(Map<String, String> insertMap) {
        this.insertMap = insertMap;
    }
    
    public Map<String, String> getUpdateMap() {
        return updateMap;
    }
    
    public void setUpdateMap(Map<String, String> updateMap) {
        this.updateMap = updateMap;
    }
    
    public String getTableName() {
        return tableName;
    }
    
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    
    public String getModel() {
        return model;
    }
    
    public void setModel(String model) {
        this.model = model;
    }
    
    public Map<String, String> getColumns() {
        return columns;
    }
    
    public void setColumns(Map<String, String> columns) {
        this.columns = columns;
    }

    public String getPrikey() {
        return prikey;
    }

    public void setPrikey(String prikey) {
        this.prikey = prikey;
    }

    public boolean isPage() {
        return page;
    }

    public void setPage(boolean page) {
        this.page = page;
    }
}
