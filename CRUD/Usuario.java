package p1.p1.CRUD;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Usuario")
class Usuario {
    @Id
    @Pattern(regexp = "\\d{8}[A-Z]", message = "ERROR! El dni tiene que contener 8 numeros seguido de 1 letra")
    private String dni;

    @NotBlank(message = "ERROR! El nombre no puede estar en blanco/vacio")
    @Size(max = 255)
    private String nombre;

    @Pattern(regexp = "normal|administrador", message = "ERROR! El usuario solo puede ser: normal | administrador")
    private String tipo;

    @Pattern(regexp = "[A-Za-z0-9]{1,50}@gmail.com", message = "ERROR! El email tiene que tener un formato correcto. Ejemplo: prueba@gmail.com")
    private String email;

    @Size(min = 4, max = 12, message = "ERROR! La contraseña debe ser una cadena de entre 4 y 12 caracteres")
    @Pattern(regexp = "[A-Za-z0-9]", message = "ERROR! La contraseña solo puede tener caracteres alfanumericos")
    private String password;

    private String penalizacionHasta;

    public Usuario(String dni, String nombre, String tipo, String email, String password, String penalizacionHasta) {
        this.dni = dni;
        this.nombre = nombre;
        this.tipo = tipo;
        this.email = email;
        this.password = password;
        this.penalizacionHasta = penalizacionHasta;
    }

    public Usuario(String dni, String nombre, String tipo, String email, String password) {
        this.dni = dni;
        this.nombre = nombre;
        this.tipo = tipo;
        this.email = email;
        this.password = password;
    }

    public Usuario() {
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public enum Tipo{
        normal, administrador
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPenalizacionHasta() {
        return penalizacionHasta;
    }

    public void setPenalizacionHasta(String penalizacionHasta) {
        this.penalizacionHasta = penalizacionHasta;
    }
}
