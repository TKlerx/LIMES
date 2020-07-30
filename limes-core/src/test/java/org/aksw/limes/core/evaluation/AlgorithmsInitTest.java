package org.aksw.limes.core.evaluation;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.aksw.limes.core.datastrutures.TaskAlgorithm;
import org.aksw.limes.core.exceptions.UnsupportedMLImplementationException;
import org.aksw.limes.core.measures.measure.MeasureType;
import org.aksw.limes.core.ml.algorithm.AMLAlgorithm;
import org.aksw.limes.core.ml.algorithm.Eagle;
import org.aksw.limes.core.ml.algorithm.LearningParameter;
import org.aksw.limes.core.ml.algorithm.MLAlgorithmFactory;
import org.aksw.limes.core.ml.algorithm.MLImplementationType;
import org.aksw.limes.core.ml.algorithm.WombatComplete;
import org.aksw.limes.core.ml.algorithm.WombatSimple;
import org.junit.Test;

public class AlgorithmsInitTest {
    final public String[] algorithmsListData = {"UNSUPERVISED:WOMBATSIMPLE","SUPERVISED_BATCH:WOMBATSIMPLE"};


    @Test
    public void test() {
        initializeMLAlgorithms(algorithmsListData,1);
    }
    //remember
    //---------AMLAlgorithm(concrete:SupervisedMLAlgorithm,ActiveMLAlgorithm or UnsupervisedMLAlgorithm--------
    //---                                                                                              --------
    //---         ACoreMLAlgorithm (concrete: EAGLE,WOMBAT,LION) u can retrieve it by get()            --------
    //---                                                                                              --------
    //---------------------------------------------------------------------------------------------------------

        public List<TaskAlgorithm> initializeMLAlgorithms(String[] algorithmsList, int datasetsNr) {
        if(algorithmsList==null)
            algorithmsList = algorithmsListData;
        List<TaskAlgorithm> mlAlgorithms = null;

        try
        {
            mlAlgorithms = new ArrayList<TaskAlgorithm>();
            for (String algorithmItem : algorithmsList) {
                String[] algorithmTitles = algorithmItem.split(":");// split to get the type and the name of the algorithm
                MLImplementationType algType = MLImplementationType.valueOf(algorithmTitles[0]);// get the type of the algorithm

                List<LearningParameter<?>> mlParameter = null;
                AMLAlgorithm mlAlgorithm = null;
                //check the mlImplementation Type
                if(algType.equals(MLImplementationType.SUPERVISED_ACTIVE))
                {
                    if(algorithmTitles[1].equals("EAGLE"))//create its core as eagle - it will be enclosed inside SupervisedMLAlgorithm that extends AMLAlgorithm
                        mlAlgorithm =  MLAlgorithmFactory.createMLAlgorithm(Eagle.class,algType).asActive(); //create an eagle learning algorithm
                    else if(algorithmTitles[1].equals("WOMBATSIMPLE"))
                        mlAlgorithm =  MLAlgorithmFactory.createMLAlgorithm(WombatSimple.class,algType).asActive(); //create an wombat simple learning algorithm
                    else if(algorithmTitles[1].equals("WOMBATCOMPLETE"))
                        mlAlgorithm =  MLAlgorithmFactory.createMLAlgorithm(WombatComplete.class,algType).asActive(); //create an wombat complete learning algorithm
                    
                   mlParameter = initializeLearningParameters(MLImplementationType.SUPERVISED_ACTIVE,algorithmTitles[1]);

                }
                else if(algType.equals(MLImplementationType.SUPERVISED_BATCH))
                {
                    if(algorithmTitles[1].equals("EAGLE"))
                        mlAlgorithm =  MLAlgorithmFactory.createMLAlgorithm(Eagle.class,algType).asSupervised(); //create an eagle learning algorithm
                    else if(algorithmTitles[1].equals("WOMBATSIMPLE"))
                        mlAlgorithm =  MLAlgorithmFactory.createMLAlgorithm(WombatSimple.class,algType).asSupervised(); //create an wombat simple learning algorithm
                    else if(algorithmTitles[1].equals("WOMBATCOMPLETE"))
                        mlAlgorithm =  MLAlgorithmFactory.createMLAlgorithm(WombatComplete.class,algType).asSupervised(); //create an wombat complete learning algorithm
                    
                    mlParameter = initializeLearningParameters(MLImplementationType.SUPERVISED_BATCH,algorithmTitles[1]);

                }
                else if(algType.equals(MLImplementationType.UNSUPERVISED))
                {
                    if(algorithmTitles[1].equals("EAGLE"))
                        mlAlgorithm =  MLAlgorithmFactory.createMLAlgorithm(Eagle.class,algType).asUnsupervised(); //create an eagle learning algorithm
                    else if(algorithmTitles[1].equals("WOMBATSIMPLE"))
                        mlAlgorithm =  MLAlgorithmFactory.createMLAlgorithm(WombatSimple.class,algType).asUnsupervised(); //create an wombat simple learning algorithm
                    else if(algorithmTitles[1].equals("WOMBATCOMPLETE"))
                        mlAlgorithm =  MLAlgorithmFactory.createMLAlgorithm(WombatComplete.class,algType).asUnsupervised(); //create an wombat complete learning algorithm
                    
                    mlParameter = initializeLearningParameters(MLImplementationType.UNSUPERVISED,algorithmTitles[1]);

                }

                //TODO add other classes cases

                mlAlgorithms.add(new TaskAlgorithm(algType, mlAlgorithm, mlParameter));// add to list of algorithms
            }

        }catch (UnsupportedMLImplementationException e) {
            e.printStackTrace();
            assertTrue(false);
        }
        assertTrue(true);
        return mlAlgorithms;
    }

