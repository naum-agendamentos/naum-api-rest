package school.sptech.naumspringapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.naumspringapi.entity.Cliente;
import school.sptech.naumspringapi.entity.Barbearia;
import school.sptech.naumspringapi.entity.Avaliacao;
import school.sptech.naumspringapi.mapper.AvaliacaoMapper;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.naumspringapi.repository.AvaliacaoRepository;
import school.sptech.naumspringapi.exception.NaoEncontradoException;
import school.sptech.naumspringapi.dto.avaliacaoDto.AvaliacaoCriacaoDto;
import school.sptech.naumspringapi.exception.RequisicaoInvalidaException;
import school.sptech.naumspringapi.exception.EntidadeImprocessavelException;
import school.sptech.naumspringapi.dto.avaliacaoDto.AvaliacaoAtualizacaoDto;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AvaliacaoService {

    private final ClienteService clienteService;
    private final BarbeariaService barbeariaService;
    private final AvaliacaoRepository avaliacaoRepository;

    @Transactional
    public Avaliacao criarAvaliacao (AvaliacaoCriacaoDto avaliacao, Long clienteId, Long barbeariaId) {
        if (Objects.isNull(avaliacao) || Objects.isNull(clienteId) || Objects.isNull(barbeariaId)) throw new EntidadeImprocessavelException("Avaliação");
        Cliente cliente = clienteService.buscarPorId(clienteId);
        Barbearia barbearia = barbeariaService.buscarPorId(barbeariaId);
        return avaliacaoRepository.save(AvaliacaoMapper.toEntity(avaliacao, cliente, barbearia));
    }

    public List<Avaliacao> listarAvaliacoesPorBarbearia (Long barbeariaId) {
        if (Objects.isNull(barbeariaId)) throw new EntidadeImprocessavelException("Avaliação");
        Barbearia barbaeria = barbeariaService.buscarPorId(barbeariaId);
        return avaliacaoRepository.findAllByBarbearia(barbaeria);
    }

    public List<Avaliacao> listarAvaliacoesPorCliente (Long clienteId) {
        if (Objects.isNull(clienteId)) throw new EntidadeImprocessavelException("Avaliação");
        Cliente cliente = clienteService.buscarPorId(clienteId);
        return avaliacaoRepository.findAllByCliente(cliente);
    }

    public List<Avaliacao> listarAvaliacoesPorEstrela (Integer estrela) {
        if (Objects.isNull(estrela)) throw new EntidadeImprocessavelException("Avaliação");
        return avaliacaoRepository.findAllByQtdEstrela(estrela);
    }

    public List<Avaliacao> listarAvaliacoesPorEstrelaAndBarbearia(Integer estrela, Long barbeariaId) {
            if (Objects.isNull(estrela) || Objects.isNull(barbeariaId)) throw new EntidadeImprocessavelException("Avaliação");
            Barbearia barbearia = barbeariaService.buscarPorId(barbeariaId);
            return avaliacaoRepository.findAllByBarbeariaAndQtdEstrela(barbearia, estrela);
    }

    public List<Avaliacao> listarAvaliacaoDinamica(Integer estrela, Long barbeariaId, Long clienteId) {
        if (Objects.isNull(estrela) && Objects.isNull(clienteId))
            return avaliacaoRepository.findAllByBarbearia(barbeariaService.buscarPorId(barbeariaId));
        else if (Objects.isNull(estrela) && Objects.isNull(barbeariaId))
            return avaliacaoRepository.findAllByCliente(clienteService.buscarPorId(clienteId));
        else if (Objects.isNull(clienteId))
            return avaliacaoRepository.findAllByBarbeariaAndQtdEstrela(barbeariaService.buscarPorId(barbeariaId), estrela);
        else if (Objects.isNull(barbeariaId))
            return avaliacaoRepository.findAllByClienteAndQtdEstrela(clienteService.buscarPorId(clienteId), estrela);
        else throw new RequisicaoInvalidaException("Avaliações");
    }

    @Transactional
    public Avaliacao atualizarAvaliacao(Long idAvaliacao, AvaliacaoAtualizacaoDto avaliacaoAtualizacaoDto) {
        if (Objects.isNull(idAvaliacao)) throw new EntidadeImprocessavelException("Avaliação");
        Avaliacao avaliacaoAtual = avaliacaoRepository.findById(idAvaliacao).orElseThrow(() -> new NaoEncontradoException("Avaliação"));
        avaliacaoAtual.setQtdEstrela(avaliacaoAtualizacaoDto.getQtdEstrela());
        return avaliacaoRepository.save(avaliacaoAtual);
    }

    @Transactional
    public void deletarAvaliacao(Long idAvaliacao) {
        if (Objects.isNull(idAvaliacao)) throw new EntidadeImprocessavelException("Avaliação");
        avaliacaoRepository.delete(avaliacaoRepository.findById(idAvaliacao).orElseThrow(() -> new NaoEncontradoException("Avaliação")));
    }


    public Double mediaAvaliacao(Long idBarbearia) {
        return avaliacaoRepository.mediaAvaliacaoEstrelas(idBarbearia);
    }
}
