/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */
 
package br.edu.infnet.mono.domain.dto;

import br.edu.infnet.mono.domain.enumerator.EnumTipoSituacao;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import static br.edu.infnet.mono.domain.util.MensagemCenter.*;

@Getter
@Setter
public class UsuarioDTO {
    private Long id;

    @NotBlank(message = E_UM_CAMPO_OBRIGATORIO)
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\s]*$", message = DEVE_CONTER_SOMENTE_LETRAS)
    private String nome;

    @NotBlank(message = E_UM_CAMPO_OBRIGATORIO)
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\s]*$", message = DEVE_CONTER_SOMENTE_LETRAS)
    private String sobreNome;

    @Size(min = 14, max = 14, message = "O tamanho deve ser igual a 14 caracteres.")
    @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$", message = "Deve ser em formato : XXX.XXX.XXX-XX")
    private String cpf;
    
    @Size(max = 11, message = "O tamanho deve ser de 11 caracteres.")
    @Pattern(regexp = "^\\d{11}$", message = "RG deve conter apenas números")
    private String rg;

    @Size(min = 10, max = 11, message = "Tamanho deve ser entre 10 e 11 caracteres.")
    @Pattern(regexp = "^\\d{10,11}$", message = DEVE_CONTER_SOMENTE_NUMEROS)
    private String telefone1;

    @Size(min = 10, max = 11 , message = "Tamanho deve ser entre 10 e 11 caracteres.")
    @Pattern(regexp = "^\\d{10,11}$", message = DEVE_CONTER_SOMENTE_NUMEROS)
    private String telefone2;

    @NotNull(message = NAO_NULL)
    @Enumerated(EnumType.STRING)
    private EnumTipoSituacao situacao;

    @Override
    public String toString() {
        return String.format("Usuario id:%d %s %s %s %s %s %s %s", getId(), nome, sobreNome, cpf, rg, telefone1, telefone2, situacao.getDescricao());
    }

}
