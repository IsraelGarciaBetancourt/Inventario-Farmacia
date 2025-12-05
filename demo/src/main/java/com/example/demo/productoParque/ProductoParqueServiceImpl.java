package com.example.demo.productoParque;

import java.util.List;

import com.example.demo.productoCatalogo.ProductoCatalogo;
import com.example.demo.productoCatalogo.ProductoCatalogoService;
import org.springframework.stereotype.Service;

@Service
public class ProductoParqueServiceImpl implements ProductoParqueService {

    private final ProductoParqueDAO dao;
    private final ProductoCatalogoService productoCatalogoService;

    public ProductoParqueServiceImpl(ProductoParqueDAO dao,
                                     ProductoCatalogoService productoCatalogoService) {
        this.dao = dao;
        this.productoCatalogoService = productoCatalogoService;
    }

    @Override
    public List<ProductoParque> listar() { return dao.listar(); }

    @Override
    public List<ProductoParque> listarActivosConStock() {
        return dao.listarActivosConStock();
    }

    @Override
    public List<ProductoParque> listarEnPeligro() {
        return dao.listarEnPeligro();
    }

    @Override
    public ProductoParque buscarPorProductoCatalogoId(int idProductoCatalogo) {
        try { return dao.buscarPorProductoCatalogoId(idProductoCatalogo); }
        catch (Exception e) { return null; }
    }

    @Override
    public void registrarIngreso(int idProductoCatalogo, int cantidad) {

        ProductoParque p = buscarPorProductoCatalogoId(idProductoCatalogo);

        if (p == null) {
            ProductoCatalogo prod = productoCatalogoService.buscarPorId(idProductoCatalogo);
            ProductoParque nuevo = new ProductoParque(0, prod, cantidad, true);
            dao.guardar(nuevo);
        } else {
            p.setExistencias(p.getExistencias() + cantidad);
            p.setActivo(true);
            dao.actualizar(p);
        }
    }

    @Override
    public boolean registrarSalida(int idProductoCatalogo, int cantidad) {

        ProductoParque p = buscarPorProductoCatalogoId(idProductoCatalogo);

        if (p == null || p.getExistencias() < cantidad) {
            return false;
        }

        int nuevaCantidad = p.getExistencias() - cantidad;

        p.setExistencias(nuevaCantidad);
        p.setActivo(nuevaCantidad > 0);

        dao.actualizar(p);
        return true;
    }

    @Override
    public void desactivar(int idProductoCatalogo) {
        dao.desactivar(idProductoCatalogo);
    }

    @Override
    public void activar(int idProductoCatalogo) {
        dao.activar(idProductoCatalogo);
    }

}
