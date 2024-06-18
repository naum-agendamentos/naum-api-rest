package school.sptech.naumspringapi.mapper;

import org.springframework.stereotype.Component;
import school.sptech.naumspringapi.entity.Cliente;
import school.sptech.naumspringapi.entity.Barbearia;
import school.sptech.naumspringapi.entity.Avaliacao;
import school.sptech.naumspringapi.dto.avaliacaoDto.AvaliacaoCriacaoDto;
import school.sptech.naumspringapi.dto.avaliacaoDto.AvaliacaoListagemDto;

import java.util.List;
import java.util.Objects;

@Component
public class AvaliacaoMapper {

    public static Avaliacao toEntity(AvaliacaoCriacaoDto avaliacaoDto, Cliente cliente, Barbearia barbearia) {
        if (Objects.isNull(avaliacaoDto) || Objects.isNull(cliente) || Objects.isNull(barbearia)) return null;
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setBarbearia(barbearia);
        avaliacao.setCliente(cliente);
        avaliacao.setQtdEstrela(avaliacaoDto.getQtdEstrela());

        return avaliacao;
    }

    public static AvaliacaoListagemDto toDto(Avaliacao avaliacao) {
        if (Objects.isNull(avaliacao)) return null;
        AvaliacaoListagemDto dto = new AvaliacaoListagemDto();
        dto.setQtdEstrela(avaliacao.getQtdEstrela());
        dto.setId(avaliacao.getId());
        dto.setBarbearia(BarbeariaMapper.toDto(avaliacao.getBarbearia()));
        dto.setCliente(ClienteMapper.toDto(avaliacao.getCliente()));
        return dto;
    }

    public static List<AvaliacaoListagemDto> toDto(List<Avaliacao> avaliacoes) {
        return avaliacoes.stream().map(AvaliacaoMapper::toDto).toList();
    }
}
