/*
 * Author: Krossby Adhemar Camacho Alviz
 * owned by Krossft.
 */

package br.edu.infnet.mono.domain.service;

import br.edu.infnet.mono.domain.dto.MoradiaDTO;
import br.edu.infnet.mono.domain.dto.UsuarioCondominioDTO;
import br.edu.infnet.mono.domain.dto.UsuarioDTO;
import br.edu.infnet.mono.domain.entity.UsuarioCondominio;
import br.edu.infnet.mono.domain.enumerator.EnumTipoResidente;
import br.edu.infnet.mono.domain.enumerator.EnumTipoSituacao;
import br.edu.infnet.mono.domain.mapper.MoradiaMapper;
import br.edu.infnet.mono.domain.mapper.UsuarioCondominioMapper;
import br.edu.infnet.mono.domain.mapper.UsuarioMapper;
import br.edu.infnet.mono.domain.exception.BusinessException;
import br.edu.infnet.mono.domain.exception.UsuarioException;
import br.edu.infnet.mono.domain.repository.UsuarioCondominioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UsuarioCondominioService implements ServiceBase<UsuarioCondominioDTO, Long> {
    private final UsuarioCondominioRepository repository;
    private final UsuarioService usuarioService;
    private final UsuarioCondominioMapper mapper;
    private final MoradiaMapper moradiaMapper;
    private final UsuarioMapper usuarioMapper;

    public UsuarioCondominioService(UsuarioCondominioRepository repository, UsuarioService usuarioService, UsuarioCondominioMapper mapper, MoradiaMapper moradiaMapper, UsuarioMapper usuarioMapper) {
        this.repository = repository;
        this.usuarioService = usuarioService;
        this.mapper = mapper;
        this.moradiaMapper = moradiaMapper;
        this.usuarioMapper = usuarioMapper;
    }

    @Transactional(readOnly = true)
    @Override
    public UsuarioCondominioDTO buscarPorId(Long idObjeto) throws BusinessException {
        try {
            return mapper.toDTO(this.buscarUsuarioCondominioPorId(idObjeto));

        } catch (UsuarioException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<UsuarioCondominioDTO> listarTodos() {
        return mapper.listEntityToDTO(repository.findAll());
    }

    @Transactional
    @Override
    public UsuarioCondominioDTO incluir(UsuarioCondominioDTO dto) throws BusinessException {
        try {
            UsuarioDTO usuario = usuarioService.buscarPorId(dto.getUsuario().getId());
            dto.setId(null);
            dto.setSituacao(EnumTipoSituacao.ATIVO);
            dto.setUsuario(usuario);
            UsuarioCondominio entidade = mapper.toEntity(dto);
            return mapper.toDTO(repository.save(entidade));

        } catch (UsuarioException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public UsuarioCondominioDTO alterar(Long idObjeto, UsuarioCondominioDTO dto) throws BusinessException {
        try {
            UsuarioCondominio usuarioCondominio = this.buscarUsuarioCondominioPorId(idObjeto);
            UsuarioDTO usuario = usuarioService.buscarPorId(dto.getUsuario().getId());

            usuarioCondominio.setUsuario(usuarioMapper.toEntity(usuario));
            usuarioCondominio.setEmail(dto.getEmail());
            usuarioCondominio.setTipoResidente(dto.getTipoResidente());
            usuarioCondominio.setSituacao(dto.getSituacao());
            return mapper.toDTO(repository.save(usuarioCondominio));

        } catch (UsuarioException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public void excluir(Long idObjeto) throws NoSuchElementException {
        this.buscarUsuarioCondominioPorId(idObjeto);
        this.repository.deleteById(idObjeto);
    }

    private UsuarioCondominio buscarUsuarioCondominioPorId(Long idUsuario) throws NoSuchElementException {
        return repository.findById(idUsuario).orElseThrow(()-> new NoSuchElementException("Usuário Condominio não encontrado"));
    }
    public UsuarioCondominioDTO inativar(Long idUsuario) throws BusinessException {

        UsuarioCondominio usuarioCondominioObj = this.buscarUsuarioCondominioPorId(idUsuario);
        if (EnumTipoSituacao.INATIVO.equals(usuarioCondominioObj.getSituacao())) {
            throw new BusinessException("Usuário Condominio Já está inativo.");
        }
        usuarioCondominioObj.setSituacao(EnumTipoSituacao.INATIVO);
        return mapper.toDTO(repository.save(usuarioCondominioObj));
    }

    @Transactional(readOnly = true)
    public List<MoradiaDTO> buscarMoradiasDoProprietario(Long idUsuario) throws NoSuchElementException {
        UsuarioCondominio usuarioCondominioObj = this.buscarUsuarioCondominioPorId(idUsuario);
        if (!usuarioCondominioObj.getTipoResidente().equals(EnumTipoResidente.PROPIETARIO)) {
            throw new BusinessException("Usuário informado não é propietario");
        }
        return moradiaMapper.listEntityToDTO(repository.findMoradiasPorId(idUsuario));
    }
}
