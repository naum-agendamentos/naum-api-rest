package school.sptech.naumspringapi.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import school.sptech.naumspringapi.dto.agendamentoDto.AgendamentoListagemDto;
import school.sptech.naumspringapi.service.DashboardService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.naumspringapi.dto.servicoDto.ServicoQtdMesListagemDto;
import school.sptech.naumspringapi.dto.barbeiroDto.BarbeiroLucroListagemDto;
import school.sptech.naumspringapi.dto.barbeiroDto.BarbeiroQtdCortesListagemDto;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/dashboards")
public class DashboardController {

    private final DashboardService dashboardService;

    // quantidade de cortes por barbeiro no mês
    @ApiOperation(value = "Buscar lista de quantos cortes foram feitos por barbeiro.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Listagem realizada com sucesso!"),
            @ApiResponse(code = 204, message = "Busca sem resultados, lista vazia.")
    })
    @Operation(summary = "Buscar quantidade de cortes por barbeiro", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/cortes-por-barbeiro")
    public ResponseEntity<List<BarbeiroQtdCortesListagemDto>> cortesPorBarbeiro() {
        List<BarbeiroQtdCortesListagemDto> costesBarbeiros = dashboardService.qtdCortesPorBarbeiro();
        return costesBarbeiros.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(costesBarbeiros);
    }

    // lucro mensal por barbeiro
    @ApiOperation(value = "Buscar lucro gerado por cada barbeiro no mês.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Listagem realizada com sucesso!"),
            @ApiResponse(code = 204, message = "Busca sem resultados, lista vazia.")
    })
    @Operation(summary = "Buscar lucro por barbeiro", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/lucro-por-barbeiro")
    public ResponseEntity<List<BarbeiroLucroListagemDto>> lucroPorBarbeiro() {
        List<BarbeiroLucroListagemDto> lucroBarbeiros = dashboardService.lucroPorBarbeiro();
        return lucroBarbeiros.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lucroBarbeiros);
    }

    // servicos mais utilizados no mês
    @ApiOperation(value = "Buscar lista de serviços mais utilizados por mês.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Listagem realizada com sucesso!"),
            @ApiResponse(code = 204, message = "Busca sem resultados, lista vazia.")
    })
    @Operation(summary = "Lista de serviços mais utilizados por mês", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/top-servicos")
    public ResponseEntity<List<ServicoQtdMesListagemDto>> topServices() {
        List<ServicoQtdMesListagemDto> servicosQtd = dashboardService.servicosMaisUsados();
        return servicosQtd.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(servicosQtd);
    }

    // Lucro total da barbearia
    @ApiOperation(value = "Lucro total da barbearia.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso!")
    })
    @Operation(summary = "Lucro total", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/lucro")
    public ResponseEntity<Double> agendamentosSemana() {
        return ResponseEntity.ok(dashboardService.lucroTotal());
    }

    // Total de agendamentos no dia
    @ApiOperation(value = "Total agendamentos hoje.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso!")
    })
    @Operation(summary = "Total Agendamento", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/total-agendamento-hoje")
    public ResponseEntity<Integer> totalAgendamentoHoje() {
        return ResponseEntity.ok(dashboardService.agendamentoHoje());
    }

    // Porcentagem de agendamentos comparando hoje e ontem
    @ApiOperation(value = "Porcentagem agendamentos hoje com ontem.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso!")
    })
    @Operation(summary = "Porcentagem Agendamento hoje com ontem", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/porcentagem-agendamento-hoje-ontem")
    public ResponseEntity<Double> porcentagemCompaHojeOntem() {
        return ResponseEntity.ok(dashboardService.porcentagemAgendHojeOntem());
    }

    // Porcentagem de avaliação
    @ApiOperation(value = "Media avaliacao barbearia")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Requisição realizada com sucesso!")
    })
    @Operation(summary = "Media avaliacao", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/media-avaliacao/{idBarbearia}")
    public ResponseEntity<Double> mediaAvaliacao(@PathVariable Long idBarbearia) {
        return ResponseEntity.ok(dashboardService.mediaAvaliacao(idBarbearia));
    }


}
