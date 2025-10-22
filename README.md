# 🚗 upn-autos-jsp-servlet

Mini aplicación web desarrollada con **JSP, Servlets y MySQL**, que permite **registrar y listar autos** aplicando una arquitectura por capas (Modelo–DAO–Servlet–JSP).  
Proyecto elaborado como parte del curso **Soluciones Web y Aplicaciones Distribuidas (SIST1402A)** de la carrera **Ingeniería de Sistemas Computacionales – UPN (Ciclo 8)**.

---

## 📘 Descripción general

El sistema permite **registrar nuevos autos** mediante un formulario y **listar los autos registrados** en una tabla.  
Se utiliza una arquitectura simple **Modelo–DAO–Servlet–JSP**, conexión a base de datos MySQL mediante **JDBC**, y **Bootstrap 5** para el diseño visual.

### 🎯 Objetivo del proyecto
> Desarrollar una mini-aplicación web que registre y liste autos aplicando buenas prácticas en JSP, Servlets y JDBC.

---

## 🧩 Características principales

- Registro de autos con validaciones:
    - Marca y modelo: requeridos (máx. 60 caracteres)
    - Año: entre 1950 y el año actual + 1
    - Precio: mayor o igual a 0
- Listado de autos en tabla con Bootstrap
- Mensaje cuando no existen registros
- Redirección automática al listado tras guardar
- Arquitectura por capas:  
  **Modelo → DAO → Servlet → JSP**

---

## 🧱 Estructura del proyecto

```
src
 └─ main
    ├─ java
    │   └─ com.upn.autos
    │       ├─ model
    │       │   └─ Auto.java
    │       ├─ dao
    │       │   ├─ AutoDao.java
    │       │   └─ AutoDaoImpl.java
    │       └─ controller
    │           └─ AutosServlet.java
    ├─ resources
    │   └─ db.properties
    └─ webapp
        ├─ WEB-INF
        │   └─ web.xml
        ├─ autos.jsp
        └─ nuevoAuto.jsp
```

---

## ⚙️ Configuración del entorno

### 🔹 Requisitos
- **JDK:** 21
- **Apache Tomcat:** 10.1.x
- **MySQL Server:** 8.0.43
- **IDE recomendado:** IntelliJ IDEA Ultimate 2025.2.3
- **Dependencias:**
    - jakarta.servlet-api 6.1.0
    - mysql-connector-java 8.0.33
    - lombok 1.18.40

---

## 💾 Base de datos

Ejecuta este script en **MySQL Workbench**:

```sql
CREATE DATABASE IF NOT EXISTS db_autos DEFAULT CHARACTER SET utf8mb4;
USE db_autos;

CREATE TABLE IF NOT EXISTS auto (
  id INT AUTO_INCREMENT PRIMARY KEY,
  marca  VARCHAR(60) NOT NULL,
  modelo VARCHAR(60) NOT NULL,
  anio   INT NOT NULL,
  precio DECIMAL(10,2) NOT NULL
);
```

Archivo `src/main/resources/db.properties` para configuración en local:

```
db.url=jdbc:mysql://localhost:3306/db_autos
db.user=root
db.password=TU_CONTRASEÑA
db.driver=com.mysql.cj.jdbc.Driver
```

> ⚠️ No se subió `db.properties` a GitHub (está en `.gitignore`).

---

## 🚀 Ejecución en IntelliJ IDEA

1. **Configurar Tomcat**
    - Ve a *Run → Edit Configurations...*
    - Crea una nueva configuración:  
      ➜ **Tomcat Server → Local**
    - En pestaña **Deployment**, agrega:  
      ➜ *Artifact:* `upn-autos-jsp-servlet:war exploded`  
      ➜ *Application context:* `/autos`
    - Clic en **Apply → OK**

2. **Ejecutar el proyecto**
    - Presiona ▶️ **Run ‘Tomcat’**
    - Accede a: [http://localhost:8080/autos](http://localhost:8080/autos)

---

## 🔄 Flujo funcional

| Paso | Acción | Resultado |
|------|---------|------------|
| 1 | El usuario accede a `/autos` (GET) | El servlet lista los autos existentes |
| 2 | Clic en **“+ Registrar Auto”** | Se abre `nuevoAuto.jsp` |
| 3 | El usuario completa el formulario (POST `/autos`) | El servlet valida y guarda los datos |
| 4 | El sistema redirige a `/autos` | Se muestra el nuevo auto en la tabla |

---

## ✅ Validaciones funcionales
#### 🔹 En el formulario (nuevoAuto.jsp)

| Campo  | Tipo    | Validación                    | Implementación                               |
|--------|---------|-------------------------------|----------------------------------------------|
| Marca  | Texto   | Requerido, máx. 60 caracteres | required maxlength="60"                      |
| Modelo | Texto   | Requerido, máx. 60 caracteres | required maxlength="60"                      |
| Año    | Número  | Entre 1950 y (año actual + 1) | min="1950" max="2026" (dinámico en servidor) | 
| Precio | Decimal | Mayor o igual a 0             | min="0" step="0.01"                          |

> Estas validaciones se realizan del lado del cliente (HTML5) para mejorar la experiencia del usuario.


#### 🔹 En el Servlet (AutosServlet.java)

Las validaciones también se aplican del lado del servidor, asegurando la integridad de los datos antes de guardar en la base de datos:
```
if (marca == null || marca.isEmpty() || marca.length() > 60) { ... }
if (modelo == null || modelo.isEmpty() || modelo.length() > 60) { ... }
if (anio < 1950 || anio > anioActual + 1) { ... }
if (precio < 0) { ... }
```

> Si alguna validación falla:
>- No se inserta el registro.
>- Se muestra un mensaje de error en la página.
>- Se mantiene la integridad de los datos en MySQL.

#### 🔹 En la base de datos

Los campos ```marca```, ```modelo```, ```anio``` y ```precio``` están como 
```NOT NULL```, lo que refuerza las validaciones del formulario y del servlet.

## 🎨 Interfaz de usuario

Diseñada con **Bootstrap 5.3**, incluye:
- Formularios responsivos
- Tabla con diseño limpio y sombreado
- Alertas de validación y mensajes amigables

---

## 🧠 Tecnologías utilizadas

| Categoría | Herramienta / Framework         |
|------------|---------------------------------|
| Lenguaje | Java 21                         |
| Backend | Jakarta Servlet API 6.1         |
| Frontend | JSP + Bootstrap 5               |
| Base de Datos | MySQL 8.0                       |
| IDE | IntelliJ IDEA Ultimate 2025.2.3 |
| Servidor | Apache Tomcat 10.1.x            |
| Control de versiones | Git + GitHub                    |

---

## 👩‍💻 Autores

**María Yepez**  
**Karen Espichán**  
**Oscar Román**  
**William Yaranga**  

Estudiantes de Ingeniería de Sistemas Computacionales – UPN  
Curso: Soluciones Web y Aplicaciones Distribuidas (SIST1402A)  
NRC: 3602  
Docente: Ing. José Miguel Flores Ingaruca


---

## 🪶 Licencia

Proyecto académico desarrollado con fines educativos.  
Puedes usar este código como referencia o práctica de laboratorio.

---

© 2025 Universidad Privada del Norte – Proyecto académico.
