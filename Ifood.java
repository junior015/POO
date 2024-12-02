import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Produto {
    String nome;
    double preco;

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }
}

class Restaurante {
    String nome;
    List<Produto> produtos;

    public Restaurante(String nome) {
        this.nome = nome;
        this.produtos = new ArrayList<>();
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }
}

class Endereco {
    String cep;
    String logradouro;
    String numero;
    String bairro;
    String cidade;
    String estado;

    public Endereco(String cep, String logradouro, String numero, String bairro, String cidade, String estado) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }
}

class Pedido {
    List<Produto> produtos;
    List<Produto> acompanhamentos;
    Endereco endereco;
    String formaPagamento;

    public Pedido() {
        produtos = new ArrayList<>();
        acompanhamentos = new ArrayList<>();
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public void adicionarAcompanhamento(Produto acompanhamento) {
        acompanhamentos.add(acompanhamento);
    }

    public double calcularValorTotal() {
        double total = 0;
        for (Produto produto : produtos) {
            total += produto.preco;
        }
        for (Produto acompanhamento : acompanhamentos) {
            total += acompanhamento.preco;
        }
        return total;
    }
}

class Sistema {
    List<Restaurante> restaurantes;
    List<Pedido> pedidosRealizados;

    public Sistema() {
        restaurantes = new ArrayList<>();
        pedidosRealizados = new ArrayList<>();
    }

    public void listarRestaurantes() {
        System.out.println("\n--- Restaurantes Disponíveis ---");
        for (int i = 0; i < restaurantes.size(); i++) {
            System.out.println((i + 1) + ". " + restaurantes.get(i).nome);
        }
        System.out.println("-------------------------------");
    }

    public Endereco consultarCep(String cep) {
        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

        try {
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                String json = response.body();
                String logradouro = extractValueFromJson(json, "logradouro");
                String bairro = extractValueFromJson(json, "bairro");
                String cidade = extractValueFromJson(json, "localidade");
                String estado = extractValueFromJson(json, "uf");

                // Verifica se o retorno da API é válido
                if (logradouro == null) {
                    System.out.println("CEP não encontrado.");
                    return null;
                }

                // Cria o objeto Endereco a partir do JSON
                return new Endereco(cep, logradouro, "", bairro, cidade, estado);
            } else {
                System.out.println("Erro na consulta ao ViaCEP. Status code: " + response.statusCode());
                return null; // Retorna nulo se houver um erro
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Retorna nulo em caso de exceção
        }
    }

