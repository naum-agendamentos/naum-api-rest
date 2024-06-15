package school.sptech.naumspringapi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.naumspringapi.entity.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DashboardServiceTest {

    @Mock
    private AgendamentoService agendamentoService;
    @Mock
    private BarbeiroService barbeiroService;
    @Mock
    private ServicoService servicoService;
    @InjectMocks
    private DashboardService dashboardService;

    private List<Agendamento> agendamentosSemana = new ArrayList<>();

    @BeforeEach
    void setUp() {
        Barbearia barbearia1 = new Barbearia();
        barbearia1.setId(1L);
        barbearia1.setAtiva(true);
        barbearia1.setNome("Barbearia 1");

        Barbeiro barbeiro1 = new Barbeiro();
        barbeiro1.setId(1L);
        barbeiro1.setNome("Barbeiro 1");
        barbeiro1.setBarbeiroAtivo(true);
        barbeiro1.setBarbearia(barbearia1);

        Barbeiro barbeiro2 = new Barbeiro();
        barbeiro2.setId(2L);
        barbeiro2.setNome("Barbeiro 2");
        barbeiro2.setBarbeiroAtivo(true);
        barbeiro2.setBarbearia(barbearia1);

        Cliente cliente1 = new Cliente();
        cliente1.setId(1L);
        cliente1.setNome("Cliente 1");
        cliente1.setEmail("cliente1@gmail.com");

        Cliente cliente2 = new Cliente();
        cliente2.setId(2L);
        cliente2.setNome("Cliente 2");
        cliente2.setEmail("cliente2@gmail.com");

        Servico servico1 = new Servico();
        servico1.setId(1L);
        servico1.setPreco(20.0);
        servico1.setBarbearia(barbearia1);

        Servico servico2 = new Servico();
        servico2.setId(2L);
        servico2.setPreco(30.0);
        servico2.setBarbearia(barbearia1);

        List<Long> listaServicos1 = new ArrayList<>();
        listaServicos1.add(servico2.getId());
        listaServicos1.add(servico1.getId());

        List<Long> listaServicos2 = new ArrayList<>();
        listaServicos2.add(servico2.getId());

        List<Long> listaServicos3 = new ArrayList<>();
        listaServicos3.add(servico1.getId());

        Agendamento agendamento1 = new Agendamento();
        agendamento1.setId(1L);
        agendamento1.setBarbeiro(barbeiro1);
        agendamento1.setServicosIds(listaServicos1);
        agendamento1.setCliente(cliente1);
        agendamento1.setInicio(LocalDateTime.now().minusDays(3));
        agendamento1.setValorTotal(servico1.getPreco() + servico2.getPreco());

        Agendamento agendamento2 = new Agendamento();
        agendamento2.setId(2L);
        agendamento2.setBarbeiro(barbeiro2);
        agendamento2.setServicosIds(listaServicos2);
        agendamento2.setCliente(cliente2);
        agendamento2.setInicio(LocalDateTime.now().minusDays(4));
        agendamento2.setValorTotal(servico2.getPreco());

        Agendamento agendamento3 = new Agendamento();
        agendamento3.setId(3L);
        agendamento3.setBarbeiro(barbeiro2);
        agendamento3.setServicosIds(listaServicos3);
        agendamento3.setCliente(cliente2);
        agendamento3.setInicio(LocalDateTime.now().minusDays(3));
        agendamento3.setValorTotal(servico1.getPreco());

        agendamentosSemana.add(agendamento1);
        agendamentosSemana.add(agendamento2);
        agendamentosSemana.add(agendamento3);
    }

//    @Test
//    @DisplayName("Sucesso ao buscar agendamentos por semana.")
//    void sucessoAgendamentosSemana() {
//        List<Integer> listaCerta = List.of(0, 0, 2, 1, 0, 0, 0);
//
//        LocalDateTime now = LocalDateTime.now();
//        when(agendamentoService.buscarAgendamentoPorData(now.minusDays(1))).thenReturn(new ArrayList<>());
//        when(agendamentoService.buscarAgendamentoPorData(now.minusDays(2))).thenReturn(new ArrayList<>());
//        when(agendamentoService.buscarAgendamentoPorData(now.minusDays(3))).thenReturn(List.of(agendamentosSemana.get(0), agendamentosSemana.get(2)));
//        when(agendamentoService.buscarAgendamentoPorData(now.minusDays(4))).thenReturn(List.of(agendamentosSemana.get(1)));
//        when(agendamentoService.buscarAgendamentoPorData(now.minusDays(5))).thenReturn(new ArrayList<>());
//        when(agendamentoService.buscarAgendamentoPorData(now.minusDays(6))).thenReturn(new ArrayList<>());
//        when(agendamentoService.buscarAgendamentoPorData(now.minusDays(7))).thenReturn(new ArrayList<>());
//
//        List<Integer> agendadosDaSemana = dashboardService.agendamentosPorSemana();
//
//        assertEquals(listaCerta, agendadosDaSemana);
//    }
}
