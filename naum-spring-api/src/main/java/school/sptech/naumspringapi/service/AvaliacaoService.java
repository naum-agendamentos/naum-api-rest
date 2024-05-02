package school.sptech.naumspringapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.naumspringapi.entity.Cliente;
import school.sptech.naumspringapi.entity.Barbearia;
import school.sptech.naumspringapi.entity.Avaliacao;
import school.sptech.naumspringapi.mapper.AvaliacaoMapper;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.naumspringapi.repository.AvaliacaoRepository;
import school.sptech.naumspringapi.dto.avaliacaoDto.AvaliacaoCriacaoDto;
import school.sptech.naumspringapi.dto.avaliacaoDto.AvaliacaoListagemDto;
import school.sptech.naumspringapi.dto.avaliacaoDto.AvaliacaoAtualizacaoDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;
    private final BarbeariaService barbeariaService;
    private final ClienteService clienteService;

    @Transactional
    public AvaliacaoListagemDto criarAvaliacao (AvaliacaoCriacaoDto avaliacao, Long clienteId, Long barbeariaId) {
        if (avaliacao == null) return null;
        Cliente cliente = clienteService.buscarPorId(clienteId);
        Barbearia barbearia = barbeariaService.buscarPorId(barbeariaId);
        return AvaliacaoMapper.toDto(avaliacaoRepository.save(AvaliacaoMapper.toEntity(avaliacao, cliente, barbearia)));
    }

    public List<AvaliacaoListagemDto> listarAvaliacoesPorBarbearia (Long barbeariaId) {
        if (barbeariaId == null) return null;
        Barbearia barbaeria = barbeariaService.buscarPorId(barbeariaId);
        return AvaliacaoMapper.toDto(avaliacaoRepository.findAllByBarbearia(barbaeria));
    }

    public List<AvaliacaoListagemDto> listarAvaliacoesPorCliente (Long clienteId) {
        if (clienteId == null) return null;
        Cliente cliente = clienteService.buscarPorId(clienteId);
        return AvaliacaoMapper.toDto(avaliacaoRepository.findAllByCliente(cliente));
    }

    public List<AvaliacaoListagemDto> listarAvaliacoesPorEstrela (Integer estrela) {
        if (estrela == null) return null;
        return AvaliacaoMapper.toDto(avaliacaoRepository.findAllByQtdEstrela(estrela));
    }

    public List<AvaliacaoListagemDto> listarAvaliacoesPorEstrelaAndBarbearia(Integer estrela, Long barbeariaId) {
        if (estrela == null || barbeariaId == null) return null;
        Barbearia barbearia = barbeariaService.buscarPorId(barbeariaId);
        return AvaliacaoMapper.toDto(avaliacaoRepository.findAllByBarbeariaAndQtdEstrela(barbearia, estrela));
    }

    public List<AvaliacaoListagemDto> listarAvaliacaoDinamica(Integer estrela, Long barbeariaId, Long clienteId) {
        if (estrela == null && clienteId == null) return AvaliacaoMapper.toDto(avaliacaoRepository.findAllByBarbearia(barbeariaService.buscarPorId(barbeariaId)));
        else if (estrela == null && barbeariaId == null) return AvaliacaoMapper.toDto(avaliacaoRepository.findAllByCliente(clienteService.buscarPorId(clienteId)));
        else if (clienteId == null) return AvaliacaoMapper.toDto(avaliacaoRepository.findAllByBarbeariaAndQtdEstrela(barbeariaService.buscarPorId(barbeariaId), estrela));
        else if (barbeariaId == null) return AvaliacaoMapper.toDto(avaliacaoRepository.findAllByClienteAndQtdEstrela(clienteService.buscarPorId(clienteId), estrela));
        else return null;
    }

    @Transactional
    public AvaliacaoListagemDto atualizarAvaliacao(Long idAvaliacao, AvaliacaoAtualizacaoDto avaliacaoAtualizacaoDto) {
        Avaliacao avaliacaoAtual = avaliacaoRepository.findById(idAvaliacao).orElseThrow();
        avaliacaoAtual.setQtdEstrela(avaliacaoAtualizacaoDto.getQtdEstrela());
        return AvaliacaoMapper.toDto(avaliacaoRepository.save(avaliacaoAtual));
    }

    @Transactional
    public void deletarAvaliacao(Long idAvaliacao) {
        avaliacaoRepository.delete(avaliacaoRepository.findById(idAvaliacao).orElseThrow());
    }
}
