package com.example.consumerservice.entities;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address {
    private String country;
    private String city;
    private String addressLine1;
    private String addressLine2;
    private int zip;
}
