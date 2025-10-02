/*
 * Author: Krossby Adhemar Camacho Alviz
 * owned by Krossft.
 */

package br.edu.infnet.mono.domain.controller;

import br.edu.infnet.mono.domain.dto.CorrespondenciaDTO;
import br.edu.infnet.mono.domain.exception.BusinessException;
import br.edu.infnet.mono.domain.service.CorrespondenciaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/correspondencia")
public class CorrespondenciaController extends ControllerBase<CorrespondenciaDTO, Long> {
    private final CorrespondenciaService service;

    public CorrespondenciaController(CorrespondenciaService service) {
        this.service = service;
    }


    @Override
    protected ResponseEntity<CorrespondenciaDTO> acaoBuscarPorId(Long id) throws BusinessException {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Override
    protected ResponseEntity<List<CorrespondenciaDTO>> acaoListarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @Override
    protected ResponseEntity<CorrespondenciaDTO> acaoIncluir(CorrespondenciaDTO dto) throws BusinessException {
        return new ResponseEntity<>(service.incluir(dto), HttpStatus.CREATED);
    }

    @Override
    protected ResponseEntity<CorrespondenciaDTO> acaoAlterar(Long id, CorrespondenciaDTO dto) throws BusinessException {
        return ResponseEntity.ok(service.alterar(id, dto));
    }

    @Override
    protected void acaoExcluir(Long id) throws BusinessException {
        service.excluir(id);
    }
}
