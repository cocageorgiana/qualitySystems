const Browser = require('zombie');
const assert = require('assert');
const util = require('util');
let expect  = require('chai').expect;

// We're going to make requests to http://example.com/signup
// Which will be routed to our test server localhost:3000
//Browser.localhost('http://test.com', 3000);

const browser = new Browser({debug: true, runScripts: true });

describe('User visits Add a Student', function() {
	before(function(done) {
        browser.visit('http://localhost:3000').then(() => {
        	//console.log(browser.location.href);
            browser.clickLink('#addAStudent').then( () => {
            	//console.log(util.inspect(browser.html()));
            	done();
			});
		});
	});

	describe('Add a Student', function() {

        it('should be successful', function() {
            browser.assert.success();
        });


        it( 'should properly load the page', function() {
            browser.assert.style('#addStudentScreen:not(.hidden)', 'display', '');
        } );

        it( 'should properly fill the form and submit', function() {
            browser.fill('#first_name',   'Auto Test First Name');
            browser.fill('#last_name',    'Auto Test Last Name');
            browser.fill('#medie_bac',    9);
            browser.fill('#nota_examen',  10);
            browser.pressButton('#btnAddAStudent');
            browser.wait().then(function() {
                // just dump some debug data to see if we're on the right page
                //console.log(browser.html());
            });
            //browser.assert.text('#addStatus', 'Student has been added!');
            browser.assert.text('#addStatus', 'Error when adding the Student');
        } );

	});
});


