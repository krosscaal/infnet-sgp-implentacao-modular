package br.edu.infnet.mono.domain.mapper;

import br.edu.infnet.mono.domain.converter.AbstractConverter;
import br.edu.infnet.mono.domain.dto.VisitanteDTO;
import br.edu.infnet.mono.domain.entity.Visitante;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VisitanteMapper extends AbstractConverter<Visitante, VisitanteDTO> {
    protected VisitanteMapper(ModelMapper modelMapper) {
        super(modelMapper, Visitante.class, VisitanteDTO.class);
    }

    @Override
    protected void configureMapping() {
        /*DTO -> Entidade*/
        modelMapper.typeMap(VisitanteDTO.class, Visitante.class)
                .addMappings(m ->{
                    m.map(VisitanteDTO::getId, Visitante::setId);
                    m.map(VisitanteDTO::getUsuarioVisitante, Visitante::setUsuarioVisitante);
                    m.map(VisitanteDTO::getTipoAcesso, Visitante::setTipoAcesso);
                    m.map(VisitanteDTO::getCartaoAcesso, Visitante::setCartaoAcesso);
                    m.map(VisitanteDTO::getMoradiaDestinoVisitante, Visitante::setMoradiaDestinoVisitante);
                    m.map(VisitanteDTO::getUsuarioAutorizacao, Visitante::setUsuarioAutorizacao);
                    m.map(VisitanteDTO::getObservacao, Visitante::setObservacao);
                    m.map(VisitanteDTO::getIngresso, Visitante::setIngresso);
                    m.map(VisitanteDTO::getSaida, Visitante::setSaida);
                });
        /*Entidade -> DTO*/
        modelMapper.typeMap(Visitante.class, VisitanteDTO.class)
                .addMappings(m ->{
                    m.map(Visitante::getId, VisitanteDTO::setId);
                    m.map(Visitante::getUsuarioVisitante, VisitanteDTO::setUsuarioVisitante);
                    m.map(Visitante::getTipoAcesso, VisitanteDTO::setTipoAcesso);
                    m.map(Visitante::getCartaoAcesso, VisitanteDTO::setCartaoAcesso);
                    m.map(Visitante::getMoradiaDestinoVisitante, VisitanteDTO::setMoradiaDestinoVisitante);
                    m.map(Visitante::getUsuarioAutorizacao, VisitanteDTO::setUsuarioAutorizacao);
                    m.map(Visitante::getObservacao, VisitanteDTO::setObservacao);
                    m.map(Visitante::getIngresso, VisitanteDTO::setIngresso);
                    m.map(Visitante::getSaida, VisitanteDTO::setSaida);
                });
    }
    public List<VisitanteDTO> listEntityToDTO(List<Visitante> listaVisitante) {
        return listaVisitante.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
