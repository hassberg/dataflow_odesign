package org.trust4.sirius.dataflow.design;

import org.eclipse.emf.ecore.EObject;
import edu.kit.ipd.sdq.dataflow.systemmodel.*;

/**
 * The services class used by VSM.
 */
public class Services {
    
    /**
    * See http://help.eclipse.org/neon/index.jsp?topic=%2Forg.eclipse.sirius.doc%2Fdoc%2Findex.html&cp=24 for documentation on how to write service methods.
    */
	public EObject myService(EObject self, String arg) {
	       // TODO Auto-generated code
	      return self;
	    }

	public OperationCall getReturnValue(ReturnValueRef self) {
	       
	return self.getCall();
	}

	public Operation getReturnValueToCallee(ReturnValueRef self) {
	       
	return self.getCall().getCallee();
	}

	public Operation getReturnValueToCaller(ReturnValueRef self) {
	       
	return (Operation) self.getCall().getCaller();
	}
    
}
