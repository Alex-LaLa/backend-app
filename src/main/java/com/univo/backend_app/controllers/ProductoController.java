package com.univo.backend_app.controllers;

import com.univo.backend_app.models.Categoria;
import com.univo.backend_app.models.Producto;
import com.univo.backend_app.repositories.CategoriaRepository;
import com.univo.backend_app.repositories.ProductoRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductoController {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    public ProductoController(
            ProductoRepository productoRepository,
            CategoriaRepository categoriaRepository) {

        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }



    // ==========================
    // GET - Obtener todos los productos
    // ==========================
    // Ej. GET /productos

    @GetMapping
    public List<Producto> obtenerProductos() {
        return productoRepository.findAll();
    }

    // ==========================
    // GET - Obtener un producto por ID
    // ==========================
    //Ej. GET /productos/1

    @GetMapping("/{id}")
    public Optional<Producto> obtenerProducto(@PathVariable Long id) {
        return productoRepository.findById(id);
    }

    // ==========================
    // POST - Crear un producto
    // ==========================

    @PostMapping
    public Producto crear(@RequestBody Producto producto){

        Categoria categoria = categoriaRepository
                .findById(producto.getCategoria().getId())
                .orElseThrow();

        producto.setCategoria(categoria);

        return productoRepository.save(producto);
    }
    // ==========================
    // PUT - Actualizar un producto
    // ==========================

    @PutMapping("/{id}")
    public Producto actualizarProducto(
            @PathVariable Long id,
            @Valid @RequestBody Producto productoActualizado) {

        Producto producto = productoRepository.findById(id).orElseThrow();

        producto.setNombre(productoActualizado.getNombre());
        producto.setCategoria(productoActualizado.getCategoria());
        producto.setPrecio(productoActualizado.getPrecio());
        producto.setActivo(productoActualizado.getActivo());

        return productoRepository.save(producto);
    }

    // ==========================
    // DELETE - Eliminar un producto
    // ==========================
    //Ej. DELETE /productos/1

    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable Long id) {
        productoRepository.deleteById(id);
    }
}