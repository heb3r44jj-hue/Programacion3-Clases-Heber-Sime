public class Motor extends Vehiculo {

    private int cilindrada;

    public Motor(String marca, String modelo, int anio, String codigo, int cilindrada) {
        super(marca, modelo, anio, codigo);
        this.cilindrada = cilindrada;
    }

    public int getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }

    @Override
    public void conducir() {
        setVelocidadActual(120.5);
        System.out.println("El motor " + getMarca() + " " + getModelo() + " esta manejando a una velocidad de " + getVelocidadActual() + " km/h.");
    }

    @Override
    public String toString() {
        return "Motor: " + super.toString() + ", Cilindrada: " + cilindrada;
    }
    
}
