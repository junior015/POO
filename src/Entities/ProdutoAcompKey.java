package Entities;
import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class ProdutoAcompKey implements Serializable {

    private Long produtoId;
    private Long acompId;

}
