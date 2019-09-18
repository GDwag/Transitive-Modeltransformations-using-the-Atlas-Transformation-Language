package pluginTest;

import org.eclipse.uml2.uml.resource.UMLResource;
import org.junit.Test;

import java.util.List;
import java.util.function.Predicate;

import org.eclipse.emf.compare.Diff;
//import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.EMFCompare;
//import org.eclipse.emf.compare.merge.BatchMerger;
//import org.eclipse.emf.compare.merge.IBatchMerger;
//import org.eclipse.emf.compare.merge.IMerger;
import org.eclipse.emf.compare.scope.DefaultComparisonScope;
import org.eclipse.emf.compare.scope.FilterComparisonScope;
import org.eclipse.emf.compare.scope.IComparisonScope;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.uml2.uml.internal.resource.UMLResourceWithoutUUIDsFactoryImpl;
import org.eclipse.uml2.uml.internal.resource.UMLResourceFactoryImpl;
import org.eclipse.emf.compare.utils.EMFComparePredicates;

public class EMFComparePluginTest {
	
	@Test
	public void compareUML_XMI() {
		//assert compare("../MediaStoreUML/MediaStoreUML.uml", "../Palladio2UML/Model/ms_small.uml");
		assert compare("../Palladio2UML/Model/MediaStoreUML_copy_2019_09_18", "../Palladio2UML/Model/ms_small_copy_2019_09_18", false);
	}
	
	@Test
	public void compareUML_UML() {
		//assert compare("../MediaStoreUML/MediaStoreUML.uml", "../Palladio2UML/Model/ms_small.uml");
		assert compare("../MediaStoreUML/MediaStoreUML.uml", "../Palladio2UML/Model/ms_small.uml", true);
	}
	
	@Test
	public void compareJava() {
		assert compare("../Palladio2JaMoPP/Model/ms_small", "../UML2JaMoPP/Model/ms_small", false);
	}
	
	private boolean compare(String model1, String model2, boolean useUML) {
		URI uri1 = URI.createURI(model1);
	    URI uri2 = URI.createURI(model2);

	    /*
	    if (useUML) UMLResource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("uml", new UMLResourceFactoryImpl());
	    else 		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
	    */
	    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, new UMLResourceWithoutUUIDsFactoryImpl());

	    ResourceSet resourceSet1 = new ResourceSetImpl();
	    ResourceSet resourceSet2 = new ResourceSetImpl();

	    resourceSet1.getResource(uri1, true);
	    resourceSet2.getResource(uri2, true);

	    FilterComparisonScope scope = new DefaultComparisonScope(resourceSet1, resourceSet2, null); //FilterComparisonScope(resourceSet1, resourceSet2, null);
	    
	    //sollte helfen diffs, die keinen inhaltlihcen konflikt darstellen loszuwerden
	    //https://www.programcreek.com/java-api-examples/?api=org.eclipse.emf.compare.scope.DefaultComparisonScope
	    //https://help.eclipse.org/oxygen/index.jsp?topic=%2Forg.eclipse.emf.compare.doc%2Fhelp%2Fdeveloper%2Fjavadoc%2Forg%2Feclipse%2Femf%2Fcompare%2Futils%2FEMFComparePredicates.html&anchor=CONTAINMENT_REFERENCE_CHANGE
		//scope.setResourceContentFilter(EMFComparePredicates.WITHOUT_CONFLICT);
		//scope.setEObjectContentFilter(EMFComparePredicates.WITHOUT_CONFLICT);
		
	    Comparison comparison = EMFCompare.builder().build().compare(scope);

	    List<Diff> differences = comparison.getDifferences();
	    if (! differences.isEmpty()) {
	    	/*
	    	System.out.println();
	    	System.out.println("----- IGNORED DIFFERENCES -----");
	    	//for (int i = 0; i < differences.size(); i++) {
	    		//Diff difference = differences.get(i);
	    	for (Diff difference : differences) {
	    		int i = differences.indexOf(difference);
	    		if (difference.getConflict() == null) { //TODO WARNING - good condition?
	    			differences.remove(i);
	    			System.out.println("Difference Ignored (because no conflict): " + difference.toString());
	    		}
	    		//trying to get rid of differences that don't matter to us
	    	}
	    	*/
	    	System.out.println();
	    	System.out.println("----- CONTENT OF DIFFERENCES -----");
	    	for (Diff difference : differences) System.out.println(difference.toString());
	    	//for (Diff difference : differences) System.out.println(difference.getConflict());//.getKind());//getKind().getName());
	    }
	    return differences.isEmpty();
	    // Let's NOT merge every single diff, because that's not the point - at least not in the file ("java.lang.RuntimeException: The resource already exists at that location.")
	    //IMerger.Registry mergerRegistry = IMerger.RegistryImpl.createStandaloneInstance();// "new IMerger.RegistryImpl();" disencouraged by https://www.eclipse.org/forums/index.php/t/531137/
	    //IBatchMerger merger = new BatchMerger(mergerRegistry);
	    //merger.copyAllLeftToRight(differences, new BasicMonitor());
	}
}
