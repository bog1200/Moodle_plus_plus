package app.romail.moodle_plus_plus.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import app.romail.moodle_plus_plus.dto.GradeDTO;
import app.romail.moodle_plus_plus.services.GradeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/grade")
public class GradeController {
     private final GradeService gradeService;

     public GradeController(GradeService gradeService) {
         this.gradeService = gradeService;
     }

     @GetMapping("/{id}")
     public ResponseEntity<GradeDTO> getGrade(@PathVariable Long id) {
         return gradeService.getById(id)
                 .map(ResponseEntity::ok)
                 .orElse(ResponseEntity.notFound().build());
     }

//     @PostMapping
//     public ResponseEntity<GradeDTO> createGrade(@RequestBody GradeDTO gradeDTO) {
//         Grade grade = gradeService.save(gradeDTO);
//         return ResponseEntity.ok(grade);
//     }
//
//     @PutMapping("/{id}")
//     public ResponseEntity<GradeDTO> updateGrade(@PathVariable Long id, @RequestBody GradeDTO gradeDTO) {
//         return gradeService.getById(id)
//                 .map(grade -> {
//                     gradeDTO.setId(id);
//                     gradeService.save(gradeDTO);
//                     return ResponseEntity.ok(gradeDTO);
//                 })
//                 .orElse(ResponseEntity.notFound().build());
//     }
//
//     @DeleteMapping("/{id}")
//     public ResponseEntity<?> deleteGrade(@PathVariable Long id) {
//         return gradeService.getById(id)
//                 .map(grade -> {
//                     gradeService.delete(grade);
//                     return ResponseEntity.ok().build();
//                 })
//                 .orElse(ResponseEntity.notFound().build());
//     }


}
