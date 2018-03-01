//cipher
function cipher() {
    var A = parseInt(document.getElementById("alpha").value);
    var B = parseInt(document.getElementById("beta").value);
    
    if(gcd(A,26) != 1){
        alert("Invalid Alpha");
        document.getElementById('decipher').value="";
        document.getElementById('alpha').value="";
    }
    else{
        var msj = document.getElementById("to_cipher").value.replace(/ /g,'');
        arr = Array.from(msj);        
        msj = "";
        arr.forEach(function(element) {
            var asc = parseInt(element.charCodeAt(0)%97);
            var aux = String.fromCharCode(((A * asc + B)%26)+65);
            msj = msj.concat(aux);
        });

        document.getElementById('cipher').value=msj;
    }
}

//decipher
function decipher() {
    var A = parseInt(document.getElementById("alpha1").value,10);
    var B = parseInt(document.getElementById("beta1").value,10);

    if(gcd(A,26) != 1){
        alert("Invalid Alpha");
        document.getElementById('decipher').value="";
        document.getElementById('alpha1').value="";
    }
    else{
        var msj = document.getElementById("cipher").value;
        arr = Array.from(msj);      
        newmsj = "";
        arr.forEach(function(element) {
            var asc = parseInt(element.charCodeAt(0)%65,10);
            var aux = String.fromCharCode((Euclid_gcd(A,26) * (asc + (26-B)))%26+97);
            newmsj = newmsj.concat(aux);
        });
        document.getElementById('decipher').value=newmsj;
    }
}

function Euclid_gcd(a, b) {
    a = +a;
    b = +b;
    
    var signX = (a < 0) ? -1 : 1, signY = (b < 0) ? -1 : 1, x = 0, y = 1, u = 1, v = 0,q, r, m, n;
    a = Math.abs(a);
    b = Math.abs(b);
  
    while (a !== 0) {
        q = Math.floor(b / a);
        r = b % a;
        m = x - u * q;
        n = y - v * q;
        b = a;
        a = r;
        x = u;
        y = v;
        u = m;
        v = n;
    }
    if(signX * x < 0){
        return 26 + (signX * x);
    }
    return signX * x;
}

function gcd(a, b) {
    if (a == 0)
        return b;

    while (b != 0) {
        if (a > b)
            a = a - b;
        else
            b = b - a;
    }

    return a;
}

//reads the text file
function leerArchivo(input, textArea){
    var fileInput = document.getElementById(input);
    var fileDisplayArea = document.getElementById('fileDisplayArea');
        
    var file = fileInput.files[0];
    var textType = /text.*/;

    if (file.type.match(textType)) {
        var reader = new FileReader();

        reader.onload = function(e) {
            document.getElementById(textArea).value=reader.result;
        }

        reader.readAsText(file);	
    } else {
        fileDisplayArea.innerText = "File not supported!"
    }     
}
    

  


