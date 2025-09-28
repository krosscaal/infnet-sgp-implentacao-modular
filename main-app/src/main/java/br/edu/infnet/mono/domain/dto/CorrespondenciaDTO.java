package br.edu.infnet.mono.domain.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

import static br.edu.infnet.mono.domain.util.MensagemCenter.*;

@Getter
@Setter
public class CorrespondenciaDTO {
    private Long id;

    @NotNull(message = NAO_NULL)
    private MoradiaDTO moradiaEntrega;

    @NotBlank(message = E_UM_CAMPO_OBRIGATORIO)
    @Size(max = 150, message = "O tamanho deve ser menor ou igual a 150 caracteres.")
    private String nomeDestinatario;

    @Size(min = 10, max = 11, message = "deve ser entre 10 e 11 caracteres numéricos")
    @Pattern(regexp = "^\\d{10,11}$", message = DEVE_CONTER_SOMENTE_NUMEROS)
    private String telefoneDestinatario;

    @Size(max = 100, message = "O tamanho deve ser menor ou igual a 100 caracteres.")
    @Email(message = FORMATO_E_MAIL_INCORRETO)
    private String emailDestinatario;

    private String codigoIdentificadorDaEntrega;

    @NotNull(message = NAO_NULL)
    @CreationTimestamp
    private LocalDateTime dataRecepcao;

    private LocalDateTime dataEntregaDestinatario;

    @NotNull(message = NAO_NULL)
    private UsuarioSistemaDTO usuarioRecepcao;

    private UsuarioSistemaDTO usuarioEntrega;

    @Size(max = 150, message = "O tamanho deve ser menor ou igual a 150 caracteres.")
    private String nomeMoradorRecepcao;
    @Override
    public String toString() {
        return String.format("Correspondencia id:%d moradia entrega:%s destinatario:%s", getId(), moradiaEntrega.getNumeroUnidade(), nomeDestinatario);
    }
}
