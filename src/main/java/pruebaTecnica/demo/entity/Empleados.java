package pruebaTecnica.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "empleados")
public class Empleados {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellidos;
    private String edad;

    @ManyToOne
    @JoinColumn(name = "id_cargo", nullable = false)
    private Cargos cargo;
}
