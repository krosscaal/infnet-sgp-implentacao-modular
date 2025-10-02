package br.edu.infnet.mono.domain.dto;

import br.edu.infnet.mono.domain.enumerator.EnumTipoSituacao;
import br.edu.infnet.mono.domain.enumerator.EnumTipoUsuarioSistema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import static br.edu.infnet.mono.domain.util.MensagemCenter.*;

@Getter
@Setter
public class UsuarioSistemaDTO {
    private Long id;

    @NotNull(message = NAO_NULL)
    private UsuarioDTO usuario;

    @NotNull(message = NAO_NULL)
    @Enumerated(EnumType.STRING)
    private EnumTipoUsuarioSistema tipoUsuarioSistema;

    @Size(max = 80, message = "O tamanho deve ser menor ou igual a 80 caracteres.")
    @NotBlank(message = E_UM_CAMPO_OBRIGATORIO)
    @Email(message = FORMATO_E_MAIL_INCORRETO)
    private String email;

    @Size(max = 100, message = "O tamanho deve ser menor ou igual a 100 caracteres.")
    @NotBlank(message = E_UM_CAMPO_OBRIGATORIO)
    private String senha;

    @Size(max = 100, message = "O tamanho deve ser menor ou igual a 100 caracteres.")
    @NotBlank(message = E_UM_CAMPO_OBRIGATORIO)
    private String password;

    @Enumerated(EnumType.STRING)
    private EnumTipoSituacao situacao;

    @Override
    public String toString() {
        return String.format("Usuário Sistema id:%d, %s %s %s %s %s %s",
                getId(), getUsuario().toString(), tipoUsuarioSistema.getDescricao(), email, senha, password, situacao.getDescricao());
    }
}
