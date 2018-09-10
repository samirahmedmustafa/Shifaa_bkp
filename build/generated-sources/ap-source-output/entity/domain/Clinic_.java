package entity.domain;

import entity.domain.Area;
import entity.domain.ClinicService;
import entity.domain.Doctor;
import entity.domain.Insurance;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-09-10T16:42:06")
@StaticMetamodel(Clinic.class)
public class Clinic_ { 

    public static volatile SingularAttribute<Clinic, Area> area;
    public static volatile SingularAttribute<Clinic, String> image;
    public static volatile ListAttribute<Clinic, Insurance> insurances;
    public static volatile ListAttribute<Clinic, ClinicService> clinicServices;
    public static volatile ListAttribute<Clinic, Doctor> doctors;
    public static volatile SingularAttribute<Clinic, String> name;
    public static volatile SingularAttribute<Clinic, String> phones;
    public static volatile SingularAttribute<Clinic, Long> id;

}