/**
 * 
 */
package br.ufpi.id3Algorithm.model.id3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Ronyerison
 *
 */
public class Attribute {
	private String label;
	private List<String> values;
	private List<String> discriminatorValues;
	private Map<String, AttributeValue> attributesValues;
	private Double gain;
	private List<String> ids;

	/**
	 * @param label
	 * @param values
	 * @param discriminatorValues
	 */
	public Attribute(String label, List<String> values,
			List<String> discriminatorValues) {
		this.label = label;
		this.values = values;
		this.discriminatorValues = discriminatorValues;
		this.gain = null;
	}

	/**
	 * @param label
	 */
	public Attribute(String label) {
		this.label = label;
		this.values = new ArrayList<String>();
		this.discriminatorValues = new ArrayList<String>();
		this.ids = new ArrayList<String>();
		this.gain = null;
	}

	public void calculeEntropy() {
		if (this.values != null && this.discriminatorValues != null) {
			HashMap<String, AttributeValue> map = new HashMap<String, AttributeValue>();
			AttributeValue av;
			for (int i = 0; i < values.size(); i++) {
				String key = values.get(i);
				if (!map.containsKey(key)) {
					av = new AttributeValue(label, key, 1);
					av.addDiscriminatorValue(discriminatorValues.get(i));
					av.addId(ids.get(i));
					map.put(key, av);
				} else {
					av = map.get(key);
					av.setCount(av.getCount() + 1);
					av.addDiscriminatorValue(discriminatorValues.get(i));
					av.addId(ids.get(i));
					map.replace(key, av);
				}
			}

			Iterator<AttributeValue> iterator = map.values().iterator();
			while (iterator.hasNext()) {
				iterator.next().calculeEntropy();
			}

			this.attributesValues = map;
		}
	}

	public void calculeGain(Double totalEntropy) {
		calculeEntropy();
		Iterator<AttributeValue> iterator = attributesValues.values()
				.iterator();
		Double sum = 0.0;
		while (iterator.hasNext()) {
			AttributeValue attributeValue = iterator.next();
			sum += attributeValue.getEntropy()
					* (attributeValue.getCount().doubleValue() / (double) values.size());
		}

		this.gain = totalEntropy - sum;
	}

	public void addValue(String value, String discriminatorValue, String id) {
		this.values.add(value);
		this.discriminatorValues.add(discriminatorValue);
		this.ids.add(id);
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label
	 *            the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the values
	 */
	public List<String> getValues() {
		return values;
	}

	/**
	 * @param values
	 *            the values to set
	 */
	public void setValues(List<String> values) {
		this.values = values;
	}

	/**
	 * @return the discriminatorValues
	 */
	public List<String> getDiscriminatorValues() {
		return discriminatorValues;
	}

	/**
	 * @param discriminatorValues
	 *            the discriminatorValues to set
	 */
	public void setDiscriminatorValues(List<String> discriminatorValues) {
		this.discriminatorValues = discriminatorValues;
	}

	/**
	 * @return the gain
	 */
	public Double getGain() {
		return gain;
	}

	/**
	 * @param gain
	 *            the gain to set
	 */
	public void setGain(Double gain) {
		this.gain = gain;
	}

	/**
	 * @return the attributesValues
	 */
	public Map<String, AttributeValue> getAttributesValues() {
		return attributesValues;
	}

	/**
	 * @param attributesValues
	 *            the attributesValues to set
	 */
	public void setAttributesValues(Map<String, AttributeValue> attributesValues) {
		this.attributesValues = attributesValues;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Attribute [label=" + label + ", gain=" + gain
				+ ", attributesValues=" + attributesValues + "]";
	}

}
