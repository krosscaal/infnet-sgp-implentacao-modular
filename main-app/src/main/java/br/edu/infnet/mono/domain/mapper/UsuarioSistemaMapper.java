package br.edu.infnet.mono.domain.mapper;

import br.edu.infnet.mono.domain.converter.AbstractConverter;
import br.edu.infnet.mono.domain.dto.UsuarioSistemaDTO;
import br.edu.infnet.mono.domain.entity.UsuarioSistema;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioSistemaMapper extends AbstractConverter<UsuarioSistema, UsuarioSistemaDTO> {
    protected UsuarioSistemaMapper(ModelMapper modelMapper) {
        super(modelMapper, UsuarioSistema.class, UsuarioSistemaDTO.class);
    }

    @Override
    protected void configureMapping() {
        /*DTO -> Entidade*/
        modelMapper.typeMap(UsuarioSistemaDTO.class, UsuarioSistema.class)
                .addMappings(m->{
                    m.map(UsuarioSistemaDTO::getId, UsuarioSistema::setId);
                    m.map(UsuarioSistemaDTO::getUsuario, UsuarioSistema::setUsuario);
                    m.map(UsuarioSistemaDTO::getSenha, UsuarioSistema::setSenha);
                    m.map(UsuarioSistemaDTO::getTipoUsuarioSistema, UsuarioSistema::setTipoUsuarioSistema);
                    m.map(UsuarioSistemaDTO::getEmail, UsuarioSistema::setEmail);
                    m.map(UsuarioSistemaDTO::getPassword, UsuarioSistema::setPassword);
                    m.map(UsuarioSistemaDTO::getSituacao, UsuarioSistema::setSituacao);
                });
        /*Entidade -> DTO*/
        modelMapper.typeMap(UsuarioSistema.class, UsuarioSistemaDTO.class)
                .addMappings(m->{
                    m.map(UsuarioSistema::getUsuario, UsuarioSistemaDTO::setUsuario);
                    m.map(UsuarioSistema::getSenha, UsuarioSistemaDTO::setSenha);
                    m.map(UsuarioSistema::getTipoUsuarioSistema, UsuarioSistemaDTO::setTipoUsuarioSistema);
                    m.map(UsuarioSistema::getEmail, UsuarioSistemaDTO::setEmail);
                    m.map(UsuarioSistema::getPassword, UsuarioSistemaDTO::setPassword);
                    m.map(UsuarioSistema::getSituacao, UsuarioSistemaDTO::setSituacao);
                    m.map(UsuarioSistema::getId, UsuarioSistemaDTO::setId);
                });
    }
    public List<UsuarioSistemaDTO> listEntityToDTO(List<UsuarioSistema> listaUsuarioSistema) {
        return listaUsuarioSistema.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
