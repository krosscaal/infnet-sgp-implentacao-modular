/*
 * Author: Krossby Adhemar Camacho Alviz
 * owned by Krossft.
 */

package br.edu.infnet.mono.domain.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class ApiErrors {
    private final Integer status;
    private final String dataHora;
    private final String titulo;
    private List<Campo> campos;

    public ApiErrors(Integer status, String dataHora, String titulo) {
        this.status = status;
        this.dataHora = dataHora;
        this.titulo = titulo;
    }

    public ApiErrors(final Integer status, final String dataHora, final String titulo,
                     final List<Campo> campos) {

        this.status = status;
        this.dataHora = dataHora;
        this.titulo = titulo;
        this.campos = campos;
    }
}
