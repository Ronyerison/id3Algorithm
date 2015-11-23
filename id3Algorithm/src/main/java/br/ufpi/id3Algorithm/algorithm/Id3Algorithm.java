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

import org.apache.commons.lang3.ArrayUtils;

import br.ufpi.id3Algorithm.model.id3.Attribute;
import br.ufpi.id3Algorithm.model.id3.AttributeValue;
import br.ufpi.id3Algorithm.model.tree.Node;

/**
 * @author Ronyerison
 *
 */
public class Id3Algorithm {

	public Node<String> buildTree(List<String[]> trainningSet) {
		List<String> discriminatorValues = getDiscriminatorValues(trainningSet);
		if (trainningSet.isEmpty()) {
			return null;
		} else if (hasEqualClassification(discriminatorValues)) {
			Node<String> tree = new Node<String>(discriminatorValues.get(0));
			return tree;
		}
		List<Attribute> attributes = getAllAttributes(trainningSet);
		Attribute best = selectBestAttribute(attributes);
		attributes.remove(best);
		Node<String> tree = new Node<String>(best.getLabel());
		for (AttributeValue av : best.getAttributesValues().values()) {
			List<String[]> newTrainning = getNewTrainningSet(trainningSet, av);
			Node<String> node = buildTree(newTrainning);
			node.setParent(tree, av.getLabel());
		}

		return tree;
	}
	
	public String classification(String[] line, Node<String> tree, HashMap<String, Integer> map){
		if(tree.isLeaf()){
			return tree.getData();
		}else{
			int index = map.get(tree.getData());	
			String value = line[index];
			for (int i=0; i<tree.getEdges().size(); i++) {
				if(tree.getEdges().get(i).equalsIgnoreCase(value)){
					return classification(line, tree.getChildren().get(i), map);
				}
			}
		}
		return "";
	}
	
	public List<String> classificationTestSet(List<String[]> testSet, Node<String> tree){
		List<String> lines = new ArrayList<String>();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for(int i=0; i<testSet.get(0).length; i++){
			map.put(testSet.get(0)[i], i);
		}
		for(int i=1; i<testSet.size(); i++){
			lines.add(classification(testSet.get(i), tree, map));
		}
		return lines;
	}
	
	private List<String[]> getNewTrainningSet(List<String[]> oldTrainning,
			AttributeValue av) {
		List<String[]> newTrainning = new ArrayList<String[]>();
		int index = ArrayUtils.indexOf(oldTrainning.get(0), av.getAttribute());

		for (int i = 0; i < oldTrainning.size(); i++) {
			String[] columns = ArrayUtils.remove(oldTrainning.get(i), index);
			if (oldTrainning.get(i)[index].equals(av.getLabel())
					|| oldTrainning.get(i)[index].equals(av.getAttribute())) {
				newTrainning.add(columns);
			}
		}
		return newTrainning;
	}

	private Attribute selectBestAttribute(List<Attribute> attributes) {
		Collections.sort(attributes, new Comparator<Attribute>() {
			@Override
			public int compare(Attribute o1, Attribute o2) {
				return o2.getGain().compareTo(o1.getGain());
			}
		});

		return attributes.get(0);
	}

	private List<Attribute> getAllAttributes(List<String[]> trainingSet) {
		List<Attribute> attributes = new ArrayList<Attribute>();
		List<String> discriminatorValues = getDiscriminatorValues(trainingSet);
		for (int i = 1; i < trainingSet.get(0).length - 1; i++) {
			Attribute attribute = new Attribute(trainingSet.get(0)[i]);
			for (int j = 1; j < trainingSet.size(); j++) {
				String value = trainingSet.get(j)[i];
				String discriminatorValue = trainingSet.get(j)[trainingSet
						.get(j).length - 1];
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

	private boolean hasEqualClassification(List<String> discriminatorValues) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (String key : discriminatorValues) {
			if (!map.containsKey(key)) {
				map.put(key, 1);
			} else {
				map.replace(key, map.get(key) + 1);
			}
		}
		if (map.values().size() > 1) {
			return false;
		}
		return true;
	}

	private Double calculeTotalEntropy(List<String> discriminatorValues) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		Integer count = discriminatorValues.size();
		for (String key : discriminatorValues) {
			if (!map.containsKey(key)) {
				map.put(key, 1);
			} else {
				map.replace(key, map.get(key) + 1);
			}
		}
		Double entropy = 0.0;
		Iterator<Integer> iterator = map.values().iterator();
		while (iterator.hasNext()) {
			Double value = iterator.next().doubleValue();
			entropy += -(value / count.doubleValue())
					* (Math.log(value / count.doubleValue()) / Math.log(2.0));
		}

		return entropy;
	}
}
