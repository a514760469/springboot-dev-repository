package com.cplh.gis;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cplh.gis.bean.Article;
import com.cplh.gis.bean.Book;
import com.cplh.gis.repository.BookRepository;
import com.google.gson.JsonObject;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootElasticsearchApplicationTests {

	@Autowired
	JestClient jestClient;
	
	@Autowired
	BookRepository bookRepository;
	
	@Test
	public void testBookRepository() {
		Book book = new Book();
		book.setId(1);
		book.setAuthor("张三");
		book.setBookName("张三长大的");
//		bookRepository.index(book);
		Iterable<Book> it = bookRepository.findAll();
		it.forEach(o -> System.out.println(o));
	}
	
	
	@Test
	public void contextLoads() {
		Article article = new Article();
		article.setId(1);
		article.setAuthor("zhangsan");
		article.setTitle("好消息");
		article.setContent("hello world es");
		
		Index index = new Index.Builder(article).index("spring").type("news").build();
		try {
			jestClient.execute(index);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSearch() throws IOException {
		String json = "{ \"query\" : { \"match\" : { \"last_name\" : \"Smith\" }}}";
		Search search = new Search.Builder(json).addIndex("megacorp").addType("employee").build();
		SearchResult result = jestClient.execute(search);
		JsonObject jsonObject = result.getJsonObject();
		System.out.println(jsonObject);
	}
}

