/*
 * Author: Krossby Adhemar Camacho Alviz
 * owned by Krossft.
 */

package br.edu.infnet.mono.domain.service;

import br.edu.infnet.mono.domain.dto.UsuarioDTO;
import br.edu.infnet.mono.domain.dto.UsuarioSistemaDTO;
import br.edu.infnet.mono.domain.entity.UsuarioSistema;
import br.edu.infnet.mono.domain.enumerator.EnumTipoSituacao;
import br.edu.infnet.mono.domain.mapper.UsuarioMapper;
import br.edu.infnet.mono.domain.mapper.UsuarioSistemaMapper;
import br.edu.infnet.mono.domain.exception.BusinessException;
import br.edu.infnet.mono.domain.exception.UsuarioException;
import br.edu.infnet.mono.domain.repository.UsuarioSistemaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UsuarioSistemaService implements ServiceBase<UsuarioSistemaDTO, Long> {
    private final UsuarioSistemaRepository repository;
    private final UsuarioService usuarioService;
    private final UsuarioSistemaMapper mapper;
    private final UsuarioMapper usuarioMapper;

    public UsuarioSistemaService(UsuarioSistemaRepository repository, UsuarioService usuarioService, UsuarioSistemaMapper mapper, UsuarioMapper usuarioMapper) {
        this.repository = repository;
        this.usuarioService = usuarioService;
        this.mapper = mapper;
        this.usuarioMapper = usuarioMapper;
    }

    @Transactional(readOnly = true)
    @Override
    public UsuarioSistemaDTO buscarPorId(Long idObjeto) throws BusinessException {
        try {
            return mapper.toDTO(this.buscarUsuarioSistemaPorId(idObjeto));
        } catch (UsuarioException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<UsuarioSistemaDTO> listarTodos() {
        return mapper.listEntityToDTO(repository.findAll());
    }

    @Transactional
    @Override
    public UsuarioSistemaDTO incluir(UsuarioSistemaDTO dto) throws BusinessException {
        try {
            this.validarUsuarioSistema(dto);
            UsuarioDTO usuarioDTO = usuarioService.buscarPorId(dto.getUsuario().getId());
            dto.setId(null);
            dto.setSituacao(EnumTipoSituacao.ATIVO);
            dto.setUsuario(usuarioDTO);
            return mapper.toDTO(repository.save(mapper.toEntity(dto)));
        } catch (UsuarioException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public UsuarioSistemaDTO alterar(Long idObjeto, UsuarioSistemaDTO dto) throws BusinessException {
        try {
            UsuarioSistema usuarioSistema = this.buscarUsuarioSistemaPorId(idObjeto);
            UsuarioDTO usuario = usuarioService.buscarPorId(dto.getUsuario().getId());
            this.validarUsuarioSistema(dto);
            usuarioSistema.setUsuario(usuarioMapper.toEntity(usuario));
            usuarioSistema.setEmail(dto.getEmail());
            usuarioSistema.setSenha(dto.getSenha());
            usuarioSistema.setPassword(dto.getPassword());
            usuarioSistema.setSituacao(dto.getSituacao());

            return mapper.toDTO(repository.save(usuarioSistema));
        } catch (UsuarioException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public void excluir(Long idObjeto) throws NoSuchElementException {
        this.buscarUsuarioSistemaPorId(idObjeto);
        repository.deleteById(idObjeto);
    }

    private UsuarioSistema buscarUsuarioSistemaPorId(Long idUsuario) throws NoSuchElementException {
        return repository.findById(idUsuario).orElseThrow(()-> new NoSuchElementException("Usuario Sistema não encontrado"));
    }

    private void validarUsuarioSistema(UsuarioSistemaDTO entidade) throws UsuarioException {
        if (entidade.getEmail().trim().isEmpty()) {
            throw new UsuarioException("Email do usuário é obrigatório!");
        }
    }

    public UsuarioSistemaDTO inativar(Long id) throws NoSuchElementException {
        UsuarioSistema usuarioSistema = this.buscarUsuarioSistemaPorId(id);
        if (EnumTipoSituacao.INATIVO.equals(usuarioSistema.getSituacao())) {
            throw new UsuarioException("Usuario Sistema já está inativo.");
        }
        usuarioSistema.setSituacao(EnumTipoSituacao.INATIVO);
        return mapper.toDTO(repository.save(usuarioSistema));
    }

}
