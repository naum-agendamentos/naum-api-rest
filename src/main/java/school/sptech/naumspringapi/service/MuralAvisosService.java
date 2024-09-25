package school.sptech.naumspringapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.naumspringapi.dto.muralAvisosDto.MuralAvisosCriacaoDto;
import school.sptech.naumspringapi.dto.muralAvisosDto.MuralAvisosAtualizacaoDto;
import school.sptech.naumspringapi.entity.MuralAvisos;
import school.sptech.naumspringapi.entity.Barbeiro;
import school.sptech.naumspringapi.exception.NaoEncontradoException;
import school.sptech.naumspringapi.mapper.MuralAvisosMapper;
import school.sptech.naumspringapi.repository.MuralAvisosRepository;
import school.sptech.naumspringapi.repository.BarbeiroRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MuralAvisosService {

    private final MuralAvisosRepository muralAvisosRepository;
    private final BarbeiroRepository barbeiroRepository;

    @Transactional
    public MuralAvisos criarAviso(MuralAvisosCriacaoDto dto) {
        Barbeiro barbeiro = barbeiroRepository.findById(dto.getBarbeiroId())
                .orElseThrow(() -> new NaoEncontradoException("Barbeiro"));

        MuralAvisos muralAvisos = MuralAvisosMapper.toEntity(dto, barbeiro);
        muralAvisos.setData(LocalDateTime.now());

        return muralAvisosRepository.save(muralAvisos);
    }

    public MuralAvisos buscarPorId(Long id) {
        return muralAvisosRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("MuralAvisos"));
    }

    public List<MuralAvisos> listarTodos() {
        return muralAvisosRepository.findAll();
    }

    @Transactional
    public MuralAvisos atualizarAviso(Long id, MuralAvisosAtualizacaoDto dto) {
        MuralAvisos avisoExistente = muralAvisosRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("MuralAvisos"));

        MuralAvisosMapper.toEntity(dto, avisoExistente);

        return muralAvisosRepository.save(avisoExistente);
    }

    @Transactional
    public void deletarAviso(Long id) {
        MuralAvisos aviso = muralAvisosRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("MuralAvisos"));
        muralAvisosRepository.delete(aviso);
    }

    public List<MuralAvisos> listarPorBarbeiro(Long barbeiroId) {
        Barbeiro barbeiro = barbeiroRepository.findById(barbeiroId)
                .orElseThrow(() -> new NaoEncontradoException("Barbeiro"));

        return muralAvisosRepository.findByBarbeiroId(barbeiro.getId());
    }
}
