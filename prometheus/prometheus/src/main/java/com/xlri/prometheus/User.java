package com.xlri.prometheus;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@Table(name = "Users_XLRI")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;

}
