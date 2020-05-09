package org.ebuy.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Burak Köken on 9.5.2020.
 */
@Entity
@Getter
@Setter
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "district",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Neighborhood> neighborhoods = new HashSet<>();

}
