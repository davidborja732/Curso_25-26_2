package Optionals;

import java.util.Optional;

public class Optionals_Explicacion {
    public static void main(String[] args) {
        Optional<Double> resultado=calculomedio(5.90,6d,8d,9d);
        if (resultado.isPresent()){
            Double resultadomedia= resultado.get();
            System.out.println(resultadomedia);
        }else {
            System.out.println("Error 404");
        }

    }
    private static Optional<Double> calculomedio(Double ...notas){
        if (notas.length==0){
            return null;
        }else {
            double sum=0;
            for (Double nota:notas) sum +=nota;
            return Optional.of(sum / notas.length);
        }
    }
}
