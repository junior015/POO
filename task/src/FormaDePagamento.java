import javax.persistence.*;

@Entity
public class FormaDePagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoDePagamento;

}
