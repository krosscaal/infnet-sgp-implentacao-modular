/*
 * Author: Krossby Adhemar Camacho Alviz
 * owned by Krossft.
 */

package br.edu.infnet.mono.domain.loader;

import br.edu.infnet.mono.domain.dto.MoradiaDTO;
import br.edu.infnet.mono.domain.dto.UsuarioCondominioDTO;
import br.edu.infnet.mono.domain.dto.UsuarioDTO;
import br.edu.infnet.mono.domain.dto.VisitanteDTO;
import br.edu.infnet.mono.domain.enumerator.EnumTipoAcesso;
import br.edu.infnet.mono.domain.service.MoradiaService;
import br.edu.infnet.mono.domain.service.UsuarioCondominioService;
import br.edu.infnet.mono.domain.service.UsuarioService;
import br.edu.infnet.mono.domain.service.VisitanteService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;


@Order(6)
@Component
public class VisitanteLoader implements ApplicationRunner {
    private final VisitanteService visitanteService;
    private final MoradiaService moradiaService;
    private final UsuarioCondominioService usuarioCondominioService;
    private final UsuarioService usuarioService;
    Logger log = Logger.getLogger(VisitanteLoader.class.getName());

    public VisitanteLoader(VisitanteService visitanteService, MoradiaService moradiaService, UsuarioCondominioService usuarioCondominioService, UsuarioService usuarioService) {
        this.visitanteService = visitanteService;
        this.moradiaService = moradiaService;
        this.usuarioCondominioService = usuarioCondominioService;
        this.usuarioService = usuarioService;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        FileReader arquivoVisitante = new FileReader("visitante.txt");

        try (BufferedReader lerArquivo = new BufferedReader(arquivoVisitante)) {
            String linha = lerArquivo.readLine();
            String[] campos = null;

            while (linha != null) {
                campos = linha.split(";");

                Long idUsuario = Long.valueOf(campos[0]);
                UsuarioDTO usuario = usuarioService.buscarPorId(idUsuario);

                int ordinalTipoAcesso = Integer.parseInt(campos[1]);
                EnumTipoAcesso tipoAcesso = EnumTipoAcesso.getByCodigo(ordinalTipoAcesso);
                VisitanteDTO visitante = criarVistante(campos);
                visitante.setUsuarioVisitante(usuario);
                visitante.setTipoAcesso(tipoAcesso);

                visitanteService.incluir(visitante);

                linha = lerArquivo.readLine();
            }
        }
        log.info("lista Visitante carregada com sucesso");
        visitanteService.listarTodos().forEach(visitante -> log.info(visitante.toString()));
    }

    private VisitanteDTO criarVistante(String[] campos) {
        Long idMoradia = Long.valueOf(campos[3]);
        Long idUsuarioAutorizado = Long.valueOf(campos[4]);
        String dataAcesso = campos[6];
        DateTimeFormatter formatar = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime localDateTimeDataAcesso = LocalDateTime.parse(dataAcesso, formatar);

        MoradiaDTO moradia = moradiaService.buscarPorId(idMoradia);
        UsuarioCondominioDTO usuarioCondominio = usuarioCondominioService.buscarPorId(idUsuarioAutorizado);

        VisitanteDTO visitante = new VisitanteDTO();
        visitante.setCartaoAcesso(campos[2]);
        visitante.setMoradiaDestinoVisitante(moradia);
        visitante.setUsuarioAutorizacao(usuarioCondominio);
        visitante.setObservacao(campos[5]);
        visitante.setIngresso(localDateTimeDataAcesso);
        return visitante;
    }
}
