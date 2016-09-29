package com.helps.pets.home.access.support.automapper;

import com.google.common.collect.Maps;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeInstance;
import org.apache.velocity.runtime.RuntimeServices;

import java.io.StringWriter;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Properties;

public class MapperTemplate {
    public static final Logger runLog = LogManager.getLogger(YamlPaser.class);
    private static final RuntimeInstance ri = new RuntimeInstance();
    private static final String encoding = "UTF-8";
    
    static {
        init();
    }
    
    public static RuntimeServices getRuntimeServices() {
        return ri;
    }
    
    protected static void init() {
        Properties properties = new Properties();
        properties.setProperty(Velocity.INPUT_ENCODING, encoding);
        properties.setProperty(Velocity.OUTPUT_ENCODING, encoding);
        properties.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        ri.init(properties);
    }
    
    private static Map<String, String> genColumnDbType(String className) throws Exception {
        Map<String, String> typeMap = Maps.newHashMap();
        try {
            Class<?> cls = Class.forName(className);
            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                String fieldName = field.getName();
                if (field.getType().equals(String.class)) {
                    typeMap.put(fieldName, "VARCHAR");
                } else if (field.getType().equals(BigDecimal.class)) {
                    typeMap.put(fieldName, "DECIMAL");
                } else if (field.getType().equals(Timestamp.class)) {
                    typeMap.put(fieldName, "TIMESTAMP");
                } else if (field.getType().equals(Integer.class)) {
                    typeMap.put(fieldName, "INTEGER");
                } else if (field.getType().equals(Short.class)) {
                    typeMap.put(fieldName, "INTEGER");
                } else if (field.getType().equals(short.class)) {
                    typeMap.put(fieldName, "INTEGER");
                } else if (field.getType().equals(Long.class)) {
                    typeMap.put(fieldName, "INTEGER");
                } else if (field.getType().equals(long.class)) {
                    typeMap.put(fieldName, "INTEGER");
                } else if (field.getType().equals(int.class)) {
                    typeMap.put(fieldName, "INTEGER");
                } else if (field.getType().equals(Float.class)) {
                    typeMap.put(fieldName, "DECIMAL");
                }
            }
        } catch (Exception ex) {
            throw ex;
        }
        return typeMap;
    }

    private static Map<String, String> genTransformObject(String className) throws Exception {
        Map<String, String> typeMap = Maps.newHashMap();
        try {
            Class<?> cls = Class.forName(className);
            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                String fieldName = field.getName();
                String trans  = (new StringBuilder()).append(Character.toUpperCase(fieldName.charAt(0))).append(fieldName.substring(1)).toString();
                typeMap.put(fieldName, trans);

            }
        } catch (Exception ex) {
            throw ex;
        }
        return typeMap;
    }
    
    private static Map<String, String> modelColumnNameToDbColumn(Map<String, String> dbTypes) {
        Map<String, String> mapper = Maps.newHashMap();
        if (null == dbTypes) {
            return mapper;
        }
        for (String key : dbTypes.keySet()) {
            mapper.put(dbTypes.get(key), key);
        }
        return mapper;
    }
    
    
    public static String genTemplate(com.helps.pets.home.access.support.automapper.Mapper mapper) {
        String tpl = "/yaml/template.vm";
        //String tpl = "/yaml/transfer.vm";
        VelocityContext vc = new VelocityContext();
        StringWriter sw = new StringWriter();
        try {
            vc.put("columns", mapper.getColumns());
            Map<String, String> dbTypes = genColumnDbType(mapper.getModel());
            vc.put("dbTypes", dbTypes);
            Map<String, String> trans = genTransformObject(mapper.getModel());
            vc.put("trans", trans);
            vc.put("inDefaultValue", mapper.getInsertMap());
            vc.put("upDefaultValue", mapper.getUpdateMap());
            vc.put("model", mapper.getModel());
            vc.put("mMapper", modelColumnNameToDbColumn(dbTypes));
            Class<?> cls = Class.forName(mapper.getModel());
            vc.put("modelName", cls.getSimpleName());
            vc.put("tableName", mapper.getTableName());
            vc.put("prikey",mapper.getPrikey());
            vc.put("likes",mapper.getLikes());
            Template template = ri.getTemplate(tpl, encoding);
            template.merge(vc, sw);
            sw.flush();
            return sw.getBuffer().toString();
        } catch (Exception ex) {
            runLog.error("[genTemplate]", ex);
            return null;
        }
    }
    
    public boolean exists(String templateName) {
        return ri.getLoaderNameForResource(templateName) != null;
    }
}
