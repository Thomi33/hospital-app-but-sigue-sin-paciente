# 🏥 Sistema de Gestión Hospitalaria

Programa de escritorio hecho en Java para llevar el inventario de los equipos
médicos de un hospital: equipos, marcas, modelos, proveedores, instituciones,
ubicaciones, intervenciones, usuarios y perfiles. Además guarda un registro de
todo lo que hace cada usuario (auditoría).

> Este sistema **no** maneja pacientes; ese módulo no forma parte del proyecto.

## Qué necesitas

- Java 21 instalado
- Maven instalado
- MySQL o MariaDB corriendo en tu máquina

## Cómo ponerlo en marcha

1. Crear la base de datos (⚠️ el script borra la base `hospital` si ya existe):

   ```bash
   mysql -u root -p < script.sql
   ```

2. Ejecutar el programa:

   ```bash
   mvn exec:java
   ```

3. Entrar con el usuario **admin** y la contraseña **admin123**.

## ¿Quién puede hacer qué?

- **Administrador**: puede usar todo el sistema.
- **Operador**: solo puede ver equipos e intervenciones.

## Probar que todo funciona

```bash
mvn test
```
