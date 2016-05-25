package org.aksw.limes.core.ml.oldalgorithm;

import java.util.Set;

import org.aksw.limes.core.io.mapping.Mapping;
import org.aksw.limes.core.ml.setting.LearningSetting;

/**
 * @author Tommaso Soru <tsoru@informatik.uni-leipzig.de>
 * @author Klaus Lyko
 */
public interface IMLAlgorithm {

	/**
	 * Name of the algorithm.
	 * 
	 * @return the name of the algorithm
	 */
	public String getName();
	
	/**
	 * Generate the machine-learning model and the associated link specification using the given mapping as training data.
	 * 
	 * @param trainingData mapping as training data
	 * @return an object containing the learned link specification and details about the training task
	 */
	public MLModel learn(Mapping trainingData);
	
	/**
	 * Compute the mapping, i.e. all predicted links among source and target datasets.
	 * 
	 * @return the mapping
	 */
	public Mapping computePredictions();

	/**
	 * Initiate the machine-learning algorithm.
	 * 
	 * @param parameters algorithm-specific hyperparameters
	 * @param trainingData all the training data (i.e., the whole mapping with or without labels)
	 * @throws Exception
	 */
	void init(LearningSetting parameters, Mapping trainingData) throws Exception;
	
	/**
	 * XXX Status: TBD
	 * 
	 * Terminate the algorithm.
	 * 
	 */
	void terminate();
	
	Set<String> parameters();
	
}