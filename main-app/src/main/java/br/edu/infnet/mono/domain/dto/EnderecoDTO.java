package br.edu.infnet.mono.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import static br.edu.infnet.mono.domain.util.MensagemCenter.DEVE_CONTER_SOMENTE_NUMEROS;
import static br.edu.infnet.mono.domain.util.MensagemCenter.E_UM_CAMPO_OBRIGATORIO;

@Getter
@Setter
public class EnderecoDTO {
    private Long id;

    @Size(min = 5, max = 150, message = "O tamanho deve ser entre 5 e 150 caracteres.")
    @NotBlank(message = E_UM_CAMPO_OBRIGATORIO)
    private String logradouro;

    @Size(min = 1, max = 6, message = "O tamanho deve ser entre 1 e 6 caracteres.")
    @Pattern(regexp = "^\\d+$", message = DEVE_CONTER_SOMENTE_NUMEROS)
    private String numero;

    @Size(max = 150, message = "O tamanho deve ser menor ou igual a 150 caracteres.")
    private String complemento;

    @Size(max = 150, message = "O tamanho deve ser menor ou igual a 150 caracteres.")
    private String bairro;

    @Size(max = 150, message = "O tamanho deve ser menor ou igual a 150 caracteres.")
    private String localidade;

    @Size(min = 8, max = 8, message = "O tamanho deve ser igual a 8 caracteres.")
    @Pattern(regexp = "^\\d+$", message = DEVE_CONTER_SOMENTE_NUMEROS)
    private String cep;

    @Size(max = 30, message = "O tamanho deve ser menor ou igual a 30 caracteres.")
    private String estado;

    @Size(min = 2, max = 2)
    @Pattern(regexp = "^[A-Z]{2}$", message = "Deve ser em formato: XX")
    private String uf;
    @Override
    public String toString() {
        StringBuilder sbEndereco = new StringBuilder();
        sbEndereco.append("Logradouro: ").append(logradouro).append("\n");
        sbEndereco.append("Numero: ").append(numero).append("\n");
        sbEndereco.append("Complemento: ").append(complemento).append("\n");
        sbEndereco.append("Bairro: ").append(bairro).append("\n");
        sbEndereco.append("Localidade: ").append(localidade).append("\n");
        sbEndereco.append("CEP: ").append(cep).append("\n");
        sbEndereco.append("Estado: ").append(estado).append("\n");
        sbEndereco.append("UF: ").append(uf).append("\n");
        return sbEndereco.toString();
    }
}
