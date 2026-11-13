# Cursos API

API REST hecha con Spring Boot para registrar y consultar cursos universitarios.
Cada curso tiene: `id`, `nombre`, `docente`, `creditos`, `semestre` y `obligatorio`.

## Tecnologías

- Java 21
- Spring Boot 3.3.5 (Spring Web + Validation)
- Maven
- GitHub Codespaces

## Ejecutar en GitHub Codespaces

1. En GitHub: **Code → Codespaces → Create codespace on main**.
2. Esperar a que termine la configuración (instala Java 21 y Maven automáticamente).
3. En la terminal:
   ```bash
   mvn spring-boot:run
   ```
   La primera vez descarga las dependencias. Esperar el mensaje `Started CursosApplication`.
4. Pestaña **Ports** → puerto **8080** → clic derecho → **Port Visibility** → **Public**.
5. Copiar la URL del puerto (`https://<nombre>-8080.app.github.dev`) y usarla en Postman/Insomnia, por ejemplo:
   `GET https://<nombre>-8080.app.github.dev/api/cursos`

Para detener la app: `Ctrl + C` en la terminal.

## Endpoints

Base: `https://<nombre>-8080.app.github.dev`

| Método | URL | Descripción | Respuesta |
|---|---|---|---|
| GET | `/api/cursos` | Lista todos los cursos | 200 OK |
| GET | `/api/cursos/{id}` | Busca un curso por su id | 200 OK / 404 Not Found |
| POST | `/api/cursos` | Registra un curso nuevo | 201 Created / 400 Bad Request |

La app inicia con 3 cursos de ejemplo (ids 1, 2 y 3).

## Ejemplo de POST

`POST https://<nombre>-8080.app.github.dev/api/cursos`

Header: `Content-Type: application/json` (en Postman: **Body → raw → JSON**)

```json
{
  "nombre": "Investigacion",
  "docente": "Pepe",
  "creditos": 4,
  "semestre": 6,
  "obligatorio": true
}
```


## Validaciones

| Campo | Regla |
|---|---|
| `nombre` | obligatorio, no puede estar vacío |
| `docente` | obligatorio, no puede estar vacío |
| `creditos` | obligatorio, entre 1 y 10 |
| `semestre` | obligatorio, entre 1 y 10 |
| `obligatorio` | obligatorio, `true` o `false` |

Si un dato no cumple las reglas, la API responde **400 Bad Request** con el mensaje de cada campo que falló.

## Notas

- Los datos se guardan en memoria: al reiniciar la app vuelven los 3 cursos iniciales.
- Si Postman recibe una página de inicio de sesión de GitHub, el puerto 8080 no está en **Public**.
