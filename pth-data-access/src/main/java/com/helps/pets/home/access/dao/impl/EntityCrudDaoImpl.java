package com.helps.pets.home.access.dao.impl;

import com.google.common.collect.Maps;
import com.helps.pets.home.access.dao.IEntityCrudDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Author : pengpeng
 * Date   : 2016-07-08
 * time   : 下午3:18
 */
public abstract class EntityCrudDaoImpl<Entity> extends SqlSessionDaoSupport implements IEntityCrudDao<Entity> {

    private Class<?> cls = null;

    public EntityCrudDaoImpl() {
        Class<?> c = getClass();
        Type type = c.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
            this.cls = (Class<?>) parameterizedType[0];
        }
    }

    @Override
    public boolean insertEntity(Entity entity) {
        String mapperId = cls.getSimpleName().concat("NS.insertEntity").concat(cls.getSimpleName());
        int cnt = getSqlSession().insert(mapperId, entity);
        return cnt > 0;
    }

    @Override
    public boolean insertBatchEntity(List<Entity> entity){
        String mapperId = cls.getSimpleName().concat("NS.insertBatchEntity").concat(cls.getSimpleName());
        int cnt = getSqlSession().insert(mapperId, entity);
        return cnt > 0;
    }

    @Override
    public boolean deleteEntity(Entity entity) {
        String mapperId = cls.getSimpleName().concat("NS.deleteEntity").concat(cls.getSimpleName());
        int cnt = getSqlSession().delete(mapperId, entity);
        return cnt > 0;
    }

    @Override
    public boolean deleteEntityByCond(Map<String, Object> queryParams) {
        return false;
    }

    @Override
    public boolean updateEntityByKey(Entity entity) {
        String mapperId = cls.getSimpleName().concat("NS.updateEntity").concat(cls.getSimpleName()).concat("ById");;
        int cnt = getSqlSession().update(mapperId, entity);
        return cnt > 0;
    }

    @Override
    public boolean updateEntity(Map<String, Object> queryParams, Map<String, Object> updateParams) {
        if (null == queryParams || queryParams.isEmpty()) {
            return false;
        }
        Map<String, Object> params = Maps.newHashMap();
        params.put("cond", queryParams);
        params.put("newvalue", updateParams);
        String mapperId = cls.getSimpleName().concat("NS.updateEntity").concat(cls.getSimpleName());
        int cnt = getSqlSession().update(mapperId, params);
        return cnt > 0;
    }

    @Override
    public List<Entity> selectEntity(Entity entity) {
        String mapperId = cls.getSimpleName().concat("NS.selectEntity").concat(cls.getSimpleName());
        List<Entity> result = getSqlSession().selectList(mapperId, entity);
        return null != result?result: Collections.<Entity>emptyList();
    }

    @Override
    public Entity selectEntityById(Integer id) {
        String mapperId = cls.getSimpleName().concat("NS.selectEntity").concat(cls.getSimpleName()).concat("ById");
        Entity result = getSqlSession().selectOne(mapperId,id);
        return null == result ? null : result;
    }

    @Override
    public List<Entity> selectEntityByIdList(List<Integer> idlist) {
        String mapperId = cls.getSimpleName().concat("NS.selectEntity").concat(cls.getSimpleName()).concat("ByIdList");
        List<Entity> result = getSqlSession().selectList(mapperId, idlist);
        return null != result?result: Collections.<Entity>emptyList();
    }

    @Override
    public List<Entity> selectEntityByCond(Map<String, Object> queryParams) {
        String mapperId = cls.getSimpleName().concat("NS.selectEntityCond").concat(cls.getSimpleName());
        List<Entity> result = getSqlSession().selectList(mapperId, queryParams);
        return null != result?result: Collections.<Entity>emptyList();
    }

    @Override
    public int selectTotalEntityByCond(Map<String, Object> queryParams) {
        String mapperId = cls.getSimpleName().concat("NS.selectTotalEntityCond").concat(cls.getSimpleName());
        Integer ret = (Integer) getSqlSession().selectOne(mapperId, queryParams);
        return null == ret ? 0 : ret;
    }

    @Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }
}
