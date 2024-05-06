package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.Account;
import app.romail.moodle_plus_plus.domain.IdDocument;
import app.romail.moodle_plus_plus.domain.IdDocumentType;
import app.romail.moodle_plus_plus.dto.IdDocumentDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.net.URI;
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

    @Transactional
    @Override
    public Optional<URI> createIdDocument(IdDocumentDTO idDocumentDTO) {
        IdDocument idDocument;
        try{
            idDocument = convertToEntity(idDocumentDTO);
            save(idDocument);

        } catch (Exception e){
            return Optional.empty();
        }
        return Optional.of(URI.create("/idDocument/" + idDocument.getId()));
    }

    @Transactional
    @Override
    public boolean deleteIdDocument(Long id) {
        IdDocument idDocument = em.find(IdDocument.class, id);
        if (idDocument == null) {
            return false;
        }
        em.remove(idDocument);
        return true;
    }


    private IdDocumentDTO convertToDTO(IdDocument idDocument) {
        IdDocumentDTO idDocumentDTO = new IdDocumentDTO();
        BeanUtils.copyProperties(idDocument, idDocumentDTO, "account","type");
        idDocumentDTO.setAccount_id(idDocument.getAccount().getId());
        idDocumentDTO.setType(idDocument.getType().toString());
        return idDocumentDTO;
    }

    private IdDocument convertToEntity(IdDocumentDTO idDocumentDTO) {
        IdDocument idDocument = new IdDocument();
        BeanUtils.copyProperties(idDocumentDTO, idDocument, "account_id", "type");
        idDocument.setAccount(em.find(Account.class, idDocumentDTO.getAccount_id()));
        idDocument.setType(IdDocumentType.valueOf(idDocumentDTO.getType()));
        return idDocument;
    }



}
