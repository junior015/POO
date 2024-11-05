package Entities;
public class StatusEntrega {

    private String idEntrega;
    private String dataEnvio;
    private String statusAtual;
    private String dataEntregaPrevista;

    public StatusEntrega(String idEntrega, String dataEnvio, String statusAtual, String dataEntregaPrevista) {
        this.idEntrega = idEntrega;
        this.dataEnvio = dataEnvio;
        this.statusAtual = statusAtual;
        this.dataEntregaPrevista = dataEntregaPrevista;
    }

    public String getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(String idEntrega) {
        this.idEntrega = idEntrega;
    }

    public String getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(String dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public String getStatusAtual() {
        return statusAtual;
    }

    public void setStatusAtual(String statusAtual) {
        this.statusAtual = statusAtual;
    }

    public String getDataEntregaPrevista() {
        return dataEntregaPrevista;
    }

    public void setDataEntregaPrevista(String dataEntregaPrevista) {
        this.dataEntregaPrevista = dataEntregaPrevista;
    }

    public void atualizarStatus(String novoStatus) {
        this.statusAtual = novoStatus;
    }

    public void exibirInformacoes() {
        System.out.println("ID da Entrega: " + idEntrega);
        System.out.println("Data de Envio: " + dataEnvio);
        System.out.println("Status Atual: " + statusAtual);
        System.out.println("Data de Entrega Prevista: " + dataEntregaPrevista);
    }

    public static void main(String[] args) {
        StatusEntrega entrega = new StatusEntrega("12345", "2024-11-01", "Em Trânsito", "2024-11-10");
        entrega.exibirInformacoes();

        entrega.atualizarStatus("Entregue");
        System.out.println("\nStatus atualizado:");
        entrega.exibirInformacoes();
    }
}
