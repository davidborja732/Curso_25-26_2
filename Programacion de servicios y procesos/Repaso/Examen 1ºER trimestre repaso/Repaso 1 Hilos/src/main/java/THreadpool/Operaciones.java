package THreadpool;
public class Operaciones {

    public double sumar(int a, int b) {
        return a + b;
    }

    public double restar(int a, int b) {
        return a - b;
    }

    public double multiplicar(int a, int b) {
        return a * b;
    }

    public double dividir(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("No se puede dividir entre cero");
        }
        return (double) a / b;
    }
}

