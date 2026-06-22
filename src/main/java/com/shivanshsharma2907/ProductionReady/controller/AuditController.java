package com.shivanshsharma2907.ProductionReady.controller;

import com.shivanshsharma2907.ProductionReady.entity.PostEntity;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/audit")
public class AuditController {

    @Autowired
    private EntityManagerFactory entityManagerFactory;


    @GetMapping(path = "/posts/{ID}")
    public List<PostEntity> getPostRevisions(@PathVariable Long ID){
        //This line is from Hibernate Envers, which is used for auditing entity changes (tracking insert, update, delete history).
        //Creates an AuditReader instance.
        //You use this object to retrieve audit history.
        AuditReader reader = AuditReaderFactory.get(entityManagerFactory.createEntityManager());

        List<Number>  revisions = reader.getRevisions(PostEntity.class,ID);

        List<PostEntity> history = revisions
                .stream()
                .map(revisionNumber -> reader.find(PostEntity.class,ID,revisionNumber))
                .toList();

        return history;
    }

}
