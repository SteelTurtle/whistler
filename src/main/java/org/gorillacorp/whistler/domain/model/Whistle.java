package org.gorillacorp.whistler.domain.model;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Table(name = "whistle")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "id", callSuper = false)
@ToString(of = "id")
public class Whistle extends AuditTable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "whistle_id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    @NotNull
    @Size(min = 2, max = 200)
    private String whistle;

    public Whistle(final User author,
                   @Size(min = 2, max = 200) final String whistle) {
        this.author = author;
        this.whistle = whistle;
    }
}
