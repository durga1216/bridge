function addParam(){
	num = num + 1;
	var contentID = document.getElementById('content');
	var newTBDiv = document.createElement('div');
	newTBDiv.setAttribute('id','strText'+num);
	var hm="";
	if(chfst=='<'){
		hm +="<select name=x"+num+" id=x"+num+">";
		hm +="<option value=dummy>Choose Xml Node</option>";
		var xmlDoc = new window.DOMParser().parseFromString(respo2,"text/xml");
		var exres=document.getElementById('ptag');
		exres.value=xmlDoc.documentElement.nodeName;
		var y=xmlDoc.documentElement.childNodes;
		var i;var z;
		for (i=0;i<y.length;i++){
			if(i==0){
				for (z=0;z<y[i].childNodes.length;z++)
				{
					var nm1=y[i].nodeName;
					var nm2=y[i].childNodes[z].nodeName;
					if(nm2=='#text'){ 
						hm +="<option value="+nm1+">" + nm1 + "</option>";
					}
					else{
						hm +="<option value="+y[i].nodeName+"/"+nm2+">"+y[i].nodeName+"/"+nm2+"</option>";
					}
				}
			}
			else{
				if(y[i].nodeName==y[i-1].nodeName){
		
				}else{
					for (z=0;z<y[i].childNodes.length;z++)
					{
						var nm1=y[i].nodeName;
						var nm2=y[i].childNodes[z].nodeName;
						var nn=y[i].childNodes.length;
						if(nn==1){ 
							hm +="<option value="+nm1+">" + nm1 + "</option>";
						}
						else{
							if(nm2=='#text'){ 
							}else{
								hm +="<option value="+y[i].nodeName+"/"+nm2+">"+y[i].nodeName+"/"+nm2+"</option>";
							}
						}
					}
				}
			}
		}
		hm +="</select>&nbsp;&nbsp;&nbsp;<input type=text placeholder=' If Other' id=xv"+num+" name=xv"+num+"><br><br>";
		newTBDiv.innerHTML = hm;
	}
	else{
	    hm +="<select name=x"+num+" id=x"+num+">";
	    hm +="<option value=dummy>Choose Json Node</option>";
		var respo1=JSON.parse(respo2);
		console.log(respo1);
		var xml=respo1;
		for(var key in xml){
			var key1=JSON.stringify(xml[""+key+""]);
			if(key1.charAt(0)=='"'){
				hm +="<option value="+key+">" + key + "</option>";
			}else if(key1.charAt(0)=='{'){
				var xml1=JSON.parse(key1);
				for(var key2 in xml1){
					hm +="<option value=" + key + "."+key2+">" + key + "/"+key2+"</option>";
				}
			}else if(key1.charAt(0)=='['){
				var xml2=JSON.parse(key1);
				var xml1=xml2[0];
				for(var aky in xml1){
					hm +="<option value=" + key + "[0]."+aky+">" + key + "/"+aky+"</option>";
				}
			}else{
				hm +="<option value="+key+">" + key + "</option>";			
			}
		}
		hm +="</select>&nbsp;&nbsp;&nbsp;<input type=text placeholder=' If Other' id=xv"+num+" name=xv"+num+"><br><br>";
		newTBDiv.innerHTML = hm;
	}
	contentID.appendChild(newTBDiv);
}
function removeParam()
{
	var contentID = document.getElementById('content');
	contentID.removeChild(document.getElementById('strText'+num));
	num=num-1;
}