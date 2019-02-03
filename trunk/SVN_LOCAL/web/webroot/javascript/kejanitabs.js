
function myFunction() {
    var x = document.getElementById('formholder');
    var y = document.getElementById('listData');
    if (x.style.display === 'none') {
        x.style.display = 'block';
        y.style.display = 'none';
    } else {
        x.style.display = 'none';
        y.style.display = 'block';
    }
}

function mailFunction() {
    var x = document.getElementById('mailbody');
    if (x.style.display === 'none') {
        x.style.display = 'block';
    } else {
        x.style.display = 'none';
    }
}

function clearMessages() {
    var x = document.getElementById('alert-danger');
    var y = document.getElementById('alert-success');

        x.style.display = 'none';
        y.style.display = 'none';

}

function myMaps() {
    var x = document.getElementById('detailsdiv4');
    var y = document.getElementById('detailsdiv5');
    if (x.style.display === 'none') {
        x.style.display = 'block';
        y.style.display = 'none';
    } 
}

function myStreets() {
    var x = document.getElementById('detailsdiv5');
    var y = document.getElementById('detailsdiv4');
    if (x.style.display === 'none') {
        x.style.display = 'block';
        y.style.display = 'none';
    } 
}


