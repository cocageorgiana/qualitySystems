function showHome() {
	$("#content").children("div").hide();
	$("#homeScreen").show();
}

function showAddStudent() {
	$("#content").children("div").hide();
	$("#addStudentScreen").show();
}

function addStudent() {
	sendAddStudent($("#first_name").val(), $("#last_name").val(), $("#medie_bac").val(), $("#nota_examen").val(), false);
}

function sendAddStudent(first_name, last_name, medie_bac, nota_examen, stripAlert) {
	$.post( "https://quality.cfapps.io/middleware/createStudent", {first_name: first_name, last_name: last_name, medie_bac: medie_bac, nota_examen: nota_examen})
	.done(function( data ) {
		console.log(data);
		if (!stripAlert) alert( "Student has been added!");
	})
	.fail(function(err) {
		console.log(err);
		if (!stripAlert) alert("Error when adding the Student");

	});
}

function loadStudentsFromFile() {
	$("#content").children("div").hide();
	$("#loadStudentsFromFileScreen").show();
}


function sendStudentsFromFile(postData) {
	console.log(JSON.stringify(postData));

	$.ajax({
		type: "POST",
		url: "http://admissioncontest.us-east-2.elasticbeanstalk.com/AdmissionContestServlet",
		data: JSON.stringify(postData),
		contentType: false,
		processType: false,
		success: function(data) {console.log(data);alert( "Students has been added!");},
		error: function(data) {console.log(data);alert( "Error when adding the Students!");}
	});


}

/*function showDeleteStudents() {
	$("#content").children("div").hide();
	$("#deleteStudentsScreen").show();
}

function deleteStudents() {
	$.ajax({
		crossOrigin: true,
		type: "DELETE",
		url: "https://quality.cfapps.io/middleware/deleteStudents",
		success: function(data) {
			// Here's where you handle a successful response.
			console.log(data);
			alert( "Students database has been erased!");
		},

		error: function(err) {
			// Here's where you handle an error response.
			// Note that if the error was due to a CORS issue,
			// this function will still fire, but there won't be any additional
			// information about the error.
			console.log(err);
			alert("Error when erasing the Students Database");
		}

	});
}*/

function showViewStudents() {
	$("#content").children("div").hide();
	$("#viewStudentScreen").show();
	$.ajax({
		crossOrigin: true,
		type: "GET",
		url: "https://quality.cfapps.io/middleware/results",
		success: function(data) {
			// Here's where you handle a successful response.
			//console.log(data);
			if (data.length > 0)
			{
				admissionListData = data;
			}

			$("#studentsTable tbody").empty();
			for (var i = 0, len = data.length; i < len; i++) {

				$("#studentsTable").find('tbody')
					.append($('<tr>')
						.append($('<td>')
							.text(data[i].first_name)
						)
						.append($('<td>')
							.text(data[i].last_name)
						)
						.append($('<td>')
							.text(data[i].medie_bac)
						)
						.append($('<td>')
							.text(data[i].nota_examen)
						)
						.append($('<td>')
							.append('<button type="button" class="btn btn-success" onClick="editStudent('+i+')" data-toggle="modal" data-target="#editModal">Edit</button> &nbsp;&nbsp;<button type="button" class="btn btn-danger" onClick="deleteStudent('+i+')">Delete</button>')
						)
					);
			}
		},

		error: function(err) {
			console.log(err);
			alert("The students list load failed");
		}

	});
}
function editStudent(i) {
	$("#edit_first_name").val(admissionListData[i].first_name);
	$("#edit_last_name").val(admissionListData[i].last_name);
	$("#edit_medie_bac").val(admissionListData[i].medie_bac);
	$("#edit_nota_examen").val(admissionListData[i].nota_examen);
	$("#edit_id").val(admissionListData[i].id);

}

function sendEditStudent() {
	$.ajax({
		crossOrigin: true,
		type: "PUT",
		url: "https://quality.cfapps.io/middleware/students/"+$("#edit_id").val(),
		data: {first_name: $("#edit_first_name").val(), last_name: $("#edit_last_name").val(), medie_bac: $("#edit_medie_bac").val(), nota_examen: $("#edit_nota_examen").val()},
		success: function(data) {
			console.log(data);
			alert( "Student has been saved!");
		},

		error: function(err) {
			console.log(err);
			alert("Error when saving the Student");
		}
	})
	.done(function( data ) {
		showViewStudents();
	});

}

function deleteStudent(i) {
	$.ajax({
		crossOrigin: true,
		type: "DELETE",
		url: "https://quality.cfapps.io/middleware/students/" + admissionListData[i].id,
		success: function (data) {
			console.log(data);
			alert("Student has been deleted!");
		},
		error: function (err) {
			console.log(err);
			alert("Error when deleting the Student");
		}
	})
	.done(function( data ) {
		showViewStudents();
	});

}

function showAdmissionList() {
	$("#content").children("div").hide();
	$("#admissionListScreen").show();
	$("#btnExportToExcel").hide();
	$.ajax({
		crossOrigin: true,
		type: "GET",
		url: "https://quality.cfapps.io/middleware/results",
		success: function(data) {
			// Here's where you handle a successful response.
			//console.log(data);
			if (data.length > 0)
			{
				admissionListData = data;
			}

			$("#admissionTable tbody").empty();
			for (var i = 0, len = data.length; i < len; i++) {

				$("#admissionTable").find('tbody')
					.append($('<tr>')
						.append($('<td>')
							.text(data[i].first_name)
						)
						.append($('<td>')
							.text(data[i].last_name)
						)
						.append($('<td>')
							.text(data[i].medie_bac)
						)
						.append($('<td>')
							.text(data[i].nota_examen)
						)
						.append($('<td>')
							.text(data[i].medie)
						)
						.append($('<td>')
							.text(data[i].classification)
						)
					);
			}

			$("#btnExportToExcel").show();
		},

		error: function(err) {
			console.log(err);
			alert("The admission list load failed");
		}

	});
}


