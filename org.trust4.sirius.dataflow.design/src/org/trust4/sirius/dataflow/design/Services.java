package org.trust4.sirius.dataflow.design;

import java.util.*;

import org.eclipse.emf.ecore.EObject;

import edu.kit.ipd.sdq.dataflow.systemmodel.*;
import edu.kit.ipd.sdq.dataflow.systemmodel.System;
import edu.kit.ipd.sdq.dataflow.systemmodel.provider.*;
import edu.kit.ipd.sdq.dataflow.systemmodel.util.SystemModelAdapterFactory;

/**
 * The services class used by VSM.
 */
public class Services {
	public String operationNumber(OperationCall call) {
		Operation callee = call.getCallee();
		Caller caller = call.getCaller();
		int callSize = caller.getCalls().size();
		
		for(int i=0; i<callSize; i++) {
			if(caller.getCalls().get(i).getCallee().equals(callee)) {
				return Integer.toString(i+1);
			}
		}
		return "0";
	}    
	public String getPreCallAssignmen(OperationCall call) {
		String returnValue ="";
		for(VariableAssignment preCallStateVar : call.getPreCallStateDefinitions()) {
			returnValue += preCallStateVar.getVariable().getName() + "\n";
		}
		
		return returnValue;
	}
	
	public String getReturnValueAssignment(OperationCall call) {
		String returnValue="";
		for (VariableAssignment var : call.getCallee().getReturnValueAssignments()) {
			returnValue += var.toString() + "\n";
		}
		
		return returnValue;
	}
	
	public String getReturnValueAssignment2(OperationCall call) {
		String returnValue="";
		VariableAssignmentItemProvider itemProvider = new VariableAssignmentItemProvider(new SystemModelAdapterFactory());
		for (VariableAssignment var : call.getCallee().getReturnValueAssignments()) {
			returnValue += itemProvider.getText(var) +"\n";
		}
		
		return returnValue;
	}
	
	public Collection<OperationCall> hasReturnValueAssignment(System system) {
		Collection<OperationCall> available = new Vector<OperationCall>();
		for (Operation operation : system.getOperations()) {
			for (OperationCall calls : operation.getCalls()) {
				if(getReturnValueAssignment(calls) != "") {
					available.add(calls);
				}
			}
		}
		return available;	
	}

	public String getText(Operation op) {
		OperationItemProvider itemProvider = new OperationItemProvider(new SystemModelAdapterFactory());
		return itemProvider.getText(op);
	}
	
	public String getSystemUsagesText(SystemUsage op) {
		SystemUsageItemProvider itemProvider = new SystemUsageItemProvider(new SystemModelAdapterFactory());
		return itemProvider.getText(op);
	}

	public String getPropertyDefinitionText(PropertyDefinition op) {
		PropertyDefinitionItemProvider definitionItemProvider = new PropertyDefinitionItemProvider(new SystemModelAdapterFactory());
		return definitionItemProvider.getText(op);
	}

	public String getReturnValueText(Variable op) {
		VariableItemProvider itemProvider = new VariableItemProvider(new SystemModelAdapterFactory());
		return itemProvider.getText(op);
	}
}
