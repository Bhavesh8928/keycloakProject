package com.key.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Employee {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String address;
    private String phone;
    private Float salary;

}
