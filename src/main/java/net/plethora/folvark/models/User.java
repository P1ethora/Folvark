package net.plethora.folvark.models;


import lombok.Getter;
import lombok.Setter;
import net.plethora.folvark.models.state.Role;
import net.plethora.folvark.models.state.Status;

import javax.persistence.*;


@Getter
@Setter
@Entity
//@Table(name = "users")
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
    @Column(name = "nick_name")
    private String nickName;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;

    public User(String email, String password, String firstName, String lastName, String middleName, String nickName, Status status, Role role) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.nickName = nickName;
        this.status = status;
        this.role = role;
    }

    public User() {
    }
}
