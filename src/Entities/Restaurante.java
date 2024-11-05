package Entities;
import javax.persistence.*;
import java.util.List;

@Entity
public class Restaurante {

    private static final String GenerationType = null;
    
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String endereco;
    private String telefone;
    private String categoria;
    private String horaFuncionamento;
    private Boolean isRetirada;

}
