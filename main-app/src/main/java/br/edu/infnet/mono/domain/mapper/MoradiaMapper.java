package br.edu.infnet.mono.domain.mapper;

import br.edu.infnet.mono.domain.converter.AbstractConverter;
import br.edu.infnet.mono.domain.dto.MoradiaDTO;
import br.edu.infnet.mono.domain.entity.Moradia;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MoradiaMapper extends AbstractConverter<Moradia, MoradiaDTO> {
    protected MoradiaMapper(ModelMapper modelMapper) {
        super(modelMapper, Moradia.class, MoradiaDTO.class);
    }

    @Override
    protected void configureMapping() {
        modelMapper.typeMap(MoradiaDTO.class, Moradia.class)
                .addMappings(m -> {
                    m.map(MoradiaDTO::getId, Moradia::setId);
                    m.map(MoradiaDTO::getTipoMoradia, Moradia::setTipoMoradia);
                    m.map(MoradiaDTO::getPropietario, Moradia::setPropietario);
                    m.map(MoradiaDTO::getMorador, Moradia::setMorador);
                    m.map(MoradiaDTO::getNumeroUnidade, Moradia::setNumeroUnidade);
                    m.map(MoradiaDTO::getSituacao, Moradia::setSituacao);
                    m.map(MoradiaDTO::getQuadraTorreBloco, Moradia::setQuadraTorreBloco);
                    m.map(MoradiaDTO::getLote, Moradia::setLote);
                });

        modelMapper.typeMap(Moradia.class, MoradiaDTO.class)
                .addMappings(m -> {
                    m.map(Moradia::getId, MoradiaDTO::setId);
                    m.map(Moradia::getTipoMoradia, MoradiaDTO::setTipoMoradia);
                    m.map(Moradia::getPropietario, MoradiaDTO::setPropietario);
                    m.map(Moradia::getMorador, MoradiaDTO::setMorador);
                    m.map(Moradia::getNumeroUnidade, MoradiaDTO::setNumeroUnidade);
                    m.map(Moradia::getSituacao, MoradiaDTO::setSituacao);
                    m.map(Moradia::getQuadraTorreBloco, MoradiaDTO::setQuadraTorreBloco);
                    m.map(Moradia::getLote, MoradiaDTO::setLote);
        });
    }
    public List<MoradiaDTO> listEntityToDTO(List<Moradia> listaMoradia) {
        return listaMoradia.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
