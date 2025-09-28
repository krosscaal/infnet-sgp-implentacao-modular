/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.edu.infnet.mono.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class EntidadeBase implements Serializable {

    private static final long serialVersionUID = 1905122041950251207L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @CreationTimestamp
    private LocalDateTime dataCriacao;

    @JsonIgnore
    @CreationTimestamp
    private LocalDateTime dataAtualizacao;

    @PrePersist
    protected void prePersist() {
        this.dataCriacao = LocalDateTime.now();
    }
    @PreUpdate
    protected void preUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
    }
}
