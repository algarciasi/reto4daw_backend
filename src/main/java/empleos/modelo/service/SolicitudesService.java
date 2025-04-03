package empleos.modelo.service;

import empleos.modelo.entity.Solicitudes;

public interface SolicitudesService {
	Solicitudes enviarSolicitud(int idVacante, String emailUsuario, Solicitudes datos);


}
