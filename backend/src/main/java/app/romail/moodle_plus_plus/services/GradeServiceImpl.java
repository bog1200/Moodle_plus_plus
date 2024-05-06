package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.AssignmentSubmission;
import app.romail.moodle_plus_plus.domain.Grade;
import app.romail.moodle_plus_plus.dto.GradeDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Optional;

@Service
public class GradeServiceImpl implements GradeService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Grade grade) {
        em.persist(grade);
    }

    @Override
    public Optional<GradeDTO> getById(Long id) {
        Grade grade = em.find(Grade.class, id);
        if (grade == null) {
            return Optional.empty();
        }
        return Optional.of(convertToDTO(grade));
    }

    @Transactional
    @Override
    public Optional<URI> createGrade(GradeDTO gradeDTO) {
        Grade grade;
        try{
            grade = convertToEntity(gradeDTO);
            save(grade);

        } catch (Exception e){
            return Optional.empty();
        }
        return Optional.of(URI.create("/grade/" + grade.getId()));
    }

    private GradeDTO convertToDTO(Grade grade) {
        GradeDTO gradeDTO = new GradeDTO();
        BeanUtils.copyProperties(grade, gradeDTO,"assignmentSubmission");
        gradeDTO.setAssignmentSubmission_id(grade.getAssignmentSubmission().getId());

        return gradeDTO;
    }

    private Grade convertToEntity(GradeDTO gradeDTO) {
        Grade grade = new Grade();
        grade.setId(gradeDTO.getId());
        grade.setAssignmentSubmission(em.find(AssignmentSubmission.class, gradeDTO.getAssignmentSubmission_id()));
        return grade;
    }
}
