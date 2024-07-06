package com.bbm.beprepared.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_citizens")
public class Citizen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, length = 9)
    private String phone;
    private String deviceId;
    @Column(length = 6)
    private String otp;
    private boolean verified;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
}
