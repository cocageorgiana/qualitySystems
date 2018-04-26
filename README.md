# qualitySystems
The quality of software systems

Pentru a fi returnati toti studentii este necesar sa fie accesat endpointul printr-un GET in Postman:https://quality.cfapps.io/middleware/results care va produce un JSON format, fisier text si Excel file.

Adaugarea unui nou student - pentru a face INSERT din FE in BE un student este necesar sa fie apelat prin enpointul de POST in Postman: https://quality.cfapps.io/middleware/createStudent iar parametri completati vor fi urmatorii: first_name, last_name, medie_bac si nota_examen. 

Requestul de DELETE este accesat prin urmatorul URL: https://quality.cfapps.io/middleware/students?id=1 Acesta va sterge un student dupa un ID specificat

Requestul de PUT este accesat prin urmatorul URL:  https://quality.cfapps.io/middleware/students?id=1 unde se modifica urmatoarele campuri: id, first_name, last_name, medie_bac si nota_examen. 

Acest endpoint permite modificarea datelor unui student. 

Excel file va stoca elemente in urmatorul format:

Id	FirstName	LastName	MedieBac	NotaExamen	Medie	Clasificare
4	  Irinel	  Ionescu	  10.0	    10.0	      10.0	  budget
 
Fisierul text va stoca elemente in urmatorul format:
ClassifyStudent{classification='budget', id=4, first_name='Irinel', last_name='Ionescu', medie_bac=10.0, nota_examen=10.0, medie=10.0} 
