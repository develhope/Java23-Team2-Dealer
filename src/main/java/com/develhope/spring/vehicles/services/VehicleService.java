package com.develhope.spring.vehicles.services;

import com.develhope.spring.users.models.Roles;
import com.develhope.spring.users.models.User;
import com.develhope.spring.users.repositories.UserRepository;
import com.develhope.spring.vehicles.components.specifications.VehicleSpecificationsBuilder;
import com.develhope.spring.vehicles.dtos.*;
import com.develhope.spring.vehicles.models.Vehicle;
import com.develhope.spring.vehicles.repositories.VehicleRepository;
import com.develhope.spring.vehicles.responseStatus.ExcessiveParameterException;
import com.develhope.spring.vehicles.responseStatus.NotAuthorizedOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.develhope.spring.vehicles.components.VehicleMapper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.NoSuchElementException;
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

    public VehicleDiscountedDTO discountPrice(double discountPercentage, long vehicleId){
        Vehicle vehicleToDiscount = findVehicleBy(vehicleId);
        calculatePriceDiscount(discountPercentage, vehicleId);
        vehicleToDiscount.setDiscountFlag(true);
        Vehicle discountedVehicle = vehicleRepository.save(vehicleToDiscount);
        return vehicleMapper.toDiscountedDTO(discountedVehicle);
    }

    public VehicleDiscountedDTO removeDiscountPrice( long vehicleId){
        Vehicle discountedVehicle = findVehicleBy(vehicleId);
        discountedVehicle.setDiscountFlag(false);
        discountedVehicle.setDiscountedPrice(discountedVehicle.getPrice());
        Vehicle originalPriceVehicle = vehicleRepository.save(discountedVehicle);
        return vehicleMapper.toDiscountedDTO(originalPriceVehicle);
    }

    private void checkUserAuthorizationBy(long userId) {
        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        if (!user.getRole().equals(Roles.ADMIN)) {
            throw new NotAuthorizedOperationException("Permesso negato. Non autorizzato ad aggiornare i veicoli");
        }
    }

    public Vehicle findVehicleBy(long vehicleId) {
        return vehicleRepository.findById(vehicleId).orElseThrow(NoSuchElementException::new);
    }

    /**
     * Calcola il prezzo scontato e modifica la variabile discountedPrice.
     * Inserisce un double che viene convertito internamente in un BigDecimal.
     *
     * @param discountPercentage è la percentuale di sconto che si desidera applicare
     * @throws ExcessiveParameterException se la percentuale inserita è fuori dai limiti 0 e 100
     */

     void calculatePriceDiscount(double discountPercentage, long vehicleId) {
        Vehicle discountedVehicle = findVehicleBy(vehicleId);
        if (discountPercentage > 100 || discountPercentage < 0) {
            throw new ExcessiveParameterException("The discount percentage must be comprehended between 0 and 100");
        }
        BigDecimal discountRate = BigDecimal.valueOf(discountPercentage / 100).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal removedPrice = discountedVehicle.getPrice().multiply(discountRate).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal discountedPrice = discountedVehicle.getPrice().subtract(removedPrice).setScale(2, RoundingMode.HALF_EVEN);
        discountedVehicle.setDiscountedPrice(discountedPrice);
    }
}