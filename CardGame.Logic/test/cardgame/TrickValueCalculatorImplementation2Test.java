/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import cardgame.trickValueCalculators.TrickValueCalculatorImplementation2;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author KG Studia
 */
@RunWith(Parameterized.class)
public class TrickValueCalculatorImplementation2Test extends TrickValueCalculatorImplementationTest {

    public TrickValueCalculatorImplementation2Test(List<Card> cards, int expectedTrickValue) {
        super(cards, expectedTrickValue);
    }
       
    
    @Before
    @Override
    public void initialize() {
        instance = new TrickValueCalculatorImplementation2();
    }
}
