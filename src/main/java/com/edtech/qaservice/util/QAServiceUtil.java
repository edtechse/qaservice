package com.edtech.qaservice.util;

import com.edtech.qaservice.model.AnswerItem;
import com.edtech.qaservice.model.QuestionItem;

import java.util.List;
import java.util.UUID;

public class QAServiceUtil {
    public static QuestionItem checkIfAnswerHasId(QuestionItem questionItem) {
        List<AnswerItem> answerList = questionItem.getAnswers();
        for(AnswerItem answer: answerList) {
            if (answer.getAnswerId() == null) {
                String answerId = UUID.randomUUID().toString().replaceAll("-", "");
                answer.setAnswerId(answerId);
            }
        }
        questionItem.setAnswers(answerList);
        return questionItem;
    }
}
