package tw.controllers;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tw.commands.InputCommand;
import tw.core.Answer;
import tw.core.Game;
import tw.core.exception.OutOfRangeAnswerException;
import tw.core.generator.AnswerGenerator;
import tw.views.GameView;

import java.io.IOException;

import static org.mockito.Mockito.*;

/**
 * 在GameControllerTest文件中完成GameController中对应的单元测试
 */
public class GameControllerTest {
    @Mock
    GameView gameView;
    @Mock
    InputCommand inputCommand;
    @Mock
    AnswerGenerator answerGenerator;
    Game game;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(answerGenerator.generate()).thenReturn(Answer.createAnswer("1 2 3 4"));
        game = new Game(answerGenerator);
    }

    @Test
    @Ignore
    public void should_playSuccess_when_guessCorrectAnswerWithIn1Time() throws OutOfRangeAnswerException, IOException {
        GameController gameController = new GameController(game, gameView);
        Answer answer = Answer.createAnswer("1 2 3 4");
        when(inputCommand.input()).thenReturn(answer);

        gameController.play(inputCommand);

        verify(gameView, times(1)).showGameStatus("success");
    }

    @Test
    public void should_playSuccess_when_guessCorrectAnswerWithIn3Time() throws OutOfRangeAnswerException, IOException {
        GameController gameController = new GameController(game, gameView);
        Answer wrongAnswer1 = Answer.createAnswer("1 2 4 3");
        Answer wrongAnswer2 = Answer.createAnswer("1 2 3 5");
        Answer correctAnswer = Answer.createAnswer("1 2 3 4");
        when(inputCommand.input()).thenReturn(wrongAnswer1).thenReturn(wrongAnswer2).thenReturn(correctAnswer);

        gameController.play(inputCommand);

        verify(gameView, times(1)).showGameStatus("success");
        verify(gameView, times(3)).showGuessResult(any());
    }

//    @Test
//    public void should_playSuccess_when_guessCorrectAnswerWithIn3Time() throws OutOfRangeAnswerException, IOException {
//        GameController gameController = new GameController(game, gameView);
//        Answer answer = Answer.createAnswer("1 2 3 4");
//        GuessResult guessResult = new GuessResult("4A0B", answer);
//        when(inputCommand.input()).thenReturn(answer).thenReturn(answer);
//        when(game.guess(answer)).thenReturn(guessResult);
//        when(game.checkStatus()).thenReturn("success");
//        when(game.checkCoutinue()).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(false);
//
//        gameController.play(inputCommand);
//
//        verify(gameView, times(1)).showGameStatus("success");
//        verify(gameView, times(3)).showGuessResult(any());
//    }
}