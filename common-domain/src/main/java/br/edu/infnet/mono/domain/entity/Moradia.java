/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.edu.infnet.mono.domain.entity;

import br.edu.infnet.mono.domain.enumerator.EnumTipoMoradia;
import br.edu.infnet.mono.domain.enumerator.EnumTipoSituacao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "tb_moradia")
public class Moradia extends EntidadeBase {

    @Enumerated(EnumType.STRING)
    private EnumTipoMoradia tipoMoradia;

    @ManyToOne
    @JoinColumn(name = "propietario_id")
    private UsuarioCondominio propietario;


    @OneToOne
    @JoinColumn(name = "morador_id", referencedColumnName = "id")
    private UsuarioCondominio morador;

    @Column(nullable = false, length = 4)
    private String numeroUnidade;

    @Enumerated(EnumType.STRING)
    private EnumTipoSituacao situacao;

    @Column
    private String quadraTorreBloco;

    @Column
    private String lote;

    @Override
    public String toString() {
        return String.format("Moradia id: %d, número unidade:%s %s", getId(), numeroUnidade, situacao.getDescricao());
    }
}
