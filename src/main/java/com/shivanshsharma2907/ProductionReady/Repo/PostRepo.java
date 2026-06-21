package com.shivanshsharma2907.ProductionReady.Repo;

import com.shivanshsharma2907.ProductionReady.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepo extends JpaRepository<PostEntity, Long> {
}
