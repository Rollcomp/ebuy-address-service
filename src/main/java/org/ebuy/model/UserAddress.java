package org.ebuy.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Burak Köken on 10.5.2020.
 */
@Entity
@Getter
@Setter
public class UserAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @OneToOne
    @Column(nullable = false)
    private City city;

    @OneToOne
    @Column(nullable = false)
    private District district;

    @OneToOne
    @Column(nullable = false)
    private Neighborhood neighborhood;

    @Column(nullable = false)
    private String detail;

    @Column(nullable = false)
    private String userId;

}
