import javax.persistence.*;

@Entity
public class Acompanhamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private Double valor;

}
