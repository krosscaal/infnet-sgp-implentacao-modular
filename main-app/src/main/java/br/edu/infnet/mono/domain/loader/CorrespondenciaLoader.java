/*
 * Author: Krossby Adhemar Camacho Alviz
 * owned by Krossft.
 */

package br.edu.infnet.mono.domain.loader;

import br.edu.infnet.mono.domain.dto.CorrespondenciaDTO;
import br.edu.infnet.mono.domain.dto.MoradiaDTO;
import br.edu.infnet.mono.domain.service.CorrespondenciaService;
import br.edu.infnet.mono.domain.service.MoradiaService;
import br.edu.infnet.mono.domain.service.UsuarioSistemaService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

@Order(7)
@Component
public class CorrespondenciaLoader implements ApplicationRunner {
    private final CorrespondenciaService correspondenciaService;
    private final UsuarioSistemaService usuarioSistemaService;
    private final MoradiaService moradiaService;
    Logger log = Logger.getLogger(CorrespondenciaLoader.class.getName());

    public CorrespondenciaLoader(CorrespondenciaService correspondenciaService, UsuarioSistemaService usuarioSistemaService, MoradiaService moradiaService) {
        this.correspondenciaService = correspondenciaService;
        this.usuarioSistemaService = usuarioSistemaService;
        this.moradiaService = moradiaService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        FileReader arquivo = new FileReader("correspondencia.txt");

        try (BufferedReader lerArquivo = new BufferedReader(arquivo)) {
            String linha = lerArquivo.readLine();
            String[] campos = null;

            while (linha != null) {
                campos = linha.split(";");
                Long idMoradia = Long.valueOf(campos[0]);
                String dataRecepcao = campos[3] + " 14:00:00";
                DateTimeFormatter formatar = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                LocalDateTime localDateTimeDataRecepcao = LocalDateTime.parse(dataRecepcao, formatar);

                MoradiaDTO moradia = moradiaService.buscarPorId(idMoradia);

                CorrespondenciaDTO dto = new CorrespondenciaDTO();
                dto.setNomeDestinatario(campos[1]);
                dto.setTelefoneDestinatario(campos[2]);
                dto.setUsuarioRecepcao(usuarioSistemaService.buscarPorId(2L));
                dto.setMoradiaEntrega(moradia);
                dto.setDataRecepcao(localDateTimeDataRecepcao);

                correspondenciaService.incluir(dto);
                linha = lerArquivo.readLine();
            }
        }
        log.info("lista Correspondencia carregada com sucesso");
        correspondenciaService.listarTodos().forEach(correspondencia -> log.info(correspondencia.toString()));
    }
}
