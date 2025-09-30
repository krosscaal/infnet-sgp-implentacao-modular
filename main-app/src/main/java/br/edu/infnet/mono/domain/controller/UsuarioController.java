/*
 * Author: Krossby Adhemar Camacho Alviz
 * owned by Krossft.
 */

package br.edu.infnet.mono.domain.controller;

import br.edu.infnet.mono.domain.dto.UsuarioDTO;
import br.edu.infnet.mono.domain.exception.BusinessException;
import br.edu.infnet.mono.domain.exception.UsuarioException;
import br.edu.infnet.mono.domain.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController extends ControllerBase<UsuarioDTO, Long> {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    protected ResponseEntity<UsuarioDTO> acaoBuscarPorId(Long id) throws BusinessException {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @Override
    protected ResponseEntity<List<UsuarioDTO>> acaoListarTodos() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @Override
    protected ResponseEntity<UsuarioDTO> acaoIncluir(UsuarioDTO dto) throws BusinessException {
        return new ResponseEntity<>(usuarioService.incluir(dto), HttpStatus.CREATED);
    }

    @Override
    protected ResponseEntity<UsuarioDTO> acaoAlterar(Long id, UsuarioDTO dto) throws BusinessException {
        return ResponseEntity.ok(usuarioService.alterar(id, dto));
    }

    @Override
    protected void acaoExcluir(Long id) throws BusinessException {
        usuarioService.excluir(id);
    }

    @PatchMapping(value = "/{id}/inativar")
    public ResponseEntity<UsuarioDTO> inativar(@PathVariable Long id) throws UsuarioException {
        return ResponseEntity.ok(usuarioService.inativar(id));
    }

    @GetMapping(value = "/cpf")
    public ResponseEntity<UsuarioDTO> acaoBuscarPorCpf(@RequestParam(value = "cpf") String cpf) throws BusinessException {
        return ResponseEntity.ok(usuarioService.buscarPorCpf(cpf));
    }
}
