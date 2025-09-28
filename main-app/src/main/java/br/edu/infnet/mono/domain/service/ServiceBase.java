/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.edu.infnet.mono.domain.service;

import br.edu.infnet.mono.domain.exception.BusinessException;

import java.util.List;

public interface ServiceBase<T, ID> {
    T buscarPorId(ID idObjeto) throws BusinessException;
    List<T> listarTodos();
    T incluir(T entidade) throws BusinessException;
    T alterar(ID idObjeto, T entidade) throws BusinessException;
    void excluir(ID idObjeto) throws BusinessException;
}
