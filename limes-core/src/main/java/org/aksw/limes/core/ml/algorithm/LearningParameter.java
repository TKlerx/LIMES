package org.aksw.limes.core.ml.algorithm;

import java.util.Objects;

/**
 * @author sherif
 *
 */
public class LearningParameter<T> {

    protected String name;
    protected T value;
    protected T rangeStart;
    protected T rangeEnd;
    protected T rangeStep;
    protected String description;
    protected String[] instanceOptions;

    
    /**
     * Constructor
     */
    public LearningParameter(){
        super();
    }
    
    
    /**
     * Constructor
     * 
     * @param name parameter's name
     * @param value parameter's value
     */
    public LearningParameter(String name, T value) {
        this();
        this.name = name;
        this.value = value;
    }
    
    /**
     * Constructor
     * 
     * @param name parameter's name
     * @param value parameter's value
     * @param clazz parameter's class
     * @param rangeStart parameter's range start
     * @param rangeEnd parameter's range end
     * @param rangeStep parameter's range step
     * @param description parameter's description
     */
    public LearningParameter(String name, T value, T rangeStart, T rangeEnd,
            T rangeStep, String description) {
        this(name, value);
        this.rangeStart = rangeStart;
        this.rangeEnd = rangeEnd;
        this.rangeStep = rangeStep;
        this.description = description;
    }
    
    
    public LearningParameter(String name, T value, String[] instanceOptions, String description) {
        this(name, value);
        this.instanceOptions = instanceOptions;
        this.description = description;
    }

	@Override
	public String toString() {
		return new StringBuilder("").append(name).append(" : ").append(value).toString();
	}

    
    
    /**
     * @return parameter's range step
     */
    public T getRangeStep() {
        return rangeStep;
    }

    /**
     * @param rangeStep to be set
     */
    public void setRangeStep(T rangeStep) {
        this.rangeStep = rangeStep;
    }

    /**
     * @return parameter's name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @param name to be set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * @return parameter's value
     */
    public T getValue() {
        return value;
    }
    
    /**
     * @param object to be set
     */
    public void setValue(T object) {
        this.value = object;
    }
    
    /**
     * @return parameter's class 
     */
    public Class<?> getClazz() {
        return value.getClass();
    }
    
    
    /**
     * @return parameter's range start as double
     */
    public T getRangeStart() {
        return rangeStart;
    }
    
    /**
     * @param rangeStart to be set
     */
    public void setRangeStart(T rangeStart) {
        this.rangeStart = rangeStart;
    }
    
    /**
     * @return parameter's range end
     */
    public T getRangeEnd() {
        return rangeEnd;
    }
    
    /**
     * @param rangeEnd to be set
     */
    public void setRangeEnd(T rangeEnd) {
        this.rangeEnd = rangeEnd;
    }
    
    /**
     * @return parameter's description
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * @param description to be set
     */
    public void setDescription(String description) {
        this.description = description;
    }

	public String[] getInstanceOptions() {
		return instanceOptions;
	}


	public void setInstanceOptions(String[] instanceOptions) {
		this.instanceOptions = instanceOptions;
	}


	@Override
	public int hashCode() {
		return Objects.hash(description, name, rangeEnd, rangeStart, rangeStep, value);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LearningParameter other = (LearningParameter) obj;
		return Objects.equals(description, other.description) && Objects.equals(name, other.name)
				&& Objects.equals(rangeEnd, other.rangeEnd) && Objects.equals(rangeStart, other.rangeStart)
				&& Objects.equals(rangeStep, other.rangeStep) && Objects.equals(value, other.value);
	}


    

}
