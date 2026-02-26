let mapa;
let marcadores = {};

function iniciarMapa() {
    mapa = L.map('mapa').setView([21.125, -101.68], 12);

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '© OpenStreetMap contributors'
    }).addTo(mapa);
}

function agregarMarcador(id, lat, lng, nombre, material, precio) {
    let iconoNormal = L.divIcon({
        className: 'marcador-normal',
        html: '<div style="background:#3388ff;width:25px;height:25px;border-radius:50%;border:3px solid white;box-shadow:0 2px 5px rgba(0,0,0,0.3);"></div>',
        iconSize: [25, 25],
        iconAnchor: [12, 12]
    });

    let iconoActivo = L.divIcon({
        className: 'marcador-activo',
        html: '<div style="background:#00ffcc;width:35px;height:35px;border-radius:50%;border:4px solid white;box-shadow:0 4px 10px rgba(0,255,204,0.5);animation:pulse 1.5s infinite;"></div>',
        iconSize: [35, 35],
        iconAnchor: [17, 17]
    });

    let marcador = L.marker([lat, lng], { icon: iconoNormal })
        .addTo(mapa)
        .bindPopup(`<strong>${nombre}</strong><br>Material: ${material}<br>Precio: $${precio} por kg`);

    marcadores[id] = {
        marker: marcador,
        iconoNormal: iconoNormal,
        iconoActivo: iconoActivo,
        lat: lat,
        lng: lng
    };

    marcador.on('click', () => {
        resaltarTarjeta(id);
    });
}

function centrarEnEmpresa(id) {
    let marcador = marcadores[id];
    if (!marcador) return;

    Object.keys(marcadores).forEach(key => {
        marcadores[key].marker.setIcon(marcadores[key].iconoNormal);
    });

    marcador.marker.setIcon(marcador.iconoActivo);
    
    mapa.flyTo([marcador.lat, marcador.lng], 16, { duration: 1.5 });

    setTimeout(() => {
        marcador.marker.openPopup();
    }, 1000);

    if (window.innerWidth < 768) {
        document.getElementById('mapa').scrollIntoView({ behavior: 'smooth', block: 'center' });
    }
}

function resaltarTarjeta(id) {
    document.querySelectorAll('.card').forEach(card => {
        card.classList.remove('card-activa');
    });

    let tarjeta = document.getElementById(`empresa-${id}`);
    if (tarjeta) {
        tarjeta.classList.add('card-activa');
        tarjeta.scrollIntoView({ behavior: 'smooth', block: 'center' });
    }
}

async function cargarCatalogo() {
    let empresas = await obtenerEmpresas();
    mostrarEmpresas(empresas);
}

async function obtenerEmpresas() {
    let url = "api/catalogo";
    let resp = await fetch(url);
    let data = await resp.json();
    return data;
}

async function buscarPorMaterial() {
    let material = document.getElementById("material").value;
    let empresas;

    if (material === "") {
        empresas = await obtenerEmpresas();
    } else {
        empresas = await obtenerEmpresasPorMaterial(material);
    }

    mostrarEmpresas(empresas);
}

async function obtenerEmpresasPorMaterial(material) {
    let url = "api/catalogo/material/" + material;
    let resp = await fetch(url);
    let data = await resp.json();
    return data;
}

function mostrarEmpresas(lista) {
    let catalogo = document.getElementById("catalogo");
    catalogo.innerHTML = "";

    Object.values(marcadores).forEach(m => mapa.removeLayer(m.marker));
    marcadores = {};

    lista.forEach((empresa, index) => {
        let id = empresa.id || index;
        let lat = empresa.lat || 21.125 + (Math.random() * 0.02);
        let lng = empresa.lng || -101.68 + (Math.random() * 0.02);

        catalogo.innerHTML += `
            <div class="card" id="empresa-${id}" onclick="centrarEnEmpresa(${id})" style="cursor:pointer;">
                <h3>${empresa.nombre}</h3>
                <p><strong>Dirección:</strong> ${empresa.direccion}</p>
                <p><strong>Material:</strong> ${empresa.material}</p>
                <p class="precio">$ ${empresa.precioKg}</p>
                <small style="color:#00ffcc;opacity:0.8;margin-top:8px;display:block;">📍 Ver en mapa</small>
            </div>
        `;

        agregarMarcador(id, lat, lng, empresa.nombre, empresa.material, empresa.precioKg);
    });
}