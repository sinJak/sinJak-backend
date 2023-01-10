package net.sinzak.server.product.repository;

import net.sinzak.server.product.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>{

    @Override
    Optional<Product> findById(Long aLong);

    @Query("select p from Product p order by p.id desc")
    List<Product> findAll();

    @Query("select p from Product p order by p.id desc")
    Page<Product> findAll(Pageable pageable);

//    @Query("select p from Product p order by p.popularity desc")
//    Page<Product> findAllPopularityDesc(Pageable pageable);
//    @Query("select p from Product p where p.complete = :sale order by p.popularity desc")
//    Page<Product> findAllByCompletePopularityDesc(Pageable pageable);

    @Query(value = "select * from Product as p order by p.id desc limit 3", nativeQuery = true)
    List<Product> findTop3RecentProduct();

    @Query(value = "select * from Product as p order by p.likesCnt desc limit 3", nativeQuery = true)
    List<Product> findTop3HotProduct();

    @Query("select p from Product p left join fetch p.productWishList where p.id = :id")
    Optional<Product> findByIdFetchPW(@Param("id")Long id);   /** 해당 작품 찜을 누른 유저 목록까지 불러오기 **/

    @Query("select p from Product p left join fetch p.productWishList left join fetch p.user where p.id = :id")
    Optional<Product> findByIdFetchPWUser(@Param("id")Long id);   /** 해당 작품 찜을 누른 유저 목록까지 불러오기 **/

}
