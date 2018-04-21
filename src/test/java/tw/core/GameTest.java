package tw.core;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tw.core.exception.OutOfRangeAnswerException;
import tw.core.generator.AnswerGenerator;
import tw.core.model.GuessResult;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * 在GameTest文件中完成Game中对应的单元测试
 */


public class GameTest {

    @Mock
    AnswerGenerator answerGenerator;
    private Game game;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        game = prepareTest();
    }

    @Test
    public void should_returnGussResult_when_inputAnswer() {
        GuessResult guessResult = game.guess(Answer.createAnswer("1 2 4 5"));

        assertThat(guessResult.getResult(),is("2A1B"));
    }

    @Test
    public void should_returnSuccess_when_guessCorrectAtLimitedTimes() {
        game.guess(Answer.createAnswer("1 2 4 5"));
        game.guess(Answer.createAnswer("2 1 3 5"));
        game.guess(Answer.createAnswer("1 2 3 4"));

        String result = game.checkStatus();

        assertThat(game.guessHistory().size(),is(3));
        assertThat(result,is("success"));
    }

    @Test
    public void should_returnFailure_when_guessWrongMoreThanLimitedTimes() {
        game.guess(Answer.createAnswer("1 2 4 5"));
        game.guess(Answer.createAnswer("2 1 3 5"));
        game.guess(Answer.createAnswer("1 2 3 7"));
        game.guess(Answer.createAnswer("1 2 3 7"));
        game.guess(Answer.createAnswer("1 2 3 7"));
        game.guess(Answer.createAnswer("1 2 3 7"));
        game.guess(Answer.createAnswer("1 2 3 7"));

        String result = game.checkStatus();

        assertThat(game.guessHistory().size(),is(7));
        assertThat(result,is("fail"));
    }

    @Test
    public void should_returnContinue_when_guessWrongLessThanLimitedTimes() {
        game.guess(Answer.createAnswer("1 2 4 5"));
        game.guess(Answer.createAnswer("2 1 3 5"));
        game.guess(Answer.createAnswer("1 2 3 7"));
        game.guess(Answer.createAnswer("1 2 3 7"));

        String result = game.checkStatus();

        assertThat(game.guessHistory().size(),is(4));
        assertThat(result,is("continue"));
    }

    private Game prepareTest() throws OutOfRangeAnswerException {
        when(answerGenerator.generate()).thenReturn(Answer.createAnswer("1 2 3 4"));
        return new Game(answerGenerator);
    }
}
