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
import java.util.Set;
import java.util.stream.Collectors;

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

    @Override
    public Set<AssignmentDTO> findBySubjectId(Long id) {
        Subject subject = em.find(Subject.class, id);
        if (subject == null) {
            return Set.of();
        }
        return subject.getAssignments().stream().map(this::convertToDTO).collect(Collectors.toSet());
    }


    private AssignmentDTO convertToDTO(Assignment assignment) {
        AssignmentDTO assignmentDTO = new AssignmentDTO();
        BeanUtils.copyProperties(assignment, assignmentDTO, "files", "submissions", "subject", "type");
        assignmentDTO.setType(assignment.getType().toString());
        assignmentDTO.setSubject_id(assignment.getSubject().getId());
        //assignmentDTO.setFiles_ids(assignment.getFiles().stream().map(File::getId).collect(Collectors.toSet()));
        assignmentDTO.setSubmissions_ids(assignment.getSubmissions().stream().map(AssignmentSubmission::getId).collect(Collectors.toSet()));
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
        //assignment.setFiles(assignmentDTO.getFiles_ids().stream().map(id -> em.find(File.class, id)).collect(Collectors.toSet()));
        assignment.setSubmissions(assignmentDTO.getSubmissions_ids().stream().map(id -> em.find(AssignmentSubmission.class, id)).collect(Collectors.toSet()));
        assignment.setSubject(em.find(Subject.class, assignmentDTO.getSubject_id()));
        return assignment;
    }
}
