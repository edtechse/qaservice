package com.edtech.qaservice.service;

import com.edtech.qaservice.controller.QuestionController;
import com.edtech.qaservice.model.QuestionItem;
import com.edtech.qaservice.repository.QARepository;
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
public class QAServiceTest {

    @Mock
    private QARepository qaRepository;

    @InjectMocks
    private QAService qaService;

    private QuestionItem questionItem;

    private List<QuestionItem> questionItemListMock;

    private List<String> questionIds;

    @Before
    public void init(){
        questionItemListMock = new ArrayList<QuestionItem>();
        questionIds = new ArrayList<String>();
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
        questionIds.add("u1");
    }

    @Test
    public void getAllQuestion_isSuccess(){
        Mockito.when(qaRepository.findAllQuestion()).thenReturn(questionItemListMock);
        List<QuestionItem> questionItemsActual = qaService.getAllQuestion();

        Assert.assertEquals(questionItemListMock.size(),questionItemsActual.size());
        Assert.assertEquals(questionItemListMock.get(0).getQuestionId(),questionItemsActual.get(0).getQuestionId());
        Assert.assertEquals(questionItemListMock.get(0).getAuthor(),questionItemsActual.get(0).getAuthor());
        Assert.assertEquals(questionItemListMock.get(0).getQuestionTitle(),questionItemsActual.get(0).getQuestionTitle());
        Assert.assertEquals(questionItemListMock.get(0).getQuestionText(),questionItemsActual.get(0).getQuestionText());
        Assert.assertEquals(questionItemListMock.get(0).getQuestionTag(),questionItemsActual.get(0).getQuestionTag());
        Assert.assertEquals(questionItemListMock.get(0).getQuestionTimestamp(),questionItemsActual.get(0).getQuestionTimestamp());
    }

    @Test
    public void getAllQuestion_isEmpty() {
        List<QuestionItem> emptyQuestionItemList = new ArrayList<QuestionItem>();
        Mockito.when(qaRepository.findAllQuestion()).thenReturn(emptyQuestionItemList);
        List<QuestionItem> questionItemsActual = qaService.getAllQuestion();
        Assert.assertEquals(new ArrayList() {}, questionItemsActual);
        Assert.assertEquals(0,questionItemsActual.size());
    }

    @Test
    public void getQuestionIdByAuthor_isSuccess(){

        Mockito.when(qaRepository.findQuestionByAuthor("u1")).thenReturn(questionItemListMock);
        List<String> questionItemsActual = qaService.getQuestionIdByAuthor("u1");

        Assert.assertEquals(questionItemListMock.size(),questionItemsActual.size());
        Assert.assertEquals(questionItemListMock.get(0).getQuestionId(),questionItemsActual.get(0));
    }

    @Test
    public void getQuestionIdByAuthor_isEmpty(){

        List<QuestionItem> emptyQuestionItemList = new ArrayList<QuestionItem>();
        Mockito.when(qaRepository.findQuestionByAuthor("u1_nonexist")).thenReturn(emptyQuestionItemList);
        List<String> questionIdsActual = qaService.getQuestionIdByAuthor("u1_nonexist");

        Assert.assertEquals(new ArrayList(){},questionIdsActual);
        Assert.assertEquals(0,questionIdsActual.size());
    }
}
