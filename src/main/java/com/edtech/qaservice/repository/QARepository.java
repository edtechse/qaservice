package com.edtech.qaservice.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.edtech.qaservice.model.QuestionItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class QARepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public QuestionItem findQuestionById(String id) {
        return dynamoDBMapper.load(QuestionItem.class, id);
    }

    public void createQuestionByQuestionItem(QuestionItem questionItem) {
        dynamoDBMapper.save(questionItem);
    }

}
