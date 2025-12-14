public class Ej_1 {
    interface Calculator{
        static String sumar(int n1, int n2){
            int suma=n1+n2;
            return "El resultado de la suma es "+suma;
        }
        static String restar(int n1, int n2){
            int resta=n1-n2;
            return "El resultado de la resta es "+resta;
        }
        static String multiplicar(int n1, int n2){
            int multi=n1*n2;
            return "El resultado de la multiplicacioon es "+multi;
        }
        static String dividir(int n1, int n2){
            int divi=n1/n2;
            return "El resultado de la division es "+divi;
        }
    }
    public static void main(String[] args) {
        int n1=15;
        int n2=3;
        System.out.println(Calculator.sumar(n1,n2));
        System.out.println(Calculator.restar(n1,n2));
        System.out.println(Calculator.multiplicar(n1,n2));
        System.out.println(Calculator.dividir(n1,n2));
    }
}
