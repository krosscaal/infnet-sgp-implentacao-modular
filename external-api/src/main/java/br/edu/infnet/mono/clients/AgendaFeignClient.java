package br.edu.infnet.mono.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Component
@FeignClient(
        name = "agenda",
        url = "${api.agendamento.url}")
public interface AgendaFeignClient {
    @GetMapping("/agendamentos/data")
    List<AgendaResponse> getAgendamento(@RequestParam(value = "data") String data);

    class AgendaResponse {
        private String nome;
        private String sobrenome;
        private String cpf;
        private EnderecoResponse endereco;
        private Long unidadeDaVisita;
        private Long idMoradorSolicitante;
        private LocalDate dataVisita;
        private String observacao;

        @Override
        public String toString() {
            return "AgendaResponse{" +
                    "nome='" + nome + '\'' +
                    ", sobrenome='" + sobrenome + '\'' +
                    ", cpf='" + cpf + '\'' +
                    ", endereco=" + endereco +
                    ", unidadeDaVisita=" + unidadeDaVisita +
                    ", idMoradorSolicitante=" + idMoradorSolicitante +
                    ", dataVisita=" + dataVisita +
                    ", observacao='" + observacao + '\'' +
                    '}';
        }
    }
    class EnderecoResponse {
        private String cep;
        private String logradouro;
        private String numero;
        private String complemento;
        private String bairro;
        private String localidade;
        private String estado;
        private String uf;

        @Override
        public String toString() {
            return "EnderecoResponse{" +
                    "cep='" + cep + '\'' +
                    ", logradouro='" + logradouro + '\'' +
                    ", numero='" + numero + '\'' +
                    ", complemento='" + complemento + '\'' +
                    ", bairro='" + bairro + '\'' +
                    ", localidade='" + localidade + '\'' +
                    ", estado='" + estado + '\'' +
                    ", uf='" + uf + '\'' +
                    '}';
        }
    }
}
