package school.sptech.naumspringapi.service.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.naumspringapi.api.configuration.security.jwt.GerenciadorTokenJwt;
import school.sptech.naumspringapi.domain.usuario.Usuario;
import school.sptech.naumspringapi.domain.usuario.UsuarioTipo;
import school.sptech.naumspringapi.domain.usuario.repository.UsuarioRepository;
import school.sptech.naumspringapi.entity.Barbeiro;
import school.sptech.naumspringapi.entity.Cliente;
import school.sptech.naumspringapi.entity.LoginAdm;
import school.sptech.naumspringapi.exception.NaoEncontradoException;
import school.sptech.naumspringapi.repository.BarbeiroRepository;
import school.sptech.naumspringapi.repository.ClienteRepository;
import school.sptech.naumspringapi.repository.LoginAdmRepository;
import school.sptech.naumspringapi.service.BarbeiroService;
import school.sptech.naumspringapi.service.ClienteService;
import school.sptech.naumspringapi.service.usuario.autenticacao.dto.UsuarioDetalhesDto;
import school.sptech.naumspringapi.service.usuario.autenticacao.dto.UsuarioLoginDto;
import school.sptech.naumspringapi.service.usuario.autenticacao.dto.UsuarioTokenDto;
import school.sptech.naumspringapi.service.usuario.dto.UsuarioCriacaoDto;
import school.sptech.naumspringapi.service.usuario.dto.UsuarioMapper;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BarbeiroRepository barbeiroRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private LoginAdmRepository loginAdmRepository;

    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;

    @Autowired
    private AuthenticationManager authenticationManager;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario criar(UsuarioCriacaoDto usuarioCriacaoDto){
        final Usuario novoUsuario = UsuarioMapper.of(usuarioCriacaoDto);

        Optional<Usuario> usuariosDoBanco = usuarioRepository.findByEmail(usuarioCriacaoDto.getEmail());

        if(usuariosDoBanco.isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe um usuário utilizando esse email, por favor tente outro email", null);
        }

        String senhaCriptografada = passwordEncoder.encode(novoUsuario.getSenha());
        novoUsuario.setSenha(senhaCriptografada);

        return this.usuarioRepository.save(novoUsuario);
    }


    // MÉTODO DE ATUALIZAÇÃO DO LOGIN
    public Usuario atualizar(Long usuarioId, UsuarioCriacaoDto usuarioCriacaoDto){
        // O Long usuarioId é o id passado pelo endpoint serve para o cliente e para o barbeiro
        if(usuarioCriacaoDto.getTipo() == UsuarioTipo.BARBEIRO){
            Optional<Barbeiro> barbeiroOpt = barbeiroRepository.findById(usuarioId);

            Optional<Usuario> usuarioOpt = usuarioRepository.findById(barbeiroOpt.get().getUsuario().getId());
            if(usuarioOpt.isEmpty()){
                return null;
            }

            // VALIDA SE O NOVO EMAIL OU SENHA PASSADA É A MESMA QUE ELE JÁ TINHA, CASO NÃO, VALIDA SE JÁ EXISTE UM USUÁRIO COM ESSE EMAIL OU SENHA
            if(!(usuarioCriacaoDto.getEmail().equals(usuarioOpt.get().getEmail()))){
                Optional<Usuario> usuariosDoBanco = usuarioRepository.findByEmail(usuarioCriacaoDto.getEmail());

                if(usuariosDoBanco.isPresent()){
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe um usuário utilizando esse email, por favor tente outro email", null);
                }
            }

            final Usuario novoUsuario = UsuarioMapper.of(usuarioCriacaoDto);
            novoUsuario.setId(usuarioOpt.get().getId());

            String senhaCriptografada = passwordEncoder.encode(novoUsuario.getSenha());
            novoUsuario.setSenha(senhaCriptografada);

            return this.usuarioRepository.save(novoUsuario);

        } else if (usuarioCriacaoDto.getTipo() == UsuarioTipo.CLIENTE){
            Optional<Cliente> clienteOpt = clienteRepository.findById(usuarioId);

            Optional<Usuario> usuarioOpt = usuarioRepository.findById(clienteOpt.get().getUsuario().getId());
            if(usuarioOpt.isEmpty()){
                return null;
            }

            if(!(usuarioCriacaoDto.getEmail().equals(usuarioOpt.get().getEmail()))){
                Optional<Usuario> usuariosDoBanco = usuarioRepository.findByEmail(usuarioCriacaoDto.getEmail());

                if(usuariosDoBanco.isPresent()){
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe um usuário utilizando esse email, por favor tente outro email", null);
                }
            }

            final Usuario novoUsuario = UsuarioMapper.of(usuarioCriacaoDto);
            novoUsuario.setId(usuarioOpt.get().getId());

            String senhaCriptografada = passwordEncoder.encode(novoUsuario.getSenha());
            novoUsuario.setSenha(senhaCriptografada);

            return this.usuarioRepository.save(novoUsuario);


        } else if (usuarioCriacaoDto.getTipo() == UsuarioTipo.ADMIN){
            Optional<LoginAdm> loginAdmOpt = loginAdmRepository.findById(Long.valueOf(1));

            Optional<Usuario> usuarioOpt = usuarioRepository.findById(loginAdmOpt.get().getUsuario().getId());
            if(usuarioOpt.isEmpty()){
                return null;
            }

            final Usuario novoUsuario = UsuarioMapper.of(usuarioCriacaoDto);
            novoUsuario.setId(usuarioOpt.get().getId());

            String senhaCriptografada = passwordEncoder.encode(novoUsuario.getSenha());
            novoUsuario.setSenha(senhaCriptografada);

            return this.usuarioRepository.save(novoUsuario);
        }

        return null;
    }



    public UsuarioTokenDto autenticar(UsuarioLoginDto usuarioLoginDto) {
        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuarioLoginDto.getEmail(), usuarioLoginDto.getSenha());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Usuario usuarioAutenticado =
            usuarioRepository.findByEmail(usuarioLoginDto.getEmail())
                    .orElseThrow(
                        () -> new ResponseStatusException(404, "Email do usuário não cadastrado", null)
                    );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return UsuarioMapper.of(usuarioAutenticado, token);
    }

    public Usuario buscarUsuarioPorId(Long idUsuario) {
        return this.usuarioRepository.findById(idUsuario).orElseThrow(
                () -> new NaoEncontradoException("usuario"));
    }
}