require("./index.js");
var Browser = require('zombie');
var assert = require('assert');
var util = require('util');
var expect = require('chai').expect;
// We're going to make requests to http://example.com/signup
// Which will be routed to our test server localhost:3000
//Browser.localhost('http://test.com', 3000);
var browser = new Browser({ debug: true, runScripts: true });
describe('User visits Add a Student', function () {
    describe('Add a Student', function () {
        before(function (done) {
            browser.visit('http://localhost:3000').then(function () {
                browser.clickLink('#addAStudent', done);
            });
        });
        it('should be successful', function () {
            browser.assert.success();
        });
        it('should properly load the page', function () {
            browser.assert.style('#addStudentScreen:not(.hidden)', 'display', '');
        });
        it('should properly fill the form and submit but fail on result because no transport layer on front-end testing', function () {
            //browser.evaluate('jQuery.support.cors=true');
            browser.fill('#first_name', 'Auto Test First Name');
            browser.fill('#last_name', 'Auto Test Last Name');
            browser.fill('#medie_bac', 9);
            browser.fill('#nota_examen', 10);
            browser.wait();
            browser.pressButton('#btnAddAStudent');
            browser.wait();
            //console.log(browser.html());
            //console.log(browser.text('#addStatus'));
            expect(browser.text('#addStatus')).not.be.equal('Student has been added!');
        });
    });
    describe('Edit / Delete Student', function () {
        before(function (done) {
            browser.visit('http://localhost:3000').then(function () {
                browser.clickLink('#editStudent').then(function () {
                    browser.wait();
                    //console.log(browser.evaluate('#viewStudentScreen'));
                    //console.log(browser.html());
                    done();
                });
            });
        });
        it('should be successful', function () {
            browser.assert.success();
        });
        it('should properly load the page', function () {
            browser.wait();
            browser.assert.style('#viewStudentScreen:not(.hidden)', 'display', '');
        });
    });
    describe('Load Students from File', function () {
        before(function (done) {
            browser.visit('http://localhost:3000').then(function () {
                browser.clickLink('#loadStudents').then(function () {
                    browser.wait();
                    //console.log(browser.evaluate('#viewStudentScreen'));
                    //console.log(browser.html());
                    done();
                });
            });
        });
        it('should be successful', function () {
            browser.assert.success();
        });
        it('should properly load the page', function () {
            browser.wait();
            browser.assert.style('#loadStudentsFromFileScreen:not(.hidden)', 'display', '');
        });
    });
    describe('View Admission List', function () {
        before(function (done) {
            browser.visit('http://localhost:3000').then(function () {
                browser.clickLink('#admissionList').then(function () {
                    browser.wait();
                    //console.log(browser.evaluate('#viewStudentScreen'));
                    //console.log(browser.html());
                    done();
                });
            });
        });
        it('should be successful', function () {
            browser.assert.success();
        });
        it('should properly load the page', function () {
            browser.wait();
            browser.assert.style('#admissionListScreen:not(.hidden)', 'display', '');
        });
    });
});
