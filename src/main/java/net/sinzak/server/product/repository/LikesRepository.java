package net.sinzak.server.product.repository;

import net.sinzak.server.product.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Likes,Long> {
}
