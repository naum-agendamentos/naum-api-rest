package school.sptech.naumspringapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.naumspringapi.entity.Endereco;
import school.sptech.naumspringapi.entity.Barbearia;
import school.sptech.naumspringapi.mapper.BarbeariaMapper;
import school.sptech.naumspringapi.exception.ConflitoException;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.naumspringapi.repository.BarbeariaRepository;
import school.sptech.naumspringapi.exception.NaoEncontradoException;
import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaCriacaoDto;
import school.sptech.naumspringapi.exception.RequisicaoInvalidaException;
import school.sptech.naumspringapi.exception.EntidadeImprocessavelException;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BarbeariaService {

    private final EnderecoService enderecoService;
    private final BarbeariaRepository barbeariaRepository;

    @Transactional
    public Barbearia criarBarbearia(BarbeariaCriacaoDto barbeariaDto) {
        if (Objects.isNull(barbeariaDto)) throw new EntidadeImprocessavelException("Barbearia");
        Barbearia barbearia = BarbeariaMapper.toEntity(barbeariaDto);
        barbearia.setAtiva(true);
        if (Objects.nonNull(barbeariaDto.getEndereco())) {
            Endereco endereco = enderecoService.cadastrarEndereco(barbeariaDto);
            barbearia.setEndereco(endereco);
        } else throw new RequisicaoInvalidaException("Barbearia");
        return barbeariaRepository.save(barbearia);
    }

    public List<Barbearia> listarBarbearia() {
        return barbeariaRepository.findByAtivaTrue();
    }

    @Transactional
    public Barbearia atualizarBarbearia(Long id, BarbeariaCriacaoDto barbeariaAtualizada) {
        if (Objects.isNull(barbeariaAtualizada) || Objects.isNull(id)) throw new EntidadeImprocessavelException("Barbearia");
        Barbearia barbeariaAtual = barbeariaRepository.findById(id).orElseThrow(() -> new NaoEncontradoException("Barbearia"));

        // Atualiza os dados da barbearia
        barbeariaAtual.setNome(barbeariaAtualizada.getNome());
        barbeariaAtual.setLinkBarbearia(barbeariaAtualizada.getLinkBarbearia());
        barbeariaAtual.setFotoBarbearia(barbeariaAtualizada.getFotoBarbearia());

        if (!Objects.isNull(barbeariaAtualizada.getEndereco())) {
            Endereco endereco = enderecoService.atualizarEndereco(id, barbeariaAtualizada);
            barbeariaAtual.setEndereco(endereco);
        }

        // Salva a barbearia, que deve manter a referência para o mesmo endereço já existente
        return barbeariaRepository.save(barbeariaAtual);
    }

    public Barbearia desativarBarbearia(Long id){
            if (Objects.isNull(id)) throw new EntidadeImprocessavelException("Barbearia");
            Barbearia barbearia = barbeariaRepository.findById(id).orElseThrow(() -> new NaoEncontradoException("Barbearia"));
            barbearia.setAtiva(false);
            return barbeariaRepository.save(barbearia);
    }

    public Barbearia buscarPorId(Long idBarbearia) {
        if (Objects.isNull(idBarbearia)) throw new EntidadeImprocessavelException("Barbearia");
        return barbeariaRepository.findById(idBarbearia).orElseThrow(() -> new NaoEncontradoException("Barbearia"));
    }
}
