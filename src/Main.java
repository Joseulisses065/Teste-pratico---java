import dominio.entidades.Funcionario;
import utils.FuncaoSimples;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();
        BigDecimal salarioMinimo = new BigDecimal("1212.00");


        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        System.out.println("--------------------------");
        System.out.println("LISTA DE FUNCIONÁRIOS COM JOÃO");
        System.out.println("--------------------------\n");

        FuncaoSimples.listarFuncionarios(funcionarios);

        FuncaoSimples.removerFuncionario("João", funcionarios);

        System.out.println("--------------------------");
        System.out.println("LISTA DE FUNCIONÁRIOS SEM JOÃO");
        System.out.println("--------------------------\n");

        FuncaoSimples.listarFuncionarios(funcionarios);

        funcionarios.forEach(f -> f.setSalario(f.getSalario().multiply(new BigDecimal("1.10"))));

        FuncaoSimples.imprimirFuncionarioPorFuncao(funcionarios);

        FuncaoSimples.imprimirFuncionariosAniversariates(funcionarios, 10, 12);

        FuncaoSimples.imprimirFuncionarioMaisVelho(funcionarios);

        FuncaoSimples.imprimirFuncionariosEmOrdemAlfabetica(funcionarios);

        FuncaoSimples.calcularTotalDeSalarios(funcionarios);

        FuncaoSimples.imprimirFuncionariosQueRecebemSalarioMinimo(funcionarios, salarioMinimo);

    }
}
