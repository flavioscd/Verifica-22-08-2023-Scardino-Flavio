package com.example.provapomeridiana;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PatrimonioController.class)
@AutoConfigureMockMvc
public class PatrimonioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatrimonioRepository patrimonioRepository;

    private Patrimonio patrimonio;

    @BeforeEach
    public void setUp() {
        patrimonio = new Patrimonio("Nome Patrimonio", 100000L, 2000);
    }

    @Test
    public void testGetAllPatrimoni() throws Exception {
        List<Patrimonio> patrimoni = Arrays.asList(patrimonio);
        when(patrimonioRepository.findAll()).thenReturn(patrimoni);

        mockMvc.perform(get("/patrimonio"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].nome").value(patrimonio.getNome()))
                .andExpect(jsonPath("$[0].valore").value(patrimonio.getValore()))
                .andExpect(jsonPath("$[0].annoDiCreazione").value(patrimonio.getAnnoDiCreazione()));

        verify(patrimonioRepository, times(1)).findAll();
    }

    @Test
    public void testGetPatrimonioById() throws Exception {
        when(patrimonioRepository.findById(1L)).thenReturn(Optional.of(patrimonio));

        mockMvc.perform(get("/patrimonio/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nome").value(patrimonio.getNome()))
                .andExpect(jsonPath("$.valore").value(patrimonio.getValore()))
                .andExpect(jsonPath("$.annoDiCreazione").value(patrimonio.getAnnoDiCreazione()));

        verify(patrimonioRepository, times(1)).findById(1L);
    }
}
