/**
 */
package Circle;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Circle</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Circle.Circle#getName <em>Name</em>}</li>
 *   <li>{@link Circle.Circle#getColor <em>Color</em>}</li>
 *   <li>{@link Circle.Circle#getRayon <em>Rayon</em>}</li>
 * </ul>
 *
 * @see Circle.CirclePackage#getCircle()
 * @model
 * @generated
 */
public interface Circle extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see Circle.CirclePackage#getCircle_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link Circle.Circle#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Color</em>' attribute.
	 * @see #setColor(String)
	 * @see Circle.CirclePackage#getCircle_Color()
	 * @model
	 * @generated
	 */
	String getColor();

	/**
	 * Sets the value of the '{@link Circle.Circle#getColor <em>Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Color</em>' attribute.
	 * @see #getColor()
	 * @generated
	 */
	void setColor(String value);

	/**
	 * Returns the value of the '<em><b>Rayon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rayon</em>' attribute.
	 * @see #setRayon(int)
	 * @see Circle.CirclePackage#getCircle_Rayon()
	 * @model
	 * @generated
	 */
	int getRayon();

	/**
	 * Sets the value of the '{@link Circle.Circle#getRayon <em>Rayon</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rayon</em>' attribute.
	 * @see #getRayon()
	 * @generated
	 */
	void setRayon(int value);

} // Circle
