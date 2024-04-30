package school.sptech.naumspringapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaAtualizacaoDto;
import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaCriacaoDto;
import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaListagemDto;
import school.sptech.naumspringapi.dto.enderecoDto.EnderecoCriacaoDto;
import school.sptech.naumspringapi.dto.enderecoDto.EnderecoListagemDto;
import school.sptech.naumspringapi.entity.Barbearia;
import school.sptech.naumspringapi.entity.Endereco;
import school.sptech.naumspringapi.mapper.BarbeariaMapper;
import school.sptech.naumspringapi.mapper.EnderecoMapper;
import school.sptech.naumspringapi.repository.BarbeariaRepository;
import school.sptech.naumspringapi.repository.EnderecoRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BarbeariaService {

    private final BarbeariaRepository barbeariaRepository;
    private final EnderecoService enderecoService;

    @Transactional
    public BarbeariaListagemDto criarBarbearia(BarbeariaCriacaoDto barbeariaDto) {
        Barbearia barbearia = BarbeariaMapper.toEntity(barbeariaDto);

        if (barbeariaDto.getEndereco() != null) {
            Endereco endereco = enderecoService.cadastrarEndereco(barbeariaDto);
            barbearia.setEndereco(endereco);
        }

        barbearia.setAtiva(true);
        // Salva a barbearia no banco de dados
        Barbearia barbeariaSalva = barbeariaRepository.save(barbearia);

        return BarbeariaMapper.toDto(barbeariaSalva);
    }

    public List<BarbeariaListagemDto> listarBarbearia() {
        List<Barbearia> barbearias = barbeariaRepository.findByAtivaTrue();
        if (barbearias.isEmpty()) return null;
        return BarbeariaMapper.toDto(barbearias);
    }

    @Transactional
    public BarbeariaListagemDto atualizarBarbearia(Long id, BarbeariaCriacaoDto barbeariaAtualizada) {
        Optional<Barbearia> barbeariaAtualOpt = barbeariaRepository.findById(id);

        if (barbeariaAtualOpt.isEmpty()){
            return null;
        }

        Barbearia barbearia = barbeariaAtualOpt.get();

        // Atualiza os dados da barbearia
        barbearia.setNome(barbeariaAtualizada.getNome());
        barbearia.setLinkBarbearia(barbeariaAtualizada.getLinkBarbearia());
        barbearia.setFotoBarbearia(barbeariaAtualizada.getFotoBarbearia());

        if (barbeariaAtualizada.getEndereco() != null) {
            Endereco endereco = enderecoService.atualizarEndereco(id, barbeariaAtualizada);
            barbearia.setEndereco(endereco);
        }

        // Salva a barbearia, que deve manter a referência para o mesmo endereço já existente
        Barbearia barbeariaSalva = barbeariaRepository.save(barbearia);

        return BarbeariaMapper.toDto(barbeariaSalva);
    }

    public BarbeariaAtualizacaoDto desativarBarbearia(Long id){
        Optional<Barbearia> barbeariaOpt = barbeariaRepository.findById(id);

        if(barbeariaOpt.isEmpty()) return null;

        barbeariaOpt.get().setAtiva(false);
        barbeariaRepository.save(barbeariaOpt.get());

        BarbeariaAtualizacaoDto barbeariaAtualizacaoDto = BarbeariaMapper.toAttDto(barbeariaOpt.get());
        return barbeariaAtualizacaoDto;
    }

    public Barbearia buscarPorId(Long idBarbearia) {
        return barbeariaRepository.findById(idBarbearia).get();
    }
}
