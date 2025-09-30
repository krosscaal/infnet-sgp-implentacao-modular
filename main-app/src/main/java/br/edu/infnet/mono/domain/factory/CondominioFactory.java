/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.edu.infnet.mono.domain.factory;

import br.edu.infnet.mono.domain.dto.CondominioDTO;
import br.edu.infnet.mono.domain.entity.Condominio;
import br.edu.infnet.mono.domain.entity.Endereco;
import br.edu.infnet.mono.domain.enumerator.EnumTipoSituacao;
import br.edu.infnet.mono.domain.exception.BusinessException;
import br.edu.infnet.mono.domain.util.GeralUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Locale;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CondominioFactory {

    private static final String SOMENTE_NUMEROS = "aceita somente números!!";
    private static final String SOMENTE_LETRAS = "aceita somente letras!!";

    public static Condominio criarCondominio(CondominioDTO condominioDTO) {
        validarCondominio(condominioDTO);
        Endereco endereco = toUpperCaseEndereco(condominioDTO.getEndereco());

        Condominio condominioEntity = new Condominio();
        condominioEntity.setNomeCondominio(condominioDTO.getNomeCondominio().toUpperCase(Locale.ROOT));
        condominioEntity.setTipoCondominio(condominioDTO.getTipoCondominio());
        condominioEntity.setTotalUnidades(condominioDTO.getTotalUnidades());
        condominioEntity.setCnpj(condominioDTO.getCnpj());
        condominioEntity.setTelefoneContato1(condominioDTO.getTelefoneContato1());
        condominioEntity.setTelefoneContato2(condominioDTO.getTelefoneContato2());
        condominioEntity.setEndereco(endereco);
        condominioEntity.setNomeSindico(condominioDTO.getNomeSindico().toUpperCase(Locale.ROOT));
        condominioEntity.setTelefoneSindico(condominioDTO.getTelefoneSindico());
        condominioEntity.setSituacao(EnumTipoSituacao.ATIVO);

        return condominioEntity;
    }

    private static Endereco toUpperCaseEndereco(Endereco endereco) {
        endereco.setLogradouro(endereco.getLogradouro() != null ? endereco.getLogradouro().toUpperCase(Locale.ROOT) : null);
        endereco.setComplemento(endereco.getComplemento() != null? endereco.getComplemento().toUpperCase(Locale.ROOT) : null);
        endereco.setBairro(endereco.getBairro() != null ? endereco.getBairro().toUpperCase(Locale.ROOT) : null);
        endereco.setLocalidade(endereco.getLocalidade() != null ? endereco.getLocalidade().toUpperCase(Locale.ROOT) : null);
        endereco.setEstado(endereco.getEstado() != null ? endereco.getEstado().toUpperCase(Locale.ROOT) : null);
        endereco.setUf(endereco.getUf() != null ? endereco.getUf().toUpperCase(Locale.ROOT) : null);
        return endereco;
    }

    private static void validarCondominio(CondominioDTO condominio) {
        if (condominio == null) {
            throw new BusinessException("CondominioRecord não pode ser nulo");
        }
        if (condominio.getNomeCondominio() == null || condominio.getNomeCondominio().trim().isEmpty()) {
            throw new BusinessException("Nome do condomínio é obrigatório");
        }
        if (condominio.getTipoCondominio() == null) {
            throw new BusinessException("Tipo do condomínio é obrigatório");
        }
        if (condominio.getTotalUnidades() <= 0) {
            throw new BusinessException("Total de unidades deve ser maior que zero");
        }
        if ((condominio.getTelefoneContato1() != null && !condominio.getTelefoneContato1().trim().isEmpty()) && !GeralUtils.contemNumeros(condominio.getTelefoneContato1())) {
            throw new BusinessException(String.format("Telefone Contato 1 %s", SOMENTE_NUMEROS));
        }
        if ((condominio.getTelefoneContato2() != null && !condominio.getTelefoneContato2().trim().isEmpty()) && !GeralUtils.contemNumeros(condominio.getTelefoneContato2())) {
            throw new BusinessException(String.format("Telefone Contato 2 %s", SOMENTE_NUMEROS));
        }
        if ((condominio.getTelefoneSindico() != null && !condominio.getTelefoneSindico().trim().isEmpty()) && !GeralUtils.contemNumeros(condominio.getTelefoneSindico())) {
            throw new BusinessException(String.format("Telefone Sindico %s", SOMENTE_NUMEROS));
        }
        if ((condominio.getCnpj() != null && !condominio.getCnpj().trim().isEmpty()) && !GeralUtils.contemNumeros(condominio.getCnpj())) {
            throw new BusinessException(String.format("Cnpj %s", SOMENTE_NUMEROS));
        }

        verificarEndereco(condominio.getEndereco());
    }

    private static void verificarEndereco(Endereco endereco) {
        if (endereco == null) {
            throw new BusinessException("Endereço não pode ser nulo");
        }
        if (endereco.getLogradouro() == null || endereco.getLogradouro().trim().isEmpty()) {
            throw new BusinessException("Logradouro do Condomínio é obrigatorio!!!");
        }
        if (!GeralUtils.contemNumeros(endereco.getNumero())) {
            throw new BusinessException(String.format("Número do Condomínio é obrigatorio, %s", SOMENTE_NUMEROS));
        }
        if (endereco.getLocalidade() == null || endereco.getLocalidade().trim().isEmpty()) {
            throw new BusinessException("Cidade do Condomínio é obrigatorio!!!");
        }
        if (!GeralUtils.contemNumeros(endereco.getCep())) {
            throw new BusinessException(String.format("CEP do Condomínio é obrigatorio, %s", SOMENTE_NUMEROS));
        }
        if (!GeralUtils.contemLetras(endereco.getEstado())) {
            throw new BusinessException(String.format("Estado do Condomínio é obrigatorio, %s", SOMENTE_LETRAS));
        }
        if (!GeralUtils.contemLetras(endereco.getUf())) {
            throw new BusinessException(String.format("UF do Condomínio é obrigatorio, %s",  SOMENTE_LETRAS));
        }
    }
}
