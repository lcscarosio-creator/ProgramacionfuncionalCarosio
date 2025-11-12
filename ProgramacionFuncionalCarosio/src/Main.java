import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args){
        List<Alumno> alumnos = Arrays.asList(
                new Alumno("Juan", 8.5, "Java"),
                new Alumno("Ana", 6.2, "Python"),
                new Alumno("Pedro", 9.0, "Java"),
                new Alumno("Maria", 5.5, "C++"),
                new Alumno("Carlos", 7.8, "Python"),
                new Alumno("Laura", 9.5, "Java")
        );

        List<String> nombresAprobados = alumnos.stream().filter(alumno -> alumno.getNota() >= 7)
                .map(alumno -> alumno.getNombre().toUpperCase())
                .sorted()
                .collect(Collectors.toList());
        System.out.println("Nombres de alumnos aprobados y ordenados: ");
        nombresAprobados.forEach(System.out::println);

        double promedioGeneral = alumnos.stream()
                .mapToDouble(Alumno::getNota)
                .average()
                .orElse(0.0);

        System.out.println("\nPromedio general de notas: " + promedioGeneral);

        Map<String, List<Alumno>> alumnosPorCurso = alumnos.stream()
                .collect(Collectors.groupingBy(Alumno::getCurso));

        System.out.println("\nAlumnos agrupados por curso: ");
        alumnosPorCurso.forEach((curso, lista) -> {
            System.out.println("Curso" + curso);
            lista.forEach(alumno -> System.out.println("\t" + alumno));
        });

        List<Double> mejoresTresNotas = alumnos.stream()
                .map(Alumno::getNota)
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .collect(Collectors.toList());

        System.out.println("\nLas 3 mejores notas son: ");
        mejoresTresNotas.forEach(System.out::println);

        List<Producto> productos = Arrays.asList(
                new Producto("Monitor LED 24\"", "Electrónica", 250.00, 15),
                new Producto("Teclado Mecánico", "Electrónica", 120.50, 30),
                new Producto("Mouse Inalámbrico", "Electrónica", 45.99, 50),
                new Producto("Silla Gamer", "Muebles", 350.00, 10),
                new Producto("Escritorio de Madera", "Muebles", 199.99, 5),
                new Producto("Libro Java Avanzado", "Libros", 85.00, 100)
        );

        List<Producto> productosCaros = productos.stream()
                .filter(producto -> producto.getPrecio() > 100)
                .sorted(Comparator.comparing(Producto::getPrecio).reversed())
                .collect(Collectors.toList());

        System.out.println("Productos con precio mayor a 100 (ordenados de mayor a menor): ");
        productosCaros.forEach(System.out::println);

        System.out.println("\nStock total por categoria: ");
        Map<String, Integer> stockPorCategoria = productos.stream()
                .collect(Collectors.groupingBy(
                        Producto::getCategoria,
                        Collectors.summingInt(Producto::getStock)
                ));
        stockPorCategoria.forEach((categoria, stock) -> System.out.println(categoria + ": " + stock));

        System.out.println("\nString de productos (nombre;precio): ");
        String reporteProductos = productos.stream()
                .map(p -> p.getNombre() + ";" + p.getPrecio())
                .collect(Collectors.joining(" | "));
        System.out.println(reporteProductos);

        System.out.println("\nPrecios promedio: ");
        double promedioGeneral2 = productos.stream()
                .mapToDouble(Producto::getPrecio)
                .average()
                .orElse(0.0);

        System.out.println("Promedio general: " + String.format("%.2f", promedioGeneral));

        Map<String, Double> promedioPorCategoria = productos.stream()
                .collect(Collectors.groupingBy(
                        Producto::getCategoria,
                        Collectors.averagingDouble(Producto::getPrecio)
                ));

        System.out.println("Promedio por categoría: ");
        promedioPorCategoria.forEach((cat, prom) -> System.out.println(cat + ": " + String.format("%.2f", prom)));

        List<Libro> libros = Arrays.asList(
                new Libro("Clean Code", "Robert C. Martin", 464, 350.50),
                new Libro("The Pragmatic Programmer", "Andrew Hunt", 352, 280.00),
                new Libro("Design Patterns", "Erich Gamma", 395, 420.75),
                new Libro("Effective Java", "Joshua Bloch", 416, 399.99),
                new Libro("Code Complete", "Steve McConnell", 960, 550.00),
                new Libro("The Mythical Man-Month", "Frederick P. Brooks Jr.", 295, 250.00)
        );
        System.out.println("\nTítulos de libros con más de 300 páginas:");
        libros.stream()
                .filter(libro -> libro.getPaginas() > 300)
                .map(Libro::getTitulo)
                .sorted()
                .forEach(System.out::println);

        System.out.println("\nPromedio de páginas:");
        double promedioPaginas = libros.stream()
                .mapToInt(Libro::getPaginas)
                .average()
                .orElse(0.0);
        System.out.println("El promedio de páginas es: " + String.format("%.2f", promedioPaginas));

        System.out.println("\nantidad de libros por autor:");
        Map<String, Long> librosPorAutor = libros.stream()
                .collect(Collectors.groupingBy(
                        Libro::getAutor,
                        Collectors.counting()
                ));
        librosPorAutor.forEach((autor, cantidad) -> System.out.println(autor + ": " + cantidad + " libro(s)"));

        System.out.println("\nLibro más caro:");
        Optional<Libro> libroMasCaro = libros.stream()
                .max(Comparator.comparing(Libro::getPrecio));
        libroMasCaro.ifPresent(libro -> System.out.println("El libro más caro es: " + libro));

        List<Empleado> empleados = Arrays.asList(
                new Empleado("Ana", "Desarrollo", 2500.00, 28),
                new Empleado("Juan", "Ventas", 1800.00, 35),
                new Empleado("Pedro", "Desarrollo", 3200.00, 25),
                new Empleado("Maria", "RRHH", 2100.00, 30),
                new Empleado("Carlos", "Ventas", 1900.00, 22),
                new Empleado("Laura", "Desarrollo", 2800.00, 31)
        );

        System.out.println("\nEmpleados con salario mayor a 2000 (ordenados descendentemente):");
        empleados.stream()
                .filter(e -> e.getSalario() > 2000)
                .sorted(Comparator.comparingDouble(Empleado::getSalario).reversed())
                .forEach(System.out::println);

        System.out.println("\nSalario promedio general:");
        double salarioPromedio = empleados.stream()
                .mapToDouble(Empleado::getSalario)
                .average()
                .orElse(0.0);
        System.out.println("El salario promedio es: " + String.format("%.2f", salarioPromedio));

        System.out.println("\nSuma de salarios por departamento:");
        Map<String, Double> salariosPorDepto = empleados.stream()
                .collect(Collectors.groupingBy(
                        Empleado::getDepartamento,
                        Collectors.summingDouble(Empleado::getSalario)
                ));
        salariosPorDepto.forEach((depto, suma) -> System.out.println(depto + ": " + String.format("%.2f", suma)));

        System.out.println("\nNombres de los 2 empleados más jóvenes:");
        empleados.stream()
                .sorted(Comparator.comparingInt(Empleado::getEdad))
                .limit(2)
                .map(Empleado::getNombre)
                .forEach(System.out::println);
    }
}
