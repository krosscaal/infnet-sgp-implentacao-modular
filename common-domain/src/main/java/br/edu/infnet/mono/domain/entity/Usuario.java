package br.edu.infnet.mono.domain.entity;

import br.edu.infnet.mono.domain.enumerator.EnumTipoSituacao;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity(name = "tb_usuario")
public class Usuario extends EntidadeBase implements Serializable {
    private static final long serialVersionUID = 5405172041950251807L;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, length = 150)
    private String sobreNome;

    @Column(length = 14)
    private String cpf;

    @Column(length = 11)
    private String rg;

    @Column(name = "telefone_1", length = 11)
    private String telefone1;

    @Column(name = "telefone_2", length = 11)
    private String telefone2;

    private EnumTipoSituacao situacao;

    @Override
    public String toString() {
        return String.format("Usuario id:%d %s %s %s %s %s %s %s", getId(), nome, sobreNome, cpf, rg, telefone1, telefone2, situacao.getDescricao());
    }

}
