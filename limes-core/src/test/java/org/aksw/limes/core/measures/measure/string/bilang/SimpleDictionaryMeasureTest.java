package org.aksw.limes.core.measures.measure.string.bilang;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.nio.file.Paths;
import org.aksw.limes.core.measures.measure.AMeasure;
import org.junit.Test;

/**
 * Testing the {@link SimpleDictionaryMeasure}. No semantic similarity is considered here.
 *
 * @author Swante Scholz
 */
public class SimpleDictionaryMeasureTest {
    
    @Test
    public void testSimilarity() {
        BilangDictionary d = new BilangDictionary(Paths.get("src/test/resources/en-de-small.txt"));
        System.out.println(d.sourceSize());
        System.out.println(d.targetSize());
        AMeasure measure = new SimpleDictionaryMeasure(d);
        System.out.println(
            measure.getSimilarity("dog", "Katze") + " " + measure.getSimilarity("dog", "Hund"));
        System.out.println(
            measure.getSimilarity("dogg", "huund") + " " + measure.getSimilarity("dog", "Hund"));
        assertTrue(measure.getSimilarity("dog", "Katze") < measure.getSimilarity("dog", "Hund"));
        assertTrue(measure.getSimilarity("dogg", "huund") < measure.getSimilarity("dog", "Hund"));
        assertEquals(measure.getSimilarity("dog", "hund"), measure.getSimilarity("dog", "Hund"),
            0.0);
        
    }
}