package Entities;

public class Endereco extends Base {
    private String rua;
    private String numero;
    private String cidade;
    private String estado;
    private String ponto_de_referencia;
    private int cep;
    private String complemento;
    private String tipo_de_endereco;
    private List<Restaurant> restaurant = new ArrayList<>();
    private List<Order> order = new ArrayList<>();

    public Endereco(String rua, String numero, String cidade, String estado, String ponto_de_referencia, int cep,
                   String complemento, String tipo_de_endereco, List<Restaurant> restaurant, List<Order> order) {
        this.rua = rua;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
        this.ponto_de_referencia = ponto_de_referencia;
        this.cep = cep;
        this.complemento = complemento;
        this.tipo_de_endereco = tipo_de_endereco;
        this.restaurant = restaurant;
        this.order = order;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumber(String numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPonto_de_referencia() {
        return ponto_de_referencia;
    }

    public void setPonto_de_referencia(String ponto_de_referencia) {
        this.ponto_de_referencia = ponto_de_referencia;
    }

    public int getcep() {
        return cep;
    }

    public void setcep(int cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getTipo_de_endereco() {
        return tipo_de_endereco;
    }

    public void setTipo_de_endereco(String tipo_de_endereco) {
        this.tipo_de_endereco = tipo_de_endereco;
    }

    public List<Restaurant> getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(List<Restaurant> restaurant) {
        this.restaurant = restaurant;
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }
}