package com.mplatrforma.amr.entity;

import com.mplatrforma.amr.entity.MetaUnit;
import java.util.Collection;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2013-01-04T18:10:47")
@StaticMetamodel(MetaUnitMultivalued.class)
public abstract class MetaUnitMultivalued_ extends MetaUnit_ {

    public static volatile ListAttribute<MetaUnitMultivalued, MetaUnit> sub_meta_units;
    public static volatile SingularAttribute<MetaUnitMultivalued, Collection> tagged_entities;
    public static volatile SingularAttribute<MetaUnitMultivalued, Long> isSplittingEnabled;
    public static volatile SingularAttribute<MetaUnitMultivalued, Long> isCatalogizable;

}