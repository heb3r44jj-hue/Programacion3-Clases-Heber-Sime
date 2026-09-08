public class Clinica {
    public static void main(String[] args) {
        Mascota mascota1 = new Mascota(5, "pepe", "Perro", 15.0);
        mascota1.mostrarDatos();
        mascota1.Clasificarpeso();
        Mascota mascota2 = new Mascota(3, "luna", "Gato", 4.0);
        mascota2.mostrarDatos();
        mascota2.Clasificarpeso();
        Mascota mascota3 = new Mascota(10, "bola de nieve", "conejo", 25.0);
        mascota3.mostrarDatos();
        mascota3.Clasificarpeso();

        System.out.println("nombre: " + mascota1.getNombre());
        System.out.println("edad: " + mascota1.getEdad());
        System.out.println("especie: " + mascota1.getEspecie());
        System.out.println("peso: " + mascota1.getPeso());
    }
    
}
