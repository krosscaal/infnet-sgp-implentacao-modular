/*
 * Author: Krossby Adhemar Camacho Alviz
 * owned by Krossft.
 */

package br.edu.infnet.mono.domain.controller;

import br.edu.infnet.mono.domain.dto.MoradiaDTO;
import br.edu.infnet.mono.domain.dto.UsuarioCondominioDTO;
import br.edu.infnet.mono.domain.exception.BusinessException;
import br.edu.infnet.mono.domain.service.UsuarioCondominioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/usuario-condominio")
public class UsuarioCondominioController extends ControllerBase<UsuarioCondominioDTO, Long> {
    private final UsuarioCondominioService service;

    public UsuarioCondominioController(UsuarioCondominioService service) {
        this.service = service;
    }

    @Override
    protected ResponseEntity<UsuarioCondominioDTO> acaoBuscarPorId(Long id) throws BusinessException {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Override
    protected ResponseEntity<List<UsuarioCondominioDTO>> acaoListarTodos() {
        return new ResponseEntity<>(service.listarTodos(), HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<UsuarioCondominioDTO> acaoIncluir(UsuarioCondominioDTO dto) throws BusinessException {
        return new ResponseEntity<>(service.incluir(dto), HttpStatus.CREATED);
    }

    @Override
    protected ResponseEntity<UsuarioCondominioDTO> acaoAlterar(Long id, UsuarioCondominioDTO dto) throws BusinessException {
        return ResponseEntity.ok(service.alterar(id, dto));
    }

    @Override
    protected void acaoExcluir(Long id) throws BusinessException {
        service.excluir(id);
    }

    @PatchMapping(value = "/{id}/inativar")
    public ResponseEntity<UsuarioCondominioDTO> inativar(@PathVariable Long id) throws BusinessException {
        return ResponseEntity.ok(service.inativar(id));
    }

    @GetMapping(value = "/moradias/{id}")
    public ResponseEntity<List<MoradiaDTO>> acaoBuscarMoradiasPropietario(@PathVariable("id") Long id) throws BusinessException {
        return ResponseEntity.ok(service.buscarMoradiasDoProprietario(id));
    }
}
