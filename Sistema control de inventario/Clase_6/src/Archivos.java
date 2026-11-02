import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Archivos {

    public static final String PRODUCTOS = "productos.txt";
    public static final String VENTAS = "ventas.txt";

    public static ArrayList<RegistroProducto> cargarProductos() throws IOException {
        ArrayList<RegistroProducto> lista = new ArrayList<>();
        File archivo = new File(PRODUCTOS);
        if (!archivo.exists()) {
            return lista;
        }
        BufferedReader br = new BufferedReader(new FileReader(archivo));
        String linea = br.readLine();
        while (linea != null) {
            String[] datos = linea.split(";");
            if (datos.length == 4) {
                try {
                    String codigo = datos[0];
                    String nombre = datos[1];
                    double precio = Double.parseDouble(datos[2]);
                    int cantidad = Integer.parseInt(datos[3]);
                    RegistroProducto p = new RegistroProducto(nombre, precio, cantidad, codigo);
                    lista.add(p);
                } catch (NumberFormatException e) {
                }
            }
            linea = br.readLine();
        }
        br.close();
        return lista;
    }

    public static void guardarProductos(List<RegistroProducto> lista) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(PRODUCTOS));
        for (int i = 0; i < lista.size(); i++) {
            pw.println(lista.get(i).toLinea());
        }
        pw.close();
        if (pw.checkError()) {
            throw new IOException("Error al escribir productos.txt");
        }
    }

    public static void registrarVenta(String linea) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(VENTAS, true));
        pw.println(linea);
        pw.close();
        if (pw.checkError()) {
            throw new IOException("Error al escribir ventas.txt");
        }
    }

    public static ArrayList<String> leerVentas() throws IOException {
        ArrayList<String> lineas = new ArrayList<>();
        File archivo = new File(VENTAS);
        if (!archivo.exists()) {
            return lineas;
        }
        BufferedReader br = new BufferedReader(new FileReader(archivo));
        String linea = br.readLine();
        while (linea != null) {
            if (!linea.trim().isEmpty()) {
                lineas.add(linea);
            }
            linea = br.readLine();
        }
        br.close();
        return lineas;
    }
}