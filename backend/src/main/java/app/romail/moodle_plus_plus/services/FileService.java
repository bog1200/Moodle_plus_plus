package app.romail.moodle_plus_plus.services;

import app.romail.moodle_plus_plus.domain.File;

public interface FileService {
    void save(File file);
    File findById(Long id);
}
