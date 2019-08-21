import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.m2m.atl.core.ATLCoreException;
import org.eclipse.m2m.atl.core.IExtractor;
import org.eclipse.m2m.atl.core.IInjector;
import org.eclipse.m2m.atl.core.IModel;
import org.eclipse.m2m.atl.core.IReferenceModel;
import org.eclipse.m2m.atl.core.ModelFactory;
import org.eclipse.m2m.atl.core.emf.EMFExtractor;
import org.eclipse.m2m.atl.core.emf.EMFInjector;
import org.eclipse.m2m.atl.core.emf.EMFModel;
import org.eclipse.m2m.atl.core.emf.EMFModelFactory;
import org.eclipse.m2m.atl.core.emf.EMFReferenceModel;
import org.eclipse.m2m.atl.core.launch.ILauncher;
import org.eclipse.m2m.atl.engine.emfvm.launch.EMFVMLauncher;

/**
 * 
 * @author Hugo Bruneliere
 */
public class Palladio2UML {

	public static void main(String[] args) {
		try {
			/*
			 * Initializations
			 */
			ILauncher transformationLauncher = new EMFVMLauncher();
			ModelFactory modelFactory = new EMFModelFactory();
			IInjector injector = new EMFInjector();
			IExtractor extractor = new EMFExtractor();
			
			/*
			 * Load metamodels
			 */
			IReferenceModel companyMetamodel = modelFactory.newReferenceModel();
			injector.inject(companyMetamodel, "http://palladiosimulator.org/PalladioComponentModel/5.2");
			IReferenceModel totalMetamodel = modelFactory.newReferenceModel();
			injector.inject(totalMetamodel, "http://www.eclipse.org/uml2/5.0.0/UML");
			
			/*
			 * Run "Cut" transformation
			 */
			//IModel companyModel = modelFactory.newModel(companyMetamodel);
			//injector.inject(companyModel,"/MediaStore3_Model/ms.repository");
			//
			//transformationLauncher.initialize(new HashMap<String,Object>());
			//transformationLauncher.addInOutModel(companyModel, "IN", "Company");
			//IReferenceModel refiningTraceMetamodel = modelFactory.getBuiltInResource("RefiningTrace.ecore");
			//IModel refiningTraceModel = modelFactory.newModel(refiningTraceMetamodel);
			//transformationLauncher.addOutModel(refiningTraceModel, "refiningTrace", "RefiningTrace");
			//transformationLauncher.launch(ILauncher.RUN_MODE, new NullProgressMonitor(), new HashMap<String,Object>(),
			//	new FileInputStream("Transformations/Cut.asm"));
			//
			//IModel companyModel_Cut = companyModel;
			//extractor.extract(companyModel_Cut, "Models/Java/sampleCompany_Cut.xmi");
			
			/*
			 * Run "ComputeTotal" transformation
			 */
			IModel companyModel_Cut = modelFactory.newModel(totalMetamodel); //nachträglich eingefügt
			IModel companyModel_Total = modelFactory.newModel(totalMetamodel);
			
			transformationLauncher.initialize(new HashMap<String,Object>());
			transformationLauncher.addInModel(companyModel_Cut, "IN", "Company");
			transformationLauncher.addOutModel(companyModel_Total, "OUT", "Total");
			transformationLauncher.launch(ILauncher.RUN_MODE, new NullProgressMonitor(), new HashMap<String,Object>(),
				new FileInputStream("/MediaStore3_Model/ms.repository"));
			
			extractor.extract(companyModel_Total, "/Palladio2UML/Model/ms");
			
			/*
			 * Unload all models and metamodels (EMF-specific)
			 */
			EMFModelFactory emfModelFactory = (EMFModelFactory) modelFactory;
			emfModelFactory.unload((EMFModel) companyModel_Cut);
			emfModelFactory.unload((EMFModel) companyModel_Total);
			emfModelFactory.unload((EMFReferenceModel) companyMetamodel);
			emfModelFactory.unload((EMFReferenceModel) totalMetamodel);
			
		} catch (ATLCoreException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}

/*import org.eclipse.m2m.atl.core.ATLCoreException;
import org.eclipse.m2m.atl.core.IExtractor;
import org.eclipse.m2m.atl.core.IInjector;
import org.eclipse.m2m.atl.core.IReferenceModel;
import org.eclipse.m2m.atl.core.ModelFactory;
import org.eclipse.m2m.atl.core.emf.EMFExtractor;
import org.eclipse.m2m.atl.core.emf.EMFInjector;
import org.eclipse.m2m.atl.core.emf.EMFModelFactory;
import org.eclipse.m2m.atl.core.launch.ILauncher;
import org.eclipse.m2m.atl.engine.emfvm.launch.EMFVMLauncher;

public class Palladio2UML {

	public static void main(String[] args) {
		try {
		
			//Initialisation of tools required for Transformation
			ILauncher transformationLauncher = new EMFVMLauncher();
			ModelFactory modelFactory = new EMFModelFactory();
			IInjector injector = new EMFInjector();
			IExtractor extractor = new EMFExtractor();
			
			//Load Metamodels
			IReferenceModel pcmMetaModel = modelFactory.newReferenceModel();
			injector.inject(pcmMetaModel, "http://palladiosimulator.org/PalladioComponentModel/5.2");
			IReferenceModel umlMetaModel = modelFactory.newReferenceModel();
			injector.inject(umlMetaModel, "http://www.eclipse.org/uml2/5.0.0/UML");
		
		} catch (ATLCoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
*/