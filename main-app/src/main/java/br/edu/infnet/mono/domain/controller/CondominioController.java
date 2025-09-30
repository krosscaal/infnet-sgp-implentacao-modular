/*
 * Author: Krossby Adhemar Camacho Alviz
 * owned by Krossft.
 */

package br.edu.infnet.mono.domain.controller;

import br.edu.infnet.mono.domain.dto.CondominioDTO;
import br.edu.infnet.mono.domain.exception.BusinessException;
import br.edu.infnet.mono.domain.service.CondominioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/condominio")
public class CondominioController extends ControllerBase<CondominioDTO, Long> {

    private final CondominioService service;

    public CondominioController(CondominioService service) {
        this.service = service;
    }

    @Override
    protected ResponseEntity<CondominioDTO> acaoBuscarPorId(Long id) throws BusinessException {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Override
    protected ResponseEntity<List<CondominioDTO>> acaoListarTodos() {
        return ResponseEntity.ok().body(service.listarTodos());    }

    @Override
    protected ResponseEntity<CondominioDTO> acaoIncluir(CondominioDTO dto) throws BusinessException {
        return new ResponseEntity<>(service.incluir(dto), HttpStatus.CREATED);
    }

    @Override
    protected ResponseEntity<CondominioDTO> acaoAlterar(Long id, CondominioDTO dto) throws BusinessException {
        return ResponseEntity.ok(service.alterar(id, dto));
    }

    @Override
    protected void acaoExcluir(Long id) throws BusinessException {
        service.excluir(id);
    }
    @PatchMapping(value = "/{id}/inativar")
    protected ResponseEntity<CondominioDTO> acaoInativar(@PathVariable("id") Long id) throws BusinessException {
        return ResponseEntity.ok(service.inativar(id));
    }

}
