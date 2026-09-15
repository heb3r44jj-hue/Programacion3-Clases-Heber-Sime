public class GestionEstudiantes {
    private Estudiante[] estudiantes;

    public GestionEstudiantes(Estudiante[] estudiantes) {
        this.estudiantes = estudiantes;
    }

    public void mostrarReporte(){
        System.out.println("NOMBRE PROMEDIO ESTADO");
        for (Estudiante estudiante : estudiantes) {
            System.out.println(estudiante.getNombre() + " " + estudiante.calcularPromedio() + " " + estudiante.determinarEstado());
        }
    }

    public void mostrarEstadisticas(){
        double sumaPromedios = 0;
        int indiceMaximo = 0;
        int indiceMinimo = 0;
        int aprobados = 0;
        int reprobados = 0;

        for (int i = 0; i < estudiantes.length; i++) {
            double promedio = estudiantes[i].calcularPromedio();
            sumaPromedios += promedio;
            if (promedio > estudiantes[indiceMaximo].calcularPromedio()) {
                indiceMaximo = i;
            }
            if (promedio < estudiantes[indiceMinimo].calcularPromedio()) {
                indiceMinimo = i;
            }
            if (promedio >= 6.0) {
                aprobados++;
            } else {
                reprobados++;
            }
        }
        double promedioGeneral = sumaPromedios / estudiantes.length;
        System.out.println("Promedio general: " + promedioGeneral);
        System.out.println("promedio mas alto: " + estudiantes[indiceMaximo].calcularPromedio());
        System.out.println("promedio mas bajo: " + estudiantes[indiceMinimo].calcularPromedio());
        System.out.println("Aprobados: " + aprobados);
        System.out.println("Reprobados: " + reprobados);
    }

    public void mostrarCorreosInstitucionales(){
        System.out.println("Estudiantes con correos institucionales: ");
        for (int i = 0; i < estudiantes.length; i++) {
            if (estudiantes[i].tieneCorreoValido()) {
                System.out.println(estudiantes[i].getNombre() + " - " + estudiantes[i].getCorreo());
            }
        }

    }

    public int buscarEstudiante(String busqueda) {
        for (int i = 0; i < estudiantes.length; i++) {
            if (estudiantes[i].getNombre().equals(busqueda)) {
                return i;
            }
        }
        return -1;
    }

    public void mostrarDatosEstudiante(int indice){
        Estudiante estudiante = estudiantes[indice];
        System.out.println("Nombre: " + estudiante.getNombre());
        System.out.println("Correo: " + estudiante.getCorreo());
        System.out.println("Parcial 1: " + estudiante.getParcial1());   
        System.out.println("Parcial 2: " + estudiante.getParcial2());
        System.out.println("Parcial 3: " + estudiante.getParcial3());
        System.out.println("Promedio: " + estudiante.calcularPromedio());
        System.out.println("Estado: " + estudiante.determinarEstado());
    }

}