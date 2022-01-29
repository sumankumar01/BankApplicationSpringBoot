package com.Banking.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(
        name="customer",uniqueConstraints = {@UniqueConstraint(columnNames = {"email","mobileNum"})}
)
public class Customer {

    @Id
    @SequenceGenerator(name = "mySeqGen", sequenceName = "mySeq", initialValue = 98723450, allocationSize = 100)
    @GeneratedValue(generator = "mySeqGen")
    private Long id;
    @Column(name="firstName", nullable=false)
    private String firstName;
    @Column(name="lastName", nullable=true)
    private String lastName;
    @Column(name="email", nullable=false)
    private String email;
    @Column(name="mobileNum", nullable=false)
    private String mobileNum;
}
