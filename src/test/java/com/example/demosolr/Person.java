package com.example.demosolr;

import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.io.Serializable;

@SolrDocument(collection = "test01_core")
@Data
public class Person implements Serializable {

    @Id
    @Field
    private Long id;
    @Field
    private String name;
    @Field
    private Integer age;
    @Field
    private String hobby;
}