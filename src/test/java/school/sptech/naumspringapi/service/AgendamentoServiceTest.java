package school.sptech.naumspringapi.service;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import school.sptech.naumspringapi.entity.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.naumspringapi.email.EmailService;
import school.sptech.naumspringapi.domain.usuario.Usuario;
import school.sptech.naumspringapi.domain.usuario.UsuarioTipo;
import school.sptech.naumspringapi.repository.ServicoRepository;
import school.sptech.naumspringapi.repository.AgendamentoRepository;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
class AgendamentoServiceTest {

    @Mock
    private AgendamentoRepository agendamentoRepository;
    @InjectMocks
    private AgendamentoService agendamentoService;
    @Mock
    private ServicoRepository servicoRepository;
    @Mock
    private BarbeiroService barbeiroService;
    @Mock
    private ClienteService clienteService;
    @Mock
    private EmailService emailService;

    // Util
    private Agendamento agendamento1;
    private Agendamento agendamento2;
    private Agendamento agendamento3;
    private Agendamento agendamento4;
    private Barbearia barbearia;
    private Barbeiro barbeiro1;
    private Barbeiro barbeiro2;
    private Barbeiro barbeiro3;
    private Cliente cliente1;
    private Cliente cliente2;
    private Cliente cliente3;
    private Servico servico1;
    private Servico servico2;
    private Servico servico3;
    private Usuario usuario1;
    private Usuario usuario2;
    private Usuario usuario3;
    private Usuario usuario4;
    private Usuario usuario5;
    private Usuario usuario6;

