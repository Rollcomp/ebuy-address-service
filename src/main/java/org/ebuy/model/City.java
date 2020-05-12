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

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String code;
    @Column(nullable = false)
    private String countryCode;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "city",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<District> districts = new HashSet<>();

    public void addDistrict(District district) {
        districts.add(district);
        district.setCity(this);
    }

    public void removeDistrict(District district) {
        districts.remove(district);
        district.setCity(null);
    }

}
