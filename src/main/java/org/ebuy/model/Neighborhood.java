package org.ebuy.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Burak Köken on 9.5.2020.
 */
@Entity
@Getter
@Setter
public class Neighborhood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String postCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id")
    private District district;

}
