package com.univo.backend_app.controllers;

import com.univo.backend_app.models.Categoria;
import com.univo.backend_app.repositories.CategoriaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categorias")
@CrossOrigin(origins = "*")
public class CategoriaController {

    private final CategoriaRepository repository;


    public CategoriaController(CategoriaRepository repository) {
        this.repository = repository;
    }


    @GetMapping
    public List<Categoria> listar() {
        return repository.findAll();
    }


    @PostMapping
    public Categoria crear(@RequestBody Categoria categoria) {
        return repository.save(categoria);
    }


    @PutMapping("/{id}")
    public Categoria actualizar(
            @PathVariable Long id,
            @RequestBody Categoria categoria) {

        Categoria existente = repository.findById(id)
                .orElseThrow();

        existente.setNombre(categoria.getNombre());
        existente.setActivo(categoria.getActivo());

        return repository.save(existente);
    }


    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}