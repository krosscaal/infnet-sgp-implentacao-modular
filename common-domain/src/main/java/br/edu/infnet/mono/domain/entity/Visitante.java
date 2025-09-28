/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.edu.infnet.mono.domain.entity;

import br.edu.infnet.mono.domain.enumerator.EnumTipoAcesso;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@Table(name = "tb_visitante")
public class Visitante extends EntidadeBase {

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario usuarioVisitante;

    @Enumerated(EnumType.ORDINAL)
    private EnumTipoAcesso tipoAcesso;

    @Column(length = 15)
    private String cartaoAcesso;

    @ManyToOne
    @JoinColumn(name = "moradia_id_destino", referencedColumnName = "id")
    private Moradia moradiaDestinoVisitante;

    @ManyToOne
    @JoinColumn(name = "autorizado_por_usuario_id", referencedColumnName = "id")
    private UsuarioCondominio usuarioAutorizacao;

    @Column(length = 150)
    private String observacao;

    @Column(name = "data_ingresso", nullable = false)
    private LocalDateTime ingresso;

    @Column(name = "data_saida")
    private LocalDateTime saida;

    @Override
    public String toString() {
        return String.format("Visitante id:%d %s %s %s %s %s %s %s",
                getId(), getUsuarioVisitante().toString(), tipoAcesso.getDescricao(), cartaoAcesso, moradiaDestinoVisitante.getNumeroUnidade(), usuarioAutorizacao.getId(), observacao, ingresso);
    }
}
