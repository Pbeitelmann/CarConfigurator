package com.computacenter.carconfig.internal;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "MANUFACTURER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode(callSuper = false, exclude = {"id"})
public class ManufacturerEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "MANUFACTURER_ID", nullable = false, unique = true))
    private ManufacturerId manufacturerId;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "CONTACT_EMAIL", nullable = false)
    private String contactEmail;

    @Column(name = "CONTACT_FIRST_NAME", nullable = false)
    private String contactFirstName;

    @Column(name = "CONTACT_LAST_NAME", nullable = false)
    private String contactLastName;
}
