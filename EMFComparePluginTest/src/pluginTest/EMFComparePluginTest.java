package pluginTest;

import org.eclipse.uml2.uml.resource.UMLResource;
import org.junit.Test;

import java.util.List;

import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.EMFCompare;
import org.eclipse.emf.compare.merge.BatchMerger;
import org.eclipse.emf.compare.merge.IBatchMerger;
import org.eclipse.emf.compare.merge.IMerger;
import org.eclipse.emf.compare.scope.DefaultComparisonScope;
import org.eclipse.emf.compare.scope.IComparisonScope;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.uml2.uml.internal.resource.UMLResourceFactoryImpl;

public class EMFComparePluginTest {
	@Test
	public void compareUML() {

	    URI uri1 = URI.createFileURI("../Palladio2UML/Model/ms.uml");
	    URI uri2 = URI.createFileURI("../Palladio2UML/Model/ms_small.uml");

	    UMLResource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("uml", new UMLResourceFactoryImpl());

	    ResourceSet resourceSet1 = new ResourceSetImpl();
	    ResourceSet resourceSet2 = new ResourceSetImpl();

	    resourceSet1.getResource(uri1, true);
	    resourceSet2.getResource(uri2, true);

	    IComparisonScope scope = new DefaultComparisonScope(resourceSet1, resourceSet2, null);
	    Comparison comparison = EMFCompare.builder().build().compare(scope);

	    List<Diff> differences = comparison.getDifferences();
	    // Let's merge every single diff
	    IMerger.Registry mergerRegistry = IMerger.RegistryImpl.createStandaloneInstance();// "new IMerger.RegistryImpl();" disencouraged by https://www.eclipse.org/forums/index.php/t/531137/
	    IBatchMerger merger = new BatchMerger(mergerRegistry);
	    merger.copyAllLeftToRight(differences, new BasicMonitor());
	}
}
