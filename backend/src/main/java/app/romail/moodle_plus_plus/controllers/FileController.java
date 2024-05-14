//package app.romail.moodle_plus_plus.controllers;
//
//import app.romail.moodle_plus_plus.dto.FileDTO;
//import app.romail.moodle_plus_plus.services.FileService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/files")
//public class FileController {
//    private final FileService fileService;
//
//    public FileController(FileService fileService) {
//        this.fileService = fileService;
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<FileDTO> getFile(@PathVariable Long id) {
//        return fileService.getById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//}
