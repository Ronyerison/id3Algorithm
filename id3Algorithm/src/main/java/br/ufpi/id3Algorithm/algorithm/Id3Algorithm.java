/**
 * 
 */
package br.ufpi.id3Algorithm.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import br.ufpi.id3Algorithm.model.Attribute;
import br.ufpi.id3Algorithm.model.AttributeValue;
import br.ufpi.id3Algorithm.model.Tree;

/**
 * @author Ronyerison
 *
 */
public class Id3Algorithm {
	
	public Tree run(List<String[]> trainingSet, List<String[]> testSet){
		Tree tree = new Tree();
		List<String> discriminatorValues = getDiscriminatorValues(trainingSet);
		if(trainingSet.isEmpty()){
			return tree;
		}else if(hasEqualClassification(discriminatorValues)){
			tree.addRoot(1l, discriminatorValues.get(0));
			return tree;
		}
		List<Attribute> attributes = getAllAttributes(trainingSet);
		Attribute best = selectBestAttribute(attributes);
		attributes.remove(best);
		tree.addRoot(1l, best.getLabel());
		
		for (AttributeValue av : best.getAttributesValues().values()) {
			@SuppressWarnings("unused")
			List<String[]> newTrainning = getNewTrainningSet(trainingSet,av);
			tree.addEdge(1L, av.getLabel(), tree.getRoot(), null);
		}
		
		return tree;
	}
	
	private List<String[]> getNewTrainningSet(List<String[]> oldTrainning, AttributeValue av) {
		
		return null;
	}

	private Attribute selectBestAttribute(List<Attribute> attributes){
		Collections.sort(attributes, new Comparator<Attribute>() {
			@Override
			public int compare(Attribute o1, Attribute o2) {
				return o2.getGain().compareTo(o1.getGain());
			}
		});
		
		return attributes.get(0);
	}
	
	private List<Attribute> getAllAttributes(List<String[]> trainingSet){
		List<Attribute> attributes = new ArrayList<Attribute>();
		List<String> discriminatorValues = getDiscriminatorValues(trainingSet);
		for (int i = 1; i < trainingSet.get(0).length; i++) {
			Attribute attribute = new Attribute(trainingSet.get(0)[i]);
			for (int j = 1; j < trainingSet.size(); j++) {
				String value = trainingSet.get(j)[i];
				String discriminatorValue = trainingSet.get(j)[trainingSet.get(j).length - 1];
				String id = trainingSet.get(j)[0];
				attribute.addValue(value, discriminatorValue, id);
			}
			
			Double totalEntropy = calculeTotalEntropy(discriminatorValues);
			attribute.calculeGain(totalEntropy);
			attributes.add(attribute);
		}
		return attributes;
	}

	/**
	 * @param trainingSet
	 * @return
	 */
	private List<String> getDiscriminatorValues(List<String[]> trainingSet) {
		List<String> discriminatorValues = new ArrayList<String>();
		for (String[] example : trainingSet) {
			discriminatorValues.add(example[example.length - 1]);
		}
		discriminatorValues.remove(0);
		return discriminatorValues;
	}
	
	private boolean hasEqualClassification(List<String> discriminatorValues){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (String key : discriminatorValues) {
			if(!map.containsKey(key)){
				map.put(key, 1);
			}else{
				map.replace(key, map.get(key)+1);
			}
		}
		if(map.values().size() > 1){
			return false;
		}
		return true;
	}
	
	private Double calculeTotalEntropy(List<String> discriminatorValues){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		Integer count = discriminatorValues.size();
		for (String key : discriminatorValues) {
			if(!map.containsKey(key)){
				map.put(key, 1);
			}else{
				map.replace(key, map.get(key)+1);
			}
		}
		Double entropy = 0.0;
		Iterator<Integer> iterator = map.values().iterator();
		while(iterator.hasNext()){
			Double value = iterator.next().doubleValue();
			entropy += -(value / count.doubleValue()) * (Math.log(value / count.doubleValue()) / Math.log(2.0));
		}
		
 		return entropy;
	}
}
