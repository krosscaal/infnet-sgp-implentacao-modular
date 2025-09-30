package br.edu.infnet.mono.domain.mapper;

import br.edu.infnet.mono.domain.converter.AbstractConverter;
import br.edu.infnet.mono.domain.dto.CondominioDTO;
import br.edu.infnet.mono.domain.entity.Condominio;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CondominioMapper extends AbstractConverter<Condominio, CondominioDTO> {
    protected CondominioMapper(ModelMapper modelMapper) {
        super(modelMapper, Condominio.class, CondominioDTO.class);
    }

    @Override
    protected void configureMapping() {
        modelMapper.typeMap(CondominioDTO.class, Condominio.class)
                .addMappings(m->{
                    m.map(CondominioDTO::getId, Condominio::setId);
                    m.map(CondominioDTO::getNomeCondominio, Condominio::setNomeCondominio);
                    m.map(CondominioDTO::getTipoCondominio, Condominio::setTipoCondominio);
                    m.map(CondominioDTO::getTotalUnidades, Condominio::setTotalUnidades);
                    m.map(CondominioDTO::getCnpj, Condominio::setCnpj);
                    m.map(CondominioDTO::getTelefoneContato1, Condominio::setTelefoneContato1);
                    m.map(CondominioDTO::getTelefoneContato2, Condominio::setTelefoneContato2);
                    m.map(CondominioDTO::getEndereco, Condominio::setEndereco);
                    m.map(CondominioDTO::getNomeSindico, Condominio::setNomeSindico);
                    m.map(CondominioDTO::getTelefoneSindico, Condominio::setTelefoneSindico);
                    m.map(CondominioDTO::getSituacao, Condominio::setSituacao);
                });

        modelMapper.typeMap(Condominio.class, CondominioDTO.class)
                .addMappings(m->{
                    m.map(Condominio::getId, CondominioDTO::setId);
                    m.map(Condominio::getNomeCondominio, CondominioDTO::setNomeCondominio);
                    m.map(Condominio::getTipoCondominio, CondominioDTO::setTipoCondominio);
                    m.map(Condominio::getTotalUnidades, CondominioDTO::setTotalUnidades);
                    m.map(Condominio::getCnpj, CondominioDTO::setCnpj);
                    m.map(Condominio::getTelefoneContato1, CondominioDTO::setTelefoneContato1);
                    m.map(Condominio::getTelefoneContato2, CondominioDTO::setTelefoneContato2);
                    m.map(Condominio::getEndereco, CondominioDTO::setEndereco);
                    m.map(Condominio::getNomeSindico, CondominioDTO::setNomeSindico);
                    m.map(Condominio::getTelefoneSindico, CondominioDTO::setTelefoneSindico);
                });
    }
    public List<CondominioDTO> listEntityToDTO(List<Condominio> listaCondominio) {
        return listaCondominio.stream().map(this::toDTO).collect(java.util.stream.Collectors.toList());
    }
}
