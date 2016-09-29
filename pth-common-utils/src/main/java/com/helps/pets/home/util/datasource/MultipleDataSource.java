package com.helps.pets.home.util.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by michealyang on 16/6/24.
 */
public class MultipleDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<String> dataSourceKey = new ThreadLocal<String>();

    private static final Map<String, String> packageDataSource = new HashMap<String, String>();

    public static void setDataSourceKey(String dataSource) {
        dataSourceKey.set(dataSource);
    }

    public static void usePackageDataSource(String pkgName) {
        dataSourceKey.set(packageDataSource.get(pkgName));
    }

    protected Object determineCurrentLookupKey() {
        String dsName = dataSourceKey.get();
        dataSourceKey.remove();
        return dsName;
    }

    public Map<String, String> getPackageDataSource() {
        return MultipleDataSource.packageDataSource;
    }

    public void setPackageDataSource(Map<String, String> packageDataSource) {
        MultipleDataSource.packageDataSource.putAll(packageDataSource);
    }

}