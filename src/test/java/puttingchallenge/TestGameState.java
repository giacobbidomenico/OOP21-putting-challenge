package puttingchallenge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import javax.swing.JFrame;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.embed.swing.JFXPanel;
import puttingchallenge.model.GamePlayGameState;
import puttingchallenge.model.GameStateManager;
import puttingchallenge.model.GameStateManagerImpl;
import puttingchallenge.model.GameStatus;
import puttingchallenge.model.events.ConcreteMediator;
import puttingchallenge.model.events.Mediator;

class TestGameState {
    private GameStateManager gsm;
    private static final int MAX_LIVES = 3;
    private static final int NONE = 0;
    @BeforeEach void start() {
        final JFrame frame = new JFrame();
        final JFXPanel jFx = new JFXPanel();
        frame.add(jFx);
        gsm = new GameStateManagerImpl();
        final Mediator mediator = new ConcreteMediator();
        mediator.addColleague(gsm);
        gsm.setMediator(mediator);
        gsm.initState();
    }
    @Test
    void gameStateTest() {
        assertEquals(gsm.getCurrentEnvironment(), Optional.empty());
        assertEquals(gsm.getCurrentState().getStatus(), GameStatus.MAIN_MENU);
        gsm.switchState(GameStatus.PLAY);
        assertEquals(gsm.getCurrentState().getStatus(), GameStatus.PLAY);
        final GamePlayGameState gpgs = (GamePlayGameState) gsm.getCurrentState();
        assertEquals(gpgs.getLives(), MAX_LIVES);
        assertEquals(gpgs.getScore(), NONE);
        
    }

}
