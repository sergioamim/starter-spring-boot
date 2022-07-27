package br.com.starter.demo.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.starter.demo.entity.Exemplo;
import br.com.starter.demo.exception.NotFoundBusinessException;
import br.com.starter.demo.repository.ExemploRepository;
import br.com.starter.demo.service.ExemploService;

@ExtendWith(MockitoExtension.class)
class ExemploServiceTest {

    private static final Long ID = 1L;

    @Mock
    private ExemploRepository repository;

    @InjectMocks
    private ExemploService service;

    @Test
    void testDelete() {
        final Optional<Exemplo> exemplo = Optional.of(createExemplo());
        when(repository.findById(ID)).thenReturn(exemplo);
        // Run the test
        service.delete(ID);
        // Verify the results
        verify(repository).delete(any(Exemplo.class));
    }

    @Test
    void testDelete_ExemploRepositoryFindByIdReturnsAbsent() {

        NotFoundBusinessException thrown = Assertions.assertThrows(NotFoundBusinessException.class, () -> {
            when(repository.findById(ID)).thenReturn(Optional.empty());
            service.delete(ID);
        });
    }

    @Test
    void testFindAll() {
        final List<Exemplo> expectedResult = List.of(createExemplo());
        final List<Exemplo> exemplos = List.of(createExemplo());
        when(repository.findAll()).thenReturn(exemplos);

        final List<Exemplo> result = service.findAll();
        assertEquals(expectedResult, result);
    }

    @Test
    void testFindAll_ExemploRepositoryReturnsNoItems() {
        when(repository.findAll()).thenReturn(Collections.emptyList());
        final List<Exemplo> result = service.findAll();
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testFindById() {
        final Exemplo expectedResult = createExemplo();
        final Optional<Exemplo> exemplo = Optional.of(createExemplo());
        when(repository.findById(ID)).thenReturn(exemplo);

        final Exemplo result = service.findById(ID);
        assertEquals(expectedResult, result);
    }

    @Test
    void testFindById_ExemploRepositoryReturnsAbsent() {
        NotFoundBusinessException thrown = Assertions.assertThrows(NotFoundBusinessException.class, () -> {
            when(repository.findById(ID)).thenReturn(Optional.empty());
            service.findById(ID);
        });
    }

    @Test
    void testUpdate() {
        // Setup
        final Exemplo exemplo = createExemplo();
        final Exemplo expectedResult =  createExemplo();

        when(repository.save( any())).thenReturn(exemplo);

        final Exemplo result = service.update(exemplo);
        assertEquals(expectedResult, result);
    }

    @Test
    void testCreate() {
        final Exemplo exemplo = createExemplo();
        final Exemplo expectedResult = createExemplo();

        when(repository.save(any())).thenReturn(exemplo);

        final Exemplo result = service.create(exemplo);

        assertEquals(expectedResult, result);
    }

    private Exemplo createExemplo() {
        final Exemplo exemplo = new Exemplo();
        exemplo.setId(ID);
        exemplo.setName("name");
        exemplo.setDescription("description");
        return exemplo;
    }
}