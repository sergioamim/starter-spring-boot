package br.com.dasa.startermodel.api.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.dasa.startermodel.api.utils.CustomUtils;
import br.com.dasa.startermodel.api.utils.ExemploTestBuilder;
import br.com.dasa.startermodel.controller.ExemploController;
import br.com.dasa.startermodel.controller.mapper.ExemploMapper;
import br.com.dasa.startermodel.entity.Exemplo;
import br.com.dasa.startermodel.repository.ExemploRepository;
import br.com.dasa.startermodel.service.ExemploService;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class ExemploControllerTest {

    private static final String ENDPOINT_URL = "/v1/exemplo";
    public static final Long ID = 1L;
    @InjectMocks
    private ExemploController controller;
    @MockBean
    private ExemploService service;
    @MockBean
    private ExemploRepository repository;
    @MockBean
    private ExemploMapper mapper;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.controller).build();
    }

    @Test
    @DisplayName("Test findAll Exemplo Success")
    public void getAll() throws Exception {
        Mockito.when(service.findAll()).thenReturn(ExemploTestBuilder.getListEntities());
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));

    }

    @Test
    @DisplayName("Test findById Exemplo Success")
    public void getById() throws Exception {

        Mockito.when(service.findById(anyLong())).thenReturn(ExemploTestBuilder.getEntity());

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/" + ID))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(ID.intValue())));
        Mockito.verify(service, Mockito.times(1)).findById(ID);
        Mockito.verifyNoMoreInteractions(service);
    }

    @Test
    @DisplayName("Test save new Exemplo Success")
    public void save() throws Exception {
        Mockito.when(service.create(any(Exemplo.class))).thenReturn(ExemploTestBuilder.getEntity());

        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(ExemploMapper.INSTANCE.toDTO(ExemploTestBuilder.getEntity()))))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(service, Mockito.times(1)).create(any(Exemplo.class));
        Mockito.verifyNoMoreInteractions(service);
    }

    @Test
    @DisplayName("Test update Exemplo Success")
    public void update() throws Exception {
        Mockito.when(service.update(any())).thenReturn(ExemploTestBuilder.getEntity());

        mockMvc.perform(
                        MockMvcRequestBuilders.put(ENDPOINT_URL + "/" + ID)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(ExemploMapper.INSTANCE.toDTO(ExemploTestBuilder.getEntity()))))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(service, Mockito.times(1)).update(any(Exemplo.class));
        Mockito.verifyNoMoreInteractions(service);
    }

    @Test
    @DisplayName("Test delete Exemplo Success")
    public void delete() throws Exception {
        Mockito.doNothing().when(service).delete(anyLong());
        mockMvc.perform(
                        MockMvcRequestBuilders.delete(ENDPOINT_URL + "/" + ID))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(service, Mockito.times(1)).delete(anyLong());
        Mockito.verifyNoMoreInteractions(service);
    }
}