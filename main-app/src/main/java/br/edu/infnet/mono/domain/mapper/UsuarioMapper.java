package br.edu.infnet.mono.domain.mapper;

import br.edu.infnet.mono.domain.converter.AbstractConverter;
import br.edu.infnet.mono.domain.dto.UsuarioDTO;
import br.edu.infnet.mono.domain.entity.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioMapper extends AbstractConverter<Usuario, UsuarioDTO> {
    protected UsuarioMapper(ModelMapper modelMapper) {
        super(modelMapper, Usuario.class, UsuarioDTO.class);
    }

    @Override
    protected void configureMapping() {
        /*DTO -> Entidade*/
        modelMapper.typeMap(UsuarioDTO.class, Usuario.class)
                .addMappings(m->{
                    m.map(UsuarioDTO::getId, Usuario::setId);
                    m.map(UsuarioDTO::getNome, Usuario::setNome);
                    m.map(UsuarioDTO::getSobreNome, Usuario::setSobreNome);
                    m.map(UsuarioDTO::getCpf, Usuario::setCpf);
                    m.map(UsuarioDTO::getRg, Usuario::setRg);
                    m.map(UsuarioDTO::getTelefone1, Usuario::setTelefone1);
                    m.map(UsuarioDTO::getTelefone2, Usuario::setTelefone2);
                    m.map(UsuarioDTO::getSituacao, Usuario::setSituacao);
                });
        /*Entidade -> DTO*/
        modelMapper.createTypeMap(Usuario.class, UsuarioDTO.class)
                .addMappings(m->{
                    m.map(Usuario::getId, UsuarioDTO::setId);
                    m.map(Usuario::getNome, UsuarioDTO::setNome);
                    m.map(Usuario::getSobreNome, UsuarioDTO::setSobreNome);
                    m.map(Usuario::getCpf, UsuarioDTO::setCpf);
                    m.map(Usuario::getRg, UsuarioDTO::setRg);
                    m.map(Usuario::getTelefone1, UsuarioDTO::setTelefone1);
                    m.map(Usuario::getTelefone2, UsuarioDTO::setTelefone2);
                    m.map(Usuario::getSituacao, UsuarioDTO::setSituacao);
                });
    }
    public List<UsuarioDTO> listEntityToDTO(List<Usuario> listaUsuario) {
        return listaUsuario.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
