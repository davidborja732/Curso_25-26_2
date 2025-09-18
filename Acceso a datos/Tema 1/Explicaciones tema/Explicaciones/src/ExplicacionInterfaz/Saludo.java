package ExplicacionInterfaz;

public class Saludo {
    interface Greeting{
        void sayHello(String name);
    }

    public static void main(String[] args) {
        Greeting greeting= (name -> System.out.println("Hola "+name));
        greeting.sayHello("Luis");
    }
}

