/*
 * Author: Krossby Adhemar Camacho Alviz
 * owned by Krossft.
 */

package br.edu.infnet.mono.domain.controller;

import br.edu.infnet.mono.domain.dto.MoradiaDTO;
import br.edu.infnet.mono.domain.exception.BusinessException;
import br.edu.infnet.mono.domain.service.MoradiaService;
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
@RequestMapping(value = "/moradia")
public class MoradiaController extends ControllerBase<MoradiaDTO, Long> {
    private final MoradiaService moradiaService;

    public MoradiaController(MoradiaService moradiaService) {
        this.moradiaService = moradiaService;
    }


    @Override
    protected ResponseEntity<MoradiaDTO> acaoBuscarPorId(Long id) throws BusinessException {
        return ResponseEntity.ok(this.moradiaService.buscarPorId(id));
    }

    @Override
    protected ResponseEntity<List<MoradiaDTO>> acaoListarTodos() {
        return ResponseEntity.ok(this.moradiaService.listarTodos());
    }

    @Override
    protected ResponseEntity<MoradiaDTO> acaoIncluir(MoradiaDTO dto) throws BusinessException {
        return new ResponseEntity<>(moradiaService.incluir(dto), HttpStatus.CREATED);
    }

    @Override
    protected ResponseEntity<MoradiaDTO> acaoAlterar(Long id, MoradiaDTO dto) throws BusinessException {
        return ResponseEntity.ok(moradiaService.alterar(id, dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping(value = "/{id}/inativar")
    public ResponseEntity<MoradiaDTO> inativar(@PathVariable("id") Long id) throws BusinessException {
        return new ResponseEntity<>(moradiaService.inativar(id), HttpStatus.OK);
    }

    @Override
    protected void acaoExcluir(Long id) throws BusinessException {
        moradiaService.excluir(id);
    }
}