    public static String extractValueFromJson(String json, String key) {
        String searchKey = "\"" + key + "\":";
        int startIndex = json.indexOf(searchKey);
        if (startIndex == -1) {
            return null;
        } else {
            startIndex += searchKey.length();
            char firstChar = json.charAt(startIndex);
            int endIndex;
            if (firstChar == '"') {
                ++startIndex;
                endIndex = json.indexOf("\"", startIndex);
                return json.substring(startIndex, endIndex);
            } else {
                endIndex = json.indexOf(",", startIndex);
                if (endIndex == -1) {
                    endIndex = json.indexOf("}", startIndex);
                }
                return json.substring(startIndex, endIndex).trim();
            }
        }
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        
        Restaurante r1 = new Restaurante("Bombada Estelar");
        r1.adicionarProduto(new Produto ("burguer void", 10.0));
        r1.adicionarProduto(new Produto("aluguel espacial", 15.0));
        
        Restaurante r2 = new Restaurante("KIOSKI DUS CALVO");
        r2.adicionarProduto(new Produto("cabeça redonda", 20.0));
        r2.adicionarProduto(new Produto("bola de bilhar", 25.0));
        
        Restaurante r3 = new Restaurante("brahma desce triangulo");
        r3.adicionarProduto(new Produto("brahma com farofa", 30.0));
        r3.adicionarProduto(new Produto("comercial da brahma", 35.0));
        
        Restaurante r4 = new Restaurante("fiz tudo errado");
        r4.adicionarProduto(new Produto("nada da certo", 40.0));
        r4.adicionarProduto(new Produto("nunca quis estar aqui", 45.0));
        
        restaurantes.add(r1);
        restaurantes.add(r2);
        restaurantes.add(r3);
        restaurantes.add(r4);

        // Fluxo principal
        while (true) {
            System.out.println("\n--- Sistema de Pedidos ---");
            listarRestaurantes();
            System.out.print("Selecione um restaurante (número): ");
            int escolhaRestaurante = scanner.nextInt() - 1;

            if (escolhaRestaurante < 0 || escolhaRestaurante >= restaurantes.size()) {
                System.out.println("Insira uma opção válida!");
                continue;
            }

            Restaurante restauranteSelecionado = restaurantes.get(escolhaRestaurante);

            System.out.println("\n--- Produtos do " + restauranteSelecionado.nome + " ---");
            for (int i = 0; i < restauranteSelecionado.produtos.size(); i++) {
                Produto produto = restauranteSelecionado.produtos.get(i);
                System.out.printf("%d. %s - R$ %.2f\n", (i + 1), produto.nome, produto.preco);
            }
            System.out.println("--------------------------------");

            Pedido pedido = new Pedido();
            boolean adicionarMaisProdutos = true;
            while (adicionarMaisProdutos) {
                System.out.print("Selecione um produto para adicionar ao pedido (número): ");
                int escolhaProduto = scanner.nextInt() - 1;

                if (escolhaProduto < 0 || escolhaProduto >= restauranteSelecionado.produtos.size()) {
                    System.out.println("Insira uma opção válida!");
                    continue;
                }

                pedido.adicionarProduto(restauranteSelecionado.produtos.get(escolhaProduto));

                // Pergunta sobre acompanhamentos
                System.out.print("Deseja acompanhamentos? (sim/não): ");
                String desejaAcompanhamentos = scanner.next();
                if (desejaAcompanhamentos.equalsIgnoreCase("sim")) {
                    boolean adicionarMaisAcompanhamentos = true;
                    while (adicionarMaisAcompanhamentos) {
                        System.out.println("\n--- Acompanhamentos Disponíveis ---");
                        System.out.println("1. Batata frita 350g - R$ 10.00");
                        System.out.println("2. Fatia de bacon - R$ 1.00");
                        System.out.println("3. Carne seca - R$ 3.00");
                        System.out.print("Selecione um acompanhamento para adicionar (número): ");
                        int escolhaAcompanhamento = scanner.nextInt();

                        switch (escolhaAcompanhamento) {
                            case 1:
                                pedido.adicionarAcompanhamento(new Produto("Batata frita 350g", 10.00));
                                break;
                            case 2:
                                pedido.adicionarAcompanhamento(new Produto("Fatia de bacon", 1.00));
                                break;
                            case 3:
                                pedido.adicionarAcompanhamento(new Produto("Carne seca", 3.00));
                                break;
                            default:
                                System.out.println("Acompanhamento inválido. Insira uma opção válida!");
                                continue;
                        }
                        System.out.print("Deseja adicionar mais acompanhamentos? (sim/não): ");
                        adicionarMaisAcompanhamentos = scanner.next().equalsIgnoreCase("sim");
                    }
                }

                // Pergunta se deseja ir para o carrinho ou continuar comprando
                System.out.print("Deseja ir para o carrinho? (sim/não): ");
                String irParaCarrinho = scanner.next();
                if (irParaCarrinho.equalsIgnoreCase("sim")) {
                    break; // Sai do loop para ir para o carrinho
                } else {
                    adicionarMaisProdutos = true; // Continua adicionando produtos
                }
            }

            // Cadastro de endereço
            System.out.print("Digite seu CEP: ");
            String cep = scanner.next();
            Endereco endereco = consultarCep(cep);
            if (endereco == null) {
                System.out.println("Endereço não encontrado. Tente novamente.");
                continue; // Retorna ao início do loop se o endereço não for encontrado
            }
            System.out.print("Digite o número: ");
            String numero = scanner.next();
            endereco.numero = numero; // Atualiza o número no objeto Endereco

            pedido.endereco = endereco;

            // Listar formas de pagamento
            System.out.println("\n--- Formas de Pagamento ---");
            System.out.println("1. Cartão");
            System.out.println("2. Dinheiro");
            System.out.print("Selecione a forma de pagamento (número): ");
            int formaPagamento = scanner.nextInt();

            if (formaPagamento < 1 || formaPagamento > 2) {
                System.out.println("Insira uma opção válida!");
                continue;
            }

            pedido.formaPagamento = (formaPagamento == 1) ? "Cartão" : "Dinheiro";

            // Imprimir confirmações antes da finalização
            System.out.printf("\nValor total: R$ %.2f\n", pedido.calcularValorTotal());
            System.out.println("Endereço fornecido: " + pedido.endereco.logradouro + ", " + pedido.endereco.numero + ", " + pedido.endereco.bairro + ", " + pedido.endereco.cidade + ", " + pedido.endereco.estado);
            System.out.println("Forma de pagamento: " + pedido.formaPagamento);

            // Perguntar se o usuário deseja adicionar mais pedidos
            System.out.print("Deseja seguir para a confirmação? (sim/não): ");
            String adicionarMaisPedidos = scanner.next();
            if (adicionarMaisPedidos.equalsIgnoreCase("não")) {
                continue; // Retorna à tela de seleção de restaurantes
            }

            // Finalizar pedido
            pedidosRealizados.add(pedido);
            System.out.println("\nPedido finalizado!");

            // Atualizar status do pedido
            new Thread(() -> {
                try {
                    System.out.println("Status do pedido: Em produção");
                    Thread.sleep(10000); // Simula tempo de produção
                    System.out.println("Status do pedido: Saiu para entrega");
                    Thread.sleep(6000); // Simula tempo de entrega
                    System.out.println("Status do pedido: Entregue");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

            // Listar pedidos realizados
            System.out.println("\n--- Pedidos Realizados ---");
            for (Pedido p : pedidosRealizados) {
                System.out.printf("Pedido com %d produtos e %d acompanhamentos, Endereço: %s, %s, %s, %s, Forma de pagamento: %s\n", 
                                  p.produtos.size(), p.acompanhamentos.size(), p.endereco.logradouro, p.endereco.numero, p.endereco.bairro, p.endereco.cidade, p.formaPagamento);
            }

            // Perguntar se o usuário deseja fazer outro pedido
            System.out.print("Deseja fazer outro pedido? (sim/não): ");
            String novoPedido = scanner.next();
            if (novoPedido.equalsIgnoreCase("não")) {
                break; // Sai do loop se o usuário não quiser fazer outro pedido
            }
        }

        scanner.close(); // Fecha o scanner ao final do uso
        System.out.println("Obrigado por usar o sistema de pedidos!");
    }

    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        sistema.iniciar();
    }
}