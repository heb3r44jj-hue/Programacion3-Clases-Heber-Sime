public abstract class Vehiculo {
    private String marca;
    private String modelo;
    private int anio;
    private String codigo;
    private double velocidadActual;

    public Vehiculo(String marca, String modelo, int anio, String codigo) {
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.codigo = codigo;
        this.velocidadActual = 0.0;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getVelocidadActual() {
        return velocidadActual;
    }

    public void setVelocidadActual(double velocidadActual) {
        this.velocidadActual = velocidadActual;
    }

    public abstract void conducir();

    @Override 
    public String toString(){
        return "Marca: " + marca + ", Modelo: " + modelo + ", Año: " + anio + ", Código: " + codigo + ", Velocidad Actual: " + velocidadActual;
    }


}
