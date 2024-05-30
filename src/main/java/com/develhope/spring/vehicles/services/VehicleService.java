package com.develhope.spring.vehicles.services;

import com.develhope.spring.users.models.Roles;
import com.develhope.spring.users.models.User;
import com.develhope.spring.users.repositories.UserRepository;
import com.develhope.spring.users.responseStatus.UserNotFoundException;
import com.develhope.spring.vehicles.dtos.VehicleDTO;
import com.develhope.spring.vehicles.dtos.VehicleUpdateDTO;
import com.develhope.spring.vehicles.models.Vehicle;
import com.develhope.spring.vehicles.models.exceptions.VehicleNotFoundException;
import com.develhope.spring.vehicles.repositories.VehicleRepository;
import com.develhope.spring.vehicles.responseStatus.NotAuthorizedOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.develhope.spring.vehicles.dtos.VehicleCreateDTO;

import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Vehicle.VehicleMapper vehicleMapper;

    /**
     * Crea un nuovo veicolo.
     *
     * @param userId l'ID dell'utente che crea il veicolo
     * @param vehicleCreateDTO l'oggetto di trasferimento dati contenente i dettagli del veicolo
     * @return il VehicleDTO creato
     * @throws UserNotFoundException se l'utente non viene trovato
     * @throws NotAuthorizedOperationException se l'utente non è autorizzato a creare un veicolo
     */
    public VehicleDTO create(long userId, VehicleCreateDTO vehicleCreateDTO) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("Nessun utente con questo ID: " + userId + " è presente");
        }
        if (!(optionalUser.get().getRoles() == Roles.ADMIN)) {
            throw new NotAuthorizedOperationException("Permesso negato. Non autorizzato a inserire nuovi veicoli");
        }

        Vehicle vehicle = vehicleMapper.toEntity(vehicleCreateDTO);
        return vehicleMapper.toDTO(vehicleRepository.save(vehicle));
    }

    /**
     * Aggiorna un veicolo esistente.
     *
     * @param userId l'ID dell'utente che aggiorna il veicolo
     * @param vehicleId l'ID del veicolo da aggiornare
     * @param updatedVehicleDTO l'oggetto di trasferimento dati contenente i dettagli aggiornati del veicolo
     * @return il VehicleDTO aggiornato
     * @throws UserNotFoundException se l'utente non viene trovato
     * @throws NotAuthorizedOperationException se l'utente non è autorizzato ad aggiornare il veicolo
     * @throws VehicleNotFoundException se il veicolo non viene trovato
     */
    public VehicleDTO updateVehicle(long userId, long vehicleId, VehicleUpdateDTO updatedVehicleDTO) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("Nessun utente con questo ID: " + userId + " è presente");
        }
        if (optionalUser.get().getRoles() != Roles.ADMIN) {
            throw new NotAuthorizedOperationException("Permesso negato. Non autorizzato ad aggiornare i veicoli");
        }

        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(vehicleId);
        if (optionalVehicle.isEmpty()) {
            throw new VehicleNotFoundException("Nessun veicolo con questo ID: " + vehicleId + " è presente");
        }

        Vehicle vehicle = optionalVehicle.get();
        vehicleMapper.toEntity(updatedVehicleDTO);

        return vehicleMapper.toDTO(vehicleRepository.save(vehicle));
    }
}
