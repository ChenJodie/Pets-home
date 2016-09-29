package com.helps.pets.home.access.support.automapper;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Map;

public class YamlPaser {
    public static final Logger runLog = LogManager.getLogger(YamlPaser.class);
    
    private List<Mapper> mapperCollections = Lists.newLinkedList();
    
    public YamlPaser(String filePath) {
        Yaml yaml = new Yaml();
        Map<String, Object> mapper = Maps.newHashMap();
        try {
            mapper = (Map<String, Object>) yaml.load(new FileReader(new File(filePath)));
            setMapperCollections(mapper);
        } catch (FileNotFoundException e) {
            runLog.error("[YamlPaser]", e);
        }
    }
    
    private void setTableAttributes(Mapper mapper, Map<String, Object> tableMaper) {
        if (null == mapper || null == tableMaper) {
            return;
        }
        for (String attr : tableMaper.keySet()) {
            Object val = tableMaper.get(attr);
            attr = attr.trim();
            if (attr.equalsIgnoreCase(Constants.COLUMNS)) {
                mapper.setColumns((Map<String, String>) val);
            } else if (attr.equalsIgnoreCase(Constants.MODEL)) {
                mapper.setModel((String) val);
            } else if (attr.equalsIgnoreCase("insert")) {
                mapper.setInsertMap((Map<String, String>) val);
            } else if (attr.equalsIgnoreCase("update")) {
                mapper.setUpdateMap((Map<String, String>) val);
            } else if (attr.equalsIgnoreCase("prikey")) {
                mapper.setPrikey((String) val);
            } else if (attr.equalsIgnoreCase("likes")) {
                mapper.setLikes((Map<String, String>) val);
            }else if (attr.equalsIgnoreCase("page")) {
                mapper.setPage((boolean) val);
            }
        }
    }
    
    private void setMapperCollections(Map<String, Object> yamlMapper) {
        if (null == yamlMapper) {
            return;
        }
        for (String tableName : yamlMapper.keySet()) {
            Mapper mapper = new Mapper();
            mapper.setTableName(tableName);
            Map<String, Object> tableMaper = (Map<String, Object>) yamlMapper.get(tableName);
            setTableAttributes(mapper, tableMaper);
            mapperCollections.add(mapper);
        }
    }
    
    public List<Mapper> getMapperCollections() {
        return mapperCollections;
    }
    
    public void setMapperCollections(List<Mapper> mapperCollections) {
        this.mapperCollections = mapperCollections;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        YamlPaser paser = new YamlPaser("D:\\test.yaml");
    }
}
