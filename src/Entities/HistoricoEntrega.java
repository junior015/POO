package Entities;
import javax.persistence.*;
import java.util.Date;

@Entity
public class HistoricoEntrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "status_entrega_id")
    private StatusEntrega statusEntrega;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    private String atendente;

}
