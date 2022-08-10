package com.ll.exam.sbb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SbbApplicationTests {
	@Autowired
	private QuestionRepository questionRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testJpa() { //추가
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
	void testJpa2() { //조회
		List<Question> all = questionRepository.findAll(); //select * from question;
		assertThat(2).isEqualTo(all.size());

		Question q = all.get(0);
		assertThat(q.getSubject()).isEqualTo("sbb가 무엇인가요?");
	}
	@Test
	void testJpa3() { //주제로 조회
		Question q = questionRepository.findBySubject("sbb가 무엇인가요?");
		assertThat(q.getId()).isEqualTo(1);
	}
	@Test
	void testJpa4() { //주제와 내용으로 조회
		Question q = this.questionRepository.findBySubjectAndContent("sbb가 무엇인가요?", "sbb에 대해서 알고 싶네요.");
		assertThat(q.getId()).isEqualTo(1);
	}
	@Test
	void testJpa5() { //sbb로 시작하는 주제로 조회
		List<Question> qList = this.questionRepository.findBySubjectLike("sbb%");
		Question q = qList.get(0);
		assertEquals("sbb가 무엇인가요?", q.getSubject());
	}
	@Test
	void testJpa6() { //id로 조회 및 수정
		Optional<Question> oq = this.questionRepository.findById(1);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		q.setContent("수정된 제목");
		this.questionRepository.save(q);
	}

	@Test
	void testJpa7() { //삭제
		assertEquals(2, this.questionRepository.count());
		Optional<Question> oq = this.questionRepository.findById(1);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		this.questionRepository.delete(q);
		assertEquals(1, this.questionRepository.count());
	}

}
