package school.sptech.naumspringapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.naumspringapi.entity.Cliente;
import school.sptech.naumspringapi.entity.Barbearia;
import school.sptech.naumspringapi.mapper.AvaliacaoMapper;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.naumspringapi.repository.AvaliacaoRepository;
import school.sptech.naumspringapi.dto.avaliacaoDto.AvaliacaoCriacaoDto;
import school.sptech.naumspringapi.dto.avaliacaoDto.AvaliacaoListagemDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;
    private final BarbeariaService barbeariaService;
    private final ClienteService clienteService;

    @Transactional
    public AvaliacaoListagemDto criarAvaliacao (AvaliacaoCriacaoDto avaliacao, Integer clienteId, Integer barbeariaId) {
        if (avaliacao == null) return null;
        Cliente cliente = clienteService.buscarPorId(clienteId);
        Barbearia barbearia = barbeariaService.buscarPorId(barbeariaId);
        return AvaliacaoMapper.toDto(avaliacaoRepository.save(AvaliacaoMapper.toEntity(avaliacao, cliente, barbearia)));
    }

    public List<AvaliacaoListagemDto> listarAvaliacoesPorBarbearia (Integer barbeariaId) {
        if (barbeariaId == null) return null;
        Barbearia barbaeria = barbeariaService.buscarPorId(barbeariaId);
        return AvaliacaoMapper.toDto(avaliacaoRepository.findAllByBarbearia(barbaeria));
    }

    public List<AvaliacaoListagemDto> listarAvaliacoesPorCliente (Integer clienteId) {
        if (clienteId == null) return null;
        Cliente cliente = clienteService.buscarPorId(clienteId);
        return AvaliacaoMapper.toDto(avaliacaoRepository.findAllByCliente(cliente));
    }

    public List<AvaliacaoListagemDto> listarAvaliacoesPorEstrela (Integer estrela) {
        if (estrela == null) return null;
        return AvaliacaoMapper.toDto(avaliacaoRepository.findAllByQtdEstrela(estrela));
    }

    public List<AvaliacaoListagemDto> listarAvaliacoesPorEstrelaAndBarbearia(Integer estrela, Integer barbeariaId) {
        if (estrela == null || barbeariaId == null) return null;
        Barbearia barbearia = barbeariaService.buscarPorId(barbeariaId);
        return AvaliacaoMapper.toDto(avaliacaoRepository.findAllByBarbeariaAndQtdEstrela(barbearia, estrela));
    }
}
