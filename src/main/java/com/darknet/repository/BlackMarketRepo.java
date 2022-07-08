package com.darknet.repository;

import com.darknet.model.BlackMarket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface BlackMarketRepo extends JpaRepository<BlackMarket, Long> {
    Optional<BlackMarket> findById(Long id);

}
