package mx.edu.utez.adoptaMe.model;


public class mascotaModel {

    String nombre;
    Integer edad;
    String descripcion;
    String tipoMascota; //Se usarán las claves “perro” o “gato”
    String imagen;
    Boolean disponibleAdopcion;


    public mascotaModel() {
    }

    public mascotaModel(String nombre, Integer edad, String descripcion, String tipoMascota, String imagen, Boolean disponibleAdopcion) {
        this.nombre = nombre;
        this.edad = edad;
        this.descripcion = descripcion;
        this.tipoMascota = tipoMascota;
        this.imagen = imagen;
        this.disponibleAdopcion = disponibleAdopcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoMascota() {
        return tipoMascota;
    }

    public void setTipoMascota(String tipoMascota) {
        this.tipoMascota = tipoMascota;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Boolean getDisponibleAdopcion() {
        return disponibleAdopcion;
    }

    public void setDisponibleAdopcion(Boolean disponibleAdopcion) {
        this.disponibleAdopcion = disponibleAdopcion;
    }
}
