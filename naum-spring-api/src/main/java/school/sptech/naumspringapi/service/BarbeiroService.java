package school.sptech.naumspringapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import school.sptech.naumspringapi.dto.barbeariaDto.BarbeariaListagemDto;
import school.sptech.naumspringapi.entity.Barbearia;
import school.sptech.naumspringapi.entity.Barbeiro;
import school.sptech.naumspringapi.mapper.BarbeiroMapper;
import school.sptech.naumspringapi.domain.usuario.Usuario;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.naumspringapi.domain.usuario.UsuarioTipo;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.naumspringapi.repository.BarbeiroRepository;
import school.sptech.naumspringapi.repository.BarbeariaRepository;
import school.sptech.naumspringapi.service.usuario.UsuarioService;
import school.sptech.naumspringapi.dto.barbeiroDto.BarbeiroCriacaoDto;
import school.sptech.naumspringapi.dto.barbeiroDto.BarbeiroListagemDto;
import org.springframework.security.core.context.SecurityContextHolder;
import school.sptech.naumspringapi.service.usuario.dto.UsuarioCriacaoDto;
import school.sptech.naumspringapi.dto.barbeiroDto.BarbeiroDesativacaoDto;
import school.sptech.naumspringapi.service.usuario.autenticacao.dto.UsuarioDetalhesDto;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BarbeiroService {

    private final BarbeiroRepository barbeiroRepository;
    private final UsuarioService usuarioService;
    private final BarbeariaRepository barbeariaRepository;


    // MÉTODO CRIAÇÃO DO BARBEIRO
    @Transactional
    public BarbeiroListagemDto criarBarbeiro(BarbeiroCriacaoDto barbeiroCriacaoDto){
        try {
            if (Objects.isNull(barbeiroCriacaoDto)) throw new BadRequestException();
            // OBTENDO O USUÁRIO LOGADO PARA PASSAR A MESMA BARBEARIA DE QUEM TA LOGADO PARA O NOVO BARBEIRO
            UsuarioDetalhesDto usuarioLogado = (UsuarioDetalhesDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Barbeiro barbeiroLogado = login(usuarioLogado.getId());

            if (Objects.isNull(barbeiroLogado) && Objects.isNull(barbeiroLogado.getBarbearia())) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

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

            return BarbeiroMapper.toDto(barbeiroRepository.save(entity));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entidade não encontrada", e);
        } catch (BadRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requisição inválida", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao cadastrar barbeiro", e);
        }
    }

    public Barbeiro buscarPorId(Long id) {
        Barbeiro barbeiro = barbeiroRepository.findByIdAndBarbeiroAtivoTrue(id);
        if (Objects.isNull(barbeiro)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return barbeiro;
    }

    public List<BarbeiroListagemDto> listaBarbeirosPorBarbearia() {
        try {
        UsuarioDetalhesDto usuarioLogado = (UsuarioDetalhesDto) SecurityContextHolder.getContext().getAuthentication().getDetails();
        Barbearia barbearia = login(usuarioLogado.getId()).getBarbearia();

        return BarbeiroMapper.toDto(barbeiroRepository.findByBarbeariaIdAndBarbeiroAtivoTrue(barbearia.getId()));

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entidade não encontrada", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao listar barbeiros", e);
        }
    }

    public List<BarbeiroListagemDto> listarBarbeiros() {
        try {
            List<Barbeiro> barbeiros = barbeiroRepository.findByBarbeiroAtivoTrue();
            if (barbeiros.isEmpty()) return null;
            return BarbeiroMapper.toDto(barbeiros);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entidade não encontrada", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao listar avaliação", e);
        }
    }


    // MÉTODO DE ATUALIZAR BARBEIRO, SOMENTE SE ELE ESTIVER ATIVO
    @Transactional
    public BarbeiroListagemDto atualizarBarbeiro(Long id, BarbeiroCriacaoDto barbeiroAtualizado) {
        try {
            Barbeiro barbeiroAtual = barbeiroRepository.findById(id).orElse(null);
            if (Objects.isNull(barbeiroAtual)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            if (!verificarBarbeiroAtivo(barbeiroAtual)) throw new ResponseStatusException(HttpStatus.FORBIDDEN, "O usuário já foi excluído.");

            UsuarioDetalhesDto usuarioLogado = (UsuarioDetalhesDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Barbeiro barbeiroLogado = login(usuarioLogado.getId());

            if (Objects.isNull(barbeiroLogado) && Objects.isNull(barbeiroLogado.getBarbearia())) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

            Barbeiro entity = BarbeiroMapper.toEntity(barbeiroAtualizado, barbeiroLogado.getBarbearia());
            entity.setId(barbeiroAtual.getId());

            UsuarioCriacaoDto usuarioCriacaoDto = new UsuarioCriacaoDto();
            usuarioCriacaoDto.setNome(barbeiroAtualizado.getNome());
            usuarioCriacaoDto.setEmail(barbeiroAtualizado.getEmail());
            usuarioCriacaoDto.setSenha(barbeiroAtualizado.getSenha());
            usuarioCriacaoDto.setTipo(UsuarioTipo.BARBEIRO);

            Usuario usuarioAtualizado = usuarioService.atualizar(barbeiroAtual.getId(), usuarioCriacaoDto);

            if (Objects.isNull(usuarioAtualizado)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi achado um login desse barbeiro");

            entity.setUsuario(usuarioAtualizado);
            entity.setBarbeiroAtivo(true);

            return BarbeiroMapper.toDto(barbeiroRepository.save(entity));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entidade não encontrada", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao listar avaliação", e);
        }
    }

    // MÉTODO DE DESATIVAR BARBEIRO
    @Transactional
    public BarbeiroDesativacaoDto desativarBarbeiro(Long id){
        try {
            Barbeiro barbeiroAtual = barbeiroRepository.findById(id).orElse(null);
            if (Objects.isNull(barbeiroAtual)) throw new BadRequestException();
            barbeiroAtual.setBarbeiroAtivo(false);
            return BarbeiroMapper.toDtoDesativacao(barbeiroRepository.save(barbeiroAtual));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entidade não encontrada", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao listar avaliação", e);
        }
    }

    public Barbeiro login(Long usuarioId) {
        return barbeiroRepository.findByUsuarioId(usuarioId)
                .orElse(null);
    }

    public boolean verificarBarbeiroAtivo(Barbeiro barbeiro){
        return barbeiro.getBarbeiroAtivo();
    }

    public BarbeiroListagemDto buscarBarbeiroPorIdDto(Long idBarbeiro) {
        return BarbeiroMapper.toDto(barbeiroRepository.findByIdAndBarbeiroAtivoTrue(idBarbeiro));
    }
}
