package school.sptech.naumspringapi.service;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
        Barbearia barbeariaNova = new Barbearia();
        barbeariaNova.setId(5L);
        Servico servicoNovo = new Servico();
        servicoNovo.setId(9L);
        servicoNovo.setBarbearia(barbeariaNova);
        when(barbeariaService.buscarPorId(5L)).thenReturn(barbeariaNova);
        when(servicoService.buscarServicoPorId(5L, 9L)).thenReturn(servicoNovo);

        Servico servicoEncontrado = servicoService.buscarServicoPorId(5L, 9L);

        assertEquals(servicoNovo, servicoEncontrado);
        verify(servicoRepository, times(1)).findByIdAndBarbearia(9L, barbeariaNova);
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

//    @Test
//    @DisplayName("Cria novo serviço com sucesso")
//    void criarServicoComSucesso() {
//        ServicoCriacaoDto servicoDto = new ServicoCriacaoDto();
//        servicoDto.setNomeServico(servico.getNomeServico());
//        servicoDto.setTempoServico(servico.getTempoServico());
//        servicoDto.setPreco(servico.getPreco());
//        Servico servicoEntity = servico;
//        servicoEntity.setId(null);
//        when(barbeariaService.buscarPorId(any())).thenReturn(barbearia);
//        when(servicoService.criarServicoPorBarbearia(servicoDto, barbearia.getId())).thenReturn(servico);
//
//        Servico servicoResponse = servicoService.criarServicoPorBarbearia(servicoDto, barbearia.getId());
//
//        assertEquals(servico, servicoResponse);
//    }

    @Test
    @DisplayName("Cria serviço com campos inválidos")
    void criarServicoErro() {
        assertThrows(EntidadeImprocessavelException.class, () -> {
            servicoService.criarServicoPorBarbearia(null, null);
        });
    }
}