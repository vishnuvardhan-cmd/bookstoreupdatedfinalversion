package com.dailycodelearner.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="Zipcode")
public class Zipcode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="zipcodeId")
    private Long id;
    private String name;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(cascade={CascadeType.ALL},fetch = FetchType.LAZY)
    @JoinColumn(name="cityId",referencedColumnName = "cityId")
    private City city;
}
