package school.sptech.naumspringapi.mapper;

import org.springframework.stereotype.Component;
import school.sptech.naumspringapi.dto.agendamentoDto.AgendamentoListagemDto;
import school.sptech.naumspringapi.dto.barbeiroDto.BarbeiroListagemBloqDto;
import school.sptech.naumspringapi.entity.Barbeiro;
import school.sptech.naumspringapi.entity.Barbearia;
import school.sptech.naumspringapi.dto.barbeiroDto.BarbeiroCriacaoDto;
import school.sptech.naumspringapi.dto.barbeiroDto.BarbeiroListagemDto;
import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaListagemDto;
import school.sptech.naumspringapi.dto.barbeiroDto.BarbeiroDesativacaoDto;

import java.util.List;
import java.util.Objects;

@Component
public class BarbeiroMapper {

    public static Barbeiro toEntity(BarbeiroCriacaoDto dto, Barbearia barbearia) {
        if (Objects.isNull(dto)) return null;

        Barbeiro barbeiro = new Barbeiro();
        barbeiro.setNome(dto.getNome());
        barbeiro.setEmail(dto.getEmail());
        barbeiro.setSenha(dto.getSenha());
        barbeiro.setTelefone(dto.getTelefone());
        barbeiro.setDescricao(dto.getDescricao());
        barbeiro.setFoto(dto.getFoto());
        barbeiro.setBarbearia(barbearia);
        barbeiro.setSemana(SemanaMapper.toEntity(dto.getSemana()));
        return barbeiro;
    }

    public static BarbeiroListagemDto toDto(Barbeiro entity) {
        if (Objects.isNull(entity)) return null;

        BarbeiroListagemDto dto = new BarbeiroListagemDto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setTelefone(entity.getTelefone());
        dto.setDescricao(entity.getDescricao());
        dto.setFoto(entity.getFoto());
        BarbeariaListagemDto dtoBarbearia = BarbeariaMapper.toDto(entity.getBarbearia());
        dto.setBarbearia(dtoBarbearia);
        dto.setSemana(SemanaMapper.toDto(entity.getSemana()));

        return dto;
    }

    public static List<BarbeiroListagemDto> toDto(List<Barbeiro> entities) {
        return entities.stream().map(BarbeiroMapper::toDto).toList();
    }

    public static BarbeiroListagemBloqDto toBarbeiroBloqDto(Barbeiro entity){
        if (Objects.isNull(entity)) return null;
        BarbeiroListagemBloqDto barbeiroDto = new BarbeiroListagemBloqDto();
        barbeiroDto.setId(entity.getId());
        barbeiroDto.setNome(entity.getNome());
        barbeiroDto.setEmail(entity.getEmail());
        barbeiroDto.setSenha(entity.getSenha());
        barbeiroDto.setTelefone(entity.getTelefone());
        barbeiroDto.setDescricao(entity.getDescricao());
        barbeiroDto.setFoto(entity.getFoto());
        BarbeariaListagemDto dtoBarbearia = BarbeariaMapper.toDto(entity.getBarbearia());
        barbeiroDto.setBarbearia(dtoBarbearia);
        barbeiroDto.setSemana(SemanaMapper.toDto(entity.getSemana()));

        return barbeiroDto;
    }
    public static List<BarbeiroListagemBloqDto> toBarbeiroBloqDto(List<Barbeiro> entities) {
        return entities.stream().map(BarbeiroMapper::toBarbeiroBloqDto).toList();
    }

    public static BarbeiroDesativacaoDto toDtoDesativacao(Barbeiro entity) {
        if (Objects.isNull(entity)) return null;

        BarbeiroDesativacaoDto dto = new BarbeiroDesativacaoDto();
        dto.setId(entity.getId());
        dto.setBarbeiroAtivo(entity.isBarbeiroAtivo());

        return dto;
    }

    public static AgendamentoListagemDto.BarbeiroListagemDto toBarbeiroAgendadoDto(Barbeiro entity) {
        if (Objects.isNull(entity)) return null;
        AgendamentoListagemDto.BarbeiroListagemDto dto = new AgendamentoListagemDto.BarbeiroListagemDto();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setNome(entity.getNome());
        dto.setTelefone(entity.getTelefone());
        dto.setFoto(entity.getFoto());
        return dto;
    }
}
