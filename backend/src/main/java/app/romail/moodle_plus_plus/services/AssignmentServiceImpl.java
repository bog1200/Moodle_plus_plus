package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.*;
import app.romail.moodle_plus_plus.dto.AssignmentDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.sql.Date;
import java.util.Optional;

@Service
public class AssignmentServiceImpl implements AssignmentService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public void save(Assignment assignment) {
        em.persist(assignment);
    }

    @Override
    public Optional<AssignmentDTO> findById(Long id) {
        Assignment assignment = em.find(Assignment.class, id);
        if (assignment == null) {
            return Optional.empty();
        }
        return Optional.of(convertToDTO(assignment));
    }

    @Transactional
    @Override
    public Optional<URI> createAssignment(AssignmentDTO assignmentDTO) {
        Assignment assignment = convertToEntity(assignmentDTO);
        try{
           save(assignment);
        } catch (Exception e) {
            return Optional.empty();
        }
        return Optional.of(URI.create("/assignment/" + assignment.getId()));
    }

    @Transactional
    @Override
    public boolean deleteAssignment(Long id) {
        Assignment assignment = em.find(Assignment.class, id);
        if (assignment == null) {
            return false;
        }
        em.remove(assignment);
        return true;
    }


    private AssignmentDTO convertToDTO(Assignment assignment) {
        AssignmentDTO assignmentDTO = new AssignmentDTO();
        BeanUtils.copyProperties(assignment, assignmentDTO, "files", "submissions", "subject", "type");
        assignmentDTO.setType(assignment.getType().toString());
        assignmentDTO.setSubject_id(assignment.getSubject().getId());
        assignmentDTO.setFiles_ids(assignment.getFiles().stream().map(File::getId).toList());
        assignmentDTO.setSubmissions_ids(assignment.getSubmissions().stream().map(AssignmentSubmission::getId).toList());
        return assignmentDTO;
    }

    private Assignment convertToEntity(AssignmentDTO assignmentDTO) {
        Assignment assignment = new Assignment();
        assignment.setName(assignmentDTO.getName());
        assignment.setDescription(assignmentDTO.getDescription());
        assignment.setMaxPoints(assignmentDTO.getMaxPoints());
        assignment.setType(AssignmentType.valueOf(assignmentDTO.getType()));
        assignment.setDeadline(new Date(assignmentDTO.getDeadline()));
        assignment.setEndDate(new Date(assignmentDTO.getEndDate()));
        assignment.setStartDate(new Date(assignmentDTO.getStartDate()));
        assignment.setFiles(assignmentDTO.getFiles_ids().stream().map(id -> em.find(File.class, id)).toList());
        assignment.setSubmissions(assignmentDTO.getSubmissions_ids().stream().map(id -> em.find(AssignmentSubmission.class, id)).toList());
        assignment.setSubject(em.find(Subject.class, assignmentDTO.getSubject_id()));
        return assignment;
    }
}
