package com.upload.file.api.adapter.outbound.repository.transaction;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, UUID> {

    Page<TransactionEntity> findAllByStoreName(String nameStore, Pageable pageable);
}
