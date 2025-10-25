package tdv.teclasunidos.entities;

public class Socio {
    private String nombre;
    private int edad;
    private String direccion;
    private String dni;

    public Socio(String nombre, int edad, String direccion, String dni) {
        if (nombre.length() > 50) {
            throw new IllegalArgumentException("El nombre no puede tener más de 50 caracteres");
        }
        this.nombre = nombre;
        this.edad = edad;
        this.direccion = direccion;
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getDni() {
        return dni;
    }
}
