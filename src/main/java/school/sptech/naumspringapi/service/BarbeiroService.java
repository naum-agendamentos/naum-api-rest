package school.sptech.naumspringapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.naumspringapi.dto.barbeiroDto.BarbeiroAtualizacaoDto;
import school.sptech.naumspringapi.entity.Barbeiro;
import school.sptech.naumspringapi.entity.Barbearia;
import school.sptech.naumspringapi.entity.Semana;
import school.sptech.naumspringapi.mapper.BarbeiroMapper;
import school.sptech.naumspringapi.domain.usuario.Usuario;
import school.sptech.naumspringapi.domain.usuario.UsuarioTipo;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.naumspringapi.mapper.SemanaMapper;
import school.sptech.naumspringapi.repository.BarbeiroRepository;
import school.sptech.naumspringapi.repository.SemanaRepository;
import school.sptech.naumspringapi.service.usuario.UsuarioService;
import school.sptech.naumspringapi.exception.IndisponivelException;
import school.sptech.naumspringapi.exception.NaoEncontradoException;
import school.sptech.naumspringapi.dto.barbeiroDto.BarbeiroCriacaoDto;
import org.springframework.security.core.context.SecurityContextHolder;
import school.sptech.naumspringapi.service.usuario.dto.UsuarioCriacaoDto;
import school.sptech.naumspringapi.exception.RequisicaoInvalidaException;
import school.sptech.naumspringapi.exception.EntidadeImprocessavelException;
import school.sptech.naumspringapi.service.usuario.autenticacao.dto.UsuarioDetalhesDto;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BarbeiroService {

    private final UsuarioService usuarioService;
    private final BarbeiroRepository barbeiroRepository;
    private final SemanaRepository semanaRepository;
    private final BarbeariaService barbeariaService;


    // MÉTODO CRIAÇÃO DO BARBEIRO
    @Transactional
    public Barbeiro criarBarbeiro(BarbeiroCriacaoDto barbeiroCriacaoDto){
        if (Objects.isNull(barbeiroCriacaoDto)) throw new EntidadeImprocessavelException("Barbeiro");
        // OBTENDO O USUÁRIO LOGADO PARA PASSAR A MESMA BARBEARIA DE QUEM TA LOGADO PARA O NOVO BARBEIRO
        UsuarioDetalhesDto usuarioLogado = (UsuarioDetalhesDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Barbeiro barbeiroLogado = login(usuarioLogado.getId());

        if (Objects.isNull(barbeiroLogado) && Objects.isNull(barbeiroLogado.getBarbearia())) throw new NaoEncontradoException("Barbeiro");

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
        Barbeiro barbeiro = barbeiroRepository.findByIdAndBarbeiroAtivoTrue(id);
        if (Objects.isNull(barbeiro)) throw new NaoEncontradoException("Barbeiro");
        return barbeiro;
    }

    public List<Barbeiro> listaBarbeirosPorBarbearia() {
        UsuarioDetalhesDto usuarioLogado = (UsuarioDetalhesDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Barbearia barbearia = login(usuarioLogado.getId()).getBarbearia();
        return barbeiroRepository.findByBarbeariaIdAndBarbeiroAtivoTrue(barbearia.getId());
    }

    public List<Barbeiro> listaBarbeirosPorBarbearia(Long idBarbearia) {
        return barbeiroRepository.findByBarbeariaIdAndBarbeiroAtivoTrue(idBarbearia);
    }

    public List<Barbeiro> listarBarbeiros() {
        return barbeiroRepository.findByBarbeiroAtivoTrue();
    }

    // MÉTODO DE ATUALIZAR BARBEIRO, SOMENTE SE ELE ESTIVER ATIVO
    @Transactional
    public Barbeiro atualizarBarbeiro(Long id, BarbeiroCriacaoDto barbeiroAtualizado) {
        Barbeiro barbeiroAtual = barbeiroRepository.findById(id).orElseThrow(() -> new NaoEncontradoException("Barbeiro"));
        if (!verificarBarbeiroAtivo(barbeiroAtual)) throw new IndisponivelException("Usuário");

        UsuarioDetalhesDto usuarioLogado = (UsuarioDetalhesDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Barbeiro barbeiroLogado = login(usuarioLogado.getId());

        if (Objects.isNull(barbeiroLogado) && Objects.isNull(barbeiroLogado.getBarbearia())) throw new NaoEncontradoException("Barbeiro");

        Barbeiro barbeiroBeforeUpdate = buscarPorId(id);
        Barbeiro entityToSave = BarbeiroMapper.toEntity(barbeiroAtualizado, barbeiroLogado.getBarbearia());

        entityToSave.setId(barbeiroAtual.getId());

        UsuarioCriacaoDto usuarioCriacaoDto = new UsuarioCriacaoDto();
        usuarioCriacaoDto.setNome(barbeiroAtualizado.getNome());
        usuarioCriacaoDto.setEmail(barbeiroAtualizado.getEmail());
        usuarioCriacaoDto.setSenha(barbeiroAtualizado.getSenha());
        usuarioCriacaoDto.setTipo(UsuarioTipo.BARBEIRO);

        Usuario usuarioAtualizado = usuarioService.atualizar(barbeiroAtual.getId(), usuarioCriacaoDto);

        if (Objects.isNull(usuarioAtualizado)) throw new RequisicaoInvalidaException("BarbeiroService");

        entityToSave.setUsuario(usuarioAtualizado);
        entityToSave.setBarbeiroAtivo(true);
        entityToSave.setAgendamentos(barbeiroAtual.getAgendamentos());
        if(barbeiroAtualizado.getSemana() != null){
            if(barbeiroBeforeUpdate.getSemana() != null){ //caso o barbeiro ja tenha uma semana só atualiza
                Semana semanaSalva = SemanaMapper.toEntity(barbeiroAtualizado.getSemana());
                semanaSalva.setId(barbeiroBeforeUpdate.getSemana().getId());
                semanaSalva = semanaRepository.save(semanaSalva);
                entityToSave.setSemana(semanaSalva);
            }
            else{ //caso o barbeiro não tenha uma semana cria uma nova
                Semana semanaSalva = semanaRepository.save(SemanaMapper.toEntity(barbeiroAtualizado.getSemana()));
                entityToSave.setSemana(semanaSalva);
            }
        }
        else{
            if(barbeiroBeforeUpdate.getSemana() != null){
                entityToSave.setSemana(barbeiroBeforeUpdate.getSemana());
            }
        }


        return barbeiroRepository.save(entityToSave);
    }

    //BUSCAR BARBEIRO POR IDUSUARIO
    public Barbeiro buscarBarbPorUsuario(Long idUsuario) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(idUsuario);
        return barbeiroRepository.findByUsuario(usuario);
    }

    // MÉTODO DE DESATIVAR BARBEIRO
    @Transactional
    public Barbeiro desativarBarbeiro(Long id){
        if (Objects.isNull(id)) throw new EntidadeImprocessavelException("idBarbeiro");
        Barbeiro barbeiroAtual = barbeiroRepository.findById(id).orElseThrow(() -> new NaoEncontradoException("Barbeiro"));
        barbeiroAtual.setBarbeiroAtivo(false);
        return barbeiroRepository.save(barbeiroAtual);
    }

    public Barbeiro login(Long usuarioId) {
        return barbeiroRepository.findByUsuarioId(usuarioId)
                .orElse(null);
    }

    public boolean verificarBarbeiroAtivo(Barbeiro barbeiro){
        return barbeiro.isBarbeiroAtivo();
    }

    @Transactional
    public Barbeiro reativarBarbeiro(Long id){
        if (Objects.isNull(id)) throw new EntidadeImprocessavelException("idBarbeiro");
        Barbeiro barbeiroAtual = barbeiroRepository.findById(id).orElseThrow(() -> new NaoEncontradoException("Barbeiro"));
        if(barbeiroAtual.isBarbeiroAtivo()) throw new RequisicaoInvalidaException("Barbeiro");
        barbeiroAtual.setBarbeiroAtivo(true);
        return barbeiroRepository.save(barbeiroAtual);
    }


    public List<Barbeiro> listaBarbeirosPorBarbeariaCliente(Long id) {
        Barbearia barbearia = barbeariaService.buscarPorId(id);
        return barbeiroRepository.findByBarbeariaIdAndBarbeiroAtivoTrue(barbearia.getId());
    }
}