     private List<LearningParameter<?>> initializeLearningParameters(MLImplementationType mlType, String className) {
        List<LearningParameter<?>> lParameters = null;
        if(mlType.equals(MLImplementationType.UNSUPERVISED))
        {
            if(className.equals("WOMBATSIMPLE") || className.equals("WOMBATCOMPLETE"))
                lParameters = initializeWombatSimple();

        }
        else  if(mlType.equals(MLImplementationType.SUPERVISED_ACTIVE))
        {
            if(className.equals("WOMBATSIMPLE") || className.equals("WOMBATCOMPLETE"))
                lParameters = initializeWombatSimple();

        }
        else  if(mlType.equals(MLImplementationType.SUPERVISED_BATCH))
        {
            if(className.equals("WOMBATSIMPLE") || className.equals("WOMBATCOMPLETE"))
                lParameters = initializeWombatSimple();

        }
        return lParameters;

    }

    private List<LearningParameter<?>> initializeWombatSimple()
    {
        List<LearningParameter<?>> wombaParameters = new ArrayList<>() ;

        wombaParameters.add(new LearningParameter<Integer>("max refinement tree size", 2000, 10, Integer.MAX_VALUE, 10, "max refinement tree size"));
        wombaParameters.add(new LearningParameter<Integer>("max iterations number", 3, 1, Integer.MAX_VALUE, 10, "max iterations number"));
        wombaParameters.add(new LearningParameter<Integer>("max iteration time in minutes", 20, 1, Integer.MAX_VALUE,1, "max iteration time in minutes"));
        wombaParameters.add(new LearningParameter<Double>("max execution time in minutes", 600d, 1d, Double.MAX_VALUE,1d, "max execution time in minutes"));
        wombaParameters.add(new LearningParameter<Double>("max fitness threshold", 1d, 0d, 1d, 0.01d, "max fitness threshold"));
        wombaParameters.add(new LearningParameter<Double>("minimum properity coverage", 0.4, 0d, 1d, 0.01d, "minimum properity coverage"));
        wombaParameters.add(new LearningParameter<Double>("properity learning rate", 0.9, 0d, 1d, 0.01d, "properity learning rate"));
        wombaParameters.add(new LearningParameter<Double>("overall penalty weit", 0.5d, 0d, 1d, 0.01d, "overall penalty weit"));
        wombaParameters.add(new LearningParameter<Double>("children penalty weit", 1d, 0d, 1d, 0.01d, "children penalty weit"));
        wombaParameters.add(new LearningParameter<Double>("complexity penalty weit", 1d, 0d, 1d, 0.01d, "complexity penalty weit"));
        wombaParameters.add(new LearningParameter<Boolean>("verbose", false, false, true, null, "verbose"));
        wombaParameters.add(new LearningParameter<Set<MeasureType>>("measures", new HashSet<MeasureType>(Arrays.asList(MeasureType.JACCARD, MeasureType.TRIGRAM, MeasureType.COSINE, MeasureType.QGRAMS)), null, null, null, "measures"));
        wombaParameters.add(new LearningParameter<Boolean>("save mapping", true, false, true, null, "save mapping"));



        return wombaParameters;
    }

}
