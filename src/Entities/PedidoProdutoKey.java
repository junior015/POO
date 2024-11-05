package Entities;
import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class PedidoProdutoKey implements Serializable {

    private Long pedidoId;
    private Long produtoId;

}
