package com.mplatrforma.amr.entity;

import com.mplatrforma.amr.entity.MetaUnitEntityItem;
import com.mplatrforma.amr.entity.MetaUnitMultivaluedEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.MapAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-06-07T16:30:11")
@StaticMetamodel(DatabankStructure.class)
public class DatabankStructure_ { 

    public static volatile SingularAttribute<DatabankStructure, MetaUnitMultivaluedEntity> root;
    public static volatile MapAttribute<DatabankStructure, Long, MetaUnitEntityItem> items;
    public static volatile SingularAttribute<DatabankStructure, String> meta_identity;

}