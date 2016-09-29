package com.helps.pets.home.access.support.automapper;

import java.net.URL;
import java.util.List;

public class AutoMapper {
    
    private YamlPaser paser = null;
    
    public AutoMapper(String filePath) {
        paser = new YamlPaser(filePath);
    }
    
    public void makeXmlMapper() {
        List<Mapper> mappers = paser.getMapperCollections();
        for (Mapper mapper : mappers) {
            String xml = MapperTemplate.genTemplate(mapper);
            System.out.println(xml);
        }
    }
    
    public static void main(String[] args) {

        URL url = AutoMapper.class.getResource("/yaml/DamAssoAttr.yaml");
        AutoMapper autoMapper = new AutoMapper(url.getPath());
        autoMapper.makeXmlMapper();
    }
}
