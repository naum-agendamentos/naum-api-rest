package school.sptech.naumspringapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.naumspringapi.entity.Servico;
import school.sptech.naumspringapi.entity.Barbeiro;
import school.sptech.naumspringapi.entity.Agendamento;
import org.springframework.security.core.context.SecurityContextHolder;
import school.sptech.naumspringapi.dto.servicoDto.ServicoQtdMesListagemDto;
import school.sptech.naumspringapi.dto.barbeiroDto.BarbeiroLucroListagemDto;
import school.sptech.naumspringapi.dto.barbeiroDto.BarbeiroQtdCortesListagemDto;
import school.sptech.naumspringapi.service.usuario.autenticacao.dto.UsuarioDetalhesDto;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardService {
    
    private final BarbeiroService barbeiroService;
    private final ServicoService servicoService;
    private final AgendamentoService agendamentoService;
    private final AvaliacaoService avaliacaoService;

    public List<BarbeiroQtdCortesListagemDto> qtdCortesPorBarbeiro() {
        List<Barbeiro> barbeiros = barbeiroService.listaBarbeirosPorBarbearia();
        return barbeiros.stream().map(barbeiro -> new BarbeiroQtdCortesListagemDto(
                barbeiro.getId(),
                barbeiro.getNome(),
                barbeiro.getAgendamentos().size()
        )).collect(Collectors.toList());
    }

    public List<BarbeiroLucroListagemDto> lucroPorBarbeiro() {
        List<Barbeiro> barbeiros = barbeiroService.listaBarbeirosPorBarbearia();
        return barbeiros.stream().map(barbeiro -> {
            Double lucro = barbeiro.getAgendamentos().stream()
                    .flatMap(agendamento -> servicoService.buscarServicosPorIds(agendamento.getServicosIds()).stream())
                    .mapToDouble(Servico::getPreco)
                    .sum();
            return new BarbeiroLucroListagemDto(barbeiro.getId(), barbeiro.getNome(), lucro);
        }).collect(Collectors.toList());
    }

    public List<ServicoQtdMesListagemDto> servicosMaisUsados() {
        UsuarioDetalhesDto usuarioLogado = (UsuarioDetalhesDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Barbeiro barbeiroLogado = barbeiroService.login(usuarioLogado.getId());
        List<Servico> servicos = servicoService.listarServicosPorBarbearia(barbeiroLogado.getBarbearia().getId());
        List<Long> servicoIds = servicos.stream().map(Servico::getId).collect(Collectors.toList());
        List<Agendamento> agendamentos = agendamentoService.agendamentosUltimoMes(servicoIds);
        Map<Long, Long> servicoCountMap = new HashMap<>(); // Map para contar a frequência de cada serviço
        for (Agendamento agendamento : agendamentos) {
            for (Long servicoId : agendamento.getServicosIds()) {
                servicoCountMap.put(servicoId, servicoCountMap.getOrDefault(servicoId, 0L) + 1);
            }
        }
        List<ServicoQtdMesListagemDto> servicoQtdMesListagemDtos = new ArrayList<>(); // Criar e ordenar a lista de DTOs
        for (Servico servico : servicos) {
            Long quantidade = servicoCountMap.getOrDefault(servico.getId(), 0L);
            servicoQtdMesListagemDtos.add(new ServicoQtdMesListagemDto(servico.getId(), servico.getNomeServico(), Math.toIntExact(quantidade)));
        }
        servicoQtdMesListagemDtos.sort(Comparator.comparingLong(ServicoQtdMesListagemDto::getQtdMes).reversed());

        return servicoQtdMesListagemDtos;
    }

    public Double lucroTotal() {
        List<BarbeiroLucroListagemDto> listaDeLucros = lucroPorBarbeiro();
        Double lucroTotal = 0.0;
        for (BarbeiroLucroListagemDto b : listaDeLucros) {
            lucroTotal += b.getLucro();
        }
        return lucroTotal;
    }

    public Integer agendamentoHoje() {
        return agendamentoService.agendamentoHoje();
    }

    public Double porcentagemAgendHojeOntem() {
        return agendamentoService.porcentagemAgendamentosHojeComparadoOntem();
    }

    public Double mediaAvaliacao(Long idBarbearia) {
        return avaliacaoService.mediaAvaliacao(idBarbearia);
    }
}
