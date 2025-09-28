/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.edu.infnet.mono.domain.repository;

import br.edu.infnet.mono.domain.entity.Visitante;
import br.edu.infnet.mono.domain.enumerator.EnumTipoAcesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VisitanteRepository extends JpaRepository<Visitante, Long> {
    @Query("SELECT v FROM Visitante v WHERE v.usuarioVisitante.cpf = :cpf AND v.tipoAcesso = :tipoAcesso")
    List<Visitante> findByCpf(String cpf, EnumTipoAcesso tipoAcesso);

    List<Visitante> findAllByIngressoBetween(LocalDateTime ingressoInicio, LocalDateTime ingressoFim);
}
