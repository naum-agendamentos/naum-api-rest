package school.sptech.naumspringapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.naumspringapi.entity.Servico;
import school.sptech.naumspringapi.entity.Agendamento;
import school.sptech.naumspringapi.mapper.ServicoMapper;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.naumspringapi.repository.ServicoRepository;
import school.sptech.naumspringapi.exception.NaoEncontradoException;
import school.sptech.naumspringapi.dto.servicoDto.ServicoCriacaoDto;
import school.sptech.naumspringapi.dto.servicoDto.ServicoAtualizacaoDto;
import school.sptech.naumspringapi.exception.EntidadeImprocessavelException;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ServicoService {

    private final BarbeariaService barbeariaService;
    private final ServicoRepository servicoRepository;

    public List<Servico> listarServicosPorBarbearia(Long idBarbearia) {
        if (Objects.isNull(idBarbearia)) throw new EntidadeImprocessavelException("idBarbearia");
        return servicoRepository.findAllByBarbearia(barbeariaService.buscarPorId(idBarbearia));
    }

    public List<Servico> buscarServicosPorIds(List<Long> idServicos) {
        List<Servico> servicos = new ArrayList<>();
        if(idServicos != null){
            for (Long idServico : idServicos) {
                servicos.add(servicoRepository.findById(idServico).orElseThrow(() -> new NaoEncontradoException("Serviço")));
            }

        }
        return servicos;
    }

    public List<List<Servico>> buscarServicosPorAgendamentos(List<Agendamento> agendamentos) {
        List<List<Servico>> servicos = new ArrayList<>();
        for (Agendamento agendamento : agendamentos) {
            servicos.add(buscarServicosPorIds(agendamento.getServicosIds()));
        }
        return servicos;
    }

    @Transactional
    public Servico criarServicoPorBarbearia(ServicoCriacaoDto servicoDto, Long idBarbearia) {
        if (Objects.isNull(servicoDto) || Objects.isNull(idBarbearia)) throw new EntidadeImprocessavelException("Serviço ou idBarbearia");
        Servico servico = ServicoMapper.toEntity(servicoDto, barbeariaService.buscarPorId(idBarbearia));
        return servicoRepository.save(servico);
    }

    public Servico buscarServicoPorId(Long idBarbearia, Long idServico) {
        if (Objects.isNull(idBarbearia) || Objects.isNull(idServico)) throw new EntidadeImprocessavelException("idBarbearia ou idServico");
        return servicoRepository.findByIdAndBarbearia(idServico, barbeariaService.buscarPorId(idBarbearia));
    }

    @Transactional
    public Servico atualizarServicoPorId(Long idServico, ServicoAtualizacaoDto servicoDto) {
        if (Objects.isNull(servicoDto) || Objects.isNull(idServico)) throw new EntidadeImprocessavelException("Serviço ou idServico");
        Servico servicoAtual = servicoRepository.findById(idServico).orElseThrow(() -> new NaoEncontradoException("Serviço"));
        servicoAtual.setNomeServico(servicoDto.getNomeServico());
        servicoAtual.setPreco(servicoDto.getPreco());
        servicoAtual.setTempoServico(servicoAtual.getTempoServico());
        return servicoRepository.save(servicoAtual);
    }

    @Transactional
    public void excluirServico(Long idServico) {
        servicoRepository.delete(servicoRepository.findById(idServico).orElseThrow(() -> new NaoEncontradoException("Servico")));
    }
}
