var num=0;
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
						hm +="<option value="+y[i].nodeName+"[last()]/"+nm2+"[last()]>"+y[i].nodeName+"/"+nm2+"</option>";
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
								hm +="<option value="+y[i].nodeName+"[last()]/"+nm2+"[last()]>"+y[i].nodeName+"/"+nm2+"</option>";
							}
						}
					}
				}
			}
		}
		hm +="</select>&nbsp;&nbsp;&nbsp;<input type=text placeholder=' If Other' id=xx"+num+" name=xx"+num+"><br><br>";
		newTBDiv.innerHTML = hm;
	}
	else{
	    hm +="<select name=x"+num+" id=x"+num+">";
	    hm +="<option value=dummy>Choose Json Node</option>";
		var respo1=JSON.parse(respo2);
		var xml="";
		if(chfst=="["){
			xml=respo1[0];
		}else if(chfst=="{"){
			xml=respo1;
		}
		for(var key in xml){
			var sxml1=JSON.stringify(xml[""+key+""]);
			var xml1=JSON.parse(sxml1);
			if(sxml1.charAt(0)=='"'){
				hm +="<option value="+key+">" + key + "</option>";
			}else if(sxml1.charAt(0)=='{'){
				for(var sky1 in xml1){
					var sxml2=JSON.stringify(xml1[""+sky1+""]);
					var xml2=JSON.parse(sxml2);
					if(sxml2.charAt(0)=='"'){
						hm +="<option value=" + key + "."+sky1+">" + key + "/"+sky1+"</option>";
					}else if(sxml2.charAt(0)=='{'){
						for(var sky2 in xml2){
							var sxml3=JSON.stringify(xml2[""+sky2+""]);
							var xml3=JSON.parse(sxml3);
							if(sxml3.charAt(0)=='"'){
								hm +="<option value=" + key + "."+sky1+"."+sky2+">" + key + "/"+sky1+"/"+sky2+"</option>";
							}else if(sxml3.charAt(0)=='{'){
								for(var sky3 in xml3){
									hm +="<option value=" + key + "."+sky1+"."+sky2+"."+sky3+">" + key + "/"+sky1+"/"+sky2+"/"+sky3+"</option>";
								}
							}else if(sxml3.charAt(0)=='['){
								var axml3=xml3[0];
								for(var aky3 in axml3){
									hm +="<option value=" + key + "."+sky1+"."+sky2+"["+(xml3.length-1)+"]."+aky3+">" + key + "/"+sky1+"/"+sky2+"/"+aky3+"</option>";
								}
							}else{
								hm +="<option value=" + key + "."+sky1+"."+sky2+">" + key + "/"+sky1+"/"+sky2+"</option>";			
							}
						}
					}else if(sxml2.charAt(0)=='['){
						var axml2=xml2[0];
						for(var aky2 in axml2){
							var sxml3=JSON.stringify(axml2[""+aky2+""]);
							var xml3=JSON.parse(sxml3);
							if(sxml3.charAt(0)=='"'){
								hm +="<option value=" + key + "."+sky1+"[0]."+aky2+">" + key + "/"+sky1+"/"+aky2+"</option>";
							}else if(sxml3.charAt(0)=='{'){
								for(var sky3 in xml3){
									hm +="<option value=" + key + "."+sky1+"[0]."+aky2+"."+sky3+">" + key + "/"+sky1+"/"+aky2+"/"+sky3+"</option>";
								}
							}else if(sxml3.charAt(0)=='['){
								var axml3=xml3[0];
								for(var aky3 in axml3){
									hm +="<option value=" + key + "."+sky1+"[0]."+aky2+"["+(xml3.length-1)+"]."+aky3+">" + key + "/"+sky1+"/"+aky2+"/"+aky3+"</option>";
								}
							}else{
								hm +="<option value=" + key + "."+sky1+"[0]."+aky2+">" + key + "/"+sky1+"/"+aky2+"</option>";			
							}
						}
					}else{
						hm +="<option value=" + key + "."+sky1+">" + key + "/"+sky1+"</option>";			
					}
				}
			}else if(sxml1.charAt(0)=='['){
				var axml1=xml1[0];
				for(var aky1 in axml1){
					var sxml2=JSON.stringify(axml1[""+aky1+""]);
					var xml2=JSON.parse(sxml2);
					if(sxml2.charAt(0)=='"'){
						hm +="<option value=" + key + "[0]."+aky1+">" + key + "/"+aky1+"</option>";
					}else if(sxml2.charAt(0)=='{'){
						for(var sky2 in xml2){
							var sxml3=JSON.stringify(xml2[""+sky2+""]);
							var xml3=JSON.parse(sxml3);
							if(sxml3.charAt(0)=='"'){
								hm +="<option value=" + key + "[0]."+aky1+"."+sky2+">" + key + "/"+aky1+"/"+sky2+"</option>";
							}else if(sxml3.charAt(0)=='{'){
								for(var sky3 in xml3){
									hm +="<option value=" + key + "[0]."+aky1+"."+sky2+"."+sky3+">" + key + "/"+aky1+"/"+sky2+"/"+sky3+"</option>";
								}
							}else if(sxml3.charAt(0)=='['){
								var axml3=xml3[0];
								for(var aky3 in axml3){
									hm +="<option value=" + key + "[0]."+aky1+"."+sky2+"["+(xml3.length-1)+"]."+aky3+">" + key + "/"+aky1+"/"+sky2+"/"+aky3+"</option>";
								}
							}else{
								hm +="<option value=" + key + "[0]."+aky1+"."+sky2+">" + key + "/"+aky1+"/"+sky2+"</option>";			
							}
						}
					}else if(sxml2.charAt(0)=='['){
						var axml2=xml2[0];
						for(var aky2 in axml2){
							var sxml3=JSON.stringify(axml2[""+aky2+""]);
							var xml3=JSON.parse(sxml3);
							if(sxml3.charAt(0)=='"'){
								hm +="<option value=" + key + "[0]."+aky1+"[0]."+aky2+">" + key + "/"+aky1+"/"+aky2+"</option>";
							}else if(sxml3.charAt(0)=='{'){
								for(var sky3 in xml3){
									hm +="<option value=" + key + "[0]."+aky1+"[0]."+aky2+"."+sky3+">" + key + "/"+aky1+"/"+aky2+"/"+sky3+"</option>";
								}
							}else if(sxml3.charAt(0)=='['){
								var axml3=xml3[0];
								for(var aky3 in axml3){
									hm +="<option value=" + key + "[0]."+aky1+"[0]."+aky2+"["+(xml3.length-1)+"]."+aky3+">" + key + "/"+aky1+"/"+aky2+"/"+aky3+"</option>";
								}
							}else{
								hm +="<option value=" + key + "[0]."+aky1+"[0]."+aky2+">" + key + "/"+aky1+"/"+aky2+"</option>";		
							}
						}
					}else{
						hm +="<option value=" + key + "[0]."+aky1+">" + key + "/"+aky1+"</option>";			
					}
				}
			}else{
				hm +="<option value="+key+">" + key + "</option>";			
			}
		}
		hm +="</select>&nbsp;&nbsp;&nbsp;<input type=text placeholder=' If Other' id=xx"+num+" name=xx"+num+"><br><br>";
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