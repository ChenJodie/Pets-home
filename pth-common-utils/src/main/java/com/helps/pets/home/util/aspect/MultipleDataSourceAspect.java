package com.helps.pets.home.util.aspect;

import com.helps.pets.home.util.annotation.DataSource;
import com.helps.pets.home.util.datasource.MultipleDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;


/**
 * Created by michealyang on 16/6/27.
 */
public class MultipleDataSourceAspect {
    private static final Logger LOG = LogManager.getLogger(MultipleDataSourceAspect.class);

    public Object doAround(ProceedingJoinPoint jp) throws Throwable {

        if (LOG.isDebugEnabled()) {
            LOG.debug("MultipleDataSourceAspectAdvice invoked!");
        }

        Signature signature = jp.getSignature();

        String dataSourceKey = getDataSourceKey(signature);

        if (StringUtils.hasText(dataSourceKey)) {
            MultipleDataSource.setDataSourceKey(dataSourceKey);
        }

        Object jpVal = jp.proceed();

        return jpVal;


    }

    private String getDataSourceKey(Signature signature) {
        if (signature == null) return null;

        if (signature instanceof MethodSignature) {

            //检测方法级注解
            MethodSignature methodSignature = (MethodSignature) signature;
            Method method = methodSignature.getMethod();
            if (method.isAnnotationPresent(DataSource.class)) {
                return dsSettingInMethod(method);
            }

            //类级注解
            Class declaringClazz = method.getDeclaringClass();
            if (declaringClazz.isAnnotationPresent(DataSource.class)) {
                try {
                    return dsSettingInConstructor(declaringClazz);
                } catch (Exception e) {
                    LOG.error("获取构造方法的DataSource注解失败", e);
                }
            }

            //包级注解,为了配置方便，包注解和类以及方法注解方式不同
            Package pkg = declaringClazz.getPackage();
            dsSettingInPackage(pkg);

        }

        return null;
    }

    private String dsSettingInMethod(Method method) {
        DataSource dataSource = method.getAnnotation(DataSource.class);
        return dataSource.value();
    }

    private String dsSettingInConstructor(Class clazz) {
        DataSource dataSource = (DataSource) clazz.getAnnotation(DataSource.class);
        return dataSource.value();
    }

    private void dsSettingInPackage(Package pkg) {
        if (LOG.isDebugEnabled()) {
            LOG.debug(pkg.getName());
        }
        MultipleDataSource.usePackageDataSource(pkg.getName());
    }
}
