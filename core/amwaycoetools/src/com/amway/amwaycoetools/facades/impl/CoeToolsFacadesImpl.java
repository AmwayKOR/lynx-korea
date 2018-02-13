package com.amway.amwaycoetools.facades.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import com.amway.amwaycoetools.data.AmwayBeanDataList;
import com.amway.amwaycoetools.data.AmwayBeanParentData;
import com.amway.amwaycoetools.data.AmwayBeanAnnotationData;
import com.amway.amwaycoetools.data.AmwayBeanMethodData;
import com.amway.amwaycoetools.facades.CoeToolsFacades;
import com.amway.amwaycoetools.constants.AmwaycoetoolsConstants;
import com.amway.amwaycoetools.data.AmwayBeanData;
import de.hybris.platform.core.Registry;

import org.apache.commons.lang3.RandomStringUtils;

import com.amway.core.annotations.AmwayBean;

import com.amway.core.annotations.AmwayBean;
import org.apache.log4j.Logger;


/**
 * Created by aiueq92 on 8/13/17.
 */
@AmwayBean(ext="amwaycoetools",docs="https://jira.amway.com:8444/display/HC/amwaycoetools")
public class CoeToolsFacadesImpl implements CoeToolsFacades {
    @Override
    public AmwayBeanDataList getAmwayBeans() {
        String XRequestID = RandomStringUtils.randomAlphanumeric(180);
        int x = XRequestID.length();
        AmwayBeanDataList beanDataList = new AmwayBeanDataList();
        beanDataList.setAmwayBeans(new ArrayList(AmwayBeansSingleton.getInstance().amwayBeans.values()));
        beanDataList.setCount(AmwayBeansSingleton.getInstance().amwayBeans.size());
        return beanDataList;
    }


    public AmwayBeanDataList getCoreAmwayBeans() {

        List<AmwayBeanData> amwayBeanList = new ArrayList(AmwayBeansSingleton.getInstance().amwayBeans.values());

        AmwayBeanDataList returnBeanDataList = new AmwayBeanDataList();
        returnBeanDataList.setAmwayBeans(new ArrayList<AmwayBeanData>());

        for(AmwayBeanData bean:amwayBeanList) {
            if (!(bean.getClassObj().getPackage().getName().indexOf(AmwaycoetoolsConstants.LYNX_TOKEN) > 0)){
                returnBeanDataList.getAmwayBeans().add(bean);
            }
        }
        returnBeanDataList.setCount(returnBeanDataList.getAmwayBeans().size());
        return returnBeanDataList;
    }


    public AmwayBeanDataList getLynxAmwayBeans() {

        List<AmwayBeanData> amwayBeanList = new ArrayList(AmwayBeansSingleton.getInstance().amwayBeans.values());

        AmwayBeanDataList returnBeanDataList = new AmwayBeanDataList();
        returnBeanDataList.setAmwayBeans(new ArrayList<AmwayBeanData>());

        for(AmwayBeanData bean:amwayBeanList) {
            if (bean.getClassObj().getPackage().getName().indexOf(AmwaycoetoolsConstants.LYNX_TOKEN) > 0){
                returnBeanDataList.getAmwayBeans().add(bean);
            }
        }
        returnBeanDataList.setCount(returnBeanDataList.getAmwayBeans().size());
        return returnBeanDataList;
    }


    public AmwayBeanDataList getAmwayAnnotation() {
        AmwayBeanDataList beanDataList = new AmwayBeanDataList();
        beanDataList.setAmwayBeans(new ArrayList(AmwayBeansSingleton.getInstance().amwayAnnotations.values()));
        beanDataList.setCount(AmwayBeansSingleton.getInstance().amwayAnnotations.size());
        return beanDataList;
    }

    public AmwayBeanDataList getAmwayBeansWithoutAnnotation() {

        List<AmwayBeanData> amwayBeanList = new ArrayList(AmwayBeansSingleton.getInstance().amwayBeans.values());

        AmwayBeanDataList returnBeanDataList = new AmwayBeanDataList();
        returnBeanDataList.setAmwayBeans(new ArrayList<AmwayBeanData>());

        for(AmwayBeanData bean:amwayBeanList) {
            if (!bean.getClassObj().isAnnotationPresent(AmwayBean.class)){
                returnBeanDataList.getAmwayBeans().add(bean);
            }
        }
        returnBeanDataList.setCount(returnBeanDataList.getAmwayBeans().size());
        return returnBeanDataList;
    }

