package bd_bloodbank.api.main;

import java.util.List;
import java.util.Scanner;

import bd_bloodbank.api.dao.BolsaSangueDAO;
import bd_bloodbank.api.dao.DoadorDAO;
import bd_bloodbank.api.dao.EstoqueDAO;
import bd_bloodbank.api.dao.GerenteDAO;
import bd_bloodbank.api.dao.HospitalDAO;
import bd_bloodbank.api.dao.SolicitacaoDAO;
import bd_bloodbank.api.domain.BolsaSangue;
import bd_bloodbank.api.domain.Doador;
import bd_bloodbank.api.domain.Estoque;
import bd_bloodbank.api.domain.Gerente;
import bd_bloodbank.api.domain.Hospital;
import bd_bloodbank.api.domain.Solicitacao;

public class App {
    private static final Scanner sc = new Scanner(System.in);

    private static final DoadorDAO doadorDAO = new DoadorDAO();
    private static final HospitalDAO hospitalDAO = new HospitalDAO();
    private static final GerenteDAO gerenteDAO = new GerenteDAO();
    private static final SolicitacaoDAO solicitacaoDAO = new SolicitacaoDAO();
    private static final BolsaSangueDAO bolsaDAO = new BolsaSangueDAO();
    private static final EstoqueDAO estoqueDAO = new EstoqueDAO();

