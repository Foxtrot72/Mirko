package com.example.demo;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/home")
public class QuadroController {
    @Autowired
    private PaintingRepository paintingRepository;
    
    @GetMapping("/Quadro")
    public List<Quadro> getMethodName() {
       return paintingRepository.findAll();
    }
    
    @PostMapping("/Aggiungi")
    public void postQuadro(@RequestBody Quadro quadro) {
       paintingRepository.save(quadro);
       return;
    }
     @PutMapping("/Aggiorna/{id}")
    public void putQuadro(@PathVariable Long id, @RequestBody Quadro quadro) {
        Optional<Quadro> optionalQuadro = paintingRepository.findById(id);
        if (optionalQuadro.isPresent()) {
            Quadro quadretto = optionalQuadro.get();
            quadretto.setAuthor(quadro.getAuthor());
            quadretto.setName(quadro.getName());
            quadretto.setYear(quadro.getYear());
            paintingRepository.save(quadretto);
        } else {
           
            throw new RuntimeException("Quadro non trovato " + id);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteQuadro(@PathVariable Long id) {
        if (paintingRepository.existsById(id)) {
            paintingRepository.deleteById(id);
        } else {
           
            throw new RuntimeException("Quadro non trovato " + id);
        }
    }
      
    
    
}
