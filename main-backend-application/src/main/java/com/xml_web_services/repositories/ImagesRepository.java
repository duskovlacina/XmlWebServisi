package com.xml_web_services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xml_web_services.domain.Images;

@Repository
public interface ImagesRepository extends JpaRepository<Images, Long>{
}
