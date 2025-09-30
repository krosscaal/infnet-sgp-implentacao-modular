/*
 * Author: Krossby Adhemar Camacho Alviz
 * owned by Krossft.
 */

package br.edu.infnet.mono.domain.service;

import br.edu.infnet.mono.domain.dto.CondominioDTO;
import br.edu.infnet.mono.domain.entity.Condominio;
import br.edu.infnet.mono.domain.factory.CondominioFactory;
import br.edu.infnet.mono.domain.enumerator.EnumTipoSituacao;
import br.edu.infnet.mono.domain.exception.BusinessException;
import br.edu.infnet.mono.domain.mapper.CondominioMapper;
import br.edu.infnet.mono.domain.repository.CondominioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

import static br.edu.infnet.mono.domain.util.MensagemCenter.REGISTRO_NAO_LOCALIZADO;

@Service
public class CondominioService implements ServiceBase<CondominioDTO, Long> {
    private final CondominioRepository repository;
    private final CondominioMapper mapper;

    public CondominioService(CondominioRepository repository, CondominioMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public CondominioDTO buscarPorId(Long idObjeto) throws BusinessException {
        Condominio condominio = this.buscarCondominioPorId(idObjeto);
        return mapper.toDTO(condominio);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CondominioDTO> listarTodos() {

        List<Condominio> lista = repository.findAll();

        return mapper.listEntityToDTO(lista);
    }
    @Transactional
    @Override
    public CondominioDTO incluir(CondominioDTO entidade) throws BusinessException {
        Condominio condominio = CondominioFactory.criarCondominio(entidade);
        return mapper.toDTO(repository.save(condominio));

    }

    @Transactional
    @Override
    public CondominioDTO alterar(Long idObjeto, CondominioDTO entidade) throws BusinessException {
        Condominio condominioObj = this.buscarCondominioPorId(idObjeto);

        Condominio novoCondominio = CondominioFactory.criarCondominio(entidade);

        condominioObj.setNomeCondominio(novoCondominio.getNomeCondominio());
        condominioObj.setTipoCondominio(novoCondominio.getTipoCondominio());
        condominioObj.setCnpj(novoCondominio.getCnpj());
        condominioObj.setTelefoneContato1(novoCondominio.getTelefoneContato1());
        condominioObj.setTelefoneContato2(novoCondominio.getTelefoneContato2());
        condominioObj.setEndereco(novoCondominio.getEndereco());
        condominioObj.setNomeSindico(novoCondominio.getNomeSindico());
        condominioObj.setTelefoneSindico(novoCondominio.getTelefoneSindico());

        return mapper.toDTO(repository.save(condominioObj));
    }

    @Transactional
    @Override
    public void excluir(Long idObjeto) throws BusinessException {
        Condominio condominio = this.buscarCondominioPorId(idObjeto);

        if (condominio != null) {
            throw new BusinessException("Condominio não pode ser Apagado somente é permitido fazer alterações");
        }
    }

    public CondominioDTO inativar(Long idCondominio) {
        Condominio condominioObj = this.buscarCondominioPorId(idCondominio);
        if (EnumTipoSituacao.INATIVO.equals(condominioObj.getSituacao())) {
            throw new BusinessException("Condominio já está inativo!");
        }
        condominioObj.setSituacao(EnumTipoSituacao.INATIVO);
        return mapper.toDTO(repository.save(condominioObj));
    }

    private Condominio buscarCondominioPorId(Long idCondominio) {
        return repository.findById(idCondominio).orElseThrow(()-> new NoSuchElementException(REGISTRO_NAO_LOCALIZADO));
    }

}
