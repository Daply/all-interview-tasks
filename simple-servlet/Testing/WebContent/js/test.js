function sendKeyword() {
        var keyword = new Object();
        keyword.text = $('#keyword').val();
        
        $.ajax({
            url: "server",
            type: 'POST',
            dataType: 'json',
            data: JSON.stringify(keyword),
            contentType: 'application/json',
            mimeType: 'application/json',
            success: function (data) {
            	var classToTurn = data.className;
            	if(classToTurn == ""){
            		document.getElementById('subjects').innerText = "no class with such keyword";
            		return;
            	}         		
            	if(data.state){
            		document.getElementById('subjects').innerHTML = classToTurn + ": I am turned on!   " + "<button id = \"turn\">"+ "turn off" +"</button>";
            	}
            	else{   
            		document.getElementById('subjects').innerHTML = classToTurn + ": I am turned off!   " + "<button id = \"turn\">"+ "turn on" +"</button>";
            	}   
        		document.getElementById("turn").addEventListener("click", function(){
        			data.state = false;
        			sendKeyword();
        		});
            },
            error:function(data,status,er) {
                alert("error: "+data+" status: "+status+" er:"+er);
            }
        });
    }
