package com.example.backend.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "user_table")
@Builder(toBuilder = true)
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class UserEntity {
    @Id
    String id;

    String firstName;

    String lastName;

    @Email
    @Column(unique = true, nullable = false)
    String email;

    @Column(name = "full_name")
    String fullName;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Singular
    Set<UserType> roles;

    @Singular
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_segments", joinColumns = @JoinColumn(name = "user_table_id"))
    @Column(name = "segment_entity_name")
    private Set<String> segments = new TreeSet<>();
}