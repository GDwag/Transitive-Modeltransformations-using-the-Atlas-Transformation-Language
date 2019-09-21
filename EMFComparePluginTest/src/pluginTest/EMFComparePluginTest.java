/*
 * Contributors:
 * Gilbert Groten - gilbert@groten.fr
 * 
 * This program is made available under the terms of the Eclipse Public License - v 1.0
 */

package pluginTest;

import org.eclipse.uml2.uml.resource.UMLResource;
import org.junit.Test;

import java.util.List;

import org.eclipse.emf.compare.Diff;
//import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.EMFCompare;
import org.eclipse.emf.compare.scope.DefaultComparisonScope;
import org.eclipse.emf.compare.scope.FilterComparisonScope;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.uml2.uml.internal.resource.UMLResourceFactoryImpl;

public class EMFComparePluginTest {
	
	@Test
	public void compareUML_XMI() {
		assert compare("../Palladio2UML/Model/MediaStoreUML_copy_2019_09_18", "../Palladio2UML/Model/ms_small_copy_2019_09_18", false);
	}
	
	@Test
	public void compareUML_UML() {
		assert compare("../MediaStoreUML/MediaStoreUML.uml", "../Palladio2UML/Model/ms_small.uml", true);
	}
	
	@Test
	public void compareJava() {
		assert compare("../Palladio2JaMoPP/Model/ms_small", "../UML2JaMoPP/Model/ms_small", false);
	}
	
	private boolean compare(String model1, String model2, boolean useUML) {
		URI uri1 = URI.createURI(model1);
	    URI uri2 = URI.createURI(model2);

	    if (useUML) UMLResource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("uml", new UMLResourceFactoryImpl());
	    else 		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());

	    ResourceSet resourceSet1 = new ResourceSetImpl();
	    ResourceSet resourceSet2 = new ResourceSetImpl();

	    resourceSet1.getResource(uri1, true);
	    resourceSet2.getResource(uri2, true);

	    FilterComparisonScope scope = new DefaultComparisonScope(resourceSet1, resourceSet2, null);
	    Comparison comparison = EMFCompare.builder().build().compare(scope);

	    List<Diff> differences = comparison.getDifferences();
	    if (! differences.isEmpty()) {
	    	System.out.println();
	    	System.out.println("----- CONTENT OF DIFFERENCES -----");
	    	for (Diff difference : differences) System.out.println(difference.toString());
	    }
	    return differences.isEmpty();
	}
}
