/**
 */
package Circle;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Architecture</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Circle.Architecture#getName <em>Name</em>}</li>
 *   <li>{@link Circle.Architecture#getCircles <em>Circles</em>}</li>
 *   <li>{@link Circle.Architecture#getRelations <em>Relations</em>}</li>
 * </ul>
 *
 * @see Circle.CirclePackage#getArchitecture()
 * @model
 * @generated
 */
public interface Architecture extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see Circle.CirclePackage#getArchitecture_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link Circle.Architecture#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Circles</b></em>' containment reference list.
	 * The list contents are of type {@link Circle.Circle}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Circles</em>' containment reference list.
	 * @see Circle.CirclePackage#getArchitecture_Circles()
	 * @model containment="true"
	 * @generated
	 */
	EList<Circle> getCircles();

	/**
	 * Returns the value of the '<em><b>Relations</b></em>' containment reference list.
	 * The list contents are of type {@link Circle.Relation}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Relations</em>' containment reference list.
	 * @see Circle.CirclePackage#getArchitecture_Relations()
	 * @model containment="true"
	 * @generated
	 */
	EList<Relation> getRelations();

} // Architecture
