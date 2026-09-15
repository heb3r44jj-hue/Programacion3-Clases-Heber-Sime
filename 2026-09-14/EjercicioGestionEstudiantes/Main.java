public class Main {
    public static void main(String[] args){
        String[] nombres = {"heber jose" , "Juan Carlos", "Angel Martines", "Dionicio Pujol"};
        String[] correos = {"heber_Sime@Ucne.edu","Juan_Carlos@Ucne.edu","Angel_Martinez@Ucne.edu","Dionicio_Pujols@Ucne.edu"};
        double[] parcial1 = {8.8, 9.0 ,4.5,7.2};
        double[] parcial2 = {8.8, 6.0 ,4.5,7.2};
        double[] parcial3 = {8.8, 6.0 ,4.5,7.2};

        Estudiante[] estudiantes = new Estudiante[nombres.length];
        for (int i = 0; i < estudiantes.length; i++) {
            estudiantes[i] = new Estudiante(nombres[i], correos[i], parcial1[i], parcial2[i], parcial3[i]);
        }

        GestionEstudiantes gestion = new GestionEstudiantes(estudiantes);
        gestion.mostrarReporte();
        gestion.mostrarEstadisticas();
        gestion.mostrarCorreosInstitucionales();

        System.out.println();
        String busqueda = "Angel Martines";
        int indice = gestion.buscarEstudiante(busqueda);
        if (indice == -1) {
            System.out.println("Estudiante no encontrado.");
        } else {
            System.out.println("Estudiante encontrado en el índice: " + indice);
            gestion.mostrarReporte();
        }
    }
}
  