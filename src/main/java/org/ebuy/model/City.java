package org.ebuy.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Burak Köken on 9.5.2020.
 */
@Entity
@Getter
@Setter
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String code;
    private String countryCode;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "city",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<District> districts = new HashSet<>();

}
