package com.nsia.officems.taqnin.document;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface DocumentRepository extends JpaRepository<Document,Long> {

    @Query("SELECT d FROM Document d WHERE d.approved = true")
    public List<Document> findByDeletedFalseOrDeletedIsNullAndApprovedTrue();

    @Query(value = "select * from public.taqnin_documents where (deleted is not true and approved = true) and (CAST(number AS VARCHAR(255)) Like %:query% or title Like %:query%)", nativeQuery = true)
    public List<Document> findAllFilteredDocuments(@Param("query") String query);

    @Query(value = "select count(*) from public.taqnin_documents where deleted is not true", nativeQuery = true)
    public long countTotalDocuments();

    @Query(value = "select count(*) from public.taqnin_documents where deleted is not true and approved = true", nativeQuery = true)
    public long countApprovedDocuments();

    @Query(value = "select count(*) from public.taqnin_documents where deleted is not true and approved = false", nativeQuery = true)
    public long countNotApprovedDocuments();

    

    // @Query(value = "select count(*) from public.taqnin_documents where deleted is not true", nativeQuery = true)
    // public long countInProgressDocuments();
}
