package com.example.microgram.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor
@Document(collection = "users")
@Data
public class User implements UserDetails {
    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();
    private String account;
    @Indexed
    private String email;
    private String password;
    @DBRef
    private List<Publication> publications;
    private int publicationCount;
    private int subscriptionCount;
    private int subscriberCount;

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public Collection<? extends GrantedAuthority>
    getAuthorities() {
        return List.of(new SimpleGrantedAuthority("FULL"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
