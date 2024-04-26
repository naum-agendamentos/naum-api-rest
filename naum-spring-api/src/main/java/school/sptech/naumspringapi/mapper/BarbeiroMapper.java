package school.sptech.naumspringapi.mapper;

import lombok.RequiredArgsConstructor;
import school.sptech.naumspringapi.dto.barbeiroDto.BarbeiroCriacaoDto;
import school.sptech.naumspringapi.dto.barbeiroDto.BarbeiroListagemDto;
import school.sptech.naumspringapi.entity.Barbearia;
import school.sptech.naumspringapi.entity.Barbeiro;

import java.util.List;

@RequiredArgsConstructor
public class BarbeiroMapper {

    public static BarbeiroListagemDto toDto(Barbeiro entity) {
        if (entity == null) return null;
        BarbeiroListagemDto dto = new BarbeiroListagemDto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setFoto(entity.getFoto());
        dto.setEmail(entity.getEmail());
        dto.setTelefone(entity.getTelefone());
        dto.setDescricao(entity.getDescricao());
        dto.setFkPermissao(entity.getFkPermissao());
        dto.setBarbeiroAtivo(entity.isBarbeiroAtivo());
        dto.setBarbearia(BarbeariaMapper.toDto(entity.getBarbearia()));
        return dto;
    }

    public static Barbeiro toEntity(BarbeiroCriacaoDto dto, Barbearia barbearia) {
        if (dto == null) return null;
        Barbeiro barbeiro = new Barbeiro();
        barbeiro.setNome(dto.getNome());
        barbeiro.setFoto(dto.getFoto());
        barbeiro.setEmail(dto.getEmail());
        barbeiro.setTelefone(dto.getTelefone());
        barbeiro.setDescricao(dto.getDescricao());
        barbeiro.setFkPermissao(dto.getFkPermissao());
        barbeiro.setBarbeiroAtivo(dto.isBarbeiroAtivo());
        barbeiro.setBarbearia(barbearia);
        return barbeiro;
    }

    public static List<BarbeiroListagemDto> toDto(List<Barbeiro> entities) {
        return entities.stream().map(BarbeiroMapper::toDto).toList();
    }


}
