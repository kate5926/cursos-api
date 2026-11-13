package com.ejemplo.cursos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Modelo Curso: id, nombre, docente, creditos, semestre, obligatorio.
 */
public class Curso {

    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El docente es obligatorio")
    private String docente;

    @NotNull(message = "Los créditos son obligatorios")
    @Min(value = 1, message = "Los créditos deben ser al menos 1")
    @Max(value = 10, message = "Los créditos no pueden ser más de 10")
    private Integer creditos;

    @NotNull(message = "El semestre es obligatorio")
    @Min(value = 1, message = "El semestre debe ser al menos 1")
    @Max(value = 10, message = "El semestre no puede ser más de 10")
    private Integer semestre;

    @NotNull(message = "Indique si el curso es obligatorio (true o false)")
    private Boolean obligatorio;

    public Curso() {
    }

    public Curso(Long id, String nombre, String docente, Integer creditos, Integer semestre, Boolean obligatorio) {
        this.id = id;
        this.nombre = nombre;
        this.docente = docente;
        this.creditos = creditos;
        this.semestre = semestre;
        this.obligatorio = obligatorio;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDocente() { return docente; }
    public void setDocente(String docente) { this.docente = docente; }

    public Integer getCreditos() { return creditos; }
    public void setCreditos(Integer creditos) { this.creditos = creditos; }

    public Integer getSemestre() { return semestre; }
    public void setSemestre(Integer semestre) { this.semestre = semestre; }

    public Boolean getObligatorio() { return obligatorio; }
    public void setObligatorio(Boolean obligatorio) { this.obligatorio = obligatorio; }
}