    public AmwayBeanDataList getAmwayHybrisOverrideBeans() {
        List<AmwayBeanData> allBeans = new ArrayList(AmwayBeansSingleton.getInstance().amwayBeans.values());
        List<AmwayBeanData> overrideBeans = new ArrayList();

        for (AmwayBeanData bean : allBeans ){
            if (bean.getParents() != null && bean.getParents().size() > 0) {
                AmwayBeanParentData parent= bean.getParents().get(0);
                if (parent.getPackageName().startsWith("de.") &&
                        !parent.getClassName().endsWith("DefaultGenericDao") &&
                        !parent.getClassName().endsWith("AbstractJobPerformable")) {
                    overrideBeans.add(bean);
                }
            }
        }

        AmwayBeanDataList beanDataList = new AmwayBeanDataList();
        beanDataList.setAmwayBeans(overrideBeans);
        beanDataList.setCount(overrideBeans.size());
        return beanDataList;
    }


    /**
     * Inner static singleton to store bean models.
     */
    private static class AmwayBeansSingleton {

        private static final Logger LOG = Logger.getLogger(AmwayBeansSingleton.class);

        private static AmwayBeansSingleton instance = null;
        private static Map amwayBeans = new HashMap<String, AmwayBeanData>();
        private static Map amwayAnnotations = new HashMap<String, AmwayBeanData>();


        private AmwayBeansSingleton() {
        }

        public static AmwayBeansSingleton getInstance() {
            synchronized(AmwayBeansSingleton.class) {
                if (instance == null) {
                    instance = new AmwayBeansSingleton();
                    instance.loadAmwayBeanMap();
                    instance.loadAmwayAnnotationsMap();
                }
            }
            return instance;
        }

        private static void loadAmwayBeanMap() {
            String[] allBeans = Registry.getCoreApplicationContext().getBeanDefinitionNames();

            Object bean = null;
            for (String beanName : allBeans) {
                try {
                    bean = Registry.getCoreApplicationContext().getBean(beanName);
                    String classNameStr = bean.getClass().getName();
                    if (classNameStr.contains(AmwaycoetoolsConstants.LYNX_TOKEN) ||
                            classNameStr.contains(AmwaycoetoolsConstants.AMWAY_CORE_TOKEN) ) {
                        amwayBeans.put(beanName, loadCommon(beanName, bean));
                    }
                } catch (Exception exc) {
                    //do something about this later ;)  but just log for now
                    LOG.error("CoETools Exception incurred when loading amway bean map: " + exc.getMessage());
                }
            }

        }

        private static void loadAmwayAnnotationsMap() {
            String[] allBeans = Registry.getCoreApplicationContext().getBeanNamesForAnnotation(AmwayBean.class);

            Object bean = null;
            for (String beanName : allBeans) {
                try {
                    bean = Registry.getCoreApplicationContext().getBean(beanName);
                    amwayAnnotations.put(beanName, loadCommon(beanName, bean));

                } catch (Exception exc) {
                    //do something about this later ;) but just log for now
                    LOG.error("CoETools Exception incurred when loading amway annotations map: " + exc.getMessage());
                }
            }

        }

        private static AmwayBeanData loadCommon(String beanName, Object bean) {

            //load up bean
            AmwayBeanData amwayBean = new AmwayBeanData();
            amwayBean.setName(beanName);
            amwayBean.setClassName(bean.getClass().getSimpleName());
            amwayBean.setClassObj(bean.getClass());
            amwayBean.setPackageName(bean.getClass().getPackage().getName());


            //load the beans parent stack
            ArrayList<AmwayBeanParentData> parents = new ArrayList<AmwayBeanParentData>();
            Class parentClass = bean.getClass().getSuperclass();
            while (!parentClass.isInstance(Object.class)) {
                //load parent bean
                AmwayBeanParentData parentBeanData = new AmwayBeanParentData();
                parentBeanData.setClassName(parentClass.getSimpleName());
                parentBeanData.setPackageName(parentClass.getPackage().getName());
                parents.add(parentBeanData);
                parentClass=parentClass.getSuperclass();
            }

            ArrayList<AmwayBeanAnnotationData> annotations = new ArrayList<AmwayBeanAnnotationData>();
            for (Annotation note : bean.getClass().getAnnotations()) {
                AmwayBeanAnnotationData annotationBeanData = new AmwayBeanAnnotationData();
                annotationBeanData.setAnnotation(note.annotationType().getSimpleName());
                annotations.add(annotationBeanData);
            }

            ArrayList<AmwayBeanMethodData> methods = new ArrayList<AmwayBeanMethodData>();
            for (Method meth : bean.getClass().getDeclaredMethods()) {
                AmwayBeanMethodData methodBeanData = new AmwayBeanMethodData();
                methodBeanData.setMethod(meth.getName());
                methodBeanData.setParamCount(meth.getParameterCount()+"");
                methodBeanData.setReturnTypeName(meth.getReturnType().getSimpleName());
                if (meth.getReturnType().getPackage() != null) {
                    methodBeanData.setReturnTypePackage(meth.getReturnType().getPackage().getName());
                } else {
                    methodBeanData.setReturnTypeName("void");
                }
                methods.add(methodBeanData);
            }

            amwayBean.setParents(parents);
            amwayBean.setAnnotations(annotations);
            amwayBean.setMethods(methods);
            return amwayBean;

        }

    }
}
