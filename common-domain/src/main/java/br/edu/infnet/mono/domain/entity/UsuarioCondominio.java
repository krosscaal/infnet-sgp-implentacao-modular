/*
 * Author: Krossby Adhemar Camacho Alviz
 * owned by Krossft.
 */

package br.edu.infnet.mono.domain.entity;

import br.edu.infnet.mono.domain.enumerator.EnumTipoResidente;
import br.edu.infnet.mono.domain.enumerator.EnumTipoSituacao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_usuario_condominio")
public class UsuarioCondominio extends EntidadeBase {

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private EnumTipoResidente tipoResidente;

    @Column(length = 80)
    private String email;

    @Enumerated(EnumType.STRING)
    private EnumTipoSituacao situacao;

    @JsonIgnore
    @OneToMany(mappedBy = "propietario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Moradia> moradias = new ArrayList<>();

    @Override
    public String toString() {
        return String.format("Usuario Condominio id:%d, %s %s %s %s",
                getId(), getUsuario().toString(), tipoResidente.getUsuarioResidente(), email, situacao.getDescricao());
    }
}
