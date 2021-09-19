package com.edtech.qaservice.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.edtech.qaservice.model.AnswerItem;
import com.edtech.qaservice.model.QuestionItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class QARepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public QuestionItem findQuestionById(String id) {
        return dynamoDBMapper.load(QuestionItem.class, id);
    }

    public List<QuestionItem> findAllQuestion() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();

        List<QuestionItem> scanResult = dynamoDBMapper.scan(QuestionItem.class, scanExpression);

        return scanResult;
    }

    public void deleteQuestionByQuestionItem(QuestionItem questionItem) {
        dynamoDBMapper.delete(questionItem);
    }

    public void saveQuestionByQuestionItem(QuestionItem questionItem) {
        dynamoDBMapper.save(questionItem);
    }

    public List<QuestionItem> findQuestionByAuthor(String author) {
        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":val", new AttributeValue().withS(author));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("author = :val").withExpressionAttributeValues(eav);

        List<QuestionItem> scanResult = dynamoDBMapper.scan(QuestionItem.class, scanExpression);

        return scanResult;
    }
}
