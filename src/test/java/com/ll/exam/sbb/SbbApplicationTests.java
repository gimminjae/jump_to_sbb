package com.ll.exam.sbb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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

		assertThat(q1.getId()).isGreaterThan(0);
		assertThat(q2.getId()).isGreaterThan(1);
	}

	@Test
	void testJpa2() {
		List<Question> all = questionRepository.findAll(); //select * from question;
		assertThat(2).isEqualTo(all.size());

		Question q = all.get(0);
		assertThat(q.getSubject()).isEqualTo("sbb가 무엇인가요?");
	}
	@Test
	void testJpa3() {
		Question q = questionRepository.findBySubject("sbb가 무엇인가요?");
		assertThat(q.getId()).isEqualTo(1);
	}
	@Test
	void testJpa4() {
		Question q = this.questionRepository.findBySubjectAndContent("sbb가 무엇인가요?", "sbb에 대해서 알고 싶네요.");
		assertThat(q.getId()).isEqualTo(1);
	}

}
