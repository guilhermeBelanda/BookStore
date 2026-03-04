package com.belanda.jpa.repository;

import com.belanda.jpa.models.bookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface BookRepository extends JpaRepository<bookModel, UUID> {

    bookModel findBookModelByTitle(String title);

    @Query(value = "SELECT * FROM tb_book WHERE publisher_id = :id", nativeQuery = true)
    List<bookModel> findBooksByPublisherId(@Param("id") UUID id);
}
