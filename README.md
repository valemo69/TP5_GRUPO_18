# TP5_GRUPO_18 — Proyecto Web (Java + MySQL)

## Qué es
Aplicación web Java (JSP + Servlet + JDBC) sobre MySQL, con el modelo en capas
`entidad` / `datoslmpl`.

## Requisitos
- **JDK 21**
- **Eclipse IDE for Enterprise Java and Web Developers**
- **Apache Tomcat** (9 o superior)
- **MySQL Server** (con usuario `root` / contraseña `root`, que es lo que usa `Conexion.java`)

## Puesta en marcha (para cualquier compañero)

### 1. Importar el proyecto
Eclipse → `File` → `Import` → `Existing Projects into Workspace` → apuntá a esta carpeta.

> El proyecto **no trae rutas absolutas** ni referencias a servidores de otras máquinas.
> Cada uno configura su propio entorno en los pasos siguientes.

### 2. Crear la base de datos
Ejecutá el script `BaseDeDatos/sistema_clientes.sql` en MySQL (Workbench o consola):
```sql
SOURCE BaseDeDatos/sistema_clientes.sql;
```

### 3. Driver MySQL
El driver **ya viene incluido** en `lib/mysql-connector-java-5.1.47-bin.jar`.
Si Eclipse no lo detecta:
- Clic derecho en el proyecto → `Build Path` → `Configure Build Path`
- Pestaña `Libraries` → `Add JARs...` → `lib/mysql-connector-java-5.1.47-bin.jar`

### 4. Configurar el servidor Tomcat
1. `Window` → `Preferences` → `Server` → `Runtime Environments` → `Add` → tu Tomcat.
2. Clic derecho en el proyecto → `Properties` → **Targeted Runtimes** → tildá tu Tomcat.
3. Clic derecho en el proyecto → `Run As` → `Run on Server`.

> Si tu Tomcat no tiene Java 21 configurado, ajustalo en
> `Preferences` → `Server` → `Runtime Environments` → tu Tomcat → `Edit`.

### 5. Correr
Abrí `http://localhost:8080/TP5_GRUPO_18/`

## Estructura

```
TP5_GRUPO_18/
├── BaseDeDatos/
│   └── sistema_clientes.sql      # script de creación de la BD
├── lib/
│   └── mysql-connector-java-5.1.47-bin.jar
├── src/main/java/
│   ├── entidad/                  # Cliente, Usuario (modelo de dominio)
│   └── datoslmpl/                # Conexion (acceso a datos)
└── src/main/webapp/
    └── WEB-INF/web.xml
```

## Notas para el equipo
- **No suban a git:** rutas absolutas en `.classpath`, el runtime de Tomcat fijado
  en `.settings/org.eclipse.wst.common.project.facet.core.xml`, ni `.metadata/`.
- **Sí suban a git:** la carpeta `lib/` con el driver (ya está permitido en `.gitignore`).
- Cada integrante configura su Tomcat localmente desde Eclipse, no desde archivos del repo.
