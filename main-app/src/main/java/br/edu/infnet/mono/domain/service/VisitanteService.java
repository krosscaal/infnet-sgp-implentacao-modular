/*
 * Author: Krossby Adhemar Camacho Alviz
 * owned by Krossft.
 */

package br.edu.infnet.mono.domain.service;

import br.edu.infnet.mono.domain.dto.MoradiaDTO;
import br.edu.infnet.mono.domain.dto.UsuarioCondominioDTO;
import br.edu.infnet.mono.domain.dto.UsuarioDTO;
import br.edu.infnet.mono.domain.dto.VisitanteDTO;
import br.edu.infnet.mono.domain.entity.Visitante;
import br.edu.infnet.mono.domain.enumerator.EnumTipoAcesso;
import br.edu.infnet.mono.domain.exception.BusinessException;
import br.edu.infnet.mono.domain.exception.UsuarioException;
import br.edu.infnet.mono.domain.mapper.MoradiaMapper;
import br.edu.infnet.mono.domain.mapper.UsuarioCondominioMapper;
import br.edu.infnet.mono.domain.mapper.UsuarioMapper;
import br.edu.infnet.mono.domain.mapper.VisitanteMapper;
import br.edu.infnet.mono.domain.repository.VisitanteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class VisitanteService implements ServiceBase<VisitanteDTO, Long> {

    private final VisitanteRepository visitanteRepository;
    private final UsuarioService usuarioService;
    private final MoradiaService moradiaService;
    private final UsuarioCondominioService usuarioCondominioService;
    private final VisitanteMapper visitanteMapper;
    private final UsuarioMapper usuarioMapper;
    private final UsuarioCondominioMapper usuarioCondominioMapper;
    private final MoradiaMapper moradiaMapper;

    public VisitanteService(VisitanteRepository visitanteRepository, UsuarioService usuarioService, MoradiaService moradiaService, UsuarioCondominioService usuarioCondominioService, VisitanteMapper visitanteMapper, UsuarioMapper usuarioMapper, UsuarioCondominioMapper usuarioCondominioMapper, MoradiaMapper moradiaMapper) {
        this.visitanteRepository = visitanteRepository;
        this.usuarioService = usuarioService;
        this.moradiaService = moradiaService;
        this.usuarioCondominioService = usuarioCondominioService;
        this.visitanteMapper = visitanteMapper;
        this.usuarioMapper = usuarioMapper;
        this.usuarioCondominioMapper = usuarioCondominioMapper;
        this.moradiaMapper = moradiaMapper;
    }

    @Transactional(readOnly = true)
    @Override
    public VisitanteDTO buscarPorId(Long idObjeto) throws BusinessException {
        try {
            return visitanteMapper.toDTO(this.buscarPorIdVisitante(idObjeto));
        } catch (UsuarioException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<VisitanteDTO> listarTodos() {
        return visitanteMapper.listEntityToDTO(visitanteRepository.findAll());
    }

    @Transactional
    @Override
    public VisitanteDTO incluir(VisitanteDTO dto) {
        UsuarioDTO usuarioDTO = usuarioService.buscarPorId(dto.getUsuarioVisitante().getId());
        MoradiaDTO moradiaDestino = moradiaService.buscarPorId(dto.getMoradiaDestinoVisitante().getId());dto.setId(null);
        UsuarioCondominioDTO autorizado = usuarioCondominioService.buscarPorId(dto.getUsuarioAutorizacao().getId());
        dto.setUsuarioVisitante(usuarioDTO);
        dto.setMoradiaDestinoVisitante(moradiaDestino);
        dto.setUsuarioAutorizacao(autorizado);
        dto.setId(null);
        return visitanteMapper.toDTO(visitanteRepository.save(visitanteMapper.toEntity(dto)));
    }

    @Transactional
    @Override
    public VisitanteDTO alterar(Long idObjeto, VisitanteDTO dto) throws BusinessException {
        try {
            UsuarioDTO usuario = usuarioService.buscarPorId(dto.getUsuarioVisitante().getId());
            MoradiaDTO moradiaDestino = moradiaService.buscarPorId(dto.getMoradiaDestinoVisitante().getId());
            UsuarioCondominioDTO autorizado = usuarioCondominioService.buscarPorId(dto.getUsuarioAutorizacao().getId());
            Visitante visitanteObj = this.buscarPorIdVisitante(idObjeto);

            visitanteObj.setUsuarioVisitante(usuarioMapper.toEntity(usuario));
            visitanteObj.setCartaoAcesso(dto.getCartaoAcesso());
            visitanteObj.setTipoAcesso(dto.getTipoAcesso());
            visitanteObj.setUsuarioAutorizacao(usuarioCondominioMapper.toEntity(autorizado));
            visitanteObj.setMoradiaDestinoVisitante(moradiaMapper.toEntity(moradiaDestino));
            visitanteObj.setObservacao(dto.getObservacao());
            return visitanteMapper.toDTO(visitanteRepository.save(visitanteObj));
        } catch (UsuarioException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public void excluir(Long idObjeto) throws NoSuchElementException {
        this.buscarPorIdVisitante(idObjeto);
        this.visitanteRepository.deleteById(idObjeto);
    }

    private Visitante buscarPorIdVisitante(Long idVisitante) throws NoSuchElementException {
        return visitanteRepository.findById(idVisitante).orElseThrow(()-> new NoSuchElementException("Registro de Visitante não encontrado!"));
    }

    public List<VisitanteDTO> buscarVisitantesPorDataIngresso(String data) {

        String dataInicio = data + " 00:00:00";
        String dataFim = data + " 23:59:59";
        DateTimeFormatter formatar = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        LocalDateTime dataInicioBusca = LocalDateTime.parse(dataInicio, formatar);
        LocalDateTime dataFimBusca = LocalDateTime.parse(dataFim, formatar);
        return visitanteMapper.listEntityToDTO(visitanteRepository.findAllByIngressoBetween(dataInicioBusca, dataFimBusca));
    }

    public List<VisitanteDTO> buscarPorCpf(String cpf) {
        return visitanteMapper.listEntityToDTO(visitanteRepository.findByCpf(cpf, EnumTipoAcesso.VISITANTE));
    }
}
