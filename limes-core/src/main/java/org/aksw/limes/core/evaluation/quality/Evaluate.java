/**
 * 
 */
package org.aksw.limes.core.evaluation.quality;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aksw.limes.core.evaluation.EvalFunc;
import org.aksw.limes.core.io.mapping.Mapping;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
/**
 * @author mofeed
 *
 */
public class Evaluate {

	Map<EvalFunc,Double> evaluations = new HashMap<EvalFunc,Double>();
	//long sourceDatasetSize, long targetDatasetSize
	public Map<EvalFunc,Double> evaluate (Mapping predictions, Mapping goldStandard,List<String> sourceUris, List<String> targetUris ,EvalFunc evaluationFunc)
	{
		
		if(evaluationFunc.equals(EvalFunc.precision))
			evaluatePrecision(predictions,goldStandard);
		else if (evaluationFunc.equals(EvalFunc.recall))
			evaluateRecall(predictions,goldStandard);
		else if (evaluationFunc.equals(EvalFunc.fmeasure))
			evaluateFMeasure(predictions,goldStandard);
		else if (evaluationFunc.equals(EvalFunc.pseuFMeasure))
			evaluatePFMeasure(predictions,sourceUris,targetUris);
		else if (evaluationFunc.equals(EvalFunc.pseuPrecision))
			evaluatePPrecision(predictions,sourceUris,targetUris);
		else if (evaluationFunc.equals(EvalFunc.PseuRecall))
			evaluatePRecall(predictions,sourceUris,targetUris);
		else if (evaluationFunc.equals(EvalFunc.accuracy))
			evaluateAccuracy(predictions,goldStandard,sourceUris.size(),targetUris.size());
		else if (evaluationFunc.equals(EvalFunc.auc))
			evaluateAUC(predictions,goldStandard);
		else if (evaluationFunc.equals(EvalFunc.all))
			evaluateAll(predictions,goldStandard,sourceUris,targetUris);
		return evaluations;
	}
	private void evaluatePrecision(Mapping predictions, Mapping goldStandard)
	{
		double precision = new Precision().calculate(predictions, goldStandard);
		evaluations.put(EvalFunc.precision, precision);
	}
	private void evaluateRecall(Mapping predictions, Mapping goldStandard)
	{
		double recall = new Recall().calculate(predictions, goldStandard);
		evaluations.put(EvalFunc.recall, recall);
	}
	private void evaluateFMeasure(Mapping predictions, Mapping goldStandard)
	{
		double fmeasure = new FMeasure().calculate(predictions, goldStandard);
		evaluations.put(EvalFunc.fmeasure, fmeasure);
	}
	private void evaluatePPrecision(Mapping predictions,List<String> sourceUris, List<String> targetUris)
	{
		double pPrecision = new PseudoFMeasure().getPseudoPrecision(sourceUris,targetUris,predictions);
		evaluations.put(EvalFunc.pseuPrecision, pPrecision);
	}
	private void evaluatePRecall(Mapping predictions,List<String> sourceUris, List<String> targetUris)
	{
		double pRecall = new PseudoFMeasure().getPseudoRecall(sourceUris,targetUris,predictions);
		evaluations.put(EvalFunc.PseuRecall, pRecall);
	}
	private void evaluatePFMeasure(Mapping predictions,List<String> sourceUris, List<String> targetUris)
	{
		double pfmeasure = new PseudoFMeasure().getPseudoFMeasure(sourceUris,targetUris,predictions);
		evaluations.put(EvalFunc.pseuFMeasure, pfmeasure);
	}

	private void evaluateAccuracy(Mapping predictions, Mapping goldStandard,long sourceUrisSize, long targetUrisSize)
	{
		double accuracy = new Accuracy().calculate(predictions, goldStandard,sourceUrisSize,targetUrisSize);
		evaluations.put(EvalFunc.accuracy, accuracy);
	}
	private void evaluateAUC(Mapping predictions, Mapping goldStandard)
	{
		double auc = new AUC().calculate(predictions, goldStandard);
		evaluations.put(EvalFunc.auc, auc);
	}
	private void evaluateAll(Mapping predictions, Mapping goldStandard,List<String> sourceUris, List<String> targetUris)
	{
		evaluatePrecision(predictions,goldStandard);
		evaluateRecall(predictions,goldStandard);
		evaluateFMeasure(predictions,goldStandard);
		evaluatePPrecision(predictions,sourceUris,targetUris);
		evaluatePRecall(predictions,sourceUris,targetUris);
		evaluatePFMeasure(predictions,sourceUris,targetUris);
		evaluateAccuracy(predictions,goldStandard,sourceUris.size(),targetUris.size());
		evaluateAUC(predictions,goldStandard);
	}
}