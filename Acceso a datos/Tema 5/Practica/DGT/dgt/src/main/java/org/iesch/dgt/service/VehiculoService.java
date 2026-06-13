package org.iesch.dgt.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iesch.dgt.dto.TransferenciaRequest;
import org.iesch.dgt.dto.VerificacionTransferenciaResponse;
import org.iesch.dgt.exception.BusinessException;
import org.iesch.dgt.exception.VehiculoNotFoundException;
import org.iesch.dgt.modelo.*;
import org.iesch.dgt.modelo.enums.EstadoMulta;
import org.iesch.dgt.modelo.enums.EstadoVehiculo;
import org.iesch.dgt.repositorio.VehiculoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicio principal para la gestión de vehículos
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class VehiculoService {

    private final VehiculoRepository vehiculoRepository;

    /**
     * Alta de un nuevo vehículo
     */
    public Vehiculo altaVehiculo(Vehiculo vehiculo) {
        log.info("Iniciando alta de vehículo con matrícula: {}", vehiculo.getMatricula());

        // Validar matrícula única
        if (vehiculoRepository.existsByMatricula(vehiculo.getMatricula())) {
            throw new BusinessException("Ya existe un vehículo con matrícula: " + vehiculo.getMatricula());
        }

        // Validar bastidor único
        if (vehiculoRepository.existsByBastidor(vehiculo.getBastidor())) {
            throw new BusinessException("Ya existe un vehículo con bastidor: " + vehiculo.getBastidor());
        }

        // Validar DNI del titular
        validarDNI(vehiculo.getTitular().getDni());

        // Validar ITV según antigüedad del vehículo
        validarITVNuevoVehiculo(vehiculo);

        // Inicializar campos
        vehiculo.setFechaCreacion(LocalDateTime.now());
        vehiculo.setFechaActualizacion(LocalDateTime.now());

        if (vehiculo.getSituacionAdministrativa() == null) {
            SituacionAdministrativa situacion = new SituacionAdministrativa();
            situacion.setEstado(EstadoVehiculo.ACTIVO);
            situacion.setFechaEstado(LocalDateTime.now());
            vehiculo.setSituacionAdministrativa(situacion);
        }

        if (vehiculo.getMultas() == null) {
            vehiculo.setMultas(new ArrayList<>());
        }

        if (vehiculo.getHistorialTitulares() == null) {
            vehiculo.setHistorialTitulares(new ArrayList<>());
        }

        Vehiculo vehiculoGuardado = vehiculoRepository.save(vehiculo);
        log.info("Vehículo dado de alta correctamente: {}", vehiculoGuardado.getMatricula());

        return vehiculoGuardado;
    }

    /**
     * Validar ITV para vehículos nuevos vs usados
     */
    private void validarITVNuevoVehiculo(Vehiculo vehiculo) {
        int anioActual = Year.now().getValue();
        int antiguedad = anioActual - vehiculo.getAnioFabricacion();

        if (antiguedad < 4) {
            // Vehículo nuevo: requiere eITV
            if (vehiculo.getItv() == null ||
                vehiculo.getItv().getNumeroInforme() == null ||
                !vehiculo.getItv().getNumeroInforme().startsWith("e-ITV")) {
                throw new BusinessException(
                    "Vehículo nuevo (menos de 4 años) requiere tarjeta electrónica eITV"
                );
            }
        } else {
            // Vehículo usado: requiere ITV en vigor
            if (vehiculo.getItv() == null || !vehiculo.getItv().getEnVigor()) {
                throw new BusinessException(
                    "Vehículo usado debe tener ITV en vigor"
                );
            }

            if (vehiculo.getItv().getFechaCaducidad().isBefore(LocalDateTime.now())) {
                throw new BusinessException(
                    "La ITV está caducada. Fecha de caducidad: " +
                    vehiculo.getItv().getFechaCaducidad()
                );
            }
        }
    }

    /**
     * Validar formato de DNI español con letra de control
     */
    private void validarDNI(String dni) {
        if (dni == null || !dni.matches("^[0-9]{8}[A-Z]$")) {
            throw new BusinessException("Formato de DNI inválido: " + dni);
        }

        // Validación avanzada del dígito de control
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        int numero = Integer.parseInt(dni.substring(0, 8));
        char letraCalculada = letras.charAt(numero % 23);
        char letraProporcionada = dni.charAt(8);

        if (letraCalculada != letraProporcionada) {
            throw new BusinessException("DNI con letra de control incorrecta");
        }
    }

    /**
     * Baja definitiva de un vehículo
     */
    public Vehiculo bajaDefinitiva(String matricula, String motivo, String certificado) {
        log.info("Procesando baja definitiva de vehículo: {}", matricula);

        Vehiculo vehiculo = buscarPorMatricula(matricula);

        // Validar estado actual
        if (!vehiculo.getSituacionAdministrativa().getEstado().equals(EstadoVehiculo.ACTIVO)) {
            throw new BusinessException(
                "Solo se pueden dar de baja definitiva vehículos en estado ACTIVO"
            );
        }

        // Validar multas pendientes
        long multasPendientes = contarMultasPendientes(vehiculo);
        if (multasPendientes > 0) {
            throw new BusinessException(
                "El vehículo tiene " + multasPendientes + " multas pendientes de pago"
            );
        }

        // Validar certificado para desguace
        if ("DESGUACE".equals(motivo) && (certificado == null || certificado.isEmpty())) {
            throw new BusinessException(
                "La baja por desguace requiere certificado de destrucción"
            );
        }

        // Actualizar situación administrativa
        SituacionAdministrativa situacion = vehiculo.getSituacionAdministrativa();
        situacion.setEstado(EstadoVehiculo.BAJA_DEFINITIVA);
        situacion.setMotivoBaja(motivo);
        situacion.setFechaEstado(LocalDateTime.now());

        vehiculo.setFechaActualizacion(LocalDateTime.now());

        Vehiculo vehiculoActualizado = vehiculoRepository.save(vehiculo);
        log.info("Baja definitiva procesada correctamente para: {}", matricula);

        return vehiculoActualizado;
    }

    /**
     * Baja temporal de un vehículo (duración: 1 año)
     */
    public Vehiculo bajaTemporal(String matricula, String motivo) {
        log.info("Procesando baja temporal de vehículo: {}", matricula);

        Vehiculo vehiculo = buscarPorMatricula(matricula);

        // Validar estado actual
        if (!vehiculo.getSituacionAdministrativa().getEstado().equals(EstadoVehiculo.ACTIVO)) {
            throw new BusinessException(
                "Solo se pueden dar de baja temporal vehículos en estado ACTIVO"
            );
        }

        // Calcular fechas (duración: exactamente 1 año)
        LocalDateTime fechaInicio = LocalDateTime.now();
        LocalDateTime fechaFin = fechaInicio.plusYears(1);

        // Actualizar situación administrativa
        SituacionAdministrativa situacion = vehiculo.getSituacionAdministrativa();
        situacion.setEstado(EstadoVehiculo.BAJA_TEMPORAL);
        situacion.setMotivoBaja(motivo);
        situacion.setFechaBajaTemporal(fechaInicio);
        situacion.setFechaFinBajaTemporal(fechaFin);
        situacion.setFechaEstado(fechaInicio);

        vehiculo.setFechaActualizacion(LocalDateTime.now());

        Vehiculo vehiculoActualizado = vehiculoRepository.save(vehiculo);
        log.info("Baja temporal procesada. Fin de baja: {}", fechaFin);

        return vehiculoActualizado;
    }

    /**
     * Reactivar vehículo (fin de baja temporal)
     */
    public Vehiculo reactivarVehiculo(String matricula) {
        log.info("Reactivando vehículo: {}", matricula);

        Vehiculo vehiculo = buscarPorMatricula(matricula);

        // Validar que esté en baja temporal
        if (!vehiculo.getSituacionAdministrativa().getEstado().equals(EstadoVehiculo.BAJA_TEMPORAL)) {
            throw new BusinessException("El vehículo no está en BAJA_TEMPORAL");
        }

        // Validar ITV en vigor
        if (vehiculo.getItv() == null || !vehiculo.getItv().getEnVigor()) {
            throw new BusinessException("Debe tener ITV en vigor para reactivar el vehículo");
        }

        // Reactivar
        SituacionAdministrativa situacion = vehiculo.getSituacionAdministrativa();
        situacion.setEstado(EstadoVehiculo.ACTIVO);
        situacion.setFechaEstado(LocalDateTime.now());
        situacion.setMotivoBaja(null);
        situacion.setFechaBajaTemporal(null);
        situacion.setFechaFinBajaTemporal(null);

        vehiculo.setFechaActualizacion(LocalDateTime.now());

        return vehiculoRepository.save(vehiculo);
    }

    /**
     * Verificar si un vehículo puede transferirse
     */
    public VerificacionTransferenciaResponse verificarTransferencia(String matricula) {
        Vehiculo vehiculo = buscarPorMatricula(matricula);

        VerificacionTransferenciaResponse response = new VerificacionTransferenciaResponse();
        response.setMatricula(matricula);

        List<String> impedimentos = new ArrayList<>();

        // Verificar estado ACTIVO
        boolean esActivo = vehiculo.getSituacionAdministrativa()
            .getEstado().equals(EstadoVehiculo.ACTIVO);
        response.setVehiculoActivo(esActivo);
        if (!esActivo) {
            impedimentos.add("El vehículo no está en estado ACTIVO");
        }

        // Verificar ITMV pagado
        boolean itmvPagado = vehiculo.getImpuestos() != null &&
                            vehiculo.getImpuestos().getItmvPagado();
        response.setItmvPagado(itmvPagado);
        if (!itmvPagado) {
            impedimentos.add("El ITMV del año en curso no ha sido abonado");
        }

        // Verificar multas pendientes
        long multasPendientes = contarMultasPendientes(vehiculo);
        double importeMultas = calcularImporteMultasPendientes(vehiculo);
        boolean sinMultas = multasPendientes == 0;
        response.setSinMultasPendientes(sinMultas);
        if (!sinMultas) {
            impedimentos.add(
                String.format("Existen %d multas pendientes por un importe de %.2f€",
                multasPendientes, importeMultas)
            );
        }

        // Verificar ITV en vigor
        boolean itvVigor = vehiculo.getItv() != null &&
                          vehiculo.getItv().getEnVigor();
        response.setItvEnVigor(itvVigor);
        if (!itvVigor) {
            impedimentos.add("La ITV no está en vigor");
        }

        // Conclusión
        response.setPuedeTransferir(impedimentos.isEmpty());
        response.setImpedimentos(impedimentos);

        return response;
    }

    /**
     * Transferir vehículo a nuevo titular
     */
    public Vehiculo transferirVehiculo(String matricula, TransferenciaRequest request) {
        log.info("Iniciando transferencia de vehículo: {}", matricula);

        // Verificar requisitos previos
        VerificacionTransferenciaResponse verificacion = verificarTransferencia(matricula);
        if (!verificacion.isPuedeTransferir()) {
            throw new BusinessException(
                "No se puede realizar la transferencia: " +
                String.join(", ", verificacion.getImpedimentos())
            );
        }

        Vehiculo vehiculo = buscarPorMatricula(matricula);

        // Validar que el nuevo titular sea diferente
        if (vehiculo.getTitular().getDni().equals(request.getNuevoTitular().getDni())) {
            throw new BusinessException(
                "El nuevo titular no puede ser el mismo que el actual"
            );
        }

        // Validar DNI del nuevo titular
        validarDNI(request.getNuevoTitular().getDni());

        // Guardar titular actual en historial
        HistorialTitular historial = new HistorialTitular();
        historial.setDni(vehiculo.getTitular().getDni());
        historial.setNombre(vehiculo.getTitular().getNombre());
        historial.setApellidos(vehiculo.getTitular().getApellidos());
        historial.setFechaInicio(vehiculo.getFechaPrimeraMatriculacion());
        historial.setFechaFin(LocalDateTime.now());
        historial.setMotivoTransferencia(request.getMotivoTransferencia());

        vehiculo.getHistorialTitulares().add(historial);

        // Asignar nuevo titular
        vehiculo.setTitular(request.getNuevoTitular());

        // Marcar ITMV como no pagado (el nuevo titular debe pagar)
        if (vehiculo.getImpuestos() != null) {
            vehiculo.getImpuestos().setItmvPagado(false);
        }

        vehiculo.setFechaActualizacion(LocalDateTime.now());

        Vehiculo vehiculoActualizado = vehiculoRepository.save(vehiculo);
        log.info("Transferencia completada. Nuevo titular: {} {}",
            request.getNuevoTitular().getNombre(),
            request.getNuevoTitular().getApellidos());

        return vehiculoActualizado;
    }

    /**
     * Buscar vehículo por matrícula
     */
    public Vehiculo buscarPorMatricula(String matricula) {
        return vehiculoRepository.findByMatricula(matricula)
            .orElseThrow(() -> new VehiculoNotFoundException(
                "No se encontró vehículo con matrícula: " + matricula
            ));
    }

    /**
     * Listar todos los vehículos
     */
    public List<Vehiculo> listarTodos() {
        return vehiculoRepository.findAll();
    }

    /**
     * Buscar vehículos por DNI del titular
     */
    public List<Vehiculo> buscarPorTitular(String dni) {
        return vehiculoRepository.findByTitularDni(dni);
    }

    /**
     * Buscar vehículos por estado
     */
    public List<Vehiculo> buscarPorEstado(EstadoVehiculo estado) {
        return vehiculoRepository.findBySituacionAdministrativaEstado(estado);
    }

    /**
     * Buscar vehículos con ITV caducada
     */
    public List<Vehiculo> buscarConITVCaducada() {
        return vehiculoRepository.buscarVehiculosConITVCaducada(LocalDateTime.now());
    }

    /**
     * Buscar vehículos con multas pendientes
     */
    public List<Vehiculo> buscarConMultasPendientes() {
        return vehiculoRepository.buscarVehiculosConMultasPendientes();
    }

    /**
     * Buscar vehículos transferibles
     */
    public List<Vehiculo> buscarVehiculosTransferibles() {
        return vehiculoRepository.buscarVehiculosTransferibles();
    }

    /**
     * Contar multas pendientes
     */
    private long contarMultasPendientes(Vehiculo vehiculo) {
        if (vehiculo.getMultas() == null) return 0;

        return vehiculo.getMultas().stream()
            .filter(m -> m.getEstado().equals(EstadoMulta.PENDIENTE_PAGO))
            .count();
    }

    /**
     * Calcular importe total de multas pendientes
     */
    private double calcularImporteMultasPendientes(Vehiculo vehiculo) {
        if (vehiculo.getMultas() == null) return 0.0;

        return vehiculo.getMultas().stream()
            .filter(m -> m.getEstado().equals(EstadoMulta.PENDIENTE_PAGO))
            .mapToDouble(Multa::getImporte)
            .sum();
    }
}
