package com.mplatrforma.amr.entity;

import com.mplatrforma.amr.entity.SocioResearch;
import com.mplatrforma.amr.entity.Zacon;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-10-02T19:22:20")
@StaticMetamodel(DatabankStartPage.class)
public class DatabankStartPage_ { 

    public static volatile SingularAttribute<DatabankStartPage, Long> id;
    public static volatile SingularAttribute<DatabankStartPage, Long> pubs_last_show;
    public static volatile ListAttribute<DatabankStartPage, SocioResearch> res;
    public static volatile ListAttribute<DatabankStartPage, Zacon> laws;

}