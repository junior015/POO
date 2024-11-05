package Entities;
import javax.persistence.*;

@Entity
public class Promo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Double valor;
    private String codigo;
    private Boolean validado;

}
