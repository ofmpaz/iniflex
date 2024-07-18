package service;
import model.Funcionario;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FuncionarioService {
    Scanner scanner = new Scanner(System.in);
    Funcionario funcionario = new Funcionario();
    List<Funcionario> listaFuncionarios = new ArrayList<>();
    private static final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");

    public void cadastroFuncionario() {
        System.out.println("Informe a quantidade de funcionários a serem cadastrados:");
        int quantidadeFuncionario = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < quantidadeFuncionario; i++) {
            Funcionario funcionario = new Funcionario();
            funcionario.setNome(recebeString("Informe o nome do funcionário:"));
            funcionario.setDataNascimento(recebeData("Informe a data de nascimento (dd/MM/yyyy):"));
            funcionario.setSalario(recebeDecimal("Informe o salário:"));
            funcionario.setFuncao(recebeString("Informe a função:"));
            listaFuncionarios.add(funcionario);
        }
        System.out.println("Funcionários registrados com sucesso");
    }

    public void removerFuncionario() {
        String nome = scanner.nextLine();
        listaFuncionarios.removeIf(funcionario -> funcionario.getNome().equals(nome));
    }

    public void listarFuncionarios() {
        System.out.println("Lista de funcionários:");
        for (Funcionario funcionario : listaFuncionarios) {
            System.out.println(funcionario);
        }
    }

    public void aumentoSalario() {
        BigDecimal aumentoSalarial = new BigDecimal("0.10");
        for (Funcionario funcionario : listaFuncionarios) {
            BigDecimal salarioAtual = funcionario.getSalario();
            BigDecimal salarioNovo = salarioAtual.add(salarioAtual.multiply(aumentoSalarial));
            funcionario.setSalario(salarioNovo);
        }
        for (Funcionario funcionario : listaFuncionarios) {
            System.out.println(funcionario.getNome() + ": " + "Salário após aumento: " + funcionario.formatarSalario(funcionario.getSalario()));
        }
    }

    public Map<String, List<Funcionario>> agruparPorFuncao() {
        Map<String, List<Funcionario>> funcionariosPorFuncao = new HashMap<>();
        for (Funcionario funcionario : listaFuncionarios) {
            String funcao = funcionario.getFuncao();
            if (!funcionariosPorFuncao.containsKey(funcao)) {
                funcionariosPorFuncao.put(funcao, new ArrayList<>());
            }
            funcionariosPorFuncao.get(funcao).add(funcionario);
        }
        return funcionariosPorFuncao;
    }

    public void imprimirAniversários() {
        for (Funcionario funcionario : listaFuncionarios) {
            int mesAniversario = funcionario.getDataNascimento().getMonthValue();
            if (mesAniversario == 10 || mesAniversario == 12) {
                System.out.println("Aniversariantes dos meses 10 e 12: " + funcionario);
            }
            else{
                System.out.println("Nenhum funcionário faz aniversário no mês 10 ou 12");
            }
        }
    }

    public void imprimirFuncionariosPorFuncao(Map<String, List<Funcionario>> funcionariosPorFuncao) {
        for (Map.Entry<String, List<Funcionario>> entry : funcionariosPorFuncao.entrySet()) {
            System.out.println("Função: " + entry.getKey());
            for (Funcionario funcionario : entry.getValue()) {
                System.out.println(" - " + funcionario.getNome());
            }
        }
    }

    public void imprimirFuncionariosOrdemAlfabetica() {
        List<Funcionario> funcionariosOrdenados = new ArrayList<>(listaFuncionarios);
        funcionariosOrdenados.sort(Comparator.comparing(Funcionario::getNome));
        System.out.println("Funcionários em ordem alfabética:");
        for (Funcionario funcionario : funcionariosOrdenados) {
            System.out.println(funcionario);
        }
    }


    public void imprimirFuncionarioMaisVelho() {
        Funcionario maisVelho = null;
        int maiorIdade = -1;

        for (Funcionario funcionario : listaFuncionarios) {
            int idadeAtual = Period.between(funcionario.getDataNascimento(), LocalDate.now()).getYears();
            if (idadeAtual > maiorIdade) {
                maiorIdade = idadeAtual;
                maisVelho = funcionario;
            }
        }
        if (maisVelho != null) {
            System.out.println("Nome: " + maisVelho.getNome() + ", Idade: " + maiorIdade);
        }
    }

    public void imprimirTotalSalarios() {
        BigDecimal totalSalarios = listaFuncionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total dos salários dos funcionários: " + totalSalarios);
    }

    public void imprimirSalariosMinimos() {
       System.out.println("Quantidade de salários mínimos que cada funcionário ganha:");
        for (Funcionario funcionario : listaFuncionarios) {
            BigDecimal quantidadeSalariosMinimos = funcionario.getSalario().divide(SALARIO_MINIMO, 2, BigDecimal.ROUND_HALF_UP);
            System.out.println(funcionario.getNome() + ": " + quantidadeSalariosMinimos + " salários mínimos");
        }
    }

    private String recebeString(String mensagem) {
        System.out.println(mensagem);
        return scanner.nextLine();
    }

    private LocalDate recebeData(String mensagem) {
        System.out.println(mensagem);
        String dataStr = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dataStr, formatter);
    }

    private BigDecimal recebeDecimal(String mensagem) {
        System.out.println(mensagem);
        BigDecimal valor = scanner.nextBigDecimal();
        scanner.nextLine();
        return valor;
    }
}
