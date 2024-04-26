package school.sptech.naumspringapi.mapper;

import lombok.RequiredArgsConstructor;
import school.sptech.naumspringapi.dto.avaliacaoDto.AvaliacaoCriacaoDto;
import school.sptech.naumspringapi.dto.avaliacaoDto.AvaliacaoListagemDto;
import school.sptech.naumspringapi.entity.Avaliacao;
import school.sptech.naumspringapi.entity.Barbearia;
import school.sptech.naumspringapi.entity.Cliente;

import java.util.List;

@RequiredArgsConstructor
public class AvaliacaoMapper {

    public static Avaliacao toEntity(AvaliacaoCriacaoDto avaliacaoDto, Cliente cliente, Barbearia barbearia) {
        if (avaliacaoDto == null || cliente == null || barbearia == null) return null;
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setBarbearia(barbearia);
        avaliacao.setCliente(cliente);
        avaliacao.setQtdEstrela(avaliacaoDto.getQtdEstrela());

        return avaliacao;
    }

    public static AvaliacaoListagemDto toDto(Avaliacao avaliacao) {
        if (avaliacao == null) return null;
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
