package com.example.filestorage.repository;

import com.example.filestorage.entity.StoredFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileStorageRepository extends JpaRepository<StoredFile, Long> {
}
