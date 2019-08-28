package atlTransformationEMFCompare;

import java.util.Map;

import org.apache.log4j.Logger;

//https://www.eclipse.org/emf/compare/documentation/latest/developer/how-to-open-compare-dialog.html
//https://stackoverflow.com/questions/26944174/load-emf-model-instance-from-xmi-file

import org.eclipse.compare.CompareConfiguration;
import org.eclipse.compare.CompareEditorInput;
import org.eclipse.emf.compare.ide.ui.internal.configuration.EMFCompareConfiguration;
import org.eclipse.emf.compare.ide.ui.internal.editor.ComparisonEditorInput;
import org.eclipse.emf.compare.scope.DefaultComparisonScope;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
//import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceStore;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.internal.resource.UMLResourceFactoryImpl;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.eclipse.compare.CompareUI;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.EMFCompare;
import org.eclipse.emf.compare.domain.ICompareEditingDomain;
import org.eclipse.emf.compare.domain.impl.EMFCompareEditingDomain;
import org.eclipse.emf.compare.scope.IComparisonScope;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;



public class CompareTest {

	//CompareConfiguration configuration;
	ICompareEditingDomain editingDomain;
	AdapterFactory adapterFactory;
	EMFCompare comparator;
	IComparisonScope scope;
	Comparison comparison;
	//IPreferenceStore preferenceStore;
	
	public void setUp() {
		//Logger logger = Logger.getLogger("org.eclipse.emf.compare.EMFCompare"); //TODO wohin damit?
		//preferenceStore = new PreferenceStore();
		//configuration = new CompareConfiguration(preferenceStore);
		
		//Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
	    //Map<String, Object> m = reg.getExtensionToFactoryMap();
	    //m.put("xmi", new XMIResourceFactoryImpl());
	    
	    
		ResourceSet resSet = new ResourceSetImpl();
		//resSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
		//resSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xml", new XMLResourceFactoryImpl());
		//resSet.getPackageRegistry().put(URI.createURI("/Palladio2UML/Model/ms"), "/Palladio2UML/Model/ms");
		//resSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
		//Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
		UMLResource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("uml", new UMLResourceFactoryImpl());
		//EPackage.Registry.INSTANCE.put("http://www.eclipse.org/uml2/5.0.0/UML",UMLPackage.eINSTANCE);
		//Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("http://www.eclipse.org/uml2/5.0.0/UML", new XMIResourceFactoryImpl());
		Resource left = resSet.getResource(URI.createURI("../Palladio2UML/Model/ms.uml"), true); //uml_model_big
		Resource right = resSet.getResource(URI.createURI("../Palladio2UML/Model/ms_small.uml"), true); //uml_model_small
		Resource ancestor = left; //TODO - was soll der ancestor sein?
		editingDomain = EMFCompareEditingDomain.create(left, right, ancestor);
		comparator = EMFCompare.builder().build();
		comparison = comparator.compare(new DefaultComparisonScope(left, right, ancestor));
		adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		scope = new DefaultComparisonScope(left, right, ancestor);
	}
	
	public void run() {
		//try {
			CompareEditorInput input = new ComparisonEditorInput(new EMFCompareConfiguration(new CompareConfiguration()), comparison, editingDomain, adapterFactory);
			CompareUI.openCompareDialog(input);
		//} catch (ExceptionInInitializerError e) {
		//	System.out.println("ExceptionInInitializerError, with message: " + e.getMessage());
		//}
	}
}
