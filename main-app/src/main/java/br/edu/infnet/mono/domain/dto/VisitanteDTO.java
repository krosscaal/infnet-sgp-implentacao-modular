package br.edu.infnet.mono.domain.dto;

import br.edu.infnet.mono.domain.enumerator.EnumTipoAcesso;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static br.edu.infnet.mono.domain.util.MensagemCenter.NAO_NULL;

@Getter
@Setter
public class VisitanteDTO {
    private Long id;

    @NotNull(message = NAO_NULL)
    private UsuarioDTO usuarioVisitante;

    @NotNull(message = NAO_NULL)
    @Enumerated(EnumType.ORDINAL)
    private EnumTipoAcesso tipoAcesso;

    @Size(max = 15, message = "O tamanho deve ser menor ou igual a 15 caracteres.")
    private String cartaoAcesso;

    @NotNull(message = NAO_NULL)
    private MoradiaDTO moradiaDestinoVisitante;

    @NotNull(message = NAO_NULL)
    private UsuarioCondominioDTO usuarioAutorizacao;

    @Size(max = 150, message = "O tamanho deve ser menor ou igual a 150 caracteres.")
    private String observacao;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @NotNull(message = NAO_NULL)
    private LocalDateTime ingresso;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime saida;

    @Override
    public String toString() {
        return String.format("Visitante id:%d %s %s %s %s %s %s %s",
                getId(), getUsuarioVisitante().toString(), tipoAcesso.getDescricao(), cartaoAcesso, moradiaDestinoVisitante.getNumeroUnidade(), usuarioAutorizacao.getId(), observacao, ingresso);
    }
}
