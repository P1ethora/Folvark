package net.plethora.folvark.models;


import lombok.Data;
import net.plethora.folvark.models.state.Gender;
import net.plethora.folvark.models.state.Role;
import net.plethora.folvark.models.state.Status;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "login_name")
    private String loginName;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;
    @Column(name = "id_cart")
    private String idCart;
    @Column(name = "url_photo")
    private String urlPhoto;
    @Column(name = "birthday")
    private Date birthday;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;
    @Column(name ="number_phone" )
    private String numberPhone;
    @Column(name ="id_bagmap")
    private String idBugMap;

    public User(String email, String password, String firstName, String lastName, String middleName, String loginName, Status status, Role role) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.loginName = loginName;
        this.status = status;
        this.role = role;
    }

    public User() {
    }
}
