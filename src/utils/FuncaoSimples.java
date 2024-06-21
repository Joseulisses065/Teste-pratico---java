package utils;

import dominio.entidades.Funcionario;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

public class FuncaoSimples {
    private static final SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    private static final DecimalFormat nf = (DecimalFormat) DecimalFormat.getInstance(Locale.getDefault());

    static {
        nf.applyPattern("#,##0.00");
    }

    public static void inserirFuncionario(Funcionario funcionario, List<Funcionario> lista) {
        lista.add(funcionario);
        System.out.println(String.format("Funcionario %s adicionado", funcionario.getNome()));
    }

    public static void removerFuncionario(String nome, List<Funcionario> lista) {
        lista.removeIf(funcionario -> funcionario.getNome().equals(nome));
    }

    public static void listarFuncionarios(List<Funcionario> funcionarios) {
        funcionarios.forEach(FuncaoSimples::imprimirFuncionario);
    }

    public static void imprimirFuncionario(Funcionario funcionario) {
        System.out.println(String.format("""
                        Dados do funcionário
                        ---------------------------
                        Nome: %s
                        Data de Nascimento: %s
                        Salário: %s
                        Função: %s
                        """, funcionario.getNome(), formatDate(funcionario.getDataDeNascimento()),
                nf.format(funcionario.getSalario()), funcionario.getFuncao()));
    }

    public static void imprimirFuncionarioPorFuncao(List<Funcionario> funcionarios) {
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        System.out.println("--------------------------");
        System.out.println("Funcionários Agrupador por função");
        System.out.println("--------------------------\n");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("Função: " + funcao);
            lista.forEach(f -> imprimirFuncionario(f));
        });
    }

    public static void imprimirFuncionariosAniversariates(List<Funcionario> funcionarios, int inicio, int fim) {
        System.out.println("--------------------------");
        System.out.println(String.format("Funcionários que fazem aniversário nos meses %s e %s:", inicio, fim));
        System.out.println("--------------------------\n");
        funcionarios.stream()
                .filter(f -> f.getDataDeNascimento().getMonthValue() == inicio || f.getDataDeNascimento().getMonthValue() == fim)
                .forEach(f -> imprimirFuncionario(f));
    }

    private static String formatDate(LocalDate localDate) {
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return df.format(date);
    }

    public static void imprimirFuncionarioMaisVelho(List<Funcionario> funcionarios) {
        Funcionario funcionarioMaisVelho = Collections.max(funcionarios, Comparator.comparing(Funcionario::getDataDeNascimento));
        System.out.println("--------------------------");
        System.out.println("Funcionário com a maior idade: ");
        System.out.println("--------------------------\n");
        System.out.println(funcionarioMaisVelho.getNome() + ", " +
                (LocalDate.now().getYear() - funcionarioMaisVelho.getDataDeNascimento().getYear()) + " anos");
    }

    public static void imprimirFuncionariosEmOrdemAlfabetica(List<Funcionario> funcionarios) {
        System.out.println("--------------------------");
        System.out.println("Funcionários em ordem alfabética:");
        System.out.println("--------------------------\n");
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(f -> FuncaoSimples.imprimirFuncionario(f));


    }

    public static void calcularTotalDeSalarios(List<Funcionario> funcionarios) {
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("--------------------------");
        System.out.println("Total dos salários: " + totalSalarios);
        System.out.println("--------------------------\n");
    }

    public static void imprimirFuncionariosQueRecebemSalarioMinimo(List<Funcionario> funcionarios, BigDecimal salarioMinimo) {
        funcionarios.forEach(f -> {
            BigDecimal salariosMinimos = f.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP);
            System.out.println(f.getNome() + " ganha " + salariosMinimos + " salários mínimos.");
            System.out.println("--------------------------");
        });
    }
}
