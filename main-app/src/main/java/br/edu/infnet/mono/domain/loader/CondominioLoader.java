/*
 * Author: Krossby Adhemar Camacho Alviz
 * owned by Krossft.
 */

package br.edu.infnet.mono.domain.loader;

import br.edu.infnet.mono.domain.entity.Endereco;
import br.edu.infnet.mono.domain.enumerator.EnumTipoCondominio;
import br.edu.infnet.mono.domain.enumerator.EnumTipoSituacao;
import br.edu.infnet.mono.domain.dto.CondominioDTO;
import br.edu.infnet.mono.domain.service.CondominioService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.logging.Logger;

@Order(1)
@Component
public class CondominioLoader implements ApplicationRunner {

    private final CondominioService condominioService;
    Logger log = Logger.getLogger(getClass().getName());

    public CondominioLoader(CondominioService condominioService) {
        this.condominioService = condominioService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        FileReader arquivo = new FileReader("condominio.txt");

        try (BufferedReader lerArquivo = new BufferedReader(arquivo)) {
            String linha = lerArquivo.readLine();

            String[] campos = null;

            while (linha != null) {
                campos = linha.split(";");

                Endereco endereco = new Endereco();
                endereco.setLogradouro(campos[6]);
                endereco.setNumero(campos[7]);
                endereco.setComplemento(campos[8]);
                endereco.setBairro(campos[9]);
                endereco.setLocalidade(campos[10]);
                endereco.setCep(campos[11]);
                endereco.setEstado(campos[12]);
                endereco.setUf(campos[13]);

                int ordinalTipoCondominio = Integer.parseInt(campos[1]);
                EnumTipoCondominio tipoCondominio = EnumTipoCondominio.valueOfTipoDeCondominio(ordinalTipoCondominio);

                int ordinalAtivo = Integer.parseInt(campos[16]);
                EnumTipoSituacao ativo = EnumTipoSituacao.valueOfAtivo(ordinalAtivo);

                CondominioDTO condominioDTO = new CondominioDTO();
                condominioDTO.setId(null);
                condominioDTO.setNomeCondominio(campos[0]);
                condominioDTO.setTipoCondominio(tipoCondominio);
                condominioDTO.setTotalUnidades(Integer.parseInt(campos[2]));
                condominioDTO.setCnpj(!campos[3].equalsIgnoreCase("null")? campos[3]: null);
                condominioDTO.setTelefoneContato1(!campos[4].equalsIgnoreCase("null")? campos[4] : null);
                condominioDTO.setTelefoneContato2(!campos[5].equalsIgnoreCase("null")? campos[5] : null);
                condominioDTO.setEndereco(endereco);
                condominioDTO.setNomeSindico(campos[14]);
                condominioDTO.setTelefoneSindico(campos[15]);
                condominioDTO.setSituacao(ativo);

                condominioService.incluir(condominioDTO);

                linha = lerArquivo.readLine();
            }

        }
        log.info("Lista de Condominios carregados com sucesso");
        condominioService.listarTodos().forEach(condominio -> log.info(condominio.toString()));


    }
}
