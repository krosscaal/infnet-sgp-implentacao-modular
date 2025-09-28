package br.edu.infnet.mono.domain.mapper;

import br.edu.infnet.mono.domain.converter.AbstractConverter;
import br.edu.infnet.mono.domain.dto.EnderecoDTO;
import br.edu.infnet.mono.domain.entity.Endereco;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EnderecoMapper extends AbstractConverter<Endereco, EnderecoDTO> {
    protected EnderecoMapper(ModelMapper modelMapper) {
        super(modelMapper, Endereco.class, EnderecoDTO.class);
    }

    @Override
    protected void configureMapping() {
        /*DTO -> Entidade*/
        modelMapper.typeMap(EnderecoDTO.class, Endereco.class)
                .addMappings(m->{
                    m.map(EnderecoDTO::getId, Endereco::setId);
                    m.map(EnderecoDTO::getLogradouro, Endereco::setLogradouro);
                    m.map(EnderecoDTO::getNumero, Endereco::setNumero);
                    m.map(EnderecoDTO::getComplemento, Endereco::setComplemento);
                    m.map(EnderecoDTO::getBairro, Endereco::setBairro);
                    m.map(EnderecoDTO::getLocalidade, Endereco::setLocalidade);
                    m.map(EnderecoDTO::getCep, Endereco::setCep);
                    m.map(EnderecoDTO::getEstado, Endereco::setEstado);
                    m.map(EnderecoDTO::getUf, Endereco::setUf);
                });
        /*Entidade -> DTO*/
        modelMapper.typeMap(Endereco.class, EnderecoDTO.class)
                .addMappings(m->{
                    m.map(Endereco::getId, EnderecoDTO::setId);
                    m.map(Endereco::getLogradouro, EnderecoDTO::setLogradouro);
                    m.map(Endereco::getNumero, EnderecoDTO::setNumero);
                    m.map(Endereco::getComplemento, EnderecoDTO::setComplemento);
                    m.map(Endereco::getBairro, EnderecoDTO::setBairro);
                    m.map(Endereco::getLocalidade, EnderecoDTO::setLocalidade);
                    m.map(Endereco::getCep, EnderecoDTO::setCep);
                    m.map(Endereco::getEstado, EnderecoDTO::setEstado);
                    m.map(Endereco::getUf, EnderecoDTO::setUf);
                });
    }
}
