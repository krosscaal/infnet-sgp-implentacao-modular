/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */
package br.edu.infnet.mono.domain.mapper;

import br.edu.infnet.mono.domain.converter.AbstractConverter;
import br.edu.infnet.mono.domain.dto.CorrespondenciaDTO;
import br.edu.infnet.mono.domain.entity.Correspondencia;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CorrespondenciaMapper extends AbstractConverter<Correspondencia, CorrespondenciaDTO> {
    protected CorrespondenciaMapper(ModelMapper modelMapper) {
        super(modelMapper, Correspondencia.class, CorrespondenciaDTO.class);
    }

    @Override
    protected void configureMapping() {
        /*DTO -> Entidade*/
        modelMapper.createTypeMap(CorrespondenciaDTO.class, Correspondencia.class)
                .addMappings(m ->{
                    m.map(CorrespondenciaDTO::getMoradiaEntrega, Correspondencia::setMoradiaEntrega);
                    m.map(CorrespondenciaDTO::getNomeDestinatario, Correspondencia::setNomeDestinatario);
                    m.map(CorrespondenciaDTO::getTelefoneDestinatario, Correspondencia::setTelefoneDestinatario);
                    m.map(CorrespondenciaDTO::getEmailDestinatario, Correspondencia::setEmailDestinatario);
                    m.map(CorrespondenciaDTO::getCodigoIdentificadorDaEntrega, Correspondencia::setCodigoIdentificadorDaEntrega);
                    m.map(CorrespondenciaDTO::getDataEntregaDestinatario, Correspondencia::setDataEntregaDestinatario);
                    m.map(CorrespondenciaDTO::getUsuarioRecepcao, Correspondencia::setUsuarioRecepcao);
                    m.map(CorrespondenciaDTO::getUsuarioEntrega, Correspondencia::setUsuarioEntrega);
                    m.map(CorrespondenciaDTO::getNomeMoradorRecepcao, Correspondencia::setNomeMoradorRecepcao);
                    m.map(CorrespondenciaDTO::getDataRecepcao, Correspondencia::setDataRecepcao );
                });

        /*Entidade -> DTO*/
        modelMapper.typeMap(Correspondencia.class, CorrespondenciaDTO.class)
                .addMappings(m-> {
                    m.map(Correspondencia::getMoradiaEntrega, CorrespondenciaDTO::setMoradiaEntrega);
                    m.map(Correspondencia::getNomeDestinatario, CorrespondenciaDTO::setNomeDestinatario);
                    m.map(Correspondencia::getTelefoneDestinatario, CorrespondenciaDTO::setTelefoneDestinatario);
                    m.map(Correspondencia::getEmailDestinatario, CorrespondenciaDTO::setEmailDestinatario);
                    m.map(Correspondencia::getCodigoIdentificadorDaEntrega, CorrespondenciaDTO::setCodigoIdentificadorDaEntrega);
                    m.map(Correspondencia::getDataRecepcao, CorrespondenciaDTO::setDataRecepcao);
                    m.map(Correspondencia::getDataEntregaDestinatario, CorrespondenciaDTO::setDataEntregaDestinatario);
                    m.map(Correspondencia::getUsuarioRecepcao, CorrespondenciaDTO::setUsuarioRecepcao);
                    m.map(Correspondencia::getUsuarioEntrega, CorrespondenciaDTO::setUsuarioEntrega);
                    m.map(Correspondencia::getNomeMoradorRecepcao, CorrespondenciaDTO::setNomeMoradorRecepcao);
                });
    }
    public List<CorrespondenciaDTO> listEntityToDTO(List<Correspondencia> listaCorrespondencia) {
        return listaCorrespondencia.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
