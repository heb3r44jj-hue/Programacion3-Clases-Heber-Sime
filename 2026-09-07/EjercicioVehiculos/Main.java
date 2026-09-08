import java.util.ArrayList;

public class Main {
     public static void main(String[] args){
        Vehiculo[] vehiculos = new Vehiculo[5];

        vehiculos[0] = new Carro("Toyota", "Corolla",2002, "ash1029", 4);
        vehiculos[1] = new Carro("BMW", "Serie 3", 2019, "bsh2039", 4);
        vehiculos[2] = new Motor("Honda", "CBR500R", 2021, "msh3049", 500);
        vehiculos[3] = new Camion("Honda", "FH10", 1999, "csh4059", 20000);
        vehiculos[4] = new Camion("Mercedes", "Actros", 1996, "csh5069", 25000);
        vehiculos[5] = new Motor("Yamaha", "MT-07", 2020, "msh6079", 689);

        System.out.println("Vehículos creados:");
        for (Vehiculo vehiculo : vehiculos) {
            System.out.println(vehiculo.toString());

            System.out.println(" tipo real getclass: " + vehiculo.getClass().getSimpleName());

            vehiculo.conducir();

        }

        System.out.println("\nIdentificando camiones y cargando/descargando:");

        ArrayList<Camion> camiones = new ArrayList<>();
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo instanceof Camion) {
                System.out.println(vehiculo.getMarca() + " " + vehiculo.getModelo() + " es un camión.");
                if (vehiculo instanceof Cargable) {
                    Camion camion = (Camion) vehiculo;
                    camiones.add(camion);
                }
            }
        }

        System.out.println("\n Cargando y descargando camiones:");
        if (!camiones.isEmpty()) {
            Camion CamionPrueba = camiones.get(0);
            System.out.println("trabajando con el camión: " + CamionPrueba.getMarca() + " " + CamionPrueba.getModelo());
            CamionPrueba.cargar(15000);
            CamionPrueba.cargar(10000);
            CamionPrueba.descargar();
        }else {
            System.out.println("No se encontraron camiones para cargar/descargar.");
        }

    }

    
}
