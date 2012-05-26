package com.mplatrforma.amr.entity;

import java.util.ArrayList;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-05-26T23:06:21")
@StaticMetamodel(UserAccount.class)
public class UserAccount_ { 

    public static volatile SingularAttribute<UserAccount, String> id;
    public static volatile SingularAttribute<UserAccount, Integer> weights_use;
    public static volatile SingularAttribute<UserAccount, Integer> filters_use;
    public static volatile SingularAttribute<UserAccount, ArrayList> filters_usage;
    public static volatile SingularAttribute<UserAccount, String> name;
    public static volatile SingularAttribute<UserAccount, String> accountType;
    public static volatile SingularAttribute<UserAccount, String> emailAddress;
    public static volatile SingularAttribute<UserAccount, String> password;
    public static volatile SingularAttribute<UserAccount, ArrayList> filters;
    public static volatile SingularAttribute<UserAccount, ArrayList> filters_categories;

}