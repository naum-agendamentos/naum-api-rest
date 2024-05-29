package school.sptech.naumspringapi.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    // quantidade de agendamentos feitos semanalmente (por dia)
    @ApiOperation(value = "Lista quantidade de agendamentos realizados nos últimos 7 dias.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Listagem realizada com sucesso!"),
            @ApiResponse(code = 204, message = "Busca sem resultados, lista vazia.")
    })
    @Operation(summary = "Lista quantidade de agendamentos da última semana", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/agendamentos-semanais")
    public ResponseEntity<List<Integer>> agendamentosSemana() {
        List<Integer> agendamentos = dashboardService.agendamentosPorSemana();
        return agendamentos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(agendamentos);
    }

}
