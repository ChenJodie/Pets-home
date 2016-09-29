package com.helps.pets.home.access.dao;

import java.util.List;
import java.util.Map;

/**
 * Author : pengpeng
 * Date   : 2016-07-08
 * time   : 下午2:52
 */
public interface IEntityCrudDao<Entity> {

    boolean insertEntity(Entity entity);

    boolean insertBatchEntity(List<Entity> entity);

    boolean deleteEntity(Entity entity);

    boolean deleteEntityByCond(Map<String, Object> queryParams);

    boolean updateEntityByKey(Entity entity);

    boolean updateEntity(Map<String, Object> queryParams, Map<String, Object> updateParams);

    Entity selectEntityById(Integer id);

    List<Entity> selectEntityByIdList(List<Integer> idlist);

    List<Entity> selectEntity(Entity entity);

    List<Entity> selectEntityByCond(Map<String, Object> queryParams);

    int selectTotalEntityByCond(Map<String, Object> queryParams);
}
