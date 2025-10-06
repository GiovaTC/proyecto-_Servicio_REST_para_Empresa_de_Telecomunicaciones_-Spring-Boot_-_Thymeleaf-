# Proyecto: Servicio REST para Empresa de Telecomunicaciones (Spring Boot + Thymeleaf)

---

## Descripción
Proyecto Java (Spring Boot) listo para abrir en IntelliJ que implementa:
- API REST para gestionar empleados, registros de empresa e instalaciones.
- Conexión a Oracle 19c (configurable en `application.properties`).
- Interfaz gráfica web agradable usando Thymeleaf + Bootstrap para manipular los datos.
- JSON de ejemplo con: datos personales del empleado, datos de registro en la empresa, datos de las instalaciones realizadas.

---

## Estructura recomendada (resumen)
```
telecom-rest/
├─ pom.xml
├─ src/main/java/com/telecom
│  ├─ TelecomApplication.java
│  ├─ config/ 
│  │  └─ OracleConfig.java
│  ├─ controller/
│  │  ├─ ApiController.java
│  │  └─ WebController.java
│  ├─ model/
│  │  ├─ Employee.java
│  │  ├─ CompanyRegistration.java
│  │  └─ Installation.java
│  ├─ repository/
│  │  ├─ EmployeeRepository.java
│  │  ├─ CompanyRegistrationRepository.java
│  │  └─ InstallationRepository.java
│  └─ service/
│     └─ TelecomService.java
├─ src/main/resources/
│  ├─ application.properties
│  ├─ schema-oracle.sql
│  └─ templates/
│     ├─ index.html
│     ├─ employees.html
│     └─ installations.html
└─ README.md
```

---

## `pom.xml` (dependencias clave)
```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" ...>
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.telecom</groupId>
  <artifactId>telecom-rest</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <packaging>jar</packaging>

  <properties>
    <java.version>17</java.version>
    <spring.boot.version>3.1.3</spring.boot.version>
  </properties>

  <dependencies>
    <!-- Spring Boot starters -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
    <!-- Oracle JDBC -->
    <dependency>
      <groupId>com.oracle.database.jdbc</groupId>
      <artifactId>ojdbc11</artifactId>
      <version>21.9.0.0</version>
    </dependency>
    <!-- Validation -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
  </dependencies>

  <build>
    <plugins>
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
      </plugin>
    </plugins>
  </build>
</project>
```

---

## `application.properties` (plantilla - reemplazar credenciales)
```
spring.datasource.url=jdbc:oracle:thin:@//HOST:1521/ORCLPDB1
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_CONTRASENA
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

spring.jpa.hibernate.ddl-auto=validate
spring.jpa.database-platform=org.hibernate.dialect.Oracle12cDialect

server.port=8080
spring.thymeleaf.cache=false
```

---

## Entidades (resumen)

### `Employee.java`
```java
package com.telecom.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "EMPLOYEES")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank private String nombre;
    @NotBlank private String apellido;
    @NotBlank private String documento; // DNI/NIT
    private LocalDate fechaNacimiento;
    private String email;
    private String telefono;

    // getters y setters
}
```

### `CompanyRegistration.java`
```java
package com.telecom.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "COMPANY_REGISTRATIONS")
public class CompanyRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String empresaNombre;
    private String nit;
    private LocalDate fechaRegistro;
    private String direccion;

    // getters y setters
}
```

### `Installation.java`
```java
package com.telecom.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "INSTALLATIONS")
public class Installation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String direccion;
    private String tipoServicio; // fibra, cobre, satelite, etc.
    private LocalDateTime fechaInstalacion;
    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee tecnico; // tecnico que realizó la instalación

    // getters y setters
}
```

---

## Repositorios
Usar Spring Data JPA: `EmployeeRepository extends JpaRepository<Employee, Long>` (mismo patrón para CompanyRegistration e Installation).

---

## Servicio (TelecomService)
Contendrá la lógica de negocio: crear/actualizar/borrar/listar y combinaciones (p. ej. listar instalaciones por técnico).

---

## Controladores

