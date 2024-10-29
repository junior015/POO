import javax.persistence.*;

@Entity
public class ProdutoAcomp {

    @EmbeddedId
    private ProdutoAcompKey id;

    @ManyToOne
    @MapsId("produtoId")
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @ManyToOne
    @MapsId("acompId")
    @JoinColumn(name = "acomp_id")
    private Acompanhamento acompanhamento;

}
