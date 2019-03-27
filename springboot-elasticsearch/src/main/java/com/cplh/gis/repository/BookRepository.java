package com.cplh.gis.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.cplh.gis.bean.Book;

public interface BookRepository extends ElasticsearchRepository<Book, Integer> {
	
}
