package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.dto.File;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(File file) {
        em.persist(file);
    }

    @Override
    public File findById(Long id) {
        return em.find(File.class, id);
    }



}
