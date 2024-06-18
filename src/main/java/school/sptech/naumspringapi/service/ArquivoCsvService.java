package school.sptech.naumspringapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.naumspringapi.dto.barbeiroDto.BarbeiroLucroListagemDto;
import school.sptech.naumspringapi.dto.barbeiroDto.BarbeiroQtdCortesListagemDto;
import school.sptech.naumspringapi.entity.Barbeiro;
import school.sptech.naumspringapi.repository.BarbeiroRepository;

import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.List;

@Service
public class ArquivoCsvService {

    @Autowired
    private BarbeiroRepository barbeiroRepository;

    @Autowired
    private DashboardService dashboardService;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");


    public byte[] recuperarValores() {
        List<Barbeiro> resultados = barbeiroRepository.findByBarbeiroAtivoTrue();
        List<BarbeiroQtdCortesListagemDto> resultados1 = dashboardService.qtdCortesPorBarbeiro();
        List<BarbeiroLucroListagemDto> resultados2 = dashboardService.lucroPorBarbeiro();

        mergesort(resultados, 0, resultados.size() - 1);
        mergesort1(resultados1, 0, resultados1.size() - 1);
        mergesort2(resultados2, 0, resultados2.size() - 1);

        return gravaArquivoCsv(resultados, resultados1, resultados2);
    }

    private void mergesort(List<Barbeiro> lista, int baixo, int alto) {
        if (baixo < alto) {
            int meio = (baixo + alto) / 2;
            mergesort(lista, baixo, meio);
            mergesort(lista, meio + 1, alto);
            merge(lista, baixo, meio, alto);
        }
    }

    private void merge(List<Barbeiro> lista, int baixo, int meio, int alto) {
        List<Barbeiro> temp = new ArrayList<>(alto - baixo + 1);
        int i = baixo, j = meio + 1;

        while (i <= meio && j <= alto) {
            if (lista.get(i).getId() <= lista.get(j).getId()) {
                temp.add(lista.get(i++));
            } else {
                temp.add(lista.get(j++));
            }
        }

        while (i <= meio) {
            temp.add(lista.get(i++));
        }
        while (j <= alto) {
            temp.add(lista.get(j++));
        }

        for (int k = 0; k < temp.size(); k++) {
            lista.set(k + baixo, temp.get(k));
        }
    }

    private void mergesort1(List<BarbeiroQtdCortesListagemDto> lista, int baixo, int alto) {
        if (baixo < alto) {
            int meio = (baixo + alto) / 2;
            mergesort1(lista, baixo, meio);
            mergesort1(lista, meio + 1, alto);
            merge1(lista, baixo, meio, alto);
        }
    }

    private void merge1(List<BarbeiroQtdCortesListagemDto> lista, int baixo, int meio, int alto) {
        List<BarbeiroQtdCortesListagemDto> temp = new ArrayList<>(alto - baixo + 1);
        int i = baixo, j = meio + 1;

        while (i <= meio && j <= alto) {
            if (lista.get(i).getId() <= lista.get(j).getId()) {
                temp.add(lista.get(i++));
            } else {
                temp.add(lista.get(j++));
            }
        }

        while (i <= meio) {
            temp.add(lista.get(i++));
        }
        while (j <= alto) {
            temp.add(lista.get(j++));
        }

        for (int k = 0; k < temp.size(); k++) {
            lista.set(k + baixo, temp.get(k));
        }
    }

    private void mergesort2(List<BarbeiroLucroListagemDto> lista, int baixo, int alto) {
        if (baixo < alto) {
            int meio = (baixo + alto) / 2;
            mergesort2(lista, baixo, meio);
            mergesort2(lista, meio + 1, alto);
            merge2(lista, baixo, meio, alto);
        }
    }

    private void merge2(List<BarbeiroLucroListagemDto> lista, int baixo, int meio, int alto) {
        List<BarbeiroLucroListagemDto> temp = new ArrayList<>(alto - baixo + 1);
        int i = baixo, j = meio + 1;

        while (i <= meio && j <= alto) {
            if (lista.get(i).getId() <= lista.get(j).getId()) {
                temp.add(lista.get(i++));
            } else {
                temp.add(lista.get(j++));
            }
        }

        while (i <= meio) {
            temp.add(lista.get(i++));
        }
        while (j <= alto) {
            temp.add(lista.get(j++));
        }

        for (int k = 0; k < temp.size(); k++) {
            lista.set(k + baixo, temp.get(k));
        }
    }

    public byte[] gravaArquivoCsv(List<Barbeiro> lista, List<BarbeiroQtdCortesListagemDto> lista1, List<BarbeiroLucroListagemDto> lista2) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Formatter saida = new Formatter(baos);

        try {
            saida.format("%s,%s,%s,%s,%s,%s\n", "ID:", "NOME:", "EMAIL:", "TELEFONE:","QTD AGENDS", "GANHOS");

            for (int i = 0; i < lista.size(); i++) {
                Barbeiro barbeiro = lista.get(i);
                BarbeiroQtdCortesListagemDto barbeiro1 = lista1.get(i);
                BarbeiroLucroListagemDto barbeiro2 = lista2.get(i);
                saida.format("%s;%s;%s;%s;%s;%s\n", barbeiro.getId(), barbeiro.getNome(), barbeiro.getEmail(), barbeiro.getTelefone(), barbeiro1.getCortes(), barbeiro2.getLucro());
            }

            saida.flush();
            return baos.toByteArray();

        } catch (FormatterClosedException e) {
            e.printStackTrace();
            return new byte[0];
        } finally {
            saida.close();
        }
    }
}