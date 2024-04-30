package school.sptech.naumspringapi.service;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.naumspringapi.dto.servicoDto.ServicoCriacaoDto;
import school.sptech.naumspringapi.dto.servicoDto.ServicoListagemDto;
import school.sptech.naumspringapi.entity.Barbearia;
import school.sptech.naumspringapi.mapper.ServicoMapper;
import school.sptech.naumspringapi.repository.ServicoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicoService {

    private final ServicoRepository servicoRepository;
    private final BarbeariaService barbeariaService;

    public List<ServicoListagemDto> listarServicosPorBarbearia(Long idBarbearia) {
        return ServicoMapper.toDto(servicoRepository.findAllByBarbearia(barbeariaService.buscarPorId(idBarbearia)));
    }

    @Transactional
    public ServicoListagemDto criarServicoPorBarbearia(ServicoCriacaoDto servicoDto, Long idBarbearia) {
        try {
            if (servicoDto == null || idBarbearia == null) throw new BadRequestException();
            Barbearia barbearia = barbeariaService.buscarPorId(idBarbearia);
            return ServicoMapper.toDto(servicoRepository.save(ServicoMapper.toEntity(servicoDto, barbearia)));
        } catch (BadRequestException e) {
            return null;
        }
    }

    public ServicoListagemDto buscarServicoPorId(Long idBarbearia, Long idServico) {
        if (idBarbearia == null || idServico == null) return null;
        servicoRepository.findByIdAndBarbearia(idServico, barbeariaService.buscarPorId(idBarbearia));
        return ServicoMapper.toDto(servicoRepository.findById(idServico).get());
    }
}