- `ApiController.java` -> Exponer endpoints REST `api/employees`, `api/registrations`, `api/installations` (GET/POST/PUT/DELETE). Retornar/aceptar JSON con el esquema pedido.
- `WebController.java` -> Rutas Thymeleaf para páginas: `/` (dashboard), `/employees`, `/installations`.

---

## JSON de ejemplo (request/response)
```json
{
  "employee":{
    "nombre":"Juan",
    "apellido":"Pérez",
    "documento":"12345678",
    "fechaNacimiento":"1985-04-12",
    "email":"juan.perez@telecom.com",
    "telefono":"+57 300 0000000"
  },
  "companyRegistration":{
    "empresaNombre":"Telecom S.A.",
    "nit":"900123456-7",
    "fechaRegistro":"2022-01-15",
    "direccion":"Cll 100 #10-10"
  },
  "installation":{
    "direccion":"Cll 10 #5-20",
    "tipoServicio":"Fibra",
    "fechaInstalacion":"2024-09-01T09:30:00",
    "observaciones":"Prueba y OK"
  }
}
```

---

## Script SQL (schema-oracle.sql)
```sql
CREATE TABLE EMPLOYEES (
  ID NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,
  NOMBRE VARCHAR2(100),
  APELLIDO VARCHAR2(100),
  DOCUMENTO VARCHAR2(50),
  FECHA_NAC DATE,
  EMAIL VARCHAR2(200),
  TELEFONO VARCHAR2(50)
);

CREATE TABLE COMPANY_REGISTRATIONS (
  ID NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,
  EMPRESA_NOMBRE VARCHAR2(200),
  NIT VARCHAR2(50),
  FECHA_REGISTRO DATE,
  DIRECCION VARCHAR2(300)
);

CREATE TABLE INSTALLATIONS (
  ID NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,
  DIRECCION VARCHAR2(300),
  TIPO_SERVICIO VARCHAR2(100),
  FECHA_INSTALACION TIMESTAMP,
  OBSERVACIONES VARCHAR2(4000),
  EMPLOYEE_ID NUMBER,
  CONSTRAINT FK_INSTALL_EMP FOREIGN KEY (EMPLOYEE_ID) REFERENCES EMPLOYEES(ID)
);
```

---

## Interfaz gráfica (Thymeleaf + Bootstrap) - ideas
- `index.html` con cards: número de empleados, instalaciones recientes, botón crear nuevo registro.
- Páginas con tablas paginadas (Bootstrap table), modales para crear/editar registros, formularios con validación.

Ejemplo de fragmento `employees.html` (Thymeleaf) estará en `src/main/resources/templates`.

---

## Ejemplo de controlador REST (fragmento)
```java
@RestController
@RequestMapping("/api/employees")
public class ApiController {
    private final TelecomService service;
    public ApiController(TelecomService service){ this.service = service; }

    @GetMapping
    public List<Employee> list(){ return service.findAllEmployees(); }

    @PostMapping
    public ResponseEntity<Employee> create(@Valid @RequestBody Employee e){
        Employee saved = service.saveEmployee(e);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
```

---

## Cómo abrir en IntelliJ
1. `File -> Open` y seleccionar la carpeta del proyecto (`telecom-rest`).
2. Asegurarse de tener JDK 17+ instalado y seleccionado en IntelliJ.
3. Configurar `application.properties` con la conexión Oracle.
4. Ejecutar `mvn spring-boot:run` o correr la clase `TelecomApplication`.

---

## Notas y recomendaciones
- Para Oracle 19c, usar driver `ojdbc11` y `Oracle12cDialect` en Hibernate suele funcionar.
- Si necesita autenticación avanzada o pool (HikariCP), lo incluyo en la siguiente iteración.
- Para una GUI más rica, considerar separar frontend (React) y usar el REST API — puedo generar ambos si lo desea.

---

## Siguientes pasos que puedo hacer ahora mismo
- Proveer el proyecto completo con archivos listos para importar (archivo zip)
- Generar la UI Thymeleaf completa con templates y modales
- Cambiar la interfaz a React (single-page app) y proveer código

