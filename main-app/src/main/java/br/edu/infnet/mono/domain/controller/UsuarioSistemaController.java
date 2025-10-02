/*
 * Author: Krossby Adhemar Camacho Alviz
 * owned by Krossft.
 */

package br.edu.infnet.mono.domain.controller;

import br.edu.infnet.mono.domain.dto.UsuarioSistemaDTO;
import br.edu.infnet.mono.domain.exception.BusinessException;
import br.edu.infnet.mono.domain.service.UsuarioSistemaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/usuario-sistema")
public class UsuarioSistemaController extends ControllerBase<UsuarioSistemaDTO, Long> {

    private final UsuarioSistemaService service;

    public UsuarioSistemaController(UsuarioSistemaService service) {
        this.service = service;
    }

    @Override
    protected ResponseEntity<UsuarioSistemaDTO> acaoBuscarPorId(Long id) throws BusinessException {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Override
    protected ResponseEntity<List<UsuarioSistemaDTO>> acaoListarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @Override
    protected ResponseEntity<UsuarioSistemaDTO> acaoIncluir(UsuarioSistemaDTO dto) throws BusinessException {
        return new ResponseEntity<>(service.incluir(dto), HttpStatus.CREATED);
    }

    @Override
    protected ResponseEntity<UsuarioSistemaDTO> acaoAlterar(Long id, UsuarioSistemaDTO dto) throws BusinessException {
        return ResponseEntity.ok(service.alterar(id, dto));
    }

    @Override
    protected void acaoExcluir(Long id) throws BusinessException {
        service.excluir(id);
    }

    @PreAuthorize( "hasRole('ADMIN')")
    @PatchMapping(value = "/{id}/inativar")
    public ResponseEntity<UsuarioSistemaDTO> inativar(@PathVariable("id") Long id) throws BusinessException {
        return ResponseEntity.ok(service.inativar(id));
    }
}
