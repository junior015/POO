import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date data;

    private Double valor;
    private String tipoEntrega;
    private String observacoes;
    private Double troco;

    @ManyToOne
    @JoinColumn(name = "restaurante_id")
    private Restaurante restaurante;

    @ManyToOne
    @JoinColumn(name = "forma_pagamento_id")
    private FormaDePagamento formaDePagamento;

}
