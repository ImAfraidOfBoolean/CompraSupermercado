package compraSupermercado;

import java.util.ArrayList;

public class Supermercado {
    private ArrayList<Producto> productos;
    private Producto p;

    Supermercado(){
        productos = new ArrayList<>();
    }

    public void addProduct(Producto producto){
            productos.add(producto);
    }

    public boolean findProduct(String nombre) {
        for (Producto producto : productos) {
            if (producto.getNombre().trim().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }

    public void calcSub(String nombre, int cantidad) {
        for (Producto p : productos) {
            if (p.getNombre().trim().equalsIgnoreCase(nombre)) {
                p.setCantidad(cantidad);
            }
        }
    }

    public ArrayList<Producto> getProductList() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public Producto getProduct() {
        return p;
    }

    public void setP(Producto p) {
        this.p = p;
    }
}
