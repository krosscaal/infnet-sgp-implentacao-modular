package br.edu.infnet.mono.domain.mapper;

import br.edu.infnet.mono.domain.converter.AbstractConverter;
import br.edu.infnet.mono.domain.dto.UsuarioCondominioDTO;
import br.edu.infnet.mono.domain.entity.UsuarioCondominio;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioCondominioMapper extends AbstractConverter<UsuarioCondominio, UsuarioCondominioDTO> {
    protected UsuarioCondominioMapper(ModelMapper modelMapper) {
        super(modelMapper, UsuarioCondominio.class, UsuarioCondominioDTO.class);
    }

    @Override
    protected void configureMapping() {
        modelMapper.typeMap(UsuarioCondominioDTO.class, UsuarioCondominio.class)
                .addMappings(mapper->{
                    mapper.map(UsuarioCondominioDTO::getId, UsuarioCondominio::setId);
                    mapper.map(UsuarioCondominioDTO::getUsuario, UsuarioCondominio::setUsuario);
                    mapper.map(UsuarioCondominioDTO::getSituacao, UsuarioCondominio::setSituacao);
                    mapper.map(UsuarioCondominioDTO::getTipoResidente, UsuarioCondominio::setTipoResidente);
                    mapper.map(UsuarioCondominioDTO::getEmail, UsuarioCondominio::setEmail);
                    mapper.map(UsuarioCondominioDTO::getMoradias, UsuarioCondominio::setMoradias);
                });
        modelMapper.typeMap(UsuarioCondominio.class, UsuarioCondominioDTO.class)
                .addMappings(mapper->{
                    mapper.map(UsuarioCondominio::getId, UsuarioCondominioDTO::setId);
                    mapper.map(UsuarioCondominio::getUsuario, UsuarioCondominioDTO::setUsuario);
                    mapper.map(UsuarioCondominio::getSituacao, UsuarioCondominioDTO::setSituacao);
                    mapper.map(UsuarioCondominio::getTipoResidente, UsuarioCondominioDTO::setTipoResidente);
                    mapper.map(UsuarioCondominio::getEmail, UsuarioCondominioDTO::setEmail);
                    mapper.map(UsuarioCondominio::getMoradias, UsuarioCondominioDTO::setMoradias);
                });
    }
    public List<UsuarioCondominioDTO> listEntityToDTO(List<UsuarioCondominio> listaUsuarioCondominio) {
        return listaUsuarioCondominio.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
