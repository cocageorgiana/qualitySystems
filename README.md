# qualitySystems
The quality of software systems

Pentru a returna toti studentii este necesar sa fie accesat endpointul printr-un GET in Postman: http://localhost:8099/middleware/results care va produce un JSON format, fisier text si Excel file.

Excel file va stoca elemente in urmatorul format:
Id	FirstName	LastName	MedieBac	NotaExamen	Medie	Clasificare
4	  Irinel	  Ionescu	  10.0	    10.0	      10.0	  budget
 
Fisierul text va stoca elemente in urmatorul format:
ClassifyStudent{classification='budget', id=4, first_name='Irinel', last_name='Ionescu', medie_bac=10.0, nota_examen=10.0, medie=10.0} 
