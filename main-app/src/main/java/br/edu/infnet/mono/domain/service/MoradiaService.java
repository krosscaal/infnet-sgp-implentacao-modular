/*
 * Author: Krossby Adhemar Camacho Alviz
 * owned by Krossft.
 */

package br.edu.infnet.mono.domain.service;

import br.edu.infnet.mono.domain.dto.MoradiaDTO;
import br.edu.infnet.mono.domain.dto.UsuarioCondominioDTO;
import br.edu.infnet.mono.domain.entity.Moradia;
import br.edu.infnet.mono.domain.enumerator.EnumTipoSituacao;
import br.edu.infnet.mono.domain.mapper.MoradiaMapper;
import br.edu.infnet.mono.domain.mapper.UsuarioCondominioMapper;
import br.edu.infnet.mono.domain.exception.BusinessException;
import br.edu.infnet.mono.domain.repository.MoradiaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MoradiaService implements ServiceBase<MoradiaDTO, Long> {
    private final MoradiaRepository moradiaRepository;
    private final UsuarioCondominioService usuarioCondominioService;
    private final MoradiaMapper moradiaMapper;
    private final UsuarioCondominioMapper usuarioCondominioMapper;

    public MoradiaService(MoradiaRepository moradiaRepository, UsuarioCondominioService usuarioCondominioService, MoradiaMapper moradiaMapper, UsuarioCondominioMapper usuarioCondominioMapper) {
        this.moradiaRepository = moradiaRepository;
        this.usuarioCondominioService = usuarioCondominioService;
        this.moradiaMapper = moradiaMapper;
        this.usuarioCondominioMapper = usuarioCondominioMapper;
    }


    @Transactional(readOnly = true)
    @Override
    public MoradiaDTO buscarPorId(Long idObjeto) throws BusinessException {
        return moradiaMapper.toDTO(this.buscarMoradiaPorId(idObjeto));
    }

    @Transactional(readOnly = true)
    @Override
    public List<MoradiaDTO> listarTodos() {
        return moradiaMapper.listEntityToDTO(moradiaRepository.findAll());
    }

    @Transactional
    @Override
    public MoradiaDTO incluir(MoradiaDTO dto) throws BusinessException {
        this.validarMoradia(dto);
        dto.setId(null);
        dto.setSituacao(EnumTipoSituacao.ATIVO);
        return moradiaMapper.toDTO(moradiaRepository.save(moradiaMapper.toEntity(dto)));
    }

    @Transactional
    @Override
    public MoradiaDTO alterar(Long idObjeto, MoradiaDTO dto) throws BusinessException {
        this.validarMoradia(dto);
        UsuarioCondominioDTO morador = usuarioCondominioService.buscarPorId(dto.getMorador().getId());
        Moradia moradia = this.buscarMoradiaPorId(idObjeto);
        moradia.setSituacao(dto.getSituacao());
        moradia.setTipoMoradia(dto.getTipoMoradia());
        moradia.setNumeroUnidade(dto.getNumeroUnidade());
        moradia.setMorador(usuarioCondominioMapper.toEntity(morador));
        moradia.setQuadraTorreBloco(dto.getQuadraTorreBloco());
        moradia.setLote(dto.getLote());
        return moradiaMapper.toDTO(moradiaRepository.save(moradia));
    }

    @Transactional
    @Override
    public void excluir(Long idObjeto) throws BusinessException {
        this.buscarMoradiaPorId(idObjeto);
        throw new BusinessException("ATENÇÃO NEMHUMA MORADIA PODE SER APAGADA, SOMENTE É PERMITIDO INATIVAR");
    }
    private Moradia buscarMoradiaPorId(Long idObjeto) throws NoSuchElementException {
        return moradiaRepository.findById(idObjeto).orElseThrow(()-> new NoSuchElementException("Moradia não encontrada"));
    }
    public MoradiaDTO inativar(Long idMoradia) throws BusinessException {
        Moradia moradia = this.buscarMoradiaPorId(idMoradia);
        if (EnumTipoSituacao.INATIVO.equals(moradia.getSituacao())) {
            throw new BusinessException("Moradia já está inativa!");
        }
        moradia.setSituacao(EnumTipoSituacao.INATIVO);
        return moradiaMapper.toDTO(this.moradiaRepository.save(moradia));
    }
    private void validarMoradia(MoradiaDTO dto) throws BusinessException {
        if (dto.getTipoMoradia() == null) {
            throw new BusinessException("tipo de moradia é obrigatório");
        }
        if (dto.getNumeroUnidade() == null || dto.getNumeroUnidade().isEmpty()) {
            throw new BusinessException("número da unidade é obrigatório");
        }
    }
}
