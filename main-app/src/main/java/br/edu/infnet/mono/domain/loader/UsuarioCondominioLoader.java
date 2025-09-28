/*
 * Author: Krossby Adhemar Camacho Alviz
 * owned by Krossft.
 */

package br.edu.infnet.mono.domain.loader;

import br.edu.infnet.mono.domain.dto.UsuarioCondominioDTO;
import br.edu.infnet.mono.domain.dto.UsuarioDTO;
import br.edu.infnet.mono.domain.enumerator.EnumTipoResidente;
import br.edu.infnet.mono.domain.enumerator.EnumTipoSituacao;
import br.edu.infnet.mono.domain.service.UsuarioCondominioService;
import br.edu.infnet.mono.domain.service.UsuarioService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.logging.Logger;

import static br.edu.infnet.mono.domain.util.GeralUtils.getTipoResidente;
import static br.edu.infnet.mono.domain.util.GeralUtils.getTipoSituacao;

@Order(3)
@Component
public class UsuarioCondominioLoader implements ApplicationRunner {
    private final UsuarioCondominioService usuarioCondominioService;
    private final UsuarioService usuarioService;
    Logger log = Logger.getLogger(UsuarioCondominioLoader.class.getName());

    public UsuarioCondominioLoader(UsuarioCondominioService usuarioCondominioService, UsuarioService usuarioService) {
        this.usuarioCondominioService = usuarioCondominioService;
        this.usuarioService = usuarioService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        FileReader arquivo = new FileReader("usuario-condominio.txt");
        try (BufferedReader lerArquivo = new BufferedReader(arquivo)) {
            String linha = lerArquivo.readLine();
            String[] campos = null;

            while (linha != null) {
                campos = linha.split(";");
                Long idUsuario = Long.valueOf(campos[0]);
                UsuarioDTO usuario = usuarioService.buscarPorId(idUsuario);

                EnumTipoResidente residente = getTipoResidente(campos[1]);
                EnumTipoSituacao situacao = getTipoSituacao(campos[3]);

                UsuarioCondominioDTO usuarioCondominio = new UsuarioCondominioDTO();
                usuarioCondominio.setUsuario(usuario);
                usuarioCondominio.setTipoResidente(residente);
                usuarioCondominio.setEmail(!campos[2].equalsIgnoreCase("null") ? campos[2] : null);
                usuarioCondominio.setSituacao(situacao);

                usuarioCondominioService.incluir(usuarioCondominio);

                linha = lerArquivo.readLine();
            }
        }
        log.info("lista UsuarioCondomino carregada com sucesso");
        usuarioCondominioService.listarTodos().forEach(usuarioCondominio -> log.info(usuarioCondominio.toString()));

    }
}
