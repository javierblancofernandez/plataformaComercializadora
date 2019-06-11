

package comercializadora.dao;

import comercializadora.modelos.Proveedor;
import java.util.List;

public interface IProveedorDao {
    
    public List<Proveedor> listAll();
    public String insert(Proveedor prov);
    public String update(Proveedor prov);
    public String delete(Proveedor prov);
    public Proveedor findById(long proveedorId);
    
    
}
