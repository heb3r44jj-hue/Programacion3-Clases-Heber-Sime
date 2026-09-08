public class Carro extends Vehiculo {

    private int CantidadPuertas;

    public Carro(String marca, String modelo, int anio, String codigo, int cantidadPuertas) {
        super(marca, modelo, anio, codigo);
        this.CantidadPuertas = cantidadPuertas;
    }

    public int getCantidadPuertas() {
        return CantidadPuertas;
    }

    public void setCantidadPuertas(int cantidadPuertas) {
        CantidadPuertas = cantidadPuertas;
    }

    @Override
    public void conducir() {
        setVelocidadActual(90.7);
        System.out.println("El carro " + getMarca() + " " + getModelo() + " esta manejado a una velocidad de " + getVelocidadActual() + " km/h.");
    }

    @Override
    public String toString() {
        return "Carro: " + super.toString() + ", Cantidad de Puertas: " + CantidadPuertas;
    }
    

}