function exportToExcel() {
	var excel = $JExcel.new("Calibri light 10 #333333");			// Default font

	// excel.set is the main function to generate content:
	// 		We can use parameter notation excel.set(sheetValue,columnValue,rowValue,cellValue,styleValue)
	// 		Or object notation excel.set({sheet:sheetValue,column:columnValue,row:rowValue,value:cellValue,style:styleValue })
	// 		null or 0 are used as default values for undefined entries

	excel.set( {sheet:0,value:"Admission List" } );

	var evenRow=excel.addStyle ( { 																	// Style for even ROWS
		border: "none,none,none,thin #333333"});													// Borders are LEFT,RIGHT,TOP,BOTTOM. Check $JExcel.borderStyles for a list of valid border styles

	var oddRow=excel.addStyle ( { 																	// Style for odd ROWS
		fill: "#ECECEC" , 																			// Background color, plain #RRGGBB, there is a helper $JExcel.rgbToHex(r,g,b)
		border: "none,none,none,thin #333333"});


	for (var i=1;i<=admissionListData.length;i++) {
		excel.set({row:i,style: i%2==0 ? evenRow: oddRow  });
	}

	var headers=["Nr. Crt.","First Name","Last Name","Medie Bac","Nota Examen","Medie","Clasificare"];							// This array holds the HEADERS text
	var formatHeader=excel.addStyle ( { 															// Format for headers
		border: "none,none,none,solid #333333", 													// 		Border for header
		font: "Calibri 12 #0000AA B",
		align: "C"
	}); 														// 		Font for headers

	for (var i=0;i<headers.length;i++){																// Loop all the haders
		excel.set(0,i,0,headers[i],formatHeader);													// Set CELL with header text, using header format
		excel.set(0,i,undefined,"auto");															// Set COLUMN width to auto (according to the standard this is only valid for numeric columns)
	}


	for (var i=1;i<=admissionListData.length;i++){																			// we will fill the 50 rows
		excel.set(0,0,i,i);															// This column is a TEXT
		excel.set(0,1,i,admissionListData[i-1].first_name);															// This column is a TEXT
		excel.set(0,2,i,admissionListData[i-1].last_name);															// This column is a TEXT
		excel.set(0,3,i,admissionListData[i-1].medie_bac);															// This column is a TEXT
		excel.set(0,4,i,admissionListData[i-1].nota_examen);															// This column is a TEXT
		excel.set(0,5,i,admissionListData[i-1].medie);															// This column is a TEXT
		excel.set(0,6,i,admissionListData[i-1].classification);															// This column is a TEXT
	}


	excel.set(0,1,undefined,30);																	// Set COLUMN 1 to 30 chars width
	excel.set(0,2,undefined,30);																	// Set COLUMN 1 to 30 chars width
	excel.set(0,3,undefined,20, excel.addStyle( {align:"R"}));										// Align column 4 to the right
	excel.set(0,4,undefined,20, excel.addStyle( {align:"R"}));										// Align column 4 to the right
	excel.set(0,5,undefined,20, excel.addStyle( {align:"R"}));										// Align column 4 to the right
	excel.set(0,6,undefined,20, excel.addStyle( {align:"C"}));										// Align column 4 to the right


	excel.generate("admissionList.xlsx");
}

function loadFileContent(evt) {
	var url;
	var file;
	file = evt.target.files[0];
	reader = new FileReader();
	//we need to instantiate a new FileReader object
	reader.addEventListener("load", readFileContent, false);
	//we add an event listener for when a file is loaded by the FileReader
	//this will call our function 'readThumbNail()'

	reader.readAsText(file);
}

function readFileContent(event) {
	processFileData(event.target.result);
}

document.querySelector("#studentsFile").addEventListener('change',loadFileContent, false);

function checkFieldsInFile(arr) {
	var validFields = {"first_name":1, "last_name": 1, "medie_bac": 1, "nota_examen":1};
	for (var i = 0; i < 4; i++) {
		if (validFields[arr[i]]) delete validFields[arr[i]];
		else {
			return false;
		}
	}
	return true;
}

var csvFileStudents = [];

function processFileData(allText) {
	csvFileStudents = [];
	//console.log(allText);
	var allTextLines = allText.split(/\r\n|\n/);
	//console.log(allTextLines);
	var headers = allTextLines[0].split(';');
	console.log(headers);
	var lines = [];

	if (headers.length != 4) {
		alert("The file does not have 4 column definition");
	}
	else if (!checkFieldsInFile(headers)) {
		alert("The file does not have a correct column definition");
	}
	else {
		for (var i=1; i<allTextLines.length; i++) {
			var data = allTextLines[i].split(';');
			if (data.length == headers.length) {

				var tarr = {};
				for (var j=0; j<headers.length; j++) {
					tarr[headers[j]] = data[j];
				}
				lines.push(tarr);
			}
		}
	}
	csvFileStudents = lines;

	$("#btnLoadStudentsFromFile").show();

	// alert(lines);
}

function sendFileStudentsData() {
	var post = {
		"operation": "insert_array",
		"into": "students",
		"values_array": []
	};

	for (var i = 0; i < csvFileStudents.length; i++) {
		post.values_array.push({"first_name": csvFileStudents[i].first_name, "last_name": csvFileStudents[i].last_name, "medie_bac":parseFloat(csvFileStudents[i].medie_bac), "nota_examen": parseFloat(csvFileStudents[i].nota_examen)});
	}
	sendStudentsFromFile(post);
	$("#btnLoadStudentsFromFile").hide();
}
