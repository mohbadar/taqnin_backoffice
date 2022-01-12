package com.nsia.officems._admin.system_registry;

import java.time.LocalDateTime;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.*;

// @Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
// @ToString
// @EqualsAndHashCode
@Entity(name = "SystemRegistry")
@Table(name = "system_registry")
public class SystemRegistry {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "system_registry_generator")
    @SequenceGenerator(name = "system_registry_generator", sequenceName = "system_registry_seq", allocationSize = 1)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private boolean active;

    @Column(name = "content")
    @Type(type = "text")
    private String content;

    // map_layer, datatables, dashboard
    @Column(name = "registry_type")
    private String registryType;

    @Column(name = "registry_slug")
    private String registrySlug;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "env_slug")
    private String envSlug;

    public SystemRegistry(String name, String description, String registryType) {
        this.name = name;
        this.description = description;
        this.registryType = registryType;
        this.active = false;
    }

    @Override
    public String toString() {
        return "SystemRegistry [id=" + id + ", name=" + name + ", description=" + description + ", active=" + active
                + ", registry_type=" + registryType +  ", registry_slug=" + registrySlug + ", envSlug=" + envSlug + ", created_at=" + createdAt
                + ", updated_at=" + updatedAt + "]";
    }
}
