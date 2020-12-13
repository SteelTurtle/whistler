package org.gorillacorp.whistler.domain.model;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "user_account")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "id", callSuper = false)
@ToString(of = {"id", "userName"})
public class User extends AuditTable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "user_id", updatable = false, nullable = false)
    private UUID id;

    @NotNull
    @Column(name = "username", unique = true)
    private String userName;

    @JoinTable(name = "relation",
            joinColumns = {@JoinColumn(name = "follower_id",
                    referencedColumnName = "user_id",
                    nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "following_id",
                    referencedColumnName = "user_id",
                    nullable = false)})
    @ManyToMany
    private Set<User> whistlers;

    @OneToMany(
            mappedBy = "author",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Whistle> whistles = new HashSet<>();

    public User(final String username) {
        this.userName = username;
        this.whistles = new HashSet<>();
        this.whistlers = new HashSet<>();
    }
}
