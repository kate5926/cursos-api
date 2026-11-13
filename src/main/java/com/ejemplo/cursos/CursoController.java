package com.ejemplo.cursos;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    // Lista en memoria (no usa base de datos)
    private final List<Curso> cursos = new CopyOnWriteArrayList<>();
    private final AtomicLong contadorId = new AtomicLong(0);

    public CursoController() {
        // Datos de ejemplo
        cursos.add(new Curso(contadorId.incrementAndGet(), "Programación I", "Ana Torres", 4, 1, true));
        cursos.add(new Curso(contadorId.incrementAndGet(), "Base de Datos", "Luis Ramírez", 4, 3, true));
        cursos.add(new Curso(contadorId.incrementAndGet(), "Fotografía Digital", "María Quispe", 2, 5, false));
    }

    // GET /api/cursos -> lista todos los cursos
    @GetMapping
    public List<Curso> listar() {
        return new ArrayList<>(cursos);
    }

    // GET /api/cursos/{id} -> busca un curso por su id
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return cursos.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "No existe un curso con id " + id)));
    }

    // POST /api/cursos -> registra un curso nuevo
    @PostMapping
    public ResponseEntity<Curso> registrar(@Valid @RequestBody Curso curso) {
        curso.setId(contadorId.incrementAndGet()); // el id lo genera el servidor
        cursos.add(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(curso);
    }

    // Si faltan datos o son inválidos, responde 400 con mensajes claros
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> manejarErrores(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(e -> errores.put(e.getField(), e.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errores);
    }

    // Si el JSON está mal escrito (comas, comillas, tipos de dato), responde 400
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> manejarJsonInvalido(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest()
                .body(Map.of("error", "El JSON enviado no es válido. Revise comillas, comas y tipos de dato."));
    }
}