    public static void main(String[] args) {
        int opcao = 0;
        while (opcao != 9) {
            System.out.println("\n=== SISTEMA DE BANCO DE SANGUE ===");
            System.out.println("1 - Menu Doador");
            System.out.println("2 - Menu Hospital");
            System.out.println("3 - Menu Gerente");
            System.out.println("4 - Menu Solicitação");
            System.out.println("5 - Menu Bolsa de Sangue");
            System.out.println("6 - Menu Estoque");
            System.out.println("7 - Registrar Doação");
            System.out.println("8 - Solicitar Bolsas");
            System.out.println("9 - Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt(); sc.nextLine();

            switch (opcao) {
                case 1 -> menuDoador();
                case 2 -> menuHospital();
                case 3 -> menuGerente();
                case 4 -> menuSolicitacao();
                case 5 -> menuBolsaSangue();
                case 6 -> menuEstoque();
                case 7 -> registrarDoacaoExemplo();
                case 8 -> solicitarBolsasExemplo();
                case 9 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    // ========================================================
    // (1) MENU DOADOR
    private static void menuDoador() {
        int op = 0;
        while (op != 5) {
            System.out.println("\n== MENU DOADOR ==");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Buscar por CPF");
            System.out.println("4 - Deletar");
            System.out.println("5 - Voltar");
            System.out.print("Escolha: ");
            op = sc.nextInt(); sc.nextLine();

            switch (op) {
                case 1 -> cadastrarDoador();
                case 2 -> listarDoadores();
                case 3 -> buscarDoador();
                case 4 -> deletarDoador();
                case 5 -> System.out.println("Voltando...");
            }
        }
    }

    private static void cadastrarDoador() {
        System.out.print("CPF: "); String cpf = sc.nextLine();
        System.out.print("Nome: "); String nome = sc.nextLine();
        System.out.print("Idade: "); int idade = sc.nextInt(); sc.nextLine();
        System.out.print("Sexo (M/F): "); String sexo = sc.nextLine();
        System.out.print("Tipo sanguíneo: "); String tipo = sc.nextLine();

        Doador d = new Doador(cpf, nome, idade, sexo, tipo);
        doadorDAO.inserir(d);
        System.out.println("✅ Doador cadastrado!");
    }

    private static void listarDoadores() {
        List<Doador> lista = doadorDAO.listarTodos();
        for (Doador d : lista) {
            System.out.println("CPF: " + d.getCpf()
                    + " | Nome: " + d.getNome()
                    + " | Tipo: " + d.getTipoSanguineo());
        }
    }

    private static void buscarDoador() {
        System.out.print("CPF: "); String cpf = sc.nextLine();
        Doador d = doadorDAO.buscarPorCpf(cpf);
        if (d != null) {
            System.out.println("Nome: " + d.getNome()
                    + ", Tipo: " + d.getTipoSanguineo());
        } else System.out.println("❌ Doador não encontrado!");
    }

    private static void deletarDoador() {
        System.out.print("CPF: "); String cpf = sc.nextLine();
        doadorDAO.deletar(cpf);
        System.out.println("✅ Doador excluído (se existia).");
    }

    // ========================================================
    // (2) MENU HOSPITAL
    private static void menuHospital() {
        int op = 0;
        while (op != 5) {
            System.out.println("\n== MENU HOSPITAL ==");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Buscar por CNPJ");
            System.out.println("4 - Deletar");
            System.out.println("5 - Voltar");
            System.out.print("Escolha: ");
            op = sc.nextInt(); sc.nextLine();

            switch (op) {
                case 1 -> cadastrarHospital();
                case 2 -> listarHospitais();
                case 3 -> buscarHospital();
                case 4 -> deletarHospital();
                case 5 -> System.out.println("Voltando...");
            }
        }
    }

    private static void cadastrarHospital() {
        System.out.print("CNPJ: "); String cnpj = sc.nextLine();
        System.out.print("Nome: "); String nome = sc.nextLine();
        System.out.print("Endereço: "); String endereco = sc.nextLine();

        Hospital h = new Hospital(cnpj, nome, endereco);
        hospitalDAO.inserir(h);
        System.out.println("✅ Hospital cadastrado!");
    }

    private static void listarHospitais() {
        List<Hospital> lista = hospitalDAO.listarTodos();
        for (Hospital h : lista) {
            System.out.println("CNPJ: " + h.getCnpj()
                    + " | Nome: " + h.getNome()
                    + " | Endereço: " + h.getEndereco());
        }
    }

    private static void buscarHospital() {
        System.out.print("CNPJ: "); String cnpj = sc.nextLine();
        Hospital h = hospitalDAO.buscarPorCnpj(cnpj);
        if (h != null) {
            System.out.println("Nome: " + h.getNome()
                    + ", Endereço: " + h.getEndereco());
        } else System.out.println("❌ Hospital não encontrado!");
    }

    private static void deletarHospital() {
        System.out.print("CNPJ: "); String cnpj = sc.nextLine();
        hospitalDAO.deletar(cnpj);
        System.out.println("✅ Hospital excluído (se existia).");
    }

    // ========================================================
    // (3) MENU GERENTE
    private static void menuGerente() {
        int op = 0;
        while (op != 5) {
            System.out.println("\n== MENU GERENTE ==");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Buscar por ID");
            System.out.println("4 - Deletar");
            System.out.println("5 - Voltar");
            System.out.print("Escolha: ");
            op = sc.nextInt(); sc.nextLine();

            switch (op) {
                case 1 -> cadastrarGerente();
                case 2 -> listarGerentes();
                case 3 -> buscarGerente();
                case 4 -> deletarGerente();
                case 5 -> System.out.println("Voltando...");
            }
        }
    }

    private static void cadastrarGerente() {
    System.out.print("Nome: ");
    String nome = sc.nextLine();

    System.out.print("CNPJ do Hospital: ");
    String cnpj = sc.nextLine();

    // Construtor correto: (idGerente, nome, cnpjHospital)
    Gerente g = new Gerente(0, nome, cnpj);

    gerenteDAO.inserir(g);
    System.out.println("✅ Gerente cadastrado!");
}


    private static void listarGerentes() {
        List<Gerente> lista = gerenteDAO.listarTodos();
        for (Gerente g : lista) {
            System.out.println("ID: " + g.getIdGerente()
                    + " | Nome: " + g.getNome()
                    + " | Hospital: " + g.getCnpjHospital());
        }
    }

    private static void buscarGerente() {
        System.out.print("ID: "); int id = sc.nextInt(); sc.nextLine();
        Gerente g = gerenteDAO.buscarPorId(id);
        if (g != null) {
            System.out.println("Nome: " + g.getNome()
                    + ", Hospital: " + g.getCnpjHospital());
        } else System.out.println("❌ Gerente não encontrado!");
    }

    private static void deletarGerente() {
        System.out.print("ID: "); int id = sc.nextInt(); sc.nextLine();
        gerenteDAO.deletar(id);
        System.out.println("✅ Gerente excluído (se existia).");
    }

    // ========================================================
    // (4) MENU SOLICITAÇÃO
    private static void menuSolicitacao() {
        int op = 0;
        while (op != 5) {
            System.out.println("\n== MENU SOLICITAÇÃO ==");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Buscar por ID");
            System.out.println("4 - Deletar");
            System.out.println("5 - Voltar");
            System.out.print("Escolha: ");
            op = sc.nextInt(); sc.nextLine();

            switch (op) {
                case 1 -> cadastrarSolicitacao();
                case 2 -> listarSolicitacoes();
                case 3 -> buscarSolicitacao();
                case 4 -> deletarSolicitacao();
                case 5 -> System.out.println("Voltando...");
            }
        }
    }

    private static void cadastrarSolicitacao() {
    System.out.print("Tipo sanguíneo: "); String tipo = sc.nextLine();
    System.out.print("Qtd bolsas: "); int qtd = sc.nextInt(); sc.nextLine();
    System.out.print("ID Gerente: "); int idG = sc.nextInt(); sc.nextLine();
    System.out.print("CNPJ Hospital: "); String cnpj = sc.nextLine();

    // Verifica estoque antes
    Estoque e = estoqueDAO.buscarPorTipo(tipo);
    if (e == null || e.getQtdDisponivel() < qtd) {
        System.out.println("❌ Estoque insuficiente!");
        return;
    }

    // Dá baixa no estoque
    e.setQtdDisponivel(e.getQtdDisponivel() - qtd);
    estoqueDAO.atualizar(e);

    // Registra a solicitação
    Solicitacao s = new Solicitacao(0, tipo, qtd, idG, cnpj);
    solicitacaoDAO.inserir(s);
    System.out.println("✅ Solicitação registrada e estoque atualizado!");
}

    private static void listarSolicitacoes() {
    List<Solicitacao> lista = solicitacaoDAO.listarTodos();
    for (Solicitacao s : lista) {
        System.out.println("ID: " + s.getIdSolicitacao()
                + " | Tipo: " + s.getTipoSanguineo()
                + " | Qtd: " + s.getQtdBolsasSolicitadas()
                + " | Gerente: " + s.getIdGerente()
                + " | Hospital: " + s.getCnpjHospital());
    }
}

    private static void buscarSolicitacao() {
        System.out.print("ID: "); int id = sc.nextInt(); sc.nextLine();
        Solicitacao s = solicitacaoDAO.buscarPorId(id);
        if (s != null) {
            System.out.println("Tipo: " + s.getTipoSanguineo()
                    + ", Qtd: " + s.getQtdBolsasSolicitadas());
        } else System.out.println("❌ Solicitação não encontrada!");
    }

    private static void deletarSolicitacao() {
        System.out.print("ID: "); int id = sc.nextInt(); sc.nextLine();
        solicitacaoDAO.deletar(id);
        System.out.println("✅ Solicitação excluída (se existia).");
    }

    // ========================================================
    // (5) MENU BOLSA DE SANGUE
    private static void menuBolsaSangue() {
        int op = 0;
        while (op != 4) {
            System.out.println("\n== MENU BOLSA DE SANGUE ==");
            System.out.println("1 - Listar Bolsas");
            System.out.println("2 - Buscar por ID");
            System.out.println("3 - Deletar Bolsa");
            System.out.println("4 - Voltar");
            System.out.print("Escolha: ");
            op = sc.nextInt(); sc.nextLine();

            switch (op) {
                case 1 -> listarBolsas();
                case 2 -> buscarBolsa();
                case 3 -> deletarBolsa();
                case 4 -> System.out.println("Voltando...");
            }
        }
    }
    private static void listarBolsas() {
        List<BolsaSangue> lista = bolsaDAO.listarTodas();
        for (BolsaSangue b : lista) {
            System.out.println("ID: " + b.getIdBolsa()
                    + " | Tipo: " + b.getTipoSanguineo());
        }
    }

    private static void buscarBolsa() {
        System.out.print("ID: "); int id = sc.nextInt(); sc.nextLine();
        BolsaSangue b = bolsaDAO.buscarPorId(id);
        if (b != null) {
            System.out.println("Tipo: " + b.getTipoSanguineo());
        } else System.out.println("❌ Bolsa não encontrada!");
    }

    private static void deletarBolsa() {
        System.out.print("ID: "); int id = sc.nextInt(); sc.nextLine();
        bolsaDAO.deletar(id);
        System.out.println("✅ Bolsa removida (se existia).");
    }

    // ========================================================
    // (6) MENU ESTOQUE
    private static void menuEstoque() {
        int op = 0;
        while (op != 5) {
            System.out.println("\n== MENU ESTOQUE ==");
            System.out.println("1 - Inserir");
            System.out.println("2 - Listar");
            System.out.println("3 - Buscar por Tipo");
            System.out.println("4 - Deletar");
            System.out.println("5 - Voltar");
            System.out.print("Escolha: ");
            op = sc.nextInt(); sc.nextLine();

            switch (op) {
                case 1 -> inserirEstoque();
                case 2 -> listarEstoque();
                case 3 -> buscarEstoquePorTipo();
                case 4 -> deletarEstoque();
                case 5 -> System.out.println("Voltando...");
            }
        }
    }

    private static void inserirEstoque() {
        System.out.print("Tipo: "); String tipo = sc.nextLine();
        System.out.print("Qtd: "); int qtd = sc.nextInt(); sc.nextLine();
        Estoque e = new Estoque(tipo, qtd);
        estoqueDAO.inserir(e);
        System.out.println("✅ Estoque inserido!");
    }

    private static void listarEstoque() {
        List<Estoque> lista = estoqueDAO.listarTodos();
        for (Estoque e : lista) {
            System.out.println("Tipo: " + e.getTipoSanguineo()
                    + " | Qtd: " + e.getQtdDisponivel());
        }
    }

    private static void buscarEstoquePorTipo() {
        System.out.print("Tipo: "); String tipo = sc.nextLine();
        Estoque e = estoqueDAO.buscarPorTipo(tipo);
        if (e != null) {
            System.out.println("Qtd: " + e.getQtdDisponivel());
        } else System.out.println("❌ Estoque vazio para esse tipo!");
    }

    private static void deletarEstoque() {
    System.out.print("Tipo: "); 
    String tipo = sc.nextLine();
    System.out.print("Qtd a remover: "); 
    int qtd = sc.nextInt(); sc.nextLine();

    try {
        estoqueDAO.remover(tipo, qtd); // novo método no DAO
        System.out.println("✅ Removido " + qtd + " bolsas de " + tipo);
    } catch (RuntimeException e) {
        System.out.println(e.getMessage());
    }
}


    // ========================================================
    // (7) REGISTRAR DOAÇÃO
    private static void registrarDoacaoExemplo() {
    System.out.print("CPF do doador: ");
    String cpf = sc.nextLine();

    Doador d = doadorDAO.buscarPorCpf(cpf);
    if (d == null) {
        System.out.println("❌ Doador não encontrado!");
        return;
    }

    System.out.print("Qtd bolsas: ");
    int qtd = sc.nextInt(); sc.nextLine();

    // Cria as bolsas associadas ao doador
    for (int i = 0; i < qtd; i++) {
        BolsaSangue b = new BolsaSangue(0, d.getTipoSanguineo(), d.getCpf());
        bolsaDAO.inserir(b);
    }

    // Atualiza o estoque
    Estoque e = estoqueDAO.buscarPorTipo(d.getTipoSanguineo());
    if (e == null) {
        e = new Estoque(d.getTipoSanguineo(), qtd);
        estoqueDAO.inserir(e);
    } else {
        e.setQtdDisponivel(e.getQtdDisponivel() + qtd);
        estoqueDAO.atualizar(e);
    }

    System.out.println("✅ Doação registrada!");
}

    // ========================================================
    // (8) SOLICITAR BOLSAS
    private static void solicitarBolsasExemplo() {
        System.out.print("Tipo: "); String tipo = sc.nextLine();
        System.out.print("Qtd: "); int qtd = sc.nextInt(); sc.nextLine();
        Estoque e = estoqueDAO.buscarPorTipo(tipo);
        if (e == null || e.getQtdDisponivel() < qtd) {
            System.out.println("❌ Estoque insuficiente!");
            return;
        }
        e.setQtdDisponivel(e.getQtdDisponivel() - qtd);
        estoqueDAO.atualizar(e);
        System.out.println("✅ Retirada concluída!");
    }
}
