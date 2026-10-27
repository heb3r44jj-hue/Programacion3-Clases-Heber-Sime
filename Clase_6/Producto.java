import java.io.Serializable;


public class Producto implements Serializable {
    private String codigo;
    private String nombre;
    private double precio;
    private int cantidad;

    public Producto(String codigo,String nombre , double precio , int cantidad){
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getCodigo(){
        return codigo;
    }

    public String getNombre(){
        return nombre;
    }

    public double getPrecio(){
        return precio;
    }

    public int getCantidad(){
        return cantidad;
    }

    public double calcularValor(){

        return precio * cantidad;
    }

    public String toLineaArchivo()
    {
        return codigo + ";" + nombre + ";" + precio + ";" + cantidad;
    }

    public String linea(){
        return("Codigo"+ codigo +"Nombre"+ nombre + "Precio" + precio + "cantidad" + cantidad );
    }

    public void mostrarInformacion(){
        System.out.println("codigo: " + codigo);
        System.out.println("nombre: " + nombre);
        System.out.println("Precio:   RD$" + String.format("%,.2f", precio));
        System.out.println("cantidad: " + cantidad);
    }
    
    
    
}
