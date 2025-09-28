/*
 * Author: Krossby Adhemar Camacho Alviz
 * owned by Krossft.
 */

package br.edu.infnet.mono.domain.loader;

import br.edu.infnet.mono.domain.dto.UsuarioDTO;
import br.edu.infnet.mono.domain.service.UsuarioService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.logging.Logger;

import static br.edu.infnet.mono.domain.util.GeralUtils.criarUsuario;

@Order(2)
@Component
public class UsuarioLoader implements ApplicationRunner {
    private final UsuarioService usuarioService;
    Logger log = Logger.getLogger(UsuarioLoader.class.getName());

    public UsuarioLoader(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        FileReader arquivo = new FileReader("usuario.txt");
        try (BufferedReader lerArquivo = new BufferedReader(arquivo)) {
            String linha = lerArquivo.readLine();
            String[] campos = null;

            while (linha != null) {
                campos = linha.split(";");
                UsuarioDTO usuario = criarUsuario(campos);
                usuarioService.incluir(usuario);
                linha = lerArquivo.readLine();
            }
        }
        log.info("lista de usuarios carregada com sucesso!");
        usuarioService.listarTodos().forEach(usuario -> log.info(usuario.toString()));
    }
}
