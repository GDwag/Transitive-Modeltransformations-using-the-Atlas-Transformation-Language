Square2Cricle Example as found at https://youtu.be/QwCKLeK9viw

Errors

Fixed
	Cannot create Children in Mode mxi
	https://youtu.be/QwCKLeK9viw?t=466
	https://www.eclipse.org/forums/index.php/t/166516/
		properties in den modellen
		new child geht genau dasnn wenns ne containment referenz ist
		sonst in properties von knoten
		-> in ecore haken an containment
		
	"impl cannot be resolved or is not a field"
		rename packages in .ecore to start with lower case
		regenerate genmodels
		delete and regenerate all elements generated from ecore