var num = 0;
function addParam() {
    var respo2 =  document.getElementById('cmt');
    num = num + 1;
    var contentID = document.getElementById('content');
    var newTBDiv = document.createElement('div');
    newTBDiv.setAttribute('id', 'strText' + num);
    var hm = "";
    hm += "<input type='text' id='x" + num + "'    name='x" + num + "' placeholder='Label(Give any name)'/>"+"<select name=xv" + num + " id=xv" + num + ">";
    hm += "<option value=dummy>Choose Xml Node</option>";
    var xmlDoc = new window.DOMParser().parseFromString(respo2, "text/xml");
    var exres = document.getElementById('xr');
    //exres.value = xmlDoc.documentElement.nodeName;
    var y = xmlDoc.documentElement.childNodes;
    var i;
    var z;
    for (i = 0; i < y.length; i++) {
        if (i == 0) {
            for (z = 0; z < y[i].childNodes.length; z++) {
                var nm1 = y[i].nodeName;
                var nm2 = y[i].childNodes[z].nodeName;
                if (nm2 == '#text') {
                    hm += "<option value=" + nm1 + ">" + nm1 + "</option>";
                }
                else {
                    hm += "<option value=" + y[i].nodeName + "/" + nm2 + ">" + y[i].nodeName + "/" + nm2 + "</option>";
                }
            }
        }
        else {
            if (y[i].nodeName == y[i - 1].nodeName) {

            } else {
                for (z = 0; z < y[i].childNodes.length; z++) {
                    var nm1 = y[i].nodeName;
                    var nm2 = y[i].childNodes[z].nodeName;
                    var nn = y[i].childNodes.length;
                    if (nn == 1) {
                        hm += "<option value=" + nm1 + ">" + nm1 + "</option>";
                    }
                    else {
                        if (nm2 == '#text') {
                        } else {
                            hm += "<option value=" + y[i].nodeName + "/" + nm2 + ">" + y[i].nodeName + "/" + nm2 + "</option>";
                        }
                    }
                }
            }
        }
    }
    hm += "</select><br><br>";
    newTBDiv.innerHTML = hm;
    contentID.appendChild(newTBDiv);
}
function removeParam() {
    var contentID = document.getElementById('content');
    contentID.removeChild(document.getElementById('strText' + num));
    num = num - 1;
}