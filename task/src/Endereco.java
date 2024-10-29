import javax.persistence.*;

@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rua;
    private String pontoReferencia;
    private String bairro;
    private String cidade;
    private String cep;

}
