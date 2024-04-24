package school.sptech.naumspringapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.naumspringapi.dto.agendamentoDto.AgendamentoCriacaoDto;
import school.sptech.naumspringapi.dto.agendamentoDto.AgendamentoListagemDto;
import school.sptech.naumspringapi.entity.Barbeiro;
import school.sptech.naumspringapi.entity.Cliente;
import school.sptech.naumspringapi.service.AgendamentoService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/agendamentos")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @PostMapping
    public ResponseEntity<AgendamentoListagemDto> Agendar(@RequestBody AgendamentoCriacaoDto agendamentoDto, @RequestParam("barbeiro, cliente") Barbeiro barbeiro, Cliente cliente) {
        AgendamentoListagemDto agendamento = agendamentoService.criarAgendamento(agendamentoDto, barbeiro, cliente);
        if (agendamento == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(agendamento);
    }

    @GetMapping()
    public ResponseEntity<List<AgendamentoListagemDto>> listarAgendamentos() {
        List<AgendamentoListagemDto> listagemDto = agendamentoService.listarAgendamentos();
    }
}
