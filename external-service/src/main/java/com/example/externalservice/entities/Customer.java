package com.example.externalservice.entities;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer {
    private Integer id;

    private Name name;

    private Contact contact;

    private Address address;
}
