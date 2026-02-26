let mapa;
let marcadores = {};

function iniciarMapa() {
    mapa = L.map('mapa', {
        zoomControl: false
    }).setView([21.125, -101.68], 13);
    L.control.zoom({
        position: 'topright'
    }).addTo(mapa);
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '© OpenStreetMap contributors',
        maxZoom: 19
    }).addTo(mapa);
}

function agregarMarcador(id, lat, lng, nombre, material, precio, direccion) {
    let iconoNormal = L.divIcon({
        className: 'marcador-normal',
        html: `<div style="
            background: linear-gradient(135deg, #3388ff 0%, #0055ff 100%);
            width: 30px; height: 30px; border-radius: 50%;
            border: 3px solid white; box-shadow: 0 3px 10px rgba(0,0,0,0.4);
            display: flex; align-items: center; justify-content: center;
            color: white; font-weight: bold; font-size: 14px;
        ">R</div>`,
        iconSize: [30, 30],
        iconAnchor: [15, 15],
        popupAnchor: [0, -15]
    });
    let iconoActivo = L.divIcon({
        className: 'marcador-activo',
        html: `<div style="
            background: linear-gradient(135deg, #00ffcc 0%, #00ccaa 100%);
            width: 40px; height: 40px; border-radius: 50%;
            border: 4px solid white; box-shadow: 0 0 20px rgba(0,255,204,0.6);
            display: flex; align-items: center; justify-content: center;
            animation: pulse 1.5s infinite; color: #16213e;
            font-weight: bold; font-size: 18px;
        ">R</div>`,
        iconSize: [40, 40],
        iconAnchor: [20, 20],
        popupAnchor: [0, -20]
    });
    let marcador = L.marker([lat, lng], { icon: iconoNormal })
        .addTo(mapa)
        .bindPopup(`
            <div style="min-width: 200px;">
                <h4 style="margin: 0 0 10px 0; color: #00ffcc; 
                    border-bottom: 1px solid #3388ff; padding-bottom: 5px;">
                    ${nombre}
                </h4>
                <p style="margin: 5px 0;"><strong>📦 Material:</strong> ${material}</p>
                <p style="margin: 5px 0;"><strong>💰 Precio:</strong> $${precio} por kg</p>
                ${direccion ? `<p style="margin: 5px 0; font-size: 0.9em; color: #aaa;">
                    <strong>📍 Dir:</strong> ${direccion}</p>` : ''}
            </div>
        `);
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
    
    mapa.flyTo([marcador.lat, marcador.lng], 17, { 
        duration: 1.5 
    });

    setTimeout(() => {
        marcador.marker.openPopup();
    }, 1000);
}

function resaltarTarjeta(id) {
    document.querySelectorAll('.card-empresa').forEach(card => {
        card.classList.remove('card-activa');
    });

    let tarjeta = document.getElementById(`empresa-${id}`);
    if (tarjeta) {
        tarjeta.classList.add('card-activa');
        tarjeta.scrollIntoView({ behavior: 'smooth', block: 'center' });
    }

    // Activar marcador visualmente
    if (marcadores[id]) {
        Object.keys(marcadores).forEach(key => {
            marcadores[key].marker.setIcon(marcadores[key].iconoNormal);
        });
        marcadores[id].marker.setIcon(marcadores[id].iconoActivo);
    }
}

async function cargarCatalogo() {
    try {
        console.log("Iniciando carga de catálogo...");
        
        let empresas = await obtenerEmpresas();
        
        console.log("Empresas recibidas:", empresas);
        
        if (!empresas || !Array.isArray(empresas)) {
            throw new Error("La respuesta no es un array válido");
        }
        
        mostrarEmpresas(empresas);
        
    } catch (error) {
        console.error("Error detallado:", error);
        document.getElementById('catalogo').innerHTML = `
            <div style="color: #ff6b6b; text-align: center; padding: 20px;">
                <p><strong>Error al cargar sucursales:</strong></p>
                <p style="font-size: 0.9em; color: #888;">${error.message}</p>
                <button onclick="cargarCatalogo()" style="
                    margin-top: 15px; padding: 8px 16px; 
                    background: #3388ff; color: white; border: none;
                    border-radius: 5px; cursor: pointer;
                ">🔄 Reintentar</button>
            </div>
        `;
    }
}

async function obtenerEmpresas() {
     let url = "http://localhost:8080/cadire/api/catalogo";

    try {
        let resp = await fetch(url, {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });
        
        console.log("Status de respuesta:", resp.status);
        
        if (!resp.ok) {
            throw new Error(`HTTP ${resp.status}: ${resp.statusText}`);
        }
        
        let data = await resp.json();
        return data;
        
    } catch (error) {
        console.error("Error en fetch:", error);
        throw error;
    }
}

function mostrarEmpresas(lista) {
    let catalogo = document.getElementById("catalogo");
    let contador = document.getElementById("contador");
    
    if (contador) {
        contador.textContent = `${lista.length} sucursal${lista.length !== 1 ? 'es' : ''}`;
    }
    
    catalogo.innerHTML = "";
    
    Object.values(marcadores).forEach(m => mapa.removeLayer(m.marker));
    marcadores = {};

    if (lista.length === 0) {
        catalogo.innerHTML = `
            <div style="color: #888; text-align: center; padding: 40px;">
                No hay sucursales registradas.
            </div>
        `;
        return;
    }

    let bounds = L.latLngBounds();
    let tieneCoordenadasValidas = false;

    lista.forEach((empresa, index) => {
        let id = empresa.id || index;
        let lat = parseFloat(empresa.lat);
        let lng = parseFloat(empresa.lng);
        
        if (isNaN(lat) || isNaN(lng) || lat === 0 || lng === 0) {
            console.warn(`Empresa ${empresa.nombre} sin coordenadas válidas, usando posición por defecto`);
            lat = 21.125 + (Math.random() * 0.04 - 0.02);
            lng = -101.68 + (Math.random() * 0.04 - 0.02);
        } else {
            tieneCoordenadasValidas = true;
        }
        
        bounds.extend([lat, lng]);
        
        let card = document.createElement('div');
        card.className = 'card-empresa';
        card.id = `empresa-${id}`;
        card.onclick = () => centrarEnEmpresa(id);
        
        card.innerHTML = `
            <h3>${empresa.nombre || 'Sin nombre'}</h3>
            <p><strong>📍 Dirección:</strong> ${empresa.direccion || 'No especificada'}</p>
            <p><strong>📦 Material:</strong> ${empresa.material || 'Varios'}</p>
            <p class="precio">💰 $${empresa.precioKg || '0.00'} /kg</p>
            <div class="ver-mapa-hint">🎯 Click para ver en mapa</div>
        `;
        
        catalogo.appendChild(card);

        agregarMarcador(
            id, 
            lat, 
            lng, 
            empresa.nombre || 'Sin nombre', 
            empresa.material || 'Varios', 
            empresa.precioKg || '0.00',
            empresa.direccion
        );
    });

    if (tieneCoordenadasValidas) {
        mapa.fitBounds(bounds, {
            padding: [50, 50],
            maxZoom: 15
        });
    }
}

window.addEventListener('resize', () => {
    if (mapa) mapa.invalidateSize();
});