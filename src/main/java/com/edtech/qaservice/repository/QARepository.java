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

    // CRUD operation for question
    public QuestionItem findQuestionById(String id) {
        return dynamoDBMapper.load(QuestionItem.class, id);
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

    // CRUD operation for answer
    public AnswerItem findAnswerById(String id) {
        return dynamoDBMapper.load(AnswerItem.class, id);
    }

    public void deleteAnswerByAnswerItem(AnswerItem answerItem) {
        dynamoDBMapper.delete(answerItem);
    }

    public void saveAnswerByAnswerItem(AnswerItem answerItem) {
        dynamoDBMapper.save(answerItem);
    }

    public List<AnswerItem> findAnswerByAuthor(String author) {
        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":val", new AttributeValue().withS(author));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("author = :val").withExpressionAttributeValues(eav);

        List<AnswerItem> scanResult = dynamoDBMapper.scan(AnswerItem.class, scanExpression);

        return scanResult;
    }

    public List<AnswerItem> findAnswerByQuestionId(String questionId) {
        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":val", new AttributeValue().withS(questionId));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("questionId = :val").withExpressionAttributeValues(eav);

        List<AnswerItem> scanResult = dynamoDBMapper.scan(AnswerItem.class, scanExpression);

        return scanResult;
    }
}
