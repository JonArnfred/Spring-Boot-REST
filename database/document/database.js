// collection names should start with _, but this can be circumvented
// by using db["x"] in place of db._x

// start by dropping the database
db["company_db"].drop()

// 'use "organisation_db"' does not work in scripted mode,
// see https://docs.mongodb.com/manual/tutorial/write-scripts-for-the-mongo-shell/
db = db.getSiblingDB("company_db");


db["company"].insertMany([{name: "My Company"}, {name: "Mærsk"}])

var myCompany = db["company"].findOne({name: "My Company"})
var maersk = db["company"].findOne({name: "Mærsk"})

db["employee"].insertMany([
{name: "Joe", organisation: myCompany._id},
{name: "Pete", organisation: myCompany._id},
{name: "Hans", organisation: maersk._id}
])