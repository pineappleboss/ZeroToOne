package com.example.demo;

import com.example.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}
	@Autowired
	private ElasticsearchRestTemplate elasticsearchRestTemplate;
	@Test
	/**
	 * 新增文档
	 */
	public void testCreateDoc() throws Exception{
		Person person = new Person("1005","陈立志",23,"2345631234@qq.com","跳舞、踢足球、下围棋");
		IndexQuery indexQuery = new IndexQueryBuilder()
				.withId(person.getId().toString())
				.withObject(person)
				.build();
		String documentId = elasticsearchRestTemplate.index(indexQuery,IndexCoordinates.of("tkqtest"));
		System.out.println(documentId);
	}

}
