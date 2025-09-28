package br.edu.infnet.mono.domain.dto;

import br.edu.infnet.mono.domain.enumerator.EnumTipoResidente;
import br.edu.infnet.mono.domain.enumerator.EnumTipoSituacao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static br.edu.infnet.mono.domain.util.MensagemCenter.FORMATO_E_MAIL_INCORRETO;
import static br.edu.infnet.mono.domain.util.MensagemCenter.NAO_NULL;

@Getter
@Setter
public class UsuarioCondominioDTO {
    private Long id;

    @NotNull(message = NAO_NULL)
    private UsuarioDTO usuario;

    @NotNull(message = NAO_NULL)
    @Enumerated(EnumType.STRING)
    private EnumTipoResidente tipoResidente;

    @Size(max = 80)
    @Email(message = FORMATO_E_MAIL_INCORRETO)
    private String email;

    @NotNull(message = NAO_NULL)
    @Enumerated(EnumType.STRING)
    private EnumTipoSituacao situacao;

    @JsonIgnore
    private List<MoradiaDTO> moradias = new ArrayList<>();

    @Override
    public String toString() {
        return String.format("Usuario Condominio id:%d, %s %s %s %s",
                getId(), getUsuario().toString(), tipoResidente.getUsuarioResidente(), email, situacao.getDescricao());
    }
}
