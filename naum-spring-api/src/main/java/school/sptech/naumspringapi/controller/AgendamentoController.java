package school.sptech.naumspringapi.controller;

import lombok.AllArgsConstructor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.naumspringapi.entity.Cliente;
import school.sptech.naumspringapi.entity.Barbeiro;
import school.sptech.naumspringapi.entity.Agendamento;
import school.sptech.naumspringapi.service.AgendamentoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import school.sptech.naumspringapi.dto.agendamentoDto.AgendamentoCriacaoDto;
import school.sptech.naumspringapi.dto.agendamentoDto.AgendamentoListagemDto;
import school.sptech.naumspringapi.dto.agendamentoDto.AgendamentoAtualizacaoDto;


import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@Api(tags = "AgendamentoController", description = "Controller de Agendamentos.")
@RestController
@AllArgsConstructor
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @PostMapping
    public Agendamento criarAgendamento(@RequestParam Long barbeiroId, @RequestParam Long clienteId, @RequestParam List<Long> servicoIds, @RequestParam LocalDateTime inicio) {
        return agendamentoService.criarAgendamento(barbeiroId, clienteId, servicoIds, inicio);
    }

    @GetMapping("/{id}")
    public Agendamento buscarAgendamento(@PathVariable Long id) {
        return agendamentoService.buscarPorId(id);
    }

    @GetMapping("/barbeiro/{barbeiroId}")
    public List<Agendamento> listarAgendamentosPorBarbeiro(@PathVariable Long barbeiroId) {
        return agendamentoService.listarPorBarbeiro(barbeiroId);
    }

//    private final AgendamentoService agendamentoService;
//
//    @ApiOperation("Criar um agendamento.")
//    @ApiResponses(value = {
//            @ApiResponse(code = 201, message = "Agendamento criado com sucesso!"),
//            @ApiResponse(code = 422, message = "Dados inválidos."),
//            @ApiResponse(code = 404, message = "Barbeiro ou cliente não encontrados.")
//    })
//    @Operation(summary = "Criar um agendamento", security = @SecurityRequirement(name = "bearerAuth"))
//    @PostMapping
//    public ResponseEntity<AgendamentoListagemDto> Agendar(@RequestBody AgendamentoCriacaoDto agendamentoDto, @RequestParam("barbeiro") Barbeiro barbeiro, @RequestParam("cliente") Cliente cliente) {
//        Agendamento agendamento = agendamentoService.criarAgendamento(agendamentoDto, barbeiro, cliente);
//        URI uri = URI.create("/clientes/" + cliente.getId() + "/agendamentos");
//        return ResponseEntity.created(uri).body(AgendamentoMapper.toDto(agendamento));
//    }
//
//    @ApiOperation("Listar os agendamentos a depender do barbeiro logado buscando.")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Agendamentos encontrados com sucesso!"),
//            @ApiResponse(code = 204, message = "Não existem agendamentos para retornar."),
//            @ApiResponse(code = 404, message = "Barbeiro não encontrado.")
//    })
//    @Operation(summary = "Listar agendamentos", security = @SecurityRequirement(name = "bearerAuth"))
//    @GetMapping
//    public ResponseEntity<List<AgendamentoListagemDto>> listarAgendamentos(@RequestParam("idBarbeiro") Long idBarbeiro) {
//        List<Agendamento> agendamentos = agendamentoService.listarAgendamentosPorBarbeiro(idBarbeiro);
//        if (agendamentos.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//        return ResponseEntity.status(HttpStatus.OK).body(AgendamentoMapper.toDto(agendamentos));
//    }
//
//    @ApiOperation("Atualizar um agendamento pelo ID.")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Agendamento encontrado com sucesso!"),
//            @ApiResponse(code = 404, message = "Agendamento não encontrado.")
//    })
////    @Operation(summary = "Buscar um agendamento por ID", security = @SecurityRequirement(name = "bearerAuth"))
////    @PutMapping("/{idAgendamento}")
////    public ResponseEntity<AgendamentoListagemDto> atualizarAgendamento(Long idAgendamento, @RequestBody AgendamentoAtualizacaoDto agendamentoDto) {
////        return ResponseEntity.status(HttpStatus.OK).body(AgendamentoMapper.toDto(agendamentoService.atualizarAgendamentoPorId(idAgendamento, agendamentoDto)));
////    }
//
////    @ApiOperation("Deletar um agendamento pelo ID.")
////    @ApiResponses(value = {
////            @ApiResponse(code = 200, message = "Agendamento encontrado com sucesso!"),
////            @ApiResponse(code = 404, message = "Agendamento não encontrado.")
////    })
//    @Operation(summary = "Buscar um agendamento por ID", security = @SecurityRequirement(name = "bearerAuth"))
//    @DeleteMapping
//    public ResponseEntity<Void> deletarAgendamento(Long idAgendamento) {
//        agendamentoService.delearAgendamento(idAgendamento);
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }
}
