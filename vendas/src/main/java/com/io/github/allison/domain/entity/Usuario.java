package com.io.github.allison.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter

@Table(name  = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(name = "login")
    private String login;

    @Column(name = "senha")
    private String senha;

    @Column(name ="admin")
    private boolean admin;


    
}
