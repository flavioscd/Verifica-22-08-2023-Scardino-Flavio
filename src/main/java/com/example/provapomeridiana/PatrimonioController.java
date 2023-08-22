package com.example.provapomeridiana;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patrimonio")
public class PatrimonioController {

    private final Logger logger = LoggerFactory.getLogger(PatrimonioController.class);

    @Autowired
    private PatrimonioRepository patrimonioRepository;

    @GetMapping
    public List<Patrimonio> getAllPatrimoni() {
        logger.info("Getting all patrimoni");
        return patrimonioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Patrimonio getPatrimonioById(@PathVariable Long id) {
        logger.info("Getting patrimonio with id: {}", id);
        return patrimonioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patrimonio not found with id: " + id));
    }

    @PostMapping
    public Patrimonio createPatrimonio(@RequestBody Patrimonio patrimonio) {
        logger.info("Creating patrimonio: {}", patrimonio);
        return patrimonioRepository.save(patrimonio);
    }

    @PutMapping("/{id}")
    public Patrimonio updatePatrimonio(@PathVariable Long id, @RequestBody Patrimonio patrimonioDetails) {
        logger.info("Updating patrimonio with id: {}", id);
        Patrimonio patrimonio = patrimonioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patrimonio not found with id: " + id));

        patrimonio.setNome(patrimonioDetails.getNome());
        patrimonio.setValore(patrimonioDetails.getValore());
        patrimonio.setAnnoDiCreazione(patrimonioDetails.getAnnoDiCreazione());

        return patrimonioRepository.save(patrimonio);
    }

    @DeleteMapping("/{id}")
    public void deletePatrimonio(@PathVariable Long id) {
        logger.info("Deleting patrimonio with id: {}", id);
        patrimonioRepository.deleteById(id);
    }
}
