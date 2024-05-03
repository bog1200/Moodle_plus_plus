package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.dto.IdDocument;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

@Service
public class IdDocumentServiceImpl implements IdDocumentService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(IdDocument idDocument) {
        em.persist(idDocument);
    }

    @Override
    public IdDocument findById(Long id) {
        return em.find(IdDocument.class, id);
    }



}
