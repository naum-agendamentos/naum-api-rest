package school.sptech.naumspringapi.service;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import school.sptech.naumspringapi.dto.servicoDto.ServicoCriacaoDto;
import school.sptech.naumspringapi.entity.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.naumspringapi.mapper.ServicoMapper;
import school.sptech.naumspringapi.repository.ServicoRepository;
import school.sptech.naumspringapi.exception.NaoEncontradoException;
import school.sptech.naumspringapi.repository.AgendamentoRepository;
import school.sptech.naumspringapi.exception.EntidadeImprocessavelException;

import java.util.List;
import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ServicoServiceTest {

    @Mock
    private ServicoRepository servicoRepository;
    @Mock
    private BarbeariaService barbeariaService;
    @Mock
    private AgendamentoRepository agendamentoRepository;
    @Mock
    private BarbeiroService barbeiroService;
    @Mock
    private ClienteService clienteService;
    @Mock
    private ServicoMapper servicoMapper;
    @InjectMocks
    private ServicoService servicoService;

    List<Servico> servicosExpected;
    Barbearia barbearia;
    Servico servico;
    Agendamento agendamento;
    Cliente cliente;
    Barbeiro barbeiro;
    List<Agendamento> agendamentosExpected;

    @BeforeEach
    void setUp() {
        barbearia = new Barbearia();
        barbearia.setNome("Barbearia1");
        barbearia.setId(1L);
        servico = new Servico();
        servico.setBarbearia(barbearia);
        servico.setNomeServico("corte");
        servico.setTempoServico(20);
        servico.setId(1L);
        servico.setPreco(40.0);
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Cliente");
        barbeiro = new Barbeiro();
        barbeiro.setBarbearia(barbearia);
        barbeiro.setId(1L);
        barbeiro.setNome("Barbeiro");
        agendamento = new Agendamento();
        agendamento.setId(1L);
        agendamento.setCliente(cliente);
        agendamento.setBarbeiro(barbeiro);
        agendamento.setValorTotal(servico.getPreco());
        agendamento.setServicosIds(List.of(servico.getId()));
        agendamentosExpected = new ArrayList<>();
        agendamentosExpected.add(agendamento);
        servicoMapper = new ServicoMapper();
    }

    @Test
    @DisplayName("lista serviço com sucesso")
    void listarServicosComSucesso() {
        servicosExpected = new ArrayList<>();
        servicosExpected.add(servico);
        when(servicoService.listarServicosPorBarbearia(barbearia.getId())).thenReturn(servicosExpected);

        List<Servico> servicos = servicoService.listarServicosPorBarbearia(1L);

        assertEquals(servicosExpected, servicos);
        verify(servicoRepository, times(1)).findAllByBarbearia(any());
        verify(servicoRepository, times(0)).findAll();
    }

    @Test
    @DisplayName("Lista serviço e retorna lista vazia")
    void listarServicosSemConteudo() {
        servicosExpected = new ArrayList<>();
        when(servicoRepository.findAllByBarbearia(any())).thenReturn(List.of());
        when(servicoService.listarServicosPorBarbearia(1L)).thenReturn(List.of());

        List<Servico> servicos = servicoService.listarServicosPorBarbearia(1L);

        assertEquals(servicosExpected, servicos);
        verify(servicoRepository, times(1)).findAllByBarbearia(any());
        verify(servicoRepository, times(0)).findAll();
    }

    @Test
    @DisplayName("Busca serviço por id com sucesso")
    void buscarServicoPorIdComSucesso() {
        when(servicoService.listarServicosPorBarbearia(servico.getId())).thenReturn(servicosExpected);

        Servico servicoEncontrado = servicoService.buscarServicoPorId(barbearia.getId(), servico.getId());

        assertEquals(servico, servicoEncontrado);
        verify(servicoRepository, times(1)).findAllByBarbearia(any());
    }

    @Test
    @DisplayName("Busca serviço por id com erro de não encontrado")
    void buscarServicoPorIdErro() {
        when(servicoService.buscarServicoPorId(1L, 100L)).thenThrow(new NaoEncontradoException("serviço"));

        assertThrows(NaoEncontradoException.class, () -> {
            servicoService.buscarServicoPorId(1L, 100L);
        });
    }

    @Test
    @DisplayName("Busca serviço por id com erro de entidade nula")
    void buscarServicoPorIdErro2() {
        assertThrows(EntidadeImprocessavelException.class, () -> {
            servicoService.buscarServicoPorId(1L, null);
        });
    }

    @Test
    @DisplayName("Cria novo serviço com sucesso")
    void criarServicoComSucesso() {
        ServicoCriacaoDto servicoDto = new ServicoCriacaoDto();
        servicoDto.setNomeServico("corte2");
        servicoDto.setTempoServico(40);
        servicoDto.setPreco(40.0);
        Servico servico2 = new Servico();
        servico2.setId(2L);
        servico2.setNomeServico("corte2");
        servico2.setTempoServico(40);
        servico2.setPreco(40.0);
        servico2.setBarbearia(barbearia);
        when(servicoService.criarServicoPorBarbearia(servicoDto, barbearia.getId())).thenReturn(servico2);
        when(barbeariaService.buscarPorId(barbearia.getId())).thenReturn(barbearia);

        Servico servicoResponse = servicoService.criarServicoPorBarbearia(servicoDto, barbearia.getId());

        assertEquals(servico2, servicoResponse);
    }

    @Test
    @DisplayName("Cria serviço com campos inválidos")
    void criarServicoErro() {
        assertThrows(EntidadeImprocessavelException.class, () -> {
            servicoService.criarServicoPorBarbearia(null, null);
        });
    }
}