package com.edtech.qaservice.controller;

import com.edtech.qaservice.model.QuestionItem;
import com.edtech.qaservice.service.QAService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(MockitoJUnitRunner.class)
public class QuestionControllerTest {

    @Mock
    private QAService qaService;

    @InjectMocks
    private QuestionController questionController;

    private QuestionItem questionItem;

    private List<QuestionItem> questionItemListMock;

    @Before
    public void init(){
        questionItemListMock = new ArrayList<QuestionItem>();
        questionItem = new QuestionItem();
        questionItem.setQuestionId("1234");
        questionItem.setAuthor("u1");
        questionItem.setQuestionTitle("Mockito, JUnit and Spring");
        questionItem.setQuestionText("I started to learn about Mockito only today. I wrote some simple test (with JUnit, see below), but I can't figure out how can I use mock object inside Spring's managed beans." +
                " What is best practices for working with Spring. How should I inject mocked dependency to my bean?");
        Set<String> tags = new HashSet<String>();
        tags.add("JUnit");
        tags.add("Mockito");
        questionItem.setQuestionTag(tags);
        questionItem.setQuestionTimestamp("11-11-2021");
        questionItemListMock.add(questionItem);
    }

    @Test
    public void getAllQuestion_isSuccess(){
        Mockito.when(qaService.getAllQuestion()).thenReturn(questionItemListMock);
        List<QuestionItem> questionItemsActual = questionController.getAllQuestion();

        Assert.assertEquals(questionItemListMock.size(),questionItemsActual.size());


    }

}
