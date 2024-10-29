import javax.persistence.*;
import java.util.List;

@Entity
public class Restaurante {

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
