package it.polimi.se2018.objective_cards;

import it.polimi.se2018.model.PlayerBoard;
import it.polimi.se2018.model.cell.Die;
import it.polimi.se2018.utils.enums.BoardName;
import it.polimi.se2018.utils.enums.Color;
import it.polimi.se2018.utils.enums.NumberEnum;
import it.polimi.se2018.utils.exceptions.AlredySetDie;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestCard2ColumnColorVariety {

    private PlayerBoard playerBoard;
    private Die die;
    private ArrayList<Color> columnColor;
    private Card2ColumnColorVariety card;

    @Before
    public void setUp() {

        columnColor = new ArrayList<Color>();
        card = new Card2ColumnColorVariety(PublicObjectiveName.COLORIDIVERSICOLONNA);
        playerBoard = new PlayerBoard(BoardName.KALEIDOSCOPICDREAM);

        //Row 0

        die = new Die(Color.YELLOW);
        try {
            playerBoard.setDie(die, 0,0);
        } catch (AlredySetDie alredySetDie) {
            fail();
        }

        die = new Die(Color.BLUE);
        try {
            playerBoard.setDie(die, 0,1);
        } catch (AlredySetDie alredySetDie) {
            fail();
        }

        die = new Die(Color.RED);
        try {
            playerBoard.setDie(die, 0,2);
        } catch (AlredySetDie alredySetDie) {
            fail();
        }

        die = new Die(Color.PURPLE);
        try {
            playerBoard.setDie(die, 0,3);
        } catch (AlredySetDie alredySetDie) {
            fail();
        }

        die = new Die(Color.GREEN);
        die.setNumber(NumberEnum.ONE);
        try {
            playerBoard.setDie(die, 0,4);
        } catch (AlredySetDie alredySetDie) {
            fail();
        }

        //Row 1

        die = new Die(Color.GREEN);
        try {
            playerBoard.setDie(die, 1,0);
        } catch (AlredySetDie alredySetDie) {
            fail();
        }

        die = new Die(Color.RED);
        try {
            playerBoard.setDie(die, 1,1);
        } catch (AlredySetDie alredySetDie) {
            fail();
        }

        die = new Die(Color.GREEN);
        try {
            playerBoard.setDie(die, 1,3);
        } catch (AlredySetDie alredySetDie) {
            fail();
        }

        //Row 2

        die = new Die(Color.YELLOW);
        die.setNumber(NumberEnum.THREE);
        try {
            playerBoard.setDie(die, 2,0);
        } catch (AlredySetDie alredySetDie) {
            fail();
        }

        die = new Die(Color.PURPLE);
        try {
            playerBoard.setDie(die, 2,1);
        } catch (AlredySetDie alredySetDie) {
            fail();
        }

        die = new Die(Color.RED);
        try {
            playerBoard.setDie(die, 2,2);
        } catch (AlredySetDie alredySetDie) {
            fail();
        }

        die = new Die(Color.YELLOW);
        try {
            playerBoard.setDie(die, 2,3);
        } catch (AlredySetDie alredySetDie) {
            fail();
        }

        die = new Die(Color.GREEN);
        try {
            playerBoard.setDie(die, 2,4);
        } catch (AlredySetDie alredySetDie) {
            fail();
        }


        //Row 3

        die = new Die(Color.GREEN);
        die.setNumber(NumberEnum.TWO);
        try {
            playerBoard.setDie(die, 3,0);
        } catch (AlredySetDie alredySetDie) {
            fail();
        }

        die = new Die(Color.YELLOW);
        try {
            playerBoard.setDie(die, 3,1);
        } catch (AlredySetDie alredySetDie) {
            fail();
        }

        die = new Die(Color.BLUE);
        try {
            playerBoard.setDie(die, 3,3);
        } catch (AlredySetDie alredySetDie) {
            fail();
        }

    }

    @Test
    public void getPoints() {

        int point;

        point = card.getPoints(playerBoard);
        assertEquals(10, point);

        playerBoard = new PlayerBoard(BoardName.KALEIDOSCOPICDREAM);
        point = card.getPoints(playerBoard);
        assertEquals(0, point);
    }

    @Test
    public void controlColorColumn() {

        boolean bool;

        bool = card.controlColorColumn(columnColor);
        assertFalse(bool);

        columnColor.add(Color.BLUE);
        columnColor.add(Color.RED);
        columnColor.add(Color.GREEN);
        columnColor.add(Color.YELLOW);
        columnColor.add(Color.PURPLE);

        bool = card.controlColorColumn(columnColor);
        assertTrue(bool);

        columnColor.add(Color.BLUE);
        bool = card.controlColorColumn(columnColor);
        assertFalse(bool);
    }

    @Test
    public void removeAll() {

        int size;

        size = 0;
        card.removeAll(columnColor);
        assertEquals(size,columnColor.size());

        columnColor.add(Color.BLUE);
        columnColor.add(Color.RED);
        columnColor.add(Color.GREEN);
        columnColor.add(Color.YELLOW);
        columnColor.add(Color.PURPLE);

        size = 5;
        assertEquals(size, columnColor.size());

        size = 0;
        card.removeAll(columnColor);
        assertEquals(size, columnColor.size());

    }
}