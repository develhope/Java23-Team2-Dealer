package com.develhope.spring.vehicles.services;

import com.develhope.spring.users.models.Roles;
import com.develhope.spring.users.models.User;
import com.develhope.spring.users.repositories.UserRepository;
import com.develhope.spring.vehicles.components.specifications.VehicleSpecificationsBuilder;
import com.develhope.spring.vehicles.dtos.VehicleFilterDTO;
import com.develhope.spring.vehicles.dtos.VehicleReworkedDTO;
import com.develhope.spring.vehicles.dtos.VehicleSavedDTO;
import com.develhope.spring.vehicles.dtos.VehicleStatusDTO;
import com.develhope.spring.vehicles.models.Vehicle;
import com.develhope.spring.vehicles.repositories.VehicleRepository;
import com.develhope.spring.exceptions.NotAuthorizedOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.develhope.spring.vehicles.dtos.VehicleCreatorDTO;
import com.develhope.spring.vehicles.components.VehicleMapper;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class VehicleService {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehicleMapper vehicleMapper;

    public VehicleSavedDTO create(VehicleCreatorDTO vehicleCreatorDTO) {
        Vehicle vehicle = vehicleMapper.toEntity(vehicleCreatorDTO);
        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return vehicleMapper.toSavedDTO(savedVehicle);
    }


onvertire autorizzazione
    public VehicleCreatorDTO update(long userId, long vehicleId, VehicleCreatorDTO vehicleCreatorDTO) {
        checkUserAuthorizationBy(userId);
        Vehicle existingVehicle;
        existingVehicle = vehicleMapper.toEntity(vehicleCreatorDTO);
        existingVehicle.setId(vehicleId);
        Vehicle updatedVehicle = vehicleRepository.save(existingVehicle);
        return vehicleMapper.toSavedDTO(updatedVehicle);
    }
    //TODO Convertire autorizzazione
    public Vehicle updateStatus(long userId, long vehicleId, VehicleStatusDTO vehicleStatusDTO) {
      
        checkUserAuthorizationBy(userId);
        Vehicle existingVehicle = findVehicleBy(vehicleId);
        existingVehicle.setMarketStatus(vehicleStatusDTO.getMarketStatus());
        Vehicle savedVehicle =  vehicleRepository.save(existingVehicle);
        return vehicleMapper.toSavedDTO(savedVehicle);

    public Page<Vehicle> search(VehicleFilterDTO vehicleFilterDTO, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        String search = vehicleFilterDTO.DTOToString();
        VehicleSpecificationsBuilder builder = new VehicleSpecificationsBuilder();
        Pattern pattern = Pattern.compile("(\\p{Punct}?)(\\w+?)(:|<|>)(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(5), matcher.group(4), matcher.group(6));
        }
        Specification<Vehicle> spec = builder.build();
        return vehicleRepository.findAll(spec, pageable);
    }

    public VehicleReworkedDTO update(long vehicleId, VehicleCreatorDTO vehicleCreatorDTO) {
        Vehicle vehicleToUpdate = findVehicleBy(vehicleId);
        vehicleToUpdate.setVehicleType(vehicleCreatorDTO.getVehicleType());
        vehicleToUpdate.setBrand(vehicleCreatorDTO.getBrand());
        vehicleToUpdate.setModel(vehicleCreatorDTO.getModel());
        vehicleToUpdate.setDisplacement(vehicleCreatorDTO.getDisplacement());
        vehicleToUpdate.setColor(vehicleCreatorDTO.getColor());
        vehicleToUpdate.setPower(vehicleCreatorDTO.getPower());
        vehicleToUpdate.setGear(vehicleCreatorDTO.getGear());
        vehicleToUpdate.setRegistrationYear(vehicleCreatorDTO.getRegistrationYear());
        vehicleToUpdate.setPowerSupply(vehicleCreatorDTO.getPowerSupply());
        vehicleToUpdate.setPrice(vehicleCreatorDTO.getPrice());
        vehicleToUpdate.setUsedFlag(vehicleCreatorDTO.getUsedFlag());
        vehicleToUpdate.setMarketStatus(vehicleCreatorDTO.getMarketStatus());
        vehicleToUpdate.setEngine(vehicleCreatorDTO.getEngine());

        Vehicle updatedVehicle = vehicleRepository.save(vehicleToUpdate);
        return vehicleMapper.toReworkedDTO(updatedVehicle);
    }

    public VehicleReworkedDTO updateStatus(long vehicleId, VehicleStatusDTO vehicleStatusDTO) {
        Vehicle vehicleToUpdate = findVehicleBy(vehicleId);
        vehicleToUpdate.setMarketStatus(vehicleStatusDTO.getMarketStatus());
        Vehicle updatedVehicle = vehicleRepository.save(vehicleToUpdate);
        return vehicleMapper.toReworkedDTO(updatedVehicle);

    }

    //TODO Convertire autorizzazione
    public void delete(long userId, long vehicleId) {
        checkUserAuthorizationBy(userId);
        vehicleRepository.deleteById(vehicleId);
    }

    private void checkUserAuthorizationBy(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("No user found with this ID"));
        if (!user.getRole().equals(Roles.ADMIN)) {
            throw new NotAuthorizedOperationException("Permesso negato. Non autorizzato ad aggiornare i veicoli");
        }
    }

    public Vehicle findVehicleBy(long vehicleId) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(vehicleId);
        return optionalVehicle.orElseThrow(NoSuchElementException::new);
    }
}