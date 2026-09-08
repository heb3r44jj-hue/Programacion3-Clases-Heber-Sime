public class Camion extends Vehiculo implements Cargable {

    private double capacidadCarga;
    private double cargaActual;

    public Camion(String marca, String modelo, int anio, String codigo, double capacidadCarga) {
        super(marca, modelo, anio, codigo);
        this.capacidadCarga = capacidadCarga;
        this.cargaActual = 0.0;
    }

    public double getCapacidadCarga() {
        return capacidadCarga;
    }

    public double getCargaActual() {
        return cargaActual;
    }

    public void setCapacidadCarga(double capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

    public void setCargaActual(double cargaActual) {
        this.cargaActual = cargaActual;
    }

    @Override
    public void conducir() {
        setVelocidadActual(75.0);
        System.out.println("El camión " + getMarca() + " " + getModelo() + " esta manejando a una velocidad de " + getVelocidadActual() + " km/h");
    }

    @Override 
    public void cargar (double peso) {
        if (cargaActual + peso <= capacidadCarga) {
            cargaActual += peso;
            System.out.println("Se ha cargado " + peso + " kg en el camión. Carga actual: " + cargaActual + " kg");
        } else {
            cargaActual += peso;
            System.out.println("No se puede cargar " + peso + " kg. Capacidad máxima excedida");
        }
    }

    @Override 
    public void descargar() {
        if( cargaActual == 0){
            System.out.println("No hay carga para descargar");
        } else {
            System.out.println("Se ha descargado " + cargaActual + " kg del camión");
            cargaActual = 0;
        }
    }

    @Override 
    public String toString() {
        return "Camión: " + super.toString() + ", Capacidad de Carga: " + capacidadCarga + " kg, Carga Actual: " + cargaActual + " kg";
    }

}
