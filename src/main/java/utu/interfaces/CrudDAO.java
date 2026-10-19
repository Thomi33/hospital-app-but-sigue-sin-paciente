package utu.interfaces;
import java.util.List;

public interface CrudDAO<T> {
    boolean insertar(T objeto);
    boolean actualizar(T objeto);
    boolean eliminar(int id);
    T buscarPorId(int id);
    List<T> listarTodos();

}
