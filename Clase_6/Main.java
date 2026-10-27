import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    final static String ARCHIVO_TEXTO = "inventario.txt";
    final static String ARCHIVO_COPIA = "inventario.dat";

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args)
    {
        crearArchivoSiNoExiste();

        int opcion = 0;

        while (opcion != 7)
        {
            System.out.println();
            System.out.println("========== TECHSTORE ==========");
            System.out.println("    SISTEMA DE INVENTARIO");
            System.out.println("1. Registrar producto");
            System.out.println("2. Mostrar inventario");
            System.out.println("3. Buscar producto");
            System.out.println("4. Calcular valor del inventario");
            System.out.println("5. Crear copia de seguridad");
            System.out.println("6. Recuperar copia de seguridad");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opcion: ");

            try
            {
                opcion = Integer.parseInt(sc.nextLine().trim());
            }
            catch (NumberFormatException e)
            {
                System.out.println("Error: debe escribir un numero del 1 al 7");
                continue;
            }

            switch (opcion)
            {
                case 1:
                    registrarProducto();
                    break;
                case 2:
                    mostrarInventario();
                    break;
                case 3:
                    buscarProducto();
                    break;
                case 4:
                    calcularValorInventario();
                    break;
                case 5:
                    crearCopiaSeguridad();
                    break;
                case 6:
                    recuperarCopiaSeguridad();
                    break;
                case 7:
                    System.out.println("Fin");
                    break;
                default:
                    System.out.println("Opcion invalida intente de nuevo");
            }
        }

        sc.close();
    }

    public static void crearArchivoSiNoExiste()
    {
        File archivo = new File(ARCHIVO_TEXTO);
        try
        {
            if (archivo.createNewFile())
            {
                System.out.println("El archivo " + archivo.getName() + " fue creado");
            }
        }
        catch (IOException e)
        {
            System.out.println("Error al crear el archivo: " + e.getMessage());
        }
    }

    public static ArrayList<Producto> leerProductos()
    {
        ArrayList<Producto> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_TEXTO)))
        {
            String linea;

            while ((linea = br.readLine()) != null)
            {
                if (linea.trim().isEmpty())
                {
                    continue;
                }

                String[] datos = linea.split(";");

                if (datos.length != 4)
                {
                    System.out.println("Linea con formato incorrecto ignorada: " + linea);
                    continue;
                }

                try
                {
                    String codigo = datos[0].trim();
                    String nombre = datos[1].trim();
                    double precio = Double.parseDouble(datos[2].trim());
                    int cantidad = Integer.parseInt(datos[3].trim());

                    lista.add(new Producto(codigo, nombre, precio, cantidad));
                }
                catch (NumberFormatException e)
                {
                    System.out.println("Linea con datos invalidos ignorada: " + linea);
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return lista;
    }

    public static Producto buscarPorCodigo(String codigo)
    {
        ArrayList<Producto> lista = leerProductos();

        for (Producto p : lista)
        {
            if (p.getCodigo().equalsIgnoreCase(codigo))
            {
                return p;
            }
        }
        return null;
    }

    public static void registrarProducto()
    {
        System.out.println("\n--- REGISTRAR PRODUCTO ---");

        System.out.print("Codigo: ");
        String codigo = sc.nextLine().trim();

        if (codigo.isEmpty() || codigo.contains(";"))
        {
            System.out.println("Error: el codigo no puede estar vacio ni contener ';'");
            return;
        }

        if (buscarPorCodigo(codigo) != null)
        {
            System.out.println("Error: ya existe un producto con el codigo " + codigo);
            return;
        }

        System.out.print("Nombre: ");
        String nombre = sc.nextLine().trim();

        if (nombre.isEmpty() || nombre.contains(";"))
        {
            System.out.println("Error: el nombre no puede estar vacio ni contener ';'");
            return;
        }

        double precio;
        int cantidad;

        try
        {
            System.out.print("Precio (RD$): ");
            precio = Double.parseDouble(sc.nextLine().trim());

            System.out.print("Cantidad: ");
            cantidad = Integer.parseInt(sc.nextLine().trim());
        }
        catch (NumberFormatException e)
        {
            System.out.println("Error: el precio y la cantidad deben ser numeros");
            return;
        }

        if (precio < 0 || cantidad < 0)
        {
            System.out.println("Error: el precio y la cantidad no pueden ser negativos");
            return;
        }

        Producto producto = new Producto(codigo, nombre, precio, cantidad);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_TEXTO, true)))
        {
            bw.write(producto.toLineaArchivo());
            bw.newLine();
            System.out.println("Producto registrado exitosamente");
        }
        catch (IOException e)
        {
            System.out.println("Error al guardar el producto: " + e.getMessage());
        }
    }

    public static void mostrarInventario()
    {
        System.out.println("\n--- INVENTARIO ---");

        ArrayList<Producto> lista = leerProductos();

        if (lista.isEmpty())
        {
            System.out.println("El inventario esta vacio");
            return;
        }

        System.out.printf("%-8s %-25s %15s %10s%n", "CODIGO", "NOMBRE", "PRECIO (RD$)", "CANTIDAD");
        System.out.println("-------------------------------------------------------------");

        for (Producto p : lista)
        {
            System.out.printf("%-8s %-25s %,15.2f %10d%n",p.getCodigo(), p.getNombre(), p.getPrecio(), p.getCantidad());
        }

        System.out.println("Total de productos: " + lista.size());
    }

    public static void buscarProducto()
    {
        System.out.println("\n--- BUSCAR PRODUCTO ---");
        System.out.print("Introduzca el codigo: ");
        String codigo = sc.nextLine().trim();

        Producto p = buscarPorCodigo(codigo);

        if (p == null)
        {
            System.out.println("Producto no encontrado en el inventario");
        }
        else
        {
            System.out.println("Producto encontrado:");
            p.mostrarInformacion();
        }
    }

    public static void calcularValorInventario()
    {
        System.out.println("\n--- VALOR DEL INVENTARIO ---");

        ArrayList<Producto> lista = leerProductos();

        if (lista.isEmpty())
        {
            System.out.println("El inventario esta vacio, valor total: RD$0.00");
            return;
        }

        double total = 0;

        for (Producto p : lista)
        {
            double valor = p.calcularValor();
            System.out.printf("%-25s %d x RD$%,.2f = RD$%,.2f%n", p.getNombre(), p.getCantidad(), p.getPrecio(), valor);
            total = total + valor;
        }

        System.out.println("-------------------------------------------------------------");
        System.out.printf("VALOR TOTAL DEL INVENTARIO: RD$%,.2f%n", total);
    }

    public static void crearCopiaSeguridad()
    {
        System.out.println("\n--- CREAR COPIA DE SEGURIDAD ---");

        ArrayList<Producto> lista = leerProductos();

        if (lista.isEmpty())
        {
            System.out.println("No hay productos para respaldar");
            return;
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_COPIA)))
        {
            oos.writeObject(lista);
            System.out.println("Copia de seguridad creada exitosamente en " + ARCHIVO_COPIA + " (" + lista.size() + " productos)");
        }
        catch (IOException e)
        {
            System.out.println("Error al crear la copia de seguridad: " + e.getMessage());
        }
    }

   
    public static void recuperarCopiaSeguridad()
    {
        System.out.println("\n--- RECUPERAR COPIA DE SEGURIDAD ---");

        File copia = new File(ARCHIVO_COPIA);
        if (!copia.exists())
        {
            System.out.println("No existe ninguna copia de seguridad todavia");
            return;
        }

        ArrayList<Producto> lista;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_COPIA)))
        {
            lista = (ArrayList<Producto>) ois.readObject();
        }
        catch (IOException e)
        {
            System.out.println("Error al leer la copia de seguridad: " + e.getMessage());
            return;
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        System.out.println("Copia recuperada exitosamente, productos reconstruidos:");
        for (Producto p : lista)
        {
            System.out.println("-----------------------");
            p.mostrarInformacion();
        }
        System.out.println("-----------------------");

        System.out.print("Desea restaurar inventario.txt con esta copia? (s/n): ");
        String respuesta = sc.nextLine().trim();

        if (respuesta.equalsIgnoreCase("s"))
        {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_TEXTO, false)))
            {
                for (Producto p : lista)
                {
                    bw.write(p.toLineaArchivo());
                    bw.newLine();
                }
                System.out.println("inventario.txt restaurado correctamente");
            }
            catch (IOException e)
            {
                System.out.println("Error al restaurar el inventario: " + e.getMessage());
            }
        }
        else
        {
            System.out.println("inventario.txt no fue modificado");
        }
    }
}