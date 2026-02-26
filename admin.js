async function login(){
    let nombreUsuario = document.getElementById("txtUsuario").value;
    let contrasenia = document.getElementById("txtContrasenia").value;
let url = "/cadire/api/usuario/login";
let params ={
                    nombre      : nombreUsuario,
                    contrasenia : contrasenia
        };
    let confServ = {
                    method  : "POST",
                    headers : {"Content-Type":"application/x-www-form-urlencoded;charset=UTF-8"},
                    body: new URLSearchParams(params)
    };
    let resp = await fetch(url, confServ);
    let data = await  resp.json();
    //alert(JSON.stringify(data));
    
    if(data.error != null)
    {
        Swal.fire('Error con el servidor 1', data.exception, 'error');
        return;
    }
    else if(data.exception != null){
        Swal.fire('Error con el servidor 2', data.exception, 'error');
        return;
    } 
    else
        {
            //Se redirige al usuario a la pagina principal del sistema:
            window.location.href = 'menu.html';
        }
}

async function cargarEmpresasAdmin() {

    let resp = await fetch("../api/catalogo");
    let data = await resp.json();

    let tabla = document.getElementById("tablaEmpresas");
    tabla.innerHTML = "";

    data.forEach(e => {
        tabla.innerHTML += `
            <tr>
                <td>${e.id}</td>
                <td>${e.nombre}</td>
                <td>${e.material}</td>
                <td>${e.precioKg}</td>
                <td>
                    <button onclick="eliminar(${e.id})">Eliminar</button>
                </td>
            </tr>
        `;
    });
}


async function guardarEmpresa() {

    let empresa = {
        nombre: document.getElementById("nombre").value,
        direccion: document.getElementById("direccion").value,
        telefono: document.getElementById("telefono").value,
        material: document.getElementById("material").value,
        precioKg: parseFloat(document.getElementById("precio").value),
        ciudad: document.getElementById("ciudad").value,
        lat: parseFloat(document.getElementById("lat").value),
        lng: parseFloat(document.getElementById("lng").value)
    };

    await fetch("../api/catalogo", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(empresa)
    });

    cargarEmpresasAdmin();
}


async function eliminar(id) {

    await fetch("../api/catalogo/" + id, {
        method: "DELETE"
    });

    cargarEmpresasAdmin();
}
function volverMenu() {
    document.getElementById('contenedor-principal').innerHTML = `
        <div id="menu-principal" class="text-center mt-5">
            <h3>Menú Principal</h3>
            <div class="mt-4">
                <button class="btn btn-primary m-2" onclick="irEmpresas()">
                    Empresas
                </button>
                <button class="btn btn-success m-2" onclick="mostrarMapaAdmin()">
                    Mapa
                </button>
            </div>
        </div>
    `;
}

function irEmpresas() {
    window.location.href = "adminEmpresas.html";
}