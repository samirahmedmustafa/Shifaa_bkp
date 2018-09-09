package entity.domain;

import entity.domain.UserAuth;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-09-09T13:14:43")
@StaticMetamodel(GroupAuth.class)
public class GroupAuth_ { 

    public static volatile ListAttribute<GroupAuth, UserAuth> userAuths;
    public static volatile SingularAttribute<GroupAuth, Long> id;
    public static volatile SingularAttribute<GroupAuth, String> groupname;

}