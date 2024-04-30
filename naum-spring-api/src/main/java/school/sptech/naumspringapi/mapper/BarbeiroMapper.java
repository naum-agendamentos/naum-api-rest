package school.sptech.naumspringapi.mapper;

import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaCriacaoDto;
import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaListagemDto;
import school.sptech.naumspringapi.dto.barbeiroDto.BarbeiroCriacaoDto;
import school.sptech.naumspringapi.dto.barbeiroDto.BarbeiroDesativacaoDto;
import school.sptech.naumspringapi.dto.barbeiroDto.BarbeiroListagemDto;
import school.sptech.naumspringapi.entity.Barbearia;
import school.sptech.naumspringapi.entity.Barbeiro;

import java.util.List;

public class BarbeiroMapper {

    public static Barbeiro toEntity(BarbeiroCriacaoDto dto, Barbearia barbearia) {
        if (dto == null) return null;

        Barbeiro barbeiro = new Barbeiro();
        barbeiro.setNome(dto.getNome());
        barbeiro.setEmail(dto.getEmail());
        barbeiro.setSenha(dto.getSenha());
        barbeiro.setTelefone(dto.getTelefone());
        barbeiro.setDescricao(dto.getDescricao());
        barbeiro.setFoto(dto.getFoto());
        barbeiro.setBarbearia(barbearia);

        return barbeiro;
    }

    public static BarbeiroListagemDto toDto(Barbeiro entity) {
        if (entity == null) return null;

        BarbeiroListagemDto dto = new BarbeiroListagemDto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setTelefone(entity.getTelefone());
        dto.setFoto(entity.getFoto());
        BarbeariaListagemDto dtoBarbearia = BarbeariaMapper.toDto(entity.getBarbearia());
        dto.setBarbearia(dtoBarbearia);

        return dto;
    }

    public static List<BarbeiroListagemDto> toDto(List<Barbeiro> entities) {
        return entities.stream().map(BarbeiroMapper::toDto).toList();
    }

    public static BarbeiroDesativacaoDto toDtoDesativacao(Barbeiro entity) {
        if (entity == null) return null;

        BarbeiroDesativacaoDto dto = new BarbeiroDesativacaoDto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setTelefone(entity.getTelefone());
        dto.setFoto(entity.getFoto());
        BarbeariaListagemDto dtoBarbearia = BarbeariaMapper.toDto(entity.getBarbearia());
        dto.setBarbearia(dtoBarbearia);
        dto.setBarbeiroAtivo(entity.isBarbeiroAtivo());

        return dto;
    }
}
