package com.ll.exam.sbb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class SbbApplicationTests {
	@Autowired
	private QuestionRepository questionRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testJpa() {
		Question q1 = new Question();
		q1.setSubject("sbb가 무엇인가요?");
		q1.setContent("sbb에 대해서 알고 싶네요.");
		q1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q1); //첫 번째 질문 저장

		Question q2 = new Question();
		q2.setSubject("spring boot가 무엇인가요?");
		q2.setContent("spring boot에 대해서 알고 싶네요.");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2); //두 번째 질문 저장

	}


}
