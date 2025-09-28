/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.edu.infnet.mono.domain.entity;

import br.edu.infnet.mono.domain.dto.CondominioDTO;
import br.edu.infnet.mono.domain.enumarator.EnumTipoSituacao;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Locale;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CondominioFactory {

    private static final String SOMENTE_NUMEROS = "aceita somente números!!";
    private static final String SOMENTE_LETRAS = "aceita somente letras!!";

    public static Condominio criarCondominio(CondominioDTO condominioDTO) {
//        validarCondominio(condominioDTO);
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

//    private static void validarCondominio(CondominioDTO condominioDTO) {
//        if (condominioDTO == null) {
//            throw new BusinessException("CondominioRecord não pode ser nulo");
//        }
//        if (condominioDTO.nomeCondominio() == null || condominioRecord.nomeCondominio().trim().isEmpty()) {
//            throw new BusinessException("Nome do condomínio é obrigatório");
//        }
//        if (condominioDTO.tipoCondominio() == null) {
//            throw new BusinessException("Tipo do condomínio é obrigatório");
//        }
//        if (condominioDTO.totalUnidades() <= 0) {
//            throw new BusinessException("Total de unidades deve ser maior que zero");
//        }
//        if ((condominioDTO.telefoneContato1() != null && !condominioRecord.telefoneContato1().trim().isEmpty()) && !GeralUtils.contemNumeros(condominioRecord.telefoneContato1())) {
//            throw new BusinessException(String.format("Telefone Contato 1 %s", SOMENTE_NUMEROS));
//        }
//        if ((condominioDTO.telefoneContato2() != null && !condominioRecord.telefoneContato2().trim().isEmpty()) && !GeralUtils.contemNumeros(condominioRecord.telefoneContato2())) {
//            throw new BusinessException(String.format("Telefone Contato 2 %s", SOMENTE_NUMEROS));
//        }
//        if ((condominioDTO.telefoneSindico() != null && !condominioRecord.telefoneSindico().trim().isEmpty()) && !GeralUtils.contemNumeros(condominioRecord.telefoneSindico())) {
//            throw new BusinessException(String.format("Telefone Sindico %s", SOMENTE_NUMEROS));
//        }
//        if ((condominioDTO.cnpj() != null && !condominioRecord.cnpj().trim().isEmpty()) && !GeralUtils.contemNumeros(condominioRecord.cnpj())) {
//            throw new BusinessException(String.format("Cnpj %s", SOMENTE_NUMEROS));
//        }
//
//        verificarEndereco(condominioDTO.endereco());
//    }
//
//    private static void verificarEndereco(Endereco endereco) {
//        if (endereco == null) {
//            throw new BusinessException("Endereço não pode ser nulo");
//        }
//        if (endereco.getLogradouro() == null || endereco.getLogradouro().trim().isEmpty()) {
//            throw new BusinessException("Logradouro do Condomínio é obrigatorio!!!");
//        }
//        if (!GeralUtils.contemNumeros(endereco.getNumero())) {
//            throw new BusinessException(String.format("Número do Condomínio é obrigatorio, %s", SOMENTE_NUMEROS));
//        }
//        if (endereco.getLocalidade() == null || endereco.getLocalidade().trim().isEmpty()) {
//            throw new BusinessException("Cidade do Condomínio é obrigatorio!!!");
//        }
//        if (!GeralUtils.contemNumeros(endereco.getCep())) {
//            throw new BusinessException(String.format("CEP do Condomínio é obrigatorio, %s", SOMENTE_NUMEROS));
//        }
//        if (!GeralUtils.contemLetras(endereco.getEstado())) {
//            throw new BusinessException(String.format("Estado do Condomínio é obrigatorio, %s", SOMENTE_LETRAS));
//        }
//        if (!GeralUtils.contemLetras(endereco.getUf())) {
//            throw new BusinessException(String.format("UF do Condomínio é obrigatorio, %s",  SOMENTE_LETRAS));
//        }
//    }
}
