package school.sptech.naumspringapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import school.sptech.naumspringapi.dto.enderecoDto.EnderecoCriacaoDto;
import school.sptech.naumspringapi.dto.enderecoDto.EnderecoListagemDto;

import java.util.Collection;

@Entity
@Getter
@Setter
public class Barbearia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String linkBarbearia;
    private boolean ativa;
    private String fotoBarbearia;

    @ManyToOne
    private Endereco endereco;


}