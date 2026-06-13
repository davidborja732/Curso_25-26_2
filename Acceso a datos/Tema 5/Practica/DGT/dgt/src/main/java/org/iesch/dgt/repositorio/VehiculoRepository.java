package org.iesch.dgt.repositorio;

import org.iesch.dgt.modelo.Vehiculo;
import org.iesch.dgt.modelo.enums.EstadoVehiculo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio para operaciones de base de datos de Vehículo
 */
@Repository
public interface VehiculoRepository extends MongoRepository<Vehiculo, String> {

    // ========== QUERY METHODS ==========

    /**
     * Buscar vehículo por matrícula
     */
    Optional<Vehiculo> findByMatricula(String matricula);

    /**
     * Buscar vehículos por DNI del titular
     */
    List<Vehiculo> findByTitularDni(String dni);

    /**
     * Buscar vehículos por estado administrativo
     */
    List<Vehiculo> findBySituacionAdministrativaEstado(EstadoVehiculo estado);

    /**
     * Buscar vehículos por marca y modelo
     */
    List<Vehiculo> findByMarcaIgnoreCaseAndModeloIgnoreCase(String marca, String modelo);

    /**
     * Verificar si existe una matrícula
     */
    boolean existsByMatricula(String matricula);

    /**
     * Verificar si existe un bastidor
     */
    boolean existsByBastidor(String bastidor);

    /**
     * Contar vehículos por tipo
     */
    Long countByTipoVehiculo(String tipoVehiculo);

    // ========== CONSULTAS PERSONALIZADAS @Query ==========

    /**
     * Buscar vehículos con ITV caducada
     */
    @Query("{'itv.fechaCaducidad': {$lt: ?0}}")
    List<Vehiculo> buscarVehiculosConITVCaducada(LocalDateTime fecha);

    /**
     * Buscar vehículos con multas pendientes
     */
    @Query("{'multas': {$elemMatch: {'estado': 'PENDIENTE_PAGO'}}}")
    List<Vehiculo> buscarVehiculosConMultasPendientes();

    /**
     * Buscar vehículos activos sin ITMV pagado
     */
    @Query("{'situacionAdministrativa.estado': 'ACTIVO', 'impuestos.itmvPagado': false}")
    List<Vehiculo> buscarVehiculosActivosSinITMV();

    /**
     * Buscar vehículos por año de fabricación entre dos años
     */
    @Query("{'anioFabricacion': {$gte: ?0, $lte: ?1}}")
    List<Vehiculo> buscarPorRangoAnios(Integer anioInicio, Integer anioFin);

    /**
     * Buscar vehículos con baja temporal que ya caducó
     */
    @Query("{'situacionAdministrativa.estado': 'BAJA_TEMPORAL', " +
           "'situacionAdministrativa.fechaFinBajaTemporal': {$lt: ?0}}")
    List<Vehiculo> buscarBajasTemporalesCaducadas(LocalDateTime fecha);

    /**
     * Buscar vehículos que pueden transferirse
     * (Activos, con ITMV pagado, sin multas pendientes)
     */
    @Query("{'situacionAdministrativa.estado': 'ACTIVO', " +
           "'impuestos.itmvPagado': true, " +
           "'multas': {$not: {$elemMatch: {'estado': 'PENDIENTE_PAGO'}}}}")
    List<Vehiculo> buscarVehiculosTransferibles();
}
