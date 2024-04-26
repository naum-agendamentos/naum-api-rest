package school.sptech.naumspringapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.naumspringapi.entity.Barbeiro;
import school.sptech.naumspringapi.mapper.BarbeiroMapper;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.naumspringapi.repository.BarbeiroRepository;
import school.sptech.naumspringapi.dto.barbeiroDto.BarbeiroCriacaoDto;
import school.sptech.naumspringapi.dto.barbeiroDto.BarbeiroListagemDto;

@Service
@RequiredArgsConstructor
public class BarbeiroService {

    private final BarbeiroRepository barbeiroRepository;
    private final BarbeariaService barbeariaService;

    public Barbeiro buscarPorId(Integer id) {
        return barbeiroRepository.findByIdAndBarbeiroAtivoTrue(id);
    }

    @Transactional
    public BarbeiroListagemDto criarBarbeiro(BarbeiroCriacaoDto dto, Integer barbeariaId) {
        if (dto == null) return null;
        return BarbeiroMapper.toDto(barbeiroRepository.save(BarbeiroMapper.toEntity(dto, barbeariaService.buscarPorId(barbeariaId))));
    }

}
