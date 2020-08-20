package com.example.demosolr;

import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrInputDocument;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@Slf4j
@SpringBootTest
class DemoSolrApplicationTests {

    @Autowired
    private SolrClient solrClient;

    @Test
    void synchronizeData() {
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", "01");
        document.addField("name", "张三");
        document.addField("age", "23");
        document.addField("hobby", "看电影，打篮球，旅游");
        try {
            solrClient.add(document);
            solrClient.commit();
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void queryData() {
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("name:张三");
        try {
            QueryResponse response = solrClient.query(solrQuery);
            log.info(response.toString());
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void update(){
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", "02");
        document.addField("name", "张三");
        document.addField("age", "244");
        document.addField("hobby", "看电影，打篮球，旅游");
        try {
            solrClient.add(document);
            solrClient.commit();
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void deleteTest(){
        try {
            solrClient.deleteByQuery("id:02");
            solrClient.commit();
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
    }

}

