/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.edu.infnet.mono.domain.entity;

import br.edu.infnet.mono.domain.enumerator.EnumTipoSituacao;
import br.edu.infnet.mono.domain.enumerator.EnumTipoUsuarioSistema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "tb_usuario_sistema")
public class UsuarioSistema extends EntidadeBase {

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private EnumTipoUsuarioSistema tipoUsuarioSistema;

    @Column(nullable = false, length = 80)
    private String email;

    @Column(length = 100)
    private String senha;

    @Column(length = 100)
    private String password;

    @Enumerated(EnumType.STRING)
    private EnumTipoSituacao situacao;

    @Override
    public String toString() {
        return String.format("Usuário Sistema id:%d, %s %s %s %s %s %s",
                getId(), getUsuario().toString(), tipoUsuarioSistema.getDescricao(), email, senha, password, situacao.getDescricao());
    }
}
