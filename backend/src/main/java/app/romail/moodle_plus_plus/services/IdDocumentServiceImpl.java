package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.IdDocument;
import app.romail.moodle_plus_plus.dto.IdDocumentDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IdDocumentServiceImpl implements IdDocumentService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(IdDocument idDocument) {
        em.persist(idDocument);
    }

    @Override
    public Optional<IdDocumentDTO> getById(Long id) {
        IdDocument idDocument = em.find(IdDocument.class, id);
        if (idDocument == null) {
            return Optional.empty();
        }
        return Optional.of(convertToDTO(idDocument));
    }

    public IdDocumentDTO convertToDTO(IdDocument idDocument) {
        IdDocumentDTO idDocumentDTO = new IdDocumentDTO();
        BeanUtils.copyProperties(idDocument, idDocumentDTO, "account","type");
        idDocumentDTO.setAccount_id(idDocument.getAccount().getId());
        idDocumentDTO.setType(idDocument.getType().toString());
        return idDocumentDTO;
    }



}
