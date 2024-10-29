import javax.persistence.*;

@Entity
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer nota;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    private String atendente;

}
