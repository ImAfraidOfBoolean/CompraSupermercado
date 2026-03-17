package compraSupermercado;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    static Supermercado s = new Supermercado();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String nombre;
        int cantidad;

        Producto avena = new Producto("Avena", 2.21);
        Producto garbanzos = new Producto("Garbanzos", 2.39);
        Producto tomate = new Producto("Tomate", 1.59);
        Producto jengibre = new Producto("Jengibre", 3.13);
        Producto quinoa = new Producto("Quinoa", 4.5);
        Producto guisantes = new Producto("Guisantes", 1.6);

        s.addProduct(avena);
        s.addProduct(garbanzos);
        s.addProduct(tomate);
        s.addProduct(jengibre);
        s.addProduct(quinoa);
        s.addProduct(guisantes);

        mostrarMenu();

        do {
            System.out.print("Producto: ");
            nombre = sc.nextLine();
            boolean existeProducto = s.findProduct(nombre);
            if (existeProducto) {
                System.out.print("Cantidad: ");
                cantidad = sc.nextInt();
                sc.nextLine();
                s.calcSub(nombre, cantidad);
            } else if (nombre.equalsIgnoreCase("fin")) {
                PrintWriter salida = null;
                try {
                    salida = new PrintWriter("ticket.txt");
                    salida.print(crearTicket());
                    salida.close();

                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("Has salido del programa");
            } else
                System.out.println("No existe");
        } while (!nombre.equalsIgnoreCase("fin"));
    }

    public static StringBuilder crearTicket(){
        StringBuilder ticket = new StringBuilder();
        double total = 0;

        ticket.append("""
                Producto    Precio    Cantidad    Subtotal
                -------------------------------------------
                """);

        for (Producto p : s.getProductList()) {
            if (p.getCantidad() > 0) {
                ticket.append(String.format("%-12s %5.2f %11d %12.2f%n", p.getNombre(), p.getPrecio(), p.getCantidad(), p.getSubtotal()));
                total += p.getSubtotal();
            }
        }
        ticket.append("-------------------------------------------");
        ticket.append(String.format("%nTOTAL: %36.2f",total));
        return ticket;
    }

    public static void mostrarMenu() {
        System.out.println("Precio      Producto");
        System.out.println("--------------------");
        for (Producto p : s.getProductList()) {
            System.out.printf("%-12s %7.2f%n", p.getNombre(),p.getPrecio());
        }
        System.out.println();
    }
}
