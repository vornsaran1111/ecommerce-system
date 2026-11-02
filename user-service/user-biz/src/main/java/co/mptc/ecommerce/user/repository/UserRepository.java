package co.mptc.ecommerce.user.repository;

import co.mptc.ecommerce.user.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    boolean existsByUsername(String username);

    List<UserEntity> id(String id);

    Optional<UserEntity> findByUsername(String username);

    Page<UserEntity> findByIdIn(List<String> ids, Pageable pageable);
}
