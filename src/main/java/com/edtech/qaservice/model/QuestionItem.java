package com.edtech.qaservice.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Date;

@DynamoDBTable(tableName="Question")
public class QuestionItem {
    private int questionId;
    private String author;
    private String questionTitle;
    private String createDate;
    private String questionText;
    private String questionTag;

    @DynamoDBHashKey(attributeName = "questionId")
    public int getQuestionId() { return questionId; }
    public void setQuestionId(int questionId) { this.questionId = questionId; }

    @DynamoDBRangeKey(attributeName = "author")
    public String getAuthor() { return author;}
    public void setAuthor(String author) {this.author = author;}

    @DynamoDBAttribute(attributeName="questionTitle")
    public String getQuestionTitle() { return questionTitle;}
    public void setQuestionTitle(String questionTitle) {this.questionTitle = questionTitle;}

    @DynamoDBAttribute(attributeName = "createDate")
    public String getCreateDate() { return createDate;}
    public void setCreateDate(String createDate) {this.createDate = createDate;}

    @DynamoDBAttribute(attributeName = "questionText")
    public String getQuestionText() { return questionText; }
    public void setQuestionText(String questionText) { this.questionText = questionText; }

    @DynamoDBAttribute(attributeName = "questionTag")
    public String getQuestionTag() { return questionTag;}
    public void setQuestionTag(String questionTag) {this.questionTag = questionTag;}

    @Override
    public String toString() {
        return "Question [questionId=" + questionId + ", questionTitle=" + questionTitle + ", createDate=" + createDate
                + ", questionText=" + questionText + ", author=" + author + ", questionTag=" + questionTag + "]";
    }
}
