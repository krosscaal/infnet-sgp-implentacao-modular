/*
 * Author: Krossby Adhemar Camacho Alviz
 * owned by Krossft.
 */

package br.edu.infnet.mono.domain.service;

import br.edu.infnet.mono.domain.dto.UsuarioDTO;
import br.edu.infnet.mono.domain.entity.Usuario;
import br.edu.infnet.mono.domain.enumerator.EnumTipoSituacao;
import br.edu.infnet.mono.domain.mapper.UsuarioMapper;
import br.edu.infnet.mono.domain.exception.BusinessException;
import br.edu.infnet.mono.domain.exception.UsuarioException;
import br.edu.infnet.mono.domain.repository.UsuarioRepository;
import org.springframework.cloud.commons.config.DefaultsBindHandlerAdvisor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UsuarioService implements ServiceBase<UsuarioDTO, Long> {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper, DefaultsBindHandlerAdvisor.MappingsProvider mappingsProvider) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    public void validarUsuario(UsuarioDTO usuario) {
        if (usuario.getNome() != null && usuario.getNome().trim().length() < 4) {
            throw new UsuarioException("Nome do Usuário é obrigatorio, e deve possuir mínimo 4 letras!");
        }
        if (usuario.getSobreNome() != null && usuario.getSobreNome().trim().length() < 5) {
            throw new UsuarioException("SobreNome do Usuário é obrigatorio, é deve possuir mínimo 5 letras!");
        }

    }

    @Transactional(readOnly = true)
    @Override
    public UsuarioDTO buscarPorId(Long idObjeto) {
        return usuarioMapper.toDTO(this.buscarUsuarioPorId(idObjeto));
    }

    @Transactional(readOnly = true)
    @Override
    public List<UsuarioDTO> listarTodos() {
        return usuarioMapper.listEntityToDTO(usuarioRepository.findAll());
    }

    @Transactional
    @Override
    public UsuarioDTO incluir(UsuarioDTO dto) {
        try {
            this.validarUsuario(dto);
            dto.setId(null);
            dto.setSituacao(EnumTipoSituacao.ATIVO);
            return usuarioMapper.toDTO(usuarioRepository.save(usuarioMapper.toEntity(dto)));
        } catch (UsuarioException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public UsuarioDTO alterar(Long idObjeto, UsuarioDTO dto) throws BusinessException {
        try {
            this.validarUsuario(dto);
            Usuario usuarioObj = this.buscarUsuarioPorId(idObjeto);
            usuarioObj.setNome(dto.getNome());
            usuarioObj.setSobreNome(dto.getSobreNome());
            usuarioObj.setCpf(dto.getCpf());
            usuarioObj.setRg(dto.getRg());
            usuarioObj.setTelefone1(dto.getTelefone1());
            usuarioObj.setTelefone2(dto.getTelefone2());
            usuarioObj.setSituacao(dto.getSituacao());
            return usuarioMapper.toDTO(usuarioRepository.save(usuarioObj));
        } catch (UsuarioException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public void excluir(Long idObjeto) throws NoSuchElementException {
        this.buscarUsuarioPorId(idObjeto);
        usuarioRepository.deleteById(idObjeto);
    }

    private Usuario buscarUsuarioPorId(Long idObjeto) throws NoSuchElementException {
        return usuarioRepository.findById(idObjeto).orElseThrow(()-> new NoSuchElementException(String.format("Usuário informado com id %d, não existe", idObjeto)));
    }

    public UsuarioDTO inativar(Long idObjeto) throws NoSuchElementException {
        Usuario usuario = this.buscarUsuarioPorId(idObjeto);
        if (EnumTipoSituacao.INATIVO.equals(usuario.getSituacao())) {
            throw new UsuarioException("Situacao do usuário já está inativa!");
        }
        usuario.setSituacao(EnumTipoSituacao.INATIVO);
        return usuarioMapper.toDTO(usuarioRepository.save(usuario));
    }

    public UsuarioDTO buscarPorCpf(String cpf) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByCpf(cpf);
        if (usuarioOptional.isEmpty()) {
            throw new NoSuchElementException(String.format("Usuário com CPF:%s não encontrado!", cpf));
        }
        return usuarioMapper.toDTO(usuarioOptional.get());
    }
}
