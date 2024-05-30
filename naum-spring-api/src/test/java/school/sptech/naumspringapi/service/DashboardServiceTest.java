package school.sptech.naumspringapi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.naumspringapi.entity.Cliente;
import school.sptech.naumspringapi.repository.AgendamentoRepository;
import school.sptech.naumspringapi.repository.BarbeiroRepository;
import school.sptech.naumspringapi.repository.ServicoRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DashboardServiceTest {

    @Mock
    private BarbeiroRepository barbeiroRepository;
    @Mock
    private ServicoRepository servicoRepository;
    @Mock
    private AgendamentoRepository agendamentoRepository;
    @InjectMocks
    private BarbeiroService barbeiroService;
    @InjectMocks
    private ServicoService servicoService;
    @InjectMocks
    private AgendamentoService agendamentoService;

    @BeforeEach
    void setUp() {
        
    }
}