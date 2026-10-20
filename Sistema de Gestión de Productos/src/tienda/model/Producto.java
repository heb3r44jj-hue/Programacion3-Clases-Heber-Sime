package tienda.model;

public class Producto {
    private final String nombre;
    private final Categoria categoria;
    private final double precio;
    private final int cantidad;

    public Producto(String nombre, Categoria categoria, double precio, int cantidad) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public String getCategoriaNombre() {
        return categoria.getNombre();
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getValor() {
        return precio * cantidad;
    }
}