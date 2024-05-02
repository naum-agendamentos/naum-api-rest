package school.sptech.naumspringapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import school.sptech.naumspringapi.entity.Barbeiro;
import school.sptech.naumspringapi.entity.Barbearia;
import school.sptech.naumspringapi.mapper.BarbeiroMapper;
import school.sptech.naumspringapi.domain.usuario.Usuario;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.naumspringapi.domain.usuario.UsuarioTipo;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.naumspringapi.repository.BarbeiroRepository;
import school.sptech.naumspringapi.service.usuario.UsuarioService;
import school.sptech.naumspringapi.dto.barbeiroDto.BarbeiroCriacaoDto;
import school.sptech.naumspringapi.dto.barbeiroDto.BarbeiroListagemDto;
import org.springframework.security.core.context.SecurityContextHolder;
import school.sptech.naumspringapi.service.usuario.dto.UsuarioCriacaoDto;
import school.sptech.naumspringapi.service.usuario.autenticacao.dto.UsuarioDetalhesDto;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BarbeiroService {

    private final BarbeiroRepository barbeiroRepository;
    private final UsuarioService usuarioService;


    // MÉTODO CRIAÇÃO DO BARBEIRO
    @Transactional
    public Barbeiro criarBarbeiro(BarbeiroCriacaoDto barbeiroCriacaoDto){

        // OBTENDO O USUÁRIO LOGADO PARA PASSAR A MESMA BARBEARIA DE QUEM TA LOGADO PARA O NOVO BARBEIRO
        UsuarioDetalhesDto usuarioLogado = (UsuarioDetalhesDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Barbeiro barbeiroLogado = login(usuarioLogado.getId());

        if (barbeiroLogado == null && barbeiroLogado.getBarbearia() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        // CONVERTE PARA ENTIDADE A DTO PASSANDO A MESMA BARBEARIA DE QUEM ESTA LOGADO
        Barbeiro entity = BarbeiroMapper.toEntity(barbeiroCriacaoDto, barbeiroLogado.getBarbearia());

        // CRIANDO UMA INSTÂNCIA DE USUARIO PARA PEGAR O MESMO NOME, EMAIL E SENHA PASSADO PELA DTO E CRIANDO UM USUÁRIO
        UsuarioCriacaoDto usuarioCriacaoDto = new UsuarioCriacaoDto();
        usuarioCriacaoDto.setNome(barbeiroCriacaoDto.getNome());
        usuarioCriacaoDto.setEmail(barbeiroCriacaoDto.getEmail());
        usuarioCriacaoDto.setSenha(barbeiroCriacaoDto.getSenha());
        usuarioCriacaoDto.setTipo(UsuarioTipo.BARBEIRO);
        Usuario usuarioCriado = usuarioService.criar(usuarioCriacaoDto);

        // ALTERANDO O CAMPO DE USUÁRIO DA ENTIDADE PASSANDO O USUARIO CRIADO
        entity.setUsuario(usuarioCriado);
        // SETANDO O BARBEIRO COMO ATIVO
        entity.setBarbeiroAtivo(true);

        return barbeiroRepository.save(entity);
    }

    public Barbeiro buscarPorId(Long id) {
        return barbeiroRepository.findByIdAndBarbeiroAtivoTrue(id);
    }

    public List<Barbeiro> listaBarbeirosPorBarbearia() {
        UsuarioDetalhesDto usuarioLogado = (UsuarioDetalhesDto) SecurityContextHolder.getContext().getAuthentication().getDetails();
        Barbearia barbearia = login(usuarioLogado.getId()).getBarbearia();

        return barbeiroRepository.findByBarbeariaIdAndBarbeiroAtivoTrue(barbearia.getId());
    }

    public List<Barbeiro> listarBarbeiros() {
        List<Barbeiro> barbeiros = barbeiroRepository.findByBarbeiroAtivoTrue();
        if (barbeiros.isEmpty()) return null;
        return barbeiros;
    }


    // MÉTODO DE ATUALIZAR BARBEIRO, SOMENTE SE ELE ESTIVER ATIVO
    @Transactional
    public Barbeiro atualizarBarbeiro(Long id, BarbeiroCriacaoDto barbeiroAtualizado) {
        Optional<Barbeiro> barbeiroAtualOpt = barbeiroRepository.findById(id);

        if (barbeiroAtualOpt.isEmpty()){
            return null;
        }

        if(!verificarBarbeiroAtivo(barbeiroAtualOpt.get())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "O usuário já foi excluído.");
        }

        UsuarioDetalhesDto usuarioLogado = (UsuarioDetalhesDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Barbeiro barbeiroLogado = login(usuarioLogado.getId());

        if (barbeiroLogado == null && barbeiroLogado.getBarbearia() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }


        Barbeiro entity = BarbeiroMapper.toEntity(barbeiroAtualizado, barbeiroLogado.getBarbearia());
        entity.setId(barbeiroAtualOpt.get().getId());

        UsuarioCriacaoDto usuarioCriacaoDto = new UsuarioCriacaoDto();
        usuarioCriacaoDto.setNome(barbeiroAtualizado.getNome());
        usuarioCriacaoDto.setEmail(barbeiroAtualizado.getEmail());
        usuarioCriacaoDto.setSenha(barbeiroAtualizado.getSenha());
        usuarioCriacaoDto.setTipo(UsuarioTipo.BARBEIRO);

        Usuario usuarioAtualizado = usuarioService.atualizar(barbeiroAtualOpt.get().getId(), usuarioCriacaoDto);

        if(usuarioAtualizado == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi achado um login desse barbeiro");
        }

        entity.setUsuario(usuarioAtualizado);
        entity.setBarbeiroAtivo(true);

        return barbeiroRepository.save(entity);
    }


    // MÉTODO DE DESATIVAR BARBEIRO
    @Transactional
    public Barbeiro desativarBarbeiro(Long id){
        Optional<Barbeiro> barbeiroOpt = barbeiroRepository.findById(id);

        if(barbeiroOpt.isEmpty()){
            return null;
        }

        barbeiroOpt.get().setBarbeiroAtivo(false);
        barbeiroRepository.save(barbeiroOpt.get());

        return barbeiroOpt.get();

    }

    public Barbeiro login(Long usuarioId) {
        return barbeiroRepository.findByUsuarioId(usuarioId)
                .orElse(null);
    }

    public boolean verificarBarbeiroAtivo(Barbeiro barbeiro){
        if(!barbeiro.getBarbeiroAtivo()){
            return false;
        }
        return true;
    }

    public BarbeiroListagemDto buscarBarbeiroPorIdDto(Long idBarbeiro) {
        return BarbeiroMapper.toDto(barbeiroRepository.findByIdAndBarbeiroAtivoTrue(idBarbeiro));
    }
}
