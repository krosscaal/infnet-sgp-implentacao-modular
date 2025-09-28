package br.edu.infnet.mono.domain.dto;

import br.edu.infnet.mono.domain.entity.Endereco;
import br.edu.infnet.mono.domain.enumarator.EnumTipoCondominio;
import br.edu.infnet.mono.domain.enumarator.EnumTipoSituacao;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import static br.edu.infnet.mono.utils.MensagemCenter.*;

@Getter
@Setter
public class CondominioDTO {
    Long id;
    @Size(min = 5, max = 150, message = "O tamanho deve ser entre 5 e 150 caracteres.")
    @NotBlank(message = E_UM_CAMPO_OBRIGATORIO)
    String nomeCondominio;

    @NotNull(message = NAO_NULL)
    @Enumerated(EnumType.ORDINAL)
    EnumTipoCondominio tipoCondominio;

    @NotNull(message = NAO_NULL)
    int totalUnidades;

    @Size(min = 15, max = 15, message = "deve conter 15 digitos numéricos")
    @Pattern(regexp = "^\\d{15}$", message = DEVE_CONTER_SOMENTE_NUMEROS)
    String cnpj;

    @Size(min = 10, max = 11, message = "deve conter entre 10 e 11 caracteres numéricos")
    @Pattern(regexp = "^\\d{10,11}$", message = DEVE_CONTER_SOMENTE_NUMEROS)
    String telefoneContato1;

    @Size(min = 10, max = 11, message = "deve conter entre 10 e 11 caracteres numéricos")
    @Pattern(regexp = "^\\d{10,11}$", message = DEVE_CONTER_SOMENTE_NUMEROS)
    String telefoneContato2;

    Endereco endereco;

    @Size(min = 4, max = 200, message = "O tamanho deve ser entre 4 e 200 caracteres.")
    @NotBlank(message = E_UM_CAMPO_OBRIGATORIO)
    String nomeSindico;

    @Size(min = 10, max = 11, message = "deve conter entre 10 e 11 caracteres numéricos")
    @Pattern(regexp = "^\\d{10,11}$", message = DEVE_CONTER_SOMENTE_NUMEROS)
    String telefoneSindico;

    @NotNull(message = NAO_NULL)
    @Enumerated(EnumType.ORDINAL)
    EnumTipoSituacao situacao;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\n");
        sb.append("Id: ").append(id).append("\n");
        sb.append("Nome Condominio: ").append(nomeCondominio).append("\n");
        sb.append("Tipo Condominio: ").append(tipoCondominio.getTipoDeCondominio()).append("\n");
        sb.append("Total de Unidades: ").append(totalUnidades).append("\n");
        sb.append("Cnpj: ").append(cnpj).append("\n");
        sb.append("Telefone de Contato 1: ").append(telefoneContato1).append("\n");
        sb.append("Telefone de Contato 2: ").append(telefoneContato2).append("\n");
        sb.append("Situação :").append(situacao.getDescricao()).append("\n");
        sb.append("Endereço: ").append(endereco);
        sb.append("Nome do Sindico: ").append(nomeSindico).append("\n");
        sb.append("Telefone do Sindico: ").append(telefoneSindico).append("\n");
        return sb.toString();
    }
}
