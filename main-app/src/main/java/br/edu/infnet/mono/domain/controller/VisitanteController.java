/*
 * Author: Krossby Adhemar Camacho Alviz
 * owned by Krossft.
 */

package br.edu.infnet.mono.domain.controller;

import br.edu.infnet.mono.domain.dto.VisitanteDTO;
import br.edu.infnet.mono.domain.exception.BusinessException;
import br.edu.infnet.mono.domain.service.VisitanteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/visitante")
public class VisitanteController extends ControllerBase<VisitanteDTO, Long> {

    private final VisitanteService visitanteService;

    public VisitanteController(VisitanteService visitanteService) {
        this.visitanteService = visitanteService;
    }


    @Override
    protected ResponseEntity<VisitanteDTO> acaoBuscarPorId(Long id) throws BusinessException {
        return ResponseEntity.ok(visitanteService.buscarPorId(id));
    }

    @Override
    protected ResponseEntity<List<VisitanteDTO>> acaoListarTodos() {
        return ResponseEntity.ok(visitanteService.listarTodos());
    }

    @Override
    protected ResponseEntity<VisitanteDTO> acaoIncluir(VisitanteDTO dto)  {
        return new ResponseEntity<>(visitanteService.incluir(dto), HttpStatus.CREATED);
    }

    @Override
    protected ResponseEntity<VisitanteDTO> acaoAlterar(Long id, VisitanteDTO dto) throws BusinessException {
        return ResponseEntity.ok(visitanteService.alterar(id, dto));
    }

    @Override
    protected void acaoExcluir(Long id) throws BusinessException {
        visitanteService.excluir(id);
    }

    @GetMapping(value = "/cpf")
    public ResponseEntity<List<VisitanteDTO>> acaoBuscarPorCpf(@RequestParam(value = "cpf") String cpf) throws BusinessException {
        return ResponseEntity.ok(visitanteService.buscarPorCpf(cpf));
    }

    @GetMapping(value = "/data")
    public ResponseEntity<List<VisitanteDTO>> acaoBuscarVisitantesPorDataIngreso(@RequestParam(value = "data") String data) {
        return ResponseEntity.ok(visitanteService.buscarVisitantesPorDataIngresso(data));
    }
}
