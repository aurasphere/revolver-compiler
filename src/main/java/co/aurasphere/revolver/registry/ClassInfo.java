package co.aurasphere.revolver.registry;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

import co.aurasphere.revolver.annotation.Generator;

public class ClassInfo extends RevolverRegistryEntry {

	private TypeElement typeElement;

	private boolean singleton;

	private boolean generator;

	private List<FieldInfo> constructorParams;

	public ClassInfo(TypeElement typeElement, ExecutableElement constructor) {
		this(typeElement);
		constructorParams = new ArrayList<FieldInfo>();
		for (VariableElement e : constructor.getParameters()) {
			constructorParams.add(new FieldInfo(e, typeElement));
		}
	}

	public ClassInfo(TypeElement typeElement) {
		super(typeElement);
		this.typeElement = typeElement;
		this.singleton = isAnnotatedWith(typeElement, Singleton.class);
		this.generator = isAnnotatedWith(typeElement, Generator.class);
	}

	@Override
	public TypeElement getType() {
		return this.typeElement;
	}

	public List<FieldInfo> getConstructorParameters() {
		return this.constructorParams;
	}

	public boolean isSingleton() {
		return this.singleton;
	}

	public boolean isGenerator() {
		return this.generator;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((constructorParams == null) ? 0 : constructorParams
						.hashCode());
		result = prime * result + (generator ? 1231 : 1237);
		result = prime * result + (singleton ? 1231 : 1237);
		result = prime * result
				+ ((typeElement == null) ? 0 : typeElement.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClassInfo other = (ClassInfo) obj;
		if (constructorParams == null) {
			if (other.constructorParams != null)
				return false;
		} else if (!constructorParams.equals(other.constructorParams))
			return false;
		if (generator != other.generator)
			return false;
		if (singleton != other.singleton)
			return false;
		if (typeElement == null) {
			if (other.typeElement != null)
				return false;
		} else if (!typeElement.equals(other.typeElement))
			return false;
		return true;
	}
	
}
