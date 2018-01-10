package com.amway.amwaycoetools.facades;

import com.amway.amwaycoetools.data.AmwayBeanData;
import com.amway.amwaycoetools.data.AmwayBeanDataList;

import java.util.List;

/**
 * Created by aiueq92 on 8/13/17.
 */
public interface CoeToolsFacades {

    public AmwayBeanDataList getAmwayBeans();

    public AmwayBeanDataList getCoreAmwayBeans();

    public AmwayBeanDataList getLynxAmwayBeans();

    public AmwayBeanDataList getAmwayAnnotation();

    public AmwayBeanDataList getAmwayBeansWithoutAnnotation();

    public AmwayBeanDataList getAmwayHybrisOverrideBeans();
}
