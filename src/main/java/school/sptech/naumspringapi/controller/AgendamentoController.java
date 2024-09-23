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
import school.sptech.naumspringapi.dto.agendamentoDto.AgendamentoBloqListagemDto;
import school.sptech.naumspringapi.email.EmailService;
import school.sptech.naumspringapi.entity.Cliente;
import school.sptech.naumspringapi.entity.Barbeiro;
import school.sptech.naumspringapi.entity.Agendamento;
import school.sptech.naumspringapi.entity.Servico;
import school.sptech.naumspringapi.mapper.AgendamentoMapper;
import school.sptech.naumspringapi.service.AgendamentoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import school.sptech.naumspringapi.dto.agendamentoDto.AgendamentoCriacaoDto;
import school.sptech.naumspringapi.dto.agendamentoDto.AgendamentoListagemDto;
import school.sptech.naumspringapi.dto.agendamentoDto.AgendamentoAtualizacaoDto;
import school.sptech.naumspringapi.service.ServicoService;


import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Api(tags = "AgendamentoController", description = "Controller de Agendamentos.")
@RestController
@AllArgsConstructor
@RequestMapping("/agendamentos")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;
    private final ServicoService servicoService;

    @ApiOperation(value = "Criar um agendamento.", response = AgendamentoListagemDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Agendamento criado com sucesso!"),
            @ApiResponse(code = 422, message = "Dados inválidos."),
            @ApiResponse(code = 404, message = "Barbeiro ou cliente não encontrados.")
    })
    @Operation(summary = "Criar um agendamento", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping
    public ResponseEntity<AgendamentoListagemDto> criarAgendamento(@RequestParam Long barbeiroId, @RequestParam Long clienteId, @RequestParam List<Long> servicoIds, @RequestParam LocalDateTime inicio) {
        Agendamento agendamento = agendamentoService.criarAgendamento(barbeiroId, clienteId, servicoIds, inicio);
        URI uri = URI.create("/agendamentos/" + agendamento.getId());
        List<Servico> servicos = servicoService.buscarServicosPorIds(agendamento.getServicosIds());
        return ResponseEntity.created(uri).body(AgendamentoMapper.toDto(agendamento, servicos));
    }

    @ApiOperation(value = "Buscar um agendamento pelo ID.", response = AgendamentoListagemDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Agendamento encontrado com sucesso!"),
            @ApiResponse(code = 404, message = "Agendamento não encontrado.")
    })
    @Operation(summary = "Buscar um agendamento por ID", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoListagemDto> buscarAgendamento(@PathVariable Long id) {
        Agendamento agendamento = agendamentoService.buscarPorId(id);
        List<Servico> servicos = servicoService.buscarServicosPorIds(agendamento.getServicosIds());
        return ResponseEntity.ok(AgendamentoMapper.toDto(agendamento, servicos));
    }

    @ApiOperation(value = "Listar os agendamentos por barbeiro.", response = AgendamentoListagemDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Agendamentos encontrados com sucesso!"),
            @ApiResponse(code = 204, message = "Não existem agendamentos para retornar."),
            @ApiResponse(code = 404, message = "Barbeiro não encontrado.")
    })
    @Operation(summary = "Listar agendamentos por babeiro", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/barbeiro/{barbeiroId}")
    public ResponseEntity<List<AgendamentoListagemDto>> listarAgendamentosPorBarbeiro(@PathVariable Long barbeiroId) {
        List<Agendamento> agendamentos = agendamentoService.listarPorBarbeiro(barbeiroId);
        if (agendamentos.isEmpty()) return ResponseEntity.noContent().build();
        List<List<Servico>> listasDeServicos = servicoService.buscarServicosPorAgendamentos(agendamentos);
        List<AgendamentoListagemDto> agendamentoListagemDtos = new ArrayList<>();
        for (int i = 0; i < agendamentos.size(); i++) {
            agendamentoListagemDtos.add(AgendamentoMapper.toDto(agendamentos.get(i), listasDeServicos.get(i)));
        }
        return ResponseEntity.ok(agendamentoListagemDtos);
    }

    @ApiOperation(value = "Listar os agendamentos por barbeiro Bloqueio.", response = AgendamentoListagemDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Agendamentos encontrados com sucesso!"),
            @ApiResponse(code = 204, message = "Não existem agendamentos para retornar."),
            @ApiResponse(code = 404, message = "Barbeiro não encontrado.")
    })
    @Operation(summary = "Listar agendamentos por babeiro", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/barbeiroBloq/{barbeiroId}")
    public ResponseEntity<List<AgendamentoBloqListagemDto>> listarAgendamentosPorBarbeiroBloq(@PathVariable Long barbeiroId) {
        List<Agendamento> agendamentos = agendamentoService.listarPorBarbeiro(barbeiroId);
        if (agendamentos.isEmpty()) return ResponseEntity.noContent().build();
        List<List<Servico>> listasDeServicos = servicoService.buscarServicosPorAgendamentos(agendamentos);
        List<AgendamentoBloqListagemDto> agendamentoListagemDtos = new ArrayList<>();
        for (int i = 0; i < agendamentos.size(); i++) {
            agendamentoListagemDtos.add(AgendamentoMapper.toDtoBloq(agendamentos.get(i),listasDeServicos.get(i)));
        }
        return ResponseEntity.ok(agendamentoListagemDtos);
    }

    @ApiOperation(value = "Atualizar um agendamento por ID.", response = AgendamentoListagemDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Agendamento atualizado com sucesso!"),
            @ApiResponse(code = 404, message = "Agendamento não encontrado.")
    })
    @Operation(summary = "Atualizar agendamento por ID", security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("/{idAgendamento}")
        public ResponseEntity<AgendamentoListagemDto> atualizarAgendamento(@PathVariable Long idAgendamento, @RequestParam Long barbeiroId, @RequestParam Long clienteId, @RequestParam List<Long> servicoIds, @RequestParam LocalDateTime inicio){
        Agendamento agendamento = agendamentoService.atualizarAgendamento(idAgendamento, barbeiroId, clienteId, servicoIds, inicio);
        List<Servico> servicos = servicoService.buscarServicosPorIds(agendamento.getServicosIds());
        return ResponseEntity.ok(AgendamentoMapper.toDto(agendamento, servicos));
    }

    @ApiOperation(value = "Excluir um agendamento por ID.", response = AgendamentoListagemDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Agendamento deletado com sucesso!"),
            @ApiResponse(code = 404, message = "Agendamento não encontrado.")
    })
    @Operation(summary = "Excluir agendamento por ID", security = @SecurityRequirement(name = "bearerAuth"))
    @DeleteMapping("/{idAgendamento}")
    public ResponseEntity<Void> excluirAgendamento(@PathVariable Long idAgendamento){
        agendamentoService.excluirAgendamento(idAgendamento);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "Listar os agendamentos por Cliente.", response = AgendamentoListagemDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Agendamentos encontrados com sucesso!"),
            @ApiResponse(code = 204, message = "Não existem agendamentos para retornar."),
            @ApiResponse(code = 404, message = "Cliente não encontrado.")
    })
    @Operation(summary = "Listar agendamentos por Cliente", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<AgendamentoListagemDto>> listarAgendamentosPorCliente(@PathVariable Long clienteId) {
        List<Agendamento> agendamentos = agendamentoService.listarPorCliente(clienteId);
        if (agendamentos.isEmpty()) return ResponseEntity.noContent().build();
        List<List<Servico>> listasDeServicos = servicoService.buscarServicosPorAgendamentos(agendamentos);
        List<AgendamentoListagemDto> agendamentoListagemDtos = new ArrayList<>();
        for (int i = 0; i < agendamentos.size(); i++) {
            agendamentoListagemDtos.add(AgendamentoMapper.toDto(agendamentos.get(i), listasDeServicos.get(i)));
        }
        return ResponseEntity.ok(agendamentoListagemDtos.stream().sorted(Comparator.comparing(AgendamentoListagemDto::getDataHoraAgendamento).reversed()).collect(Collectors.toList()));
    }

    //bloquear  horários de atendimento
    @ApiOperation(value = "Bloquear Horários de funcionamento", response = AgendamentoListagemDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Horários Bloqueados"),
            @ApiResponse(code = 422, message = "Dados inválidos."),
            @ApiResponse(code = 404, message = "Barbeiro não encontrado")
    })
    @Operation(summary = "Bloquear Horários de funcionamento", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/bloquearHorarios")
    public ResponseEntity<List<AgendamentoBloqListagemDto>> bloquearHorarios(@RequestParam Long barbeiroId,@RequestParam List<LocalDateTime> datas) {
        List<Agendamento> agendamentos = agendamentoService.bloquearHorarios(barbeiroId, datas);
        List<List<Servico>> listasDeServicos = servicoService.buscarServicosPorAgendamentos(agendamentos);
        URI uri = URI.create("/agendamentos/" + agendamentos.get(0).getId());
        List<AgendamentoBloqListagemDto> agendamentoBloqListagemDtos = new ArrayList<>();

        for (int i = 0; i < agendamentos.size(); i++) {
                agendamentoBloqListagemDtos.add(AgendamentoMapper.toDtoBloq(agendamentos.get(i), listasDeServicos.get(i)));
        }


        return ResponseEntity.created(uri).body(agendamentoBloqListagemDtos);
    }

}
