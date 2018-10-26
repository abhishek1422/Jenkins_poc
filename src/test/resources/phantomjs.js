//var json = require('C:/Users/anagpurkar/Desktop/Artifact_id_1/src/test/resources/jsonOutput.json');

//var id=json.hits.hits[0]._id;
var id="Batch Monitor";
//var id=json.dashboardInfo[0].dashboardID1;
console.log(id);

var url ='http://logs.adds.manhdev.com/app/kibana#/dashboard/'+id+'?_g=()';

var page = require('webpage').create();
//wait to load webpage in ms
var waitTime = 20 * 1000;

//size of virtual browser window
page.viewportSize = { width: 1000, height: 500 };

page.open(url, function (status) {
    if (status !== 'success') {
        console.log('Unable to load the address!');
        phantom.exit();
    } else {
        window.setTimeout(function () {
            //page.zoomFactor = 2.0;
//save as image
            page.render('dashboard'+id+'.jpg');
            phantom.exit();
        }, waitTime);
    }
});
