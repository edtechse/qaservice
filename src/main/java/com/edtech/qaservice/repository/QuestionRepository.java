package com.edtech.qaservice.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.edtech.qaservice.model.QuestionItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class QuestionRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public QuestionItem findQuestionById(int questionId) {
        return dynamoDBMapper.load(QuestionItem.class, 1, "Garfield");
    }

}
