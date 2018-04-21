package tw.core;

import org.junit.Test;
import tw.core.exception.OutOfRangeAnswerException;
import tw.core.model.Record;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * 在AnswerTest文件中完成Answer中对应的单元测试
 */
public class AnswerTest {
    @Test
    public void should_returnAnswerWithCorrectList_whenInputStr() {
        String input = "1 2 3 4";
        Answer result = Answer.createAnswer(input);

        assertThat(result.getIndexOfNum("2"), is(1));
    }


    @Test
    public void should_PassValidate_when_inputDistinctAnswer() throws OutOfRangeAnswerException {
        String input = "1 2 3 4";
        Answer answer = Answer.createAnswer(input);

        answer.validate();
    }

    @Test(expected = OutOfRangeAnswerException.class)
    public void should_throwOutofRangeAnserException_when_InputRepeatAnswer() throws OutOfRangeAnswerException {
        String input = "1 2 2 4";
        Answer answer = Answer.createAnswer(input);

        answer.validate();
    }

    @Test
    public void should_returnRecord21_when_input1245_given_correctAnswer1234() {
        String correctAnwser = "1 2 3 4";
        Answer answer = Answer.createAnswer(correctAnwser);
        Answer inputAnswer = Answer.createAnswer("1 2 4 5");

        Record record = answer.check(inputAnswer);

        assertThat(record.getValue(),is(new int[]{2, 1}));
    }
}