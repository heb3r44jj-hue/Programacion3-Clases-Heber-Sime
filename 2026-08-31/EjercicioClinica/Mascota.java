public class Mascota {

    private int edad;
    private String nombre;
    private String especie;
    private double peso;

    public Mascota(int edad, String nombre, String especie, double peso){
        this.edad = edad;
        this.nombre = nombre;
        this.especie = especie;
        this.peso = peso;
    }

    public void setEdad(int edad){
        this.edad = edad;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setEspecie(String especie){
        this.especie = especie;
    }

    public void setPeso(double peso){
        this.peso = peso;
    }

    public int getEdad(){
        return edad;
    }

    public String getNombre(){
        return nombre;
    }

    public String getEspecie(){
        return especie;
    }

    public double getPeso(){
        return peso;
    }

    public void mostrarDatos(){
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad);
        System.out.println("Especie: " + especie);
        System.out.println("Peso: " + peso);
    }

    public void Clasificarpeso(){
        if(peso < 2){
            System.out.println("Bajo peso");
        } else if(peso >= 2 && peso <= 10){
            System.out.println("Peso normal");
        } else {
            System.out.println("Sobrepeso");
        }
    }
       
}
