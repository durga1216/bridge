function addValue(){
	var num=arguments[0];
	var hm="";var n=1;
	    var respo1="{}";
	    try{
	    	respo1=JSON.parse(num);
	    }catch(e){
	    	respo1="{}";
	    }
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
				var hn=addParam(n);
				hm +="<div id='map'>"+key+"*<br>"+hn+"<div>";
				n++;
			}else if(sxml1.charAt(0)=='{'){
				for(var sky1 in xml1){
					var sxml2=JSON.stringify(xml1[""+sky1+""]);
					var xml2=JSON.parse(sxml2);
					if(sxml2.charAt(0)=='"'){
						//hm +="<option value=" + key + "."+sky1+">" + key + "/"+sky1+"</option>";
						var hn=addParam(n);
						hm +="<div id='map'>"+key+"/"+sky1+"*<br>"+hn+"<div>";
						n++;
					}else if(sxml2.charAt(0)=='{'){
						for(var sky2 in xml2){
							var sxml3=JSON.stringify(xml2[""+sky2+""]);
							var xml3=JSON.parse(sxml3);
							if(sxml3.charAt(0)=='"'){
								//hm +="<option value=" + key + "."+sky1+"."+sky2+">" + key + "/"+sky1+"/"+sky2+"</option>";
								var hn=addParam(n);
								hm +="<div id='map'>"+key+ "/"+sky1+"/"+sky2+"*<br>"+hn+"<div>";
								n++;
							}else if(sxml3.charAt(0)=='{'){
								for(var sky3 in xml3){
									var hn=addParam(n);
									hm +="<div id='map'>"+key+ "/"+sky1+"/"+sky2+"/"+sky3+"*<br>"+hn+"<div>";
									n++;
									//hm +="<option value=" + key + "."+sky1+"."+sky2+"."+sky3+">" + key + "/"+sky1+"/"+sky2+"/"+sky3+"</option>";
								}
							}else if(sxml3.charAt(0)=='['){
								var axml3=xml3[0];
								for(var aky3 in axml3){
									var hn=addParam(n);
									hm +="<div id='map'>"+key+"/"+sky1+"/"+sky2+"/"+aky3+"*<br>"+hn+"<div>";
									n++;
									//hm +="<option value=" + key + "."+sky1+"."+sky2+"["+(xml3.length-1)+"]."+aky3+">" + key + "/"+sky1+"/"+sky2+"/"+aky3+"</option>";
								}
							}else{
								var hn=addParam(n);
								hm +="<div id='map'>"+key+"/"+sky1+"/"+sky2+"*<br>"+hn+"<div>";
								n++;
								//hm +="<option value=" + key + "."+sky1+"."+sky2+">" + key + "/"+sky1+"/"+sky2+"</option>";			
							}
						}
					}else if(sxml2.charAt(0)=='['){
						var axml2=xml2[0];
						for(var aky2 in axml2){
							var sxml3=JSON.stringify(axml2[""+aky2+""]);
							var xml3=JSON.parse(sxml3);
							if(sxml3.charAt(0)=='"'){
								var hn=addParam(n);
								hm +="<div id='map'>"+key+"/"+sky1+"/"+aky2+"*<br>"+hn+"<div>";
								n++;
								//hm +="<option value=" + key + "."+sky1+"[0]."+aky2+">" + key + "/"+sky1+"/"+aky2+"</option>";
							}else if(sxml3.charAt(0)=='{'){
								for(var sky3 in xml3){
									var hn=addParam(n);
									hm +="<div id='map'>"+key+"/"+sky1+"/"+aky2+"/"+sky3+"*<br>"+hn+"<div>";
									n++;
									//hm +="<option value=" + key + "."+sky1+"[0]."+aky2+"."+sky3+">" + key + "/"+sky1+"/"+aky2+"/"+sky3+"</option>";
								}
							}else if(sxml3.charAt(0)=='['){
								var axml3=xml3[0];
								for(var aky3 in axml3){
									var hn=addParam(n);
									hm +="<div id='map'>"+key+"/"+sky1+"/"+aky2+"/"+aky3+"*<br>"+hn+"<div>";
									n++;
									//hm +="<option value=" + key + "."+sky1+"[0]."+aky2+"["+(xml3.length-1)+"]."+aky3+">" + key + "/"+sky1+"/"+aky2+"/"+aky3+"</option>";
								}
							}else{
								var hn=addParam(n);
								hm +="<div id='map'>"+key+"/"+sky1+"/"+aky2+"*<br>"+hn+"<div>";
								n++;
								//hm +="<option value=" + key + "."+sky1+"[0]."+aky2+">" + key + "/"+sky1+"/"+aky2+"</option>";			
							}
						}
					}else{
						var hn=addParam(n);
						hm +="<div id='map'>"+key+"/"+sky1+"*<br>"+hn+"<div>";
						n++;
						//hm +="<option value=" + key + "."+sky1+">" + key + "/"+sky1+"</option>";			
					}
				}
			}else if(sxml1.charAt(0)=='['){
				var axml1=xml1[0];
				for(var aky1 in axml1){
					var sxml2=JSON.stringify(axml1[""+aky1+""]);
					var xml2=JSON.parse(sxml2);
					if(sxml2.charAt(0)=='"'){
						var hn=addParam(n);
						hm +="<div id='map'>"+key+"/"+aky1+"*<br>"+hn+"<div>";
						n++;
						//hm +="<option value=$." + key + "[*]."+aky1+">" + key + "/"+aky1+"</option>";
					}else if(sxml2.charAt(0)=='{'){
						for(var sky2 in xml2){
							var sxml3=JSON.stringify(xml2[""+sky2+""]);
							var xml3=JSON.parse(sxml3);
							if(sxml3.charAt(0)=='"'){
								var hn=addParam(n);
								hm +="<div id='map'>"+key+"/"+aky1+"/"+sky2+"*<br>"+hn+"<div>";
								n++;
								//hm +="<option value=$." + key + "[*]."+aky1+"."+sky2+">" + key + "/"+aky1+"/"+sky2+"</option>";
							}else if(sxml3.charAt(0)=='{'){
								for(var sky3 in xml3){
									var hn=addParam(n);
									hm +="<div id='map'>"+key+"/"+aky1+"/"+sky2+"/"+sky3+"*<br>"+hn+"<div>";
									n++;
									//hm +="<option value=$." + key + "[*]."+aky1+"."+sky2+"."+sky3+">" + key + "/"+aky1+"/"+sky2+"/"+sky3+"</option>";
								}
							}else if(sxml3.charAt(0)=='['){
								var axml3=xml3[0];
								for(var aky3 in axml3){
									var hn=addParam(n);
									hm +="<div id='map'>"+key+"/"+aky1+"/"+sky2+"/"+aky3+"*<br>"+hn+"<div>";
									n++;
									//hm +="<option value=$." + key + "[*]."+aky1+"."+sky2+"["+(xml3.length-1)+"]."+aky3+">" + key + "/"+aky1+"/"+sky2+"/"+aky3+"</option>";
								}
							}else{
								var hn=addParam(n);
								hm +="<div id='map'>"+key+"/"+aky1+"/"+sky2+"*<br>"+hn+"<div>";
								n++;
								//hm +="<option value=$." + key + "[*]."+aky1+"."+sky2+">" + key + "/"+aky1+"/"+sky2+"</option>";			
							}
						}
					}else if(sxml2.charAt(0)=='['){
						var axml2=xml2[0];
						for(var aky2 in axml2){
							var sxml3=JSON.stringify(axml2[""+aky2+""]);
							var xml3=JSON.parse(sxml3);
							if(sxml3.charAt(0)=='"'){
								var hn=addParam(n);
								hm +="<div id='map'>"+key+"/"+aky1+"/"+aky2+"*<br>"+hn+"<div>";
								n++;
								//hm +="<option value=$." + key + "[*]."+aky1+"[*]."+aky2+">" + key + "/"+aky1+"/"+aky2+"</option>";
							}else if(sxml3.charAt(0)=='{'){
								for(var sky3 in xml3){
									var hn=addParam(n);
									hm +="<div id='map'>"+key+"/"+aky1+"/"+aky2+"/"+sky3+"*<br>"+hn+"<div>";
									n++;
									//hm +="<option value=$." + key + "[*]."+aky1+"[*]."+aky2+"."+sky3+">" + key + "/"+aky1+"/"+aky2+"/"+sky3+"</option>";
								}
							}else if(sxml3.charAt(0)=='['){
								var axml3=xml3[0];
								for(var aky3 in axml3){
									var hn=addParam(n);
									hm +="<div id='map'>"+key+"/"+aky1+"/"+aky2+"/"+aky3+"*<br>"+hn+"<div>";
									n++;
									//hm +="<option value=$." + key + "[*]."+aky1+"[*]."+aky2+"["+(xml3.length-1)+"]."+aky3+">" + key + "/"+aky1+"/"+aky2+"/"+aky3+"</option>";
								}
							}else{
								var hn=addParam(n);
								hm +="<div id='map'>"+key+"/"+aky1+"/"+aky2+"*<br>"+hn+"<div>";
								n++;
								//hm +="<option value=$." + key + "[*]."+aky1+"[*]."+aky2+">" + key + "/"+aky1+"/"+aky2+"</option>";		
							}
						}
					}else{
						var hn=addParam(n);
						hm +="<div id='map'>"+key+"/"+aky1+"*<br>"+hn+"<div>";
						n++;
						//hm +="<option value=$." + key + "[*]."+aky1+">" + key + "/"+aky1+"</option>";			
					}
				}
			}else{
				var hn=addParam(n);
				hm +="<div id='map'>"+key+"*<br>"+hn+"<div>";
				n++;
				//hm +="<option value="+key+">" + key + "</option>";			
			}
		}
	return hm;
}