/**
 * 
 */
package br.ufpi.id3Algorithm.model.id3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * @author Ronyerison
 *
 */
public class AttributeValue {
	private String attribute;
	private String label;
	private Integer count;
	private Double entropy;
	private List<String> discriminatorValues;
	private List<String> ids;
	
	/**
	 * @param attribute
	 * @param label
	 * @param count
	 */
	public AttributeValue(String attribute, String label, Integer count) {
		this.attribute = attribute;
		this.label = label;
		this.count = count;
		this.ids = new ArrayList<String>();
		this.discriminatorValues = new ArrayList<String>();
	}
	
	public void calculeEntropy(){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
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
		
		this.entropy = entropy;
	}
	
	public void addDiscriminatorValue(String value){
		this.discriminatorValues.add(value);
	}
	
	public void addId(String id){
		this.ids.add(id);
	}
	
	/**
	 * @return the attribute
	 */
	public String getAttribute() {
		return attribute;
	}
	
	/**
	 * @param attribute the attribute to set
	 */
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	/**
	 * @return the count
	 */
	public Integer getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
	/**
	 * @return the entropy
	 */
	public Double getEntropy() {
		return entropy;
	}
	/**
	 * @param entropy the entropy to set
	 */
	public void setEntropy(Double entropy) {
		this.entropy = entropy;
	}

	/**
	 * @return the discriminatorValues
	 */
	public List<String> getDiscriminatorValues() {
		return discriminatorValues;
	}

	/**
	 * @param discriminatorValues the discriminatorValues to set
	 */
	public void setDiscriminatorValues(List<String> discriminatorValues) {
		this.discriminatorValues = discriminatorValues;
	}

	/**
	 * @return the ids
	 */
	public List<String> getIds() {
		return ids;
	}

	/**
	 * @param ids the ids to set
	 */
	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AttributeValue [label=" + label + ", entropy=" + entropy + "]";
	}
	
	
	
}
