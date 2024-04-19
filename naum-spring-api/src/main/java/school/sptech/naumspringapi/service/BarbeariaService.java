package school.sptech.naumspringapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaAtualizacaoDto;
import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaCriacaoDto;
import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaListagemDto;
import school.sptech.naumspringapi.entity.Barbearia;
import school.sptech.naumspringapi.mapper.BarbeariaMapper;
import school.sptech.naumspringapi.repository.BarbeariaRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BarbeariaService {

    private final BarbeariaRepository barbeariaRepository;

    public BarbeariaListagemDto criarBarbearia(BarbeariaCriacaoDto barbeariaDto) {
        Barbearia barbearia = BarbeariaMapper.toEntity(barbeariaDto);
        Barbearia barbeariaSalva = barbeariaRepository.save(barbearia);
        return BarbeariaMapper.toDto(barbeariaSalva);
    }

    public List<BarbeariaListagemDto> listarBarbearia() {
        List<Barbearia> barbearias = barbeariaRepository.findAll();
        if (barbearias.isEmpty()) return null;
        return BarbeariaMapper.toDto(barbearias);
    }

    public BarbeariaListagemDto atualizarBarbearia(BarbeariaCriacaoDto barbeariaAtt, int id) {
        Optional<Barbearia> barbeariaAtualOpt = barbeariaRepository.findById(id);

        if (barbeariaAtualOpt.isEmpty()) return null;

        BarbeariaAtualizacaoDto attDto = BarbeariaMapper.toAttDto(barbeariaAtualOpt.get());

        if (barbeariaAtt.getNome() != null) barbeariaAtualOpt.get().setNome(attDto.getNome());
        if (barbeariaAtt.getLinkBarbearia() != null) barbeariaAtualOpt.get().setLinkBarbearia(attDto.getLinkBarbearia());
        if (barbeariaAtt.getFotoBarbearia() != null) barbeariaAtualOpt.get().setFotoBarbearia(attDto.getFotoBarbearia());
        if (attDto.isAtiva() && !barbeariaAtt.isAtiva()) barbeariaAtualOpt.get().setAtiva(false);

        Barbearia barbeariaProBanco = barbeariaAtualOpt.get();
        Barbearia barbeariaSalva = barbeariaRepository.save(barbeariaProBanco);
        return BarbeariaMapper.toDto(barbeariaSalva);

    }
}
