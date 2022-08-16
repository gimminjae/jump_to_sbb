package com.ll.exam.sbb;

import com.ll.exam.sbb.question.Question;

//이 예외가 발생하면 프로그램이 정지한다. -> RuntimeException
public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(String question_not_found) {
        super();
    }
}
