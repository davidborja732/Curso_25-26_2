package ExplicacionContenedor;

public class Explicacion_Contenedor {
    public static void main(String[] args) {
        Contenedor<String> stringContenedor=new Contenedor<>("Hola mundo");
        Contenedor<Integer> integerContenedor=new Contenedor<>(40);
        imprimir(stringContenedor);
        imprimir(integerContenedor);
        imprimirNumeroDoble(integerContenedor);
        imprimirConMultiplicador(integerContenedor,5);

    }
    private static void imprimirConMultiplicador(Contenedor<Integer> c,int multiplicador){
        System.out.println(c.getObjeto().intValue()*multiplicador);
    }
    private static void imprimirNumeroDoble(Contenedor<Integer> c){
        System.out.println(c.getObjeto().doubleValue()*2);
    }
    private static void imprimir(Contenedor<?> c){
        System.out.println(c.getObjeto());
    }
}
class Contenedor<T>{
    private T objeto;

    public Contenedor() {
    }

    public Contenedor(T objeto) {
        this.objeto = objeto;
    }

    public T getObjeto() {
        return objeto;
    }

    public void setObjeto(T objeto) {
        this.objeto = objeto;
    }

    @Override
    public String toString() {
        return "Explicacioncontenedor.Contenedor{" +
                "objeto=" + objeto +
                '}';
    }

}