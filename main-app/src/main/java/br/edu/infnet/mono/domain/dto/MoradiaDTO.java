package br.edu.infnet.mono.domain.dto;

import br.edu.infnet.mono.domain.enumerator.EnumTipoMoradia;
import br.edu.infnet.mono.domain.enumerator.EnumTipoSituacao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import static br.edu.infnet.mono.domain.util.MensagemCenter.DEVE_CONTER_SOMENTE_NUMEROS;
import static br.edu.infnet.mono.domain.util.MensagemCenter.NAO_NULL;

@Getter
@Setter
public class MoradiaDTO {
    private Long id;

    @Enumerated(EnumType.STRING)
    private EnumTipoMoradia tipoMoradia;

    @JsonIgnore
    private UsuarioCondominioDTO propietario;

    private UsuarioCondominioDTO morador;

    @NotNull(message = NAO_NULL)
    @Size(max = 4)
    @Pattern(regexp = "^\\d+$", message = DEVE_CONTER_SOMENTE_NUMEROS)
    private String numeroUnidade;

    @NotNull(message = NAO_NULL)
    @Enumerated(EnumType.STRING)
    private EnumTipoSituacao situacao;

    @Size(max = 150, message = "O tamanho deve ser menor ou igual a 150 caracteres.")
    private String quadraTorreBloco;

    @Size(max = 150, message = "O tamanho deve ser menor ou igual a 150 caracteres.")
    private String lote;
    @Override
    public String toString() {
        return String.format("Moradia id: %d, número unidade:%s %s", getId(), numeroUnidade, situacao.getDescricao());
    }
}