    @BeforeEach
    void setUp() {

        // Instância 1
        usuario1 = new Usuario();
        usuario1.setId(1L);
        usuario1.setNome("Usuário Um");
        usuario1.setEmail("usuario1@example.com");
        usuario1.setSenha("senha1");
        usuario1.setTipo(UsuarioTipo.BARBEIRO);

        // Instância 2
        usuario2 = new Usuario();
        usuario2.setId(2L);
        usuario2.setNome("Usuário Dois");
        usuario2.setEmail("usuario2@example.com");
        usuario2.setSenha("senha2");
        usuario2.setTipo(UsuarioTipo.BARBEIRO);

        // Instância 3
        usuario3 = new Usuario();
        usuario3.setId(3L);
        usuario3.setNome("Usuário Três");
        usuario3.setEmail("usuario3@example.com");
        usuario3.setSenha("senha3");
        usuario3.setTipo(UsuarioTipo.BARBEIRO);

        // Instâncias de Usuario com tipo CLIENTE
        usuario4 = new Usuario();
        usuario4.setId(4L);
        usuario4.setNome("Cliente Um");
        usuario4.setEmail("cliente1@example.com");
        usuario4.setSenha("senha4");
        usuario4.setTipo(UsuarioTipo.CLIENTE);

        usuario5 = new Usuario();
        usuario5.setId(5L);
        usuario5.setNome("Cliente Dois");
        usuario5.setEmail("cliente2@example.com");
        usuario5.setSenha("senha5");
        usuario5.setTipo(UsuarioTipo.CLIENTE);

        usuario6 = new Usuario();
        usuario6.setId(6L);
        usuario6.setNome("Cliente Três");
        usuario6.setEmail("cliente3@example.com");
        usuario6.setSenha("senha6");
        usuario6.setTipo(UsuarioTipo.CLIENTE);

        // Instância de Barbearia
        barbearia = new Barbearia();
        barbearia.setId(1L);
        barbearia.setNome("Barbearia Central");
        barbearia.setLinkBarbearia("http://barbeariacentral.com");
        barbearia.setAtiva(true);
        barbearia.setFotoBarbearia("foto_barbearia.png");
        barbearia.setEndereco(null);

        // Instância 1
        cliente1 = new Cliente();
        cliente1.setId(1L);
        cliente1.setEmail("cliente1@example.com");
        cliente1.setNome("Cliente Um");
        cliente1.setTelefone("1234567890");
        cliente1.setSenha("senha1");
        cliente1.setUsuario(usuario6);

        // Instância 2
        cliente2 = new Cliente();
        cliente2.setId(2L);
        cliente2.setEmail("cliente2@example.com");
        cliente2.setNome("Cliente Dois");
        cliente2.setTelefone("0987654321");
        cliente2.setSenha("senha2");
        cliente2.setUsuario(usuario5);

        // Instância 3
        cliente3 = new Cliente();
        cliente3.setId(3L);
        cliente3.setEmail("cliente3@example.com");
        cliente3.setNome("Cliente Três");
        cliente3.setTelefone("1122334455");
        cliente3.setSenha("senha3");
        cliente3.setUsuario(usuario4);

        // Instância 1
        barbeiro1 = new Barbeiro();
        barbeiro1.setId(1L);
        barbeiro1.setNome("Barbeiro Um");
        barbeiro1.setEmail("barbeiro1@example.com");
        barbeiro1.setSenha("senha1");
        barbeiro1.setTelefone("1234567890");
        barbeiro1.setDescricao("Descrição do Barbeiro Um");
        barbeiro1.setBarbeiroAtivo(true);
        barbeiro1.setFoto("foto1.png");
        barbeiro1.setBarbearia(barbearia);
        barbeiro1.setFkPermissao(1);
        barbeiro1.setUsuario(usuario3);

        // Instância 2
        barbeiro2 = new Barbeiro();
        barbeiro2.setId(2L);
        barbeiro2.setNome("Barbeiro Dois");
        barbeiro2.setEmail("barbeiro2@example.com");
        barbeiro2.setSenha("senha2");
        barbeiro2.setTelefone("0987654321");
        barbeiro2.setDescricao("Descrição do Barbeiro Dois");
        barbeiro2.setBarbeiroAtivo(true);
        barbeiro2.setFoto("foto2.png");
        barbeiro2.setBarbearia(barbearia);
        barbeiro2.setFkPermissao(2);
        barbeiro2.setUsuario(usuario2);

        // Instância 3
        barbeiro3 = new Barbeiro();
        barbeiro3.setId(3L);
        barbeiro3.setNome("Barbeiro Três");
        barbeiro3.setEmail("barbeiro3@example.com");
        barbeiro3.setSenha("senha3");
        barbeiro3.setTelefone("1122334455");
        barbeiro3.setDescricao("Descrição do Barbeiro Três");
        barbeiro3.setBarbeiroAtivo(true);
        barbeiro3.setFoto("foto3.png");
        barbeiro3.setBarbearia(barbearia);
        barbeiro3.setFkPermissao(2);
        barbeiro3.setUsuario(usuario1);

        // Instância 1 de Servico
        servico1 = new Servico();
        servico1.setId(1L);
        servico1.setNomeServico("Corte de Cabelo");
        servico1.setPreco(30.0);
        servico1.setTempoServico(30);
        servico1.setBarbearia(barbearia);

        // Instância 2 de Servico
        servico2 = new Servico();
        servico2.setId(2L);
        servico2.setNomeServico("Barba");
        servico2.setPreco(20.0);
        servico2.setTempoServico(20);
        servico2.setBarbearia(barbearia);

        // Instância 3 de Servico
        servico3 = new Servico();
        servico3.setId(3L);
        servico3.setNomeServico("Corte e Barba");
        servico3.setPreco(45.0);
        servico3.setTempoServico(50);
        servico3.setBarbearia(barbearia);

        // Instância 1 de Agendamento
        agendamento1 = new Agendamento();
        agendamento1.setId(1L);
        agendamento1.setBarbeiro(barbeiro1);
        List<Long> servicosIds1 = new ArrayList<>();
        servicosIds1.add(servico1.getId());
        agendamento1.setServicosIds(servicosIds1);
        agendamento1.setCliente(cliente1);
        agendamento1.setInicio(LocalDateTime.now().plusDays(1));
        agendamento1.setFim(LocalDateTime.now().plusDays(1).plusMinutes(servico1.getTempoServico()));
        agendamento1.setValorTotal(servico1.getPreco());

        // Instância 2 de Agendamento
        agendamento2 = new Agendamento();
        agendamento2.setId(2L);
        agendamento2.setBarbeiro(barbeiro1);
        List<Long> servicosIds2 = new ArrayList<>();
        servicosIds2.add(servico1.getId());
        servicosIds2.add(servico2.getId());
        agendamento2.setServicosIds(servicosIds2);
        agendamento2.setCliente(cliente2);
        agendamento2.setInicio(LocalDateTime.now().minusDays(10));
        agendamento2.setFim(LocalDateTime.now().minusDays(10).plusMinutes(servico1.getTempoServico() + servico2.getTempoServico()));
        agendamento2.setValorTotal(servico1.getPreco() + servico2.getPreco());

        // Instância 3 de Agendamento
        agendamento3 = new Agendamento();
        agendamento3.setId(3L);
        agendamento3.setBarbeiro(barbeiro2);
        List<Long> servicosIds3 = new ArrayList<>();
        servicosIds3.add(servico2.getId());
        agendamento3.setServicosIds(servicosIds3);
        agendamento3.setCliente(cliente1);
        agendamento3.setInicio(LocalDateTime.now().minusDays(20));
        agendamento3.setFim(LocalDateTime.now().minusDays(20).plusMinutes(servico2.getTempoServico()));
        agendamento3.setValorTotal(servico2.getPreco());

        // Instância 4 de Agendamento
        agendamento4 = new Agendamento();
        agendamento4.setId(4L);
        agendamento4.setBarbeiro(barbeiro3);
        List<Long> servicosIds4 = new ArrayList<>();
        servicosIds4.add(servico1.getId());
        agendamento4.setServicosIds(servicosIds4);
        agendamento4.setCliente(cliente3);
        agendamento4.setInicio(LocalDateTime.now().plusDays(5));
        agendamento4.setFim(LocalDateTime.now().plusDays(5).plusMinutes(servico1.getTempoServico()));
        agendamento4.setValorTotal(servico1.getPreco());

        cliente1.setAgendamentos(List.of(agendamento1, agendamento3));
        cliente2.setAgendamentos(List.of(agendamento2));
        cliente3.setAgendamentos(List.of(agendamento4));

        barbeiro1.setAgendamentos(List.of(agendamento1, agendamento2));
        barbeiro2.setAgendamentos(List.of(agendamento3));
        barbeiro3.setAgendamentos(List.of(agendamento4));
    }

