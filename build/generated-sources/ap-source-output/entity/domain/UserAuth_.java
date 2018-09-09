package entity.domain;

import entity.domain.Clinic;
import entity.domain.GroupAuth;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-09-09T13:14:43")
@StaticMetamodel(UserAuth.class)
public class UserAuth_ { 

    public static volatile SingularAttribute<UserAuth, String> password;
    public static volatile ListAttribute<UserAuth, GroupAuth> groupAuths;
    public static volatile SingularAttribute<UserAuth, Long> id;
    public static volatile SingularAttribute<UserAuth, Clinic> clinic;
    public static volatile SingularAttribute<UserAuth, String> email;
    public static volatile SingularAttribute<UserAuth, String> username;

}