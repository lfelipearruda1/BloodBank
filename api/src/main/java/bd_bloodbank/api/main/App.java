package bd_bloodbank.api.main;

import java.util.List;
import java.util.Scanner;

import bd_bloodbank.api.dao.BolsaSangueDAO;
import bd_bloodbank.api.dao.DoadorDAO;
import bd_bloodbank.api.dao.EstoqueDAO;
import bd_bloodbank.api.dao.GerenteHospitalDAO;
import bd_bloodbank.api.dao.SolicitacaoDAO;
import bd_bloodbank.api.dao.TelefoneDAO;
import bd_bloodbank.api.domain.BolsaSangue;
import bd_bloodbank.api.domain.Doador;
import bd_bloodbank.api.domain.Estoque;
import bd_bloodbank.api.domain.GerenteHospital;
import bd_bloodbank.api.domain.Solicitacao;
import bd_bloodbank.api.domain.Telefone;

public class App {
    private static final Scanner sc = new Scanner(System.in);

    private static final DoadorDAO doadorDAO = new DoadorDAO();
    private static final GerenteHospitalDAO gerenteHospitalDAO = new GerenteHospitalDAO();
    private static final TelefoneDAO telefoneDAO = new TelefoneDAO();
    private static final SolicitacaoDAO solicitacaoDAO = new SolicitacaoDAO();
    private static final BolsaSangueDAO bolsaDAO = new BolsaSangueDAO();
    private static final EstoqueDAO estoqueDAO = new EstoqueDAO();

    public static void main(String[] args) {
        int opcao = 0;
        while (opcao != 9) {
            System.out.println("\n=== SISTEMA DE BANCO DE SANGUE ===");
            System.out.println("1 - Menu Doador");
            System.out.println("2 - Menu Hospital/Gerente");
            System.out.println("3 - Menu Solicitação");
            System.out.println("4 - Menu Bolsa de Sangue");
            System.out.println("5 - Menu Estoque");
            System.out.println("6 - Registrar Doação");
            System.out.println("7 - Solicitar Bolsas");
            System.out.println("9 - Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt(); sc.nextLine();

            switch (opcao) {
                case 1 -> menuDoador();
                case 2 -> menuHospital();
                case 3 -> menuSolicitacao();
                case 4 -> menuBolsaSangue();
                case 5 -> menuEstoque();
                case 6 -> registrarDoacaoExemplo();
                case 7 -> solicitarBolsasExemplo();
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
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private static void cadastrarDoador() {
        System.out.print("CPF: "); String cpf = sc.nextLine();
        System.out.print("Nome: "); String nome = sc.nextLine();
        System.out.print("Sobrenome: "); String sobrenome = sc.nextLine();
        System.out.print("Idade: "); int idade = sc.nextInt(); sc.nextLine();
        System.out.print("Sexo (M/F): "); String sexo = sc.nextLine();
        System.out.print("Tipo sanguíneo: "); String tipo = sc.nextLine();

        Doador d = new Doador(cpf, nome, sobrenome, idade, sexo, tipo, 0, null, null, null, null);
        doadorDAO.inserir(d);
        System.out.println("✅ Doador cadastrado!");
    }

    private static void listarDoadores() {
        List<Doador> lista = doadorDAO.listarTodos();
        for (Doador d : lista) {
            System.out.println("CPF: " + d.getCpf()
                    + " | Nome: " + d.getNome() + " " + d.getSobrenome()
                    + " | Tipo: " + d.getTipoSanguineo());
        }
    }

    private static void buscarDoador() {
        System.out.print("CPF: "); String cpf = sc.nextLine();
        Doador d = doadorDAO.buscarPorCpf(cpf);
        if (d != null) {
            System.out.println("Nome: " + d.getNome() + " " + d.getSobrenome()
                    + ", Tipo: " + d.getTipoSanguineo());
        } else System.out.println("❌ Doador não encontrado!");
    }

    private static void deletarDoador() {
        System.out.print("CPF: "); String cpf = sc.nextLine();
        doadorDAO.deletar(cpf);
        System.out.println("✅ Doador excluído (se existia).");
    }

    // ========================================================
    // (2) MENU HOSPITAL/GERENTE
    private static void menuHospital() {
        int op = 0;
        while (op != 5) {
            System.out.println("\n== MENU HOSPITAL/GERENTE ==");
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
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private static void cadastrarHospital() {
        System.out.print("CNPJ: "); String cnpj = sc.nextLine();
        System.out.print("Nome: "); String nome = sc.nextLine();
        System.out.print("Endereço: "); String endereco = sc.nextLine();
        System.out.print("Telefone: "); String tel = sc.nextLine();

        Telefone t = new Telefone(); t.setTelefone(tel);
        int idTel = telefoneDAO.inserir(t);

        GerenteHospital g = new GerenteHospital(0, nome, cnpj, idTel, endereco);
        gerenteHospitalDAO.inserir(g);
        System.out.println("✅ Hospital/Gerente cadastrado!");
    }

    private static void listarHospitais() {
        List<GerenteHospital> lista = gerenteHospitalDAO.listarTodos();
        for (GerenteHospital g : lista) {
            String telefone = telefoneDAO.buscarPorId(g.getTelefoneId()).getTelefone();
            System.out.println("ID: " + g.getIdGerente()
                    + " | Nome: " + g.getNome()
                    + " | CNPJ: " + g.getCnpj()
                    + " | Endereço: " + g.getEndereco()
                    + " | Telefone: " + telefone);
        }
    }

    private static void buscarHospital() {
        System.out.print("CNPJ: "); String cnpj = sc.nextLine();
        GerenteHospital g = gerenteHospitalDAO.buscarPorCnpj(cnpj);
        if (g != null) {
            String telefone = telefoneDAO.buscarPorId(g.getTelefoneId()).getTelefone();
            System.out.println("Nome: " + g.getNome()
                    + ", Endereço: " + g.getEndereco()
                    + ", Telefone: " + telefone);
        } else System.out.println("❌ Hospital/Gerente não encontrado!");
    }

    private static void deletarHospital() {
        System.out.print("CNPJ: "); String cnpj = sc.nextLine();
        gerenteHospitalDAO.deletar(cnpj);
        System.out.println("✅ Hospital/Gerente excluído (se existia).");
    }

    // ========================================================
    // (3) MENU SOLICITAÇÃO
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
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private static void cadastrarSolicitacao() {
        System.out.print("Tipo sanguíneo: "); String tipo = sc.nextLine();
        System.out.print("Qtd bolsas: "); int qtd = sc.nextInt(); sc.nextLine();
        System.out.print("ID Gerente: "); int idG = sc.nextInt(); sc.nextLine();
        System.out.print("CNPJ Hospital: "); String cnpj = sc.nextLine();

        Solicitacao s = new Solicitacao(0, tipo, qtd, idG, cnpj);
        solicitacaoDAO.inserir(s);
        System.out.println("✅ Solicitação registrada!");
    }

    private static void listarSolicitacoes() {
        List<Solicitacao> lista = solicitacaoDAO.listarTodos();
        for (Solicitacao s : lista) {
            System.out.println("ID: " + s.getIdSolicitacao()
                    + " | Tipo: " + s.getTipoSanguineo()
                    + " | Qtd: " + s.getQtdBolsas()
                    + " | Gerente: " + s.getIdGerente()
                    + " | Hospital: " + s.getCnpjHospital());
        }
    }

    private static void buscarSolicitacao() {
        System.out.print("ID: "); int id = sc.nextInt(); sc.nextLine();
        Solicitacao s = solicitacaoDAO.buscarPorId(id);
        if (s != null) {
            System.out.println("Tipo: " + s.getTipoSanguineo()
                    + ", Qtd: " + s.getQtdBolsas());
        } else System.out.println("❌ Solicitação não encontrada!");
    }

    private static void deletarSolicitacao() {
        System.out.print("ID: "); int id = sc.nextInt(); sc.nextLine();
        solicitacaoDAO.deletar(id);
        System.out.println("✅ Solicitação excluída (se existia).");
    }

    // ========================================================
    // (4) MENU BOLSA DE SANGUE
    private static void menuBolsaSangue() {
        int op = 0;
        while (op != 5) {
            System.out.println("\n== MENU BOLSA DE SANGUE ==");
            System.out.println("1 - Criar Bolsa");
            System.out.println("2 - Listar Bolsas");
            System.out.println("3 - Buscar por ID");
            System.out.println("4 - Deletar Bolsa");
            System.out.println("5 - Voltar");
            System.out.print("Escolha: ");
            op = sc.nextInt(); sc.nextLine();

            switch (op) {
                case 1 -> criarBolsa();
                case 2 -> listarBolsas();
                case 3 -> buscarBolsa();
                case 4 -> deletarBolsa();
                case 5 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private static void criarBolsa() {
        System.out.print("Tipo: "); String tipo = sc.nextLine();
        BolsaSangue b = new BolsaSangue(0, tipo);
        bolsaDAO.inserir(b);
        System.out.println("✅ Bolsa criada!");
    }

    private static void listarBolsas() {
        List<BolsaSangue> lista = bolsaDAO.listarTodas();
        for (BolsaSangue b : lista) {
            System.out.println("ID: " + b.getIdBolsa()
                    + " | Tipo: " + b.getTipoSanguineo());
        }
    }

    private static void buscarBolsa() {
    System.out.print("ID: ");
    int id = sc.nextInt();
    sc.nextLine();

    BolsaSangue b = bolsaDAO.buscarPorId(id);
    if (b != null) {
        System.out.println("Tipo: " + b.getTipoSanguineo());
    } else {
        System.out.println("❌ Bolsa não encontrada!");
    }
}


    private static void deletarBolsa() {
        System.out.print("ID: "); int id = sc.nextInt(); sc.nextLine();
        bolsaDAO.deletar(id);
        System.out.println("✅ Bolsa removida (se existia).");
    }

    // ========================================================
    // (5) MENU ESTOQUE
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
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private static void inserirEstoque() {
        System.out.print("Tipo: "); String tipo = sc.nextLine();
        System.out.print("Qtd: "); int qtd = sc.nextInt(); sc.nextLine();
        Estoque e = new Estoque(0, tipo, qtd);
        estoqueDAO.inserir(e);
        System.out.println("✅ Estoque inserido!");
    }

    private static void listarEstoque() {
        List<Estoque> lista = estoqueDAO.listarTodos();
        for (Estoque e : lista) {
            System.out.println("ID: " + e.getIdEstoque()
                    + " | Tipo: " + e.getTipoSanguineo()
                    + " | Qtd: " + e.getQtdBolsas());
        }
    }

    private static void buscarEstoquePorTipo() {
        System.out.print("Tipo: "); String tipo = sc.nextLine();
        Estoque e = estoqueDAO.buscarPorTipo(tipo);
        if (e != null) {
            System.out.println("Qtd: " + e.getQtdBolsas());
        } else System.out.println("❌ Estoque vazio para esse tipo!");
    }

    private static void deletarEstoque() {
        System.out.print("ID: "); int id = sc.nextInt(); sc.nextLine();
        estoqueDAO.deletar(id);
        System.out.println("✅ Estoque removido (se existia).");
    }

    // ========================================================
    // (6) REGISTRAR DOAÇÃO
    private static void registrarDoacaoExemplo() {
        System.out.print("CPF do doador: "); String cpf = sc.nextLine();
        Doador d = doadorDAO.buscarPorCpf(cpf);
        if (d == null) {
            System.out.println("❌ Doador não encontrado!");
            return;
        }
        System.out.print("Qtd bolsas: "); int qtd = sc.nextInt(); sc.nextLine();
        for (int i = 0; i < qtd; i++) {
            BolsaSangue b = new BolsaSangue(0, d.getTipoSanguineo());
            bolsaDAO.inserir(b);
        }
        Estoque e = estoqueDAO.buscarPorTipo(d.getTipoSanguineo());
        if (e == null) {
            e = new Estoque(0, d.getTipoSanguineo(), qtd);
            estoqueDAO.inserir(e);
        } else {
            e.setQtdBolsas(e.getQtdBolsas() + qtd);
            estoqueDAO.atualizar(e);
        }
        d.setQtdBolsasDoadas(d.getQtdBolsasDoadas() + qtd);
        doadorDAO.atualizar(d);
        System.out.println("✅ Doação registrada!");
    }

    // ========================================================
    // (7) SOLICITAR BOLSAS
    private static void solicitarBolsasExemplo() {
        System.out.print("Tipo: "); String tipo = sc.nextLine();
        System.out.print("Qtd: "); int qtd = sc.nextInt(); sc.nextLine();
        Estoque e = estoqueDAO.buscarPorTipo(tipo);
        if (e == null || e.getQtdBolsas() < qtd) {
            System.out.println("❌ Estoque insuficiente!");
            return;
        }
        e.setQtdBolsas(e.getQtdBolsas() - qtd);
        estoqueDAO.atualizar(e);
        System.out.println("✅ Retirada concluída!");
    }
}
