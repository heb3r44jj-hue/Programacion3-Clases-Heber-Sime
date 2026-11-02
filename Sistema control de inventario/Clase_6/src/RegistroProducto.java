public class RegistroProducto {
    private String nombre;
    private double precio;
    private int cantidad;
    private String codigo;

    public RegistroProducto(String nombre, double precio, int cantidad, String codigo) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getValorTotal() {
        return precio * cantidad;
    }

    public String toLinea() {
        return nombre + "," + precio + "," + cantidad + "," + codigo;
    }


}