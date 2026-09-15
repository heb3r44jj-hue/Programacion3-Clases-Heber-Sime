public class Estudiante {
    private String nombre;
    private String correo;
    private double parcial1;
    private double parcial2;
    private double parcial3;

    public Estudiante(String nombre, String correo, double parcial1, double parcial2, double parcial3) {
        this.nombre = nombre;
        this.correo = correo;
        this.parcial1 = parcial1;
        this.parcial2 = parcial2;
        this.parcial3 = parcial3;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public double getParcial1() {
        return parcial1;
    }

    public double getParcial2() {
        return parcial2;
    }

    public double getParcial3() {
        return parcial3;
    }

    public double calcularPromedio() {
        return (parcial1 + parcial2 + parcial3) / 3;
    }

    public String determinarEstado() {
        double promedio = calcularPromedio();
        if (promedio >= 6.0) {
            return "Aprobado";
        } else {
            return "Reprobado";
        }
    }

    public boolean tieneCorreoValido() {
        return correo.endsWith("Ucne.edu");
    }

    
}
