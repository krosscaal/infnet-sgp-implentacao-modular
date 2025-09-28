/*
 * Author: Krossby Adhemar Camacho Alviz
 * owned by Krossft.
 */

package br.edu.infnet.mono.domain.loader;

import br.edu.infnet.mono.domain.dto.UsuarioDTO;
import br.edu.infnet.mono.domain.dto.UsuarioSistemaDTO;
import br.edu.infnet.mono.domain.enumerator.EnumTipoSituacao;
import br.edu.infnet.mono.domain.enumerator.EnumTipoUsuarioSistema;
import br.edu.infnet.mono.domain.service.UsuarioService;
import br.edu.infnet.mono.domain.service.UsuarioSistemaService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.logging.Logger;

import static br.edu.infnet.mono.domain.util.GeralUtils.getTipoSituacao;
import static br.edu.infnet.mono.domain.util.GeralUtils.getTipoUsuariosistema;

@Order(4)
@Component
public class UsuarioSistemaLoader implements ApplicationRunner {

    private final UsuarioSistemaService usuarioSistemaService;
    private final UsuarioService usuarioService;
    Logger log = Logger.getLogger(getClass().getName());

    public UsuarioSistemaLoader(UsuarioSistemaService usuarioSistemaService, UsuarioService usuarioService) {
        this.usuarioSistemaService = usuarioSistemaService;
        this.usuarioService = usuarioService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        FileReader arquivo = new FileReader("usuario-sistema.txt");
        try (BufferedReader lerArquivo = new BufferedReader(arquivo)) {
            String linha = lerArquivo.readLine();
            String[] campos = null;

            while (linha != null) {
                campos = linha.split(";");

                Long idUsuario = Long.valueOf(campos[0]);
                UsuarioDTO usuario = usuarioService.buscarPorId(idUsuario);

                EnumTipoUsuarioSistema usuariosistema = getTipoUsuariosistema(campos[1]);
                EnumTipoSituacao situacao = getTipoSituacao(campos[5]);

                UsuarioSistemaDTO usuarioSistema = new UsuarioSistemaDTO();
                usuarioSistema.setUsuario(usuario);
                usuarioSistema.setTipoUsuarioSistema(usuariosistema);
                usuarioSistema.setEmail(!campos[2].equalsIgnoreCase("null") ? campos[2] : null);
                usuarioSistema.setSenha(!campos[3].equalsIgnoreCase("null") ? campos[3] : null);
                usuarioSistema.setPassword(!campos[4].equalsIgnoreCase("null") ? campos[4] : null);
                usuarioSistema.setSituacao(situacao);

                usuarioSistemaService.incluir(usuarioSistema);
                linha = lerArquivo.readLine();
            }
        }
        log.info("lista UsuarioSistema carregada com sucesso");
        usuarioSistemaService.listarTodos().forEach(usuarioSistema -> log.info(usuarioSistema.toString()));
    }

}
