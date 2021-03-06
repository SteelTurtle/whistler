package org.gorillacorp.whistler.domain.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@MappedSuperclass
@Data
public class AuditTable {
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt = LocalDate.now();

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "updated_at", nullable = false)
    private LocalDate updatedAt = LocalDate.now();
}