    @Test
    @DisplayName("Criar um agendamento com sucesso")
    void criarAgendamento_ComSucesso() {
        Agendamento agendamentoSemId = agendamento1;
        agendamentoSemId.setId(null);
        when(barbeiroService.buscarPorId(barbeiro1.getId())).thenReturn(barbeiro1);
        when(clienteService.buscarPorId(cliente1.getId())).thenReturn(cliente1);
        when(servicoRepository.existsById(any())).thenReturn(true);
        when(agendamentoRepository.save(any())).thenReturn(agendamento1);

        Agendamento resultado = agendamentoService.criarAgendamento(barbeiro1.getId(), cliente1.getId(), agendamento1.getServicosIds(), agendamento1.getInicio());

        assertNotNull(resultado);
        assertEquals(agendamento1.getId(), resultado.getId());
        verify(agendamentoRepository, times(1)).save(any(Agendamento.class));
    }

    @Test
    @DisplayName("Busca por um agendamento pelo id com sucesso.")
    void buscarPorId() {
        when(agendamentoRepository.findById(agendamento1.getId())).thenReturn(Optional.of(agendamento1));

        Agendamento resultado = agendamentoService.buscarPorId(agendamento1.getId());

        assertNotNull(resultado);
        assertEquals(agendamento1.getId(), resultado.getId());
        verify(agendamentoRepository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Lista os agendamentos buscando por barbeiro com sucesso.")
    void listarPorBarbeiro() {
        when(agendamentoRepository.findByBarbeiroId(barbeiro1.getId())).thenReturn(List.of(agendamento1, agendamento2));

        List<Agendamento> resultados = agendamentoService.listarPorBarbeiro(barbeiro1.getId());

        assertNotNull(resultados);
        assertEquals(2, resultados.size());
        verify(agendamentoRepository, times(1)).findByBarbeiroId(anyLong());
    }

    @Test
    @DisplayName("Lista os agendamentos buscando por cliente com sucesso.")
    void listarPorCliente() {
        when(agendamentoRepository.findByClienteId(cliente1.getId())).thenReturn(List.of(agendamento1, agendamento3));

        List<Agendamento> resultados = agendamentoService.listarPorCliente(cliente1.getId());

        assertNotNull(resultados);
        assertEquals(2, resultados.size());
        verify(agendamentoRepository, times(1)).findByClienteId(anyLong());
    }

    @Test
    @DisplayName("Verifica se o horário tem conflito.")
    void horarioConflitante() {
        LocalDateTime inicio = agendamento1.getInicio();
        LocalDateTime fim = agendamento1.getFim();

        boolean conflito = agendamentoService.horarioConflitante(inicio, fim, agendamento1);

        assertTrue(conflito);
    }

    @Test
    @DisplayName("Atualiza um agendamento com sucesso.")
    void atualizarAgendamento() {
        when(agendamentoRepository.findById(agendamento1.getId())).thenReturn(Optional.of(agendamento1));
        when(agendamentoRepository.save(any(Agendamento.class))).thenReturn(agendamento1);
        when(servicoRepository.existsById(any())).thenReturn(true);

        List<Long> servicos = agendamento1.getServicosIds();
// Long barbeiroId, Long clienteId, List<Long> servicoIds, LocalDateTime inicio
        Agendamento resultado = agendamentoService.atualizarAgendamento(agendamento1.getId(), 1L, 1L, servicos, agendamento4.getInicio());

        assertNotNull(resultado);
        assertEquals(agendamento1.getId(), resultado.getId());
        verify(agendamentoRepository, times(1)).findById(anyLong());
        verify(agendamentoRepository, times(1)).save(any(Agendamento.class));
    }

    @Test
    @DisplayName("Exclui um agendamento com sucesso.")
    void excluirAgendamento() {
        when(agendamentoRepository.findById(agendamento1.getId())).thenReturn(Optional.of(agendamento1));
        doNothing().when(agendamentoRepository).delete(agendamento1);

        agendamentoService.excluirAgendamento(agendamento1.getId());

        verify(agendamentoRepository, times(1)).findById(anyLong());
        verify(agendamentoRepository, times(1)).delete(any());
    }
}
