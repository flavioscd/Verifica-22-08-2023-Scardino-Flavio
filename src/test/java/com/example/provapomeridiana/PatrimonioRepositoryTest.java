package com.example.provapomeridiana;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PatrimonioRepositoryTest {

    @Autowired
    private PatrimonioRepository patrimonioRepository;

    @Test
    public void testSaveAndFindById() {
        Patrimonio patrimonio = new Patrimonio("Nome Patrimonio", 1000L, 2023);
        patrimonioRepository.save(patrimonio);

        // Trova il patrimonio salvato nel database
        Patrimonio retrievedPatrimonio = patrimonioRepository.findById(patrimonio.getId()).orElse(null);
        assertThat(retrievedPatrimonio).isNotNull();
        assertThat(retrievedPatrimonio.getNome()).isEqualTo("Nome Patrimonio");
        assertThat(retrievedPatrimonio.getValore()).isEqualTo(1000L);
        assertThat(retrievedPatrimonio.getAnnoDiCreazione()).isEqualTo(2023);
    }
}

