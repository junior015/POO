import javax.persistence.*;

@Entity
public class PedidoProduto {

    @EmbeddedId
    private PedidoProdutoKey id;

    @ManyToOne
    @MapsId("pedidoId")
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @MapsId("produtoId")
    @JoinColumn(name = "produto_id")
    private Produto produto;

    private Integer quantidade;

}
