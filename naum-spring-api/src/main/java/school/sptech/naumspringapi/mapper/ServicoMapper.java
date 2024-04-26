package school.sptech.naumspringapi.mapper;

import lombok.RequiredArgsConstructor;
import school.sptech.naumspringapi.dto.servicoDto.ServicoCriacaoDto;
import school.sptech.naumspringapi.entity.Servico;
import school.sptech.naumspringapi.entity.Barbearia;
import school.sptech.naumspringapi.dto.servicoDto.ServicoListagemDto;

import java.util.List;

@RequiredArgsConstructor
public class ServicoMapper {

    public static ServicoListagemDto toDto(Servico entity) {
        if (entity == null) return null;
        ServicoListagemDto dto = new ServicoListagemDto();
        dto.setId(entity.getId());
        dto.setNomeServico(entity.getNomeServico());
        dto.setPreco(entity.getPreco());
        dto.setBarbearia(BarbeariaMapper.toDto(entity.getBarbearia()));
        return dto;
    }

    public static Servico toEntity(ServicoCriacaoDto dto, Barbearia barbearia) {
        if (dto == null) return null;
        Servico entity = new Servico();
        entity.setNomeServico(dto.getNomeServico());
        entity.setPreco(dto.getPreco());
        entity.setBarbearia(barbearia);
        return entity;
    }

    public static List<ServicoListagemDto> toDto(List<Servico> entities) {
        return entities.stream().map(ServicoMapper::toDto).toList();
    }
}
