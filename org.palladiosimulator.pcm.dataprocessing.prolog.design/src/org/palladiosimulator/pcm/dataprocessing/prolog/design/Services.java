package org.palladiosimulator.pcm.dataprocessing.prolog.design;

import java.util.Collection;
import java.util.Vector;

import org.palladiosimulator.pcm.dataprocessing.prolog.prologmodel.*;
import org.palladiosimulator.pcm.dataprocessing.prolog.prologmodel.System;
import org.palladiosimulator.pcm.dataprocessing.prolog.prologmodel.provider.*;


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
	
	
	public String getPreCallAssignment(OperationCall call) {
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
		VariableAssignmentItemProvider itemProvider = new VariableAssignmentItemProvider(new PrologmodelItemProviderAdapterFactory());
		for (VariableAssignment var : call.getCallee().getReturnValueAssignments()) {
			returnValue += itemProvider.getText(var) +"\n";
		}
		
		return returnValue;
	}
	
	public Collection<OperationCall> hasReturnValueAssignment (System system) {
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
		OperationItemProvider itemProvider = new OperationItemProvider(new PrologmodelItemProviderAdapterFactory());
		return itemProvider.getText(op);
	}
	
	public String getSystemUsagesText(SystemUsage op) {
		SystemUsageItemProvider itemProvider = new SystemUsageItemProvider(new PrologmodelItemProviderAdapterFactory());
		return itemProvider.getText(op);
	}

	public String getPropertyDefinitionText(PropertyDefinition op) {
		PropertyDefinitionItemProvider definitionItemProvider = new PropertyDefinitionItemProvider(new PrologmodelItemProviderAdapterFactory());
		return definitionItemProvider.getText(op);
	}

	public String getReturnValueText(Variable op) {
		VariableItemProvider itemProvider = new VariableItemProvider(new PrologmodelItemProviderAdapterFactory());
		return itemProvider.getText(op);
	}
}
