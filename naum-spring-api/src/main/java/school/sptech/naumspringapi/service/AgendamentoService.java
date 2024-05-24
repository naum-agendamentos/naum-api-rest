package school.sptech.naumspringapi.service;

import lombok.RequiredArgsConstructor;
import school.sptech.naumspringapi.dto.agendamentoDto.*;
import school.sptech.naumspringapi.dto.servicoDto.ServicoListagemDto;
import school.sptech.naumspringapi.entity.*;
import org.springframework.stereotype.Service;
import school.sptech.naumspringapi.mapper.*;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.naumspringapi.exception.NaoEncontradoException;
import school.sptech.naumspringapi.repository.AgendamentoRepository;
import school.sptech.naumspringapi.exception.EntidadeImprocessavelException;
import school.sptech.naumspringapi.repository.AgendamentoServicoRepository;
import school.sptech.naumspringapi.repository.ServicoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final ClienteService clienteService;
    private final BarbeiroService barbeiroService;
    private final AgendamentoRepository agendamentoRepository;
    private final ServicoRepository servicoRepository;
    private final AgendamentoServicoRepository agendamentoServicoRepository;
    private final AgendamentoServicoMapper agendamentoServicoMapper;
    private final AgendamentoMapper agendamentoMapper;
    private final ServicoMapper servicoMapper;

    @Transactional
    public Agendamento criarAgendamento(AgendamentoCriacaoDto agendamentoCriacaoDto, Long barbeiroId, Long clienteId, List<ServicoListagemDto> servicos) {
        if (Objects.isNull(barbeiroId) || Objects.isNull(clienteId)) throw new EntidadeImprocessavelException("barbeiro ou cliente");
        if (Objects.isNull(agendamentoCriacaoDto)) throw new EntidadeImprocessavelException("Agendamento");
        Cliente cliente = clienteService.buscarPorId(clienteId);
        Barbeiro barbeiro = barbeiroService.buscarPorId(barbeiroId);
        Agendamento agendamento = agendamentoRepository.save(Objects.requireNonNull(AgendamentoMapper.toEntity(agendamentoCriacaoDto, cliente, barbeiro)));
        List<Long> servicosId = new ArrayList<>();
        for (ServicoListagemDto servicoListagemDto : servicos) {
            servicosId.add(servicoListagemDto.getId());
        }
        for (Long servicoId : servicosId) {
            AgendamentoServicoCriacaoDto agendamentoServicoCriacaoDto = new AgendamentoServicoCriacaoDto();
            agendamentoServicoCriacaoDto.setAgendamento(agendamento);
            agendamentoServicoCriacaoDto.setServico(servicoRepository.findById(servicoId).get());
            agendamentoServicoRepository.save(Objects.requireNonNull(AgendamentoServicoMapper.toEntity(agendamentoServicoCriacaoDto)));
        }
        Integer duracaoAgendamento = 0;
        for (ServicoListagemDto servico : servicos) {
            duracaoAgendamento += servico.getTempoServico();
        }
        agendamento.setDuracaoServico(duracaoAgendamento);
        agendamentoRepository.save(agendamento);
        return agendamento;
    }

    public List<AgendamentoListagemDto> listarAgendamentosPorBarbeiro(Long idBarbeiro) {
        // 1
        Barbeiro barbeiro = barbeiroService.buscarPorId(idBarbeiro);
        /*
        1 - puxar o barbeiro
        1.5 - puxar todos os agendamentos com data presente ou futura do barbeiro.
        2 - puxar todos os agendamentosServicos
        3 - colocar todos os agendamentosServicos em uma lista
        3.5 - fazer uma lista com os IDs dos agendamentos.
        4 - passar por todos os agendamentos diferentes e criar uma lista com todos eles
         */
        // 1.5
        List<Agendamento> agendamentos = agendamentoRepository.findAllByDataAgendamentoGreaterThanEqualAndBarbeiro(LocalDate.now(), barbeiro);
        // 2
        List<List<AgendamentoServico>> listaDeListas = new ArrayList<>();
        for (Agendamento agendamento : agendamentos) {
            List<AgendamentoServico> agendServicos = agendamentoServicoRepository.findByAgendamento(agendamento);
            listaDeListas.add(agendServicos);
        }
        // 4
        List<AgendamentoListagemDto> agendamentoListagemDtos = new ArrayList<>();
        for (int i = 0; i < listaDeListas.size(); i++) {
            AgendamentoListagemDto agendamentoListagemDto = new AgendamentoListagemDto();
            List<ServicoListagemDto> servicoListagemDtos = new ArrayList<>();
            for (AgendamentoServico a : listaDeListas.get(i)) {
                agendamentoListagemDto.setId(a.getAgendamento().getId());
                agendamentoListagemDto.setBarbeiro(BarbeiroMapper.toDto(a.getAgendamento().getBarbeiro()));
                agendamentoListagemDto.setDataAgendamento(a.getAgendamento().getDataAgendamento());
                agendamentoListagemDto.setHoraAgendamento(a.getAgendamento().getHoraAgendamento());
                agendamentoListagemDto.setCliente(ClienteMapper.toDto(a.getAgendamento().getCliente()));
                agendamentoListagemDtos.add(agendamentoListagemDto);
                servicoListagemDtos.add(ServicoMapper.toDto(a.getServico()));
            }
        }
    }

    public Agendamento atualizarAgendamentoPorId(Long idAgendamento, AgendamentoAtualizacaoDto agendamento) {
        if (Objects.isNull(idAgendamento) || Objects.isNull(agendamento)) throw new EntidadeImprocessavelException("idAgendamento");
        Agendamento agendamentoAtual = agendamentoRepository.findById(idAgendamento).orElseThrow(() -> new NaoEncontradoException("agendamento"));
        agendamentoAtual.setDataAgendamento(agendamento.getDataAgendamneto());
        agendamentoAtual.setHoraAgendamento(agendamento.getHoraAgendamneto());
        Integer duracaoAgendamento = 0;
        for (Long servico : agendamento.getServicos()) {
            Servico servicoEntity = servicoRepository.findById(servico).orElseThrow(() -> new NaoEncontradoException("servico"));
            duracaoAgendamento += servicoEntity.getTempoServico();
        }
        agendamentoAtual.setDuracaoServico(duracaoAgendamento);
        return agendamentoRepository.save(agendamentoAtual);
    }

    @Transactional
    public void delearAgendamento(Long idAgendamento) {
        if (Objects.isNull(idAgendamento)) throw new EntidadeImprocessavelException("idAgendamento");
        Agendamento agendamento = agendamentoRepository.findById(idAgendamento).orElseThrow(() -> new NaoEncontradoException("agendamento"));
        agendamentoRepository.delete(agendamento);
    }

//    @Transactional
//    protected List<AgendamentoServico> atualizarAgendamentoServico(Agendamento agendamento, List<ServicoListagemDto> servicos) {
//        List<AgendamentoServico> agendamentos = agendamentoServicoRepository.findByAgendamento(agendamento);
//
//    }
}