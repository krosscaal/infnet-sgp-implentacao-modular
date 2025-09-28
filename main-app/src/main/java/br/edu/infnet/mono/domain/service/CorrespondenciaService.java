/*
 * Author: Krossby Adhemar Camacho Alviz
 * owned by Krossft.
 */

package br.edu.infnet.mono.domain.service;

import br.edu.infnet.mono.domain.dto.CorrespondenciaDTO;
import br.edu.infnet.mono.domain.dto.MoradiaDTO;
import br.edu.infnet.mono.domain.dto.UsuarioSistemaDTO;
import br.edu.infnet.mono.domain.entity.Correspondencia;
import br.edu.infnet.mono.domain.entity.Moradia;
import br.edu.infnet.mono.domain.entity.UsuarioSistema;
import br.edu.infnet.mono.domain.mapper.CorrespondenciaMapper;
import br.edu.infnet.mono.domain.mapper.MoradiaMapper;
import br.edu.infnet.mono.domain.mapper.UsuarioSistemaMapper;
import br.edu.infnet.mono.domain.exception.BusinessException;
import br.edu.infnet.mono.domain.repository.CorrespondenciaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CorrespondenciaService implements ServiceBase<CorrespondenciaDTO, Long> {
    private final CorrespondenciaRepository correspondenciaRepository;
    private final MoradiaService moradiaService;
    private final UsuarioSistemaService usuarioSistemaService;
    private final CorrespondenciaMapper mapper;
    private final MoradiaMapper moradiaMapper;
    private final UsuarioSistemaMapper usuarioSistemaMapper;

    public CorrespondenciaService(
            CorrespondenciaRepository correspondenciaRepository,
            MoradiaService moradiaService,
            UsuarioSistemaService usuarioSistemaService,
            CorrespondenciaMapper correspondenciaMapper,
            MoradiaMapper moradiaMapper,
            UsuarioSistemaMapper usuarioSistemaMapper) {
        this.correspondenciaRepository = correspondenciaRepository;
        this.moradiaService = moradiaService;
        this.usuarioSistemaService = usuarioSistemaService;
        this.mapper = correspondenciaMapper;
        this.moradiaMapper = moradiaMapper;
        this.usuarioSistemaMapper = usuarioSistemaMapper;
    }

    @Override
    public CorrespondenciaDTO buscarPorId(Long idObjeto) throws BusinessException {
        return mapper.toDTO(this.buscarCorrespondenciaPorId(idObjeto));

    }

    @Transactional(readOnly = true)
    @Override
    public List<CorrespondenciaDTO> listarTodos() {
        return mapper.listEntityToDTO(correspondenciaRepository.findAll());
    }

    @Transactional
    @Override
    public CorrespondenciaDTO incluir(CorrespondenciaDTO dto) throws BusinessException {
        MoradiaDTO moradiaDTO = moradiaService.buscarPorId(dto.getMoradiaEntrega().getId());
        UsuarioSistemaDTO usuarioRecepcao = usuarioSistemaService.buscarPorId(dto.getUsuarioRecepcao().getId());
        this.validarCorrespondencia(dto);
        dto.setId(null);
        dto.setMoradiaEntrega(moradiaDTO);
        dto.setUsuarioRecepcao(usuarioRecepcao);
        Correspondencia entidade = mapper.toEntity(dto);
        return mapper.toDTO(correspondenciaRepository.save(entidade));
    }

    private void validarCorrespondencia(CorrespondenciaDTO correspondenciaDTO) {
        if (correspondenciaDTO.getMoradiaEntrega() == null) {
            throw new BusinessException("campo moradia é obrigatório");
        }
        if (correspondenciaDTO.getNomeDestinatario() == null || correspondenciaDTO.getNomeDestinatario().isEmpty()) {
            throw new BusinessException("Campo destinatario é obrigatório");
        }

    }

    @Transactional
    @Override
    public CorrespondenciaDTO alterar(Long idObjeto, CorrespondenciaDTO dto) throws BusinessException {
        this.validarCorrespondencia(dto);
        Moradia moradia = moradiaMapper.toEntity(moradiaService.buscarPorId(dto.getMoradiaEntrega().getId()));
        UsuarioSistema usuarioRecepcao = usuarioSistemaMapper.toEntity(usuarioSistemaService.buscarPorId(dto.getUsuarioRecepcao().getId()));
        Correspondencia correspondencia = this.buscarCorrespondenciaPorId(idObjeto);
        correspondencia.setMoradiaEntrega(moradia);
        correspondencia.setUsuarioRecepcao(usuarioRecepcao);
        correspondencia.setEmailDestinatario(dto.getEmailDestinatario());
        correspondencia.setNomeDestinatario(dto.getNomeDestinatario());
        correspondencia.setEmailDestinatario(dto.getEmailDestinatario());
        correspondencia.setCodigoIdentificadorDaEntrega(dto.getCodigoIdentificadorDaEntrega());
        correspondencia.setNomeMoradorRecepcao(dto.getNomeMoradorRecepcao());
        correspondencia.setTelefoneDestinatario(dto.getTelefoneDestinatario());
        return mapper.toDTO(correspondenciaRepository.save(correspondencia));
    }

    @Transactional
    @Override
    public void excluir(Long idObjeto) throws BusinessException {
        this.buscarCorrespondenciaPorId(idObjeto);
        this.correspondenciaRepository.deleteById(idObjeto);
    }

    private Correspondencia buscarCorrespondenciaPorId(Long id) {
        return correspondenciaRepository.findById(id).orElseThrow(()-> new NoSuchElementException(String.format("Correspondencia com id:%d não encontrado", id)));
    }
}
