package com.backend.empowerpro.service.impl;

import com.backend.empowerpro.dto.MarkCalendar.MarkCalendarDto;
import com.backend.empowerpro.entity.MarkCalendar;
import com.backend.empowerpro.repository.MarkCalendarRepo;
import com.backend.empowerpro.service.MarkCalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MarkCalendarServiceImp implements MarkCalendarService {
    private final MarkCalendarRepo markCalendarRepo;

    @Override
    public List<MarkCalendar> getAllCalendarMarker(Long userId) {
        List<MarkCalendar> markCalendars= markCalendarRepo.findMarkCalendarById(userId);
        System.out.println(markCalendars);
        return markCalendars.stream().collect(Collectors.toList());
    }

    // create calendar marker

    @Override
    public String createMarkCalendar(MarkCalendarDto markCalendarDto) {
        MarkCalendar newMarkCalendar = new MarkCalendar();

        try {
            newMarkCalendar.setMarkId(markCalendarDto.getMarkId());
            newMarkCalendar.setId(markCalendarDto.getId());
            newMarkCalendar.setEventDate(markCalendarDto.getEventDate());
            newMarkCalendar.setEventTime(markCalendarDto.getEventTime());
            newMarkCalendar.setEvent(markCalendarDto.getEvent());
            newMarkCalendar.setState(markCalendarDto.getState());

            markCalendarRepo.save(newMarkCalendar);
            return "Mark Calendar created successfully with ID: " + newMarkCalendar.getId();
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while creating mark calendar", e);
        }
    }


    //get one marker
    @Override
    public MarkCalendar getMarkerById(Long MarkId) throws Throwable {
        MarkCalendar Marker= (MarkCalendar) markCalendarRepo.findById(MarkId)
                .orElseThrow( () ->
                        new RuntimeException("An unexpected error occurred while fetching the supplier"));
        return Marker;
    }

    //     delete
    @Override
    public String deleteMarker(Long id) {
        try {
            MarkCalendar existingMarkCalendar = markCalendarRepo.findById(id).orElseThrow(() -> new RuntimeException("Marker not found with ID: " + id));
            markCalendarRepo.delete(existingMarkCalendar);
            return "Mark Calendar deleted successfully with ID: " + id;
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while deleting marker", e);
        }
    }

    //update calendar
    @Override
    public MarkCalendarDto updateState(Long id, MarkCalendarDto markCalendarDto) {
        try {
            // Retrieve the state from the database
            MarkCalendar state = markCalendarRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Supplier not found with ID: " + id));

            // Toggle the state between 'complete' and 'not complete'
            if ("complete".equals(state.getState())) {
                state.setState("not complete"); // If current state is 'complete', change to 'not complete'
            } else {
                state.setState("complete"); // Otherwise, change to 'complete'
            }

            // Save the updated state to the database
            MarkCalendar updatedState = markCalendarRepo.save(state);

            // Return the updated state in the DTO
            return new MarkCalendarDto(
                    updatedState.getMarkId(),
                    updatedState.getId(),
                    updatedState.getEventDate(),
                    updatedState.getEventTime(),
                    updatedState.getEvent(),
                    updatedState.getState()
            );
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while updating the supplier", e);
        }
    }


    //    @Override
//    public SuppliersDto updateSuppliers(Long id, SuppliersCreationDto suppliersCreationDto) {
//        try {
//            Suppliers existingSupplier = supplierRepo.findById(id).orElseThrow(() -> new RuntimeException("Supplier not found with ID: " + id));
//            existingSupplier.setSupplierName(suppliersCreationDto.getSupplierName());
//            existingSupplier.setContactEmail(suppliersCreationDto.getContactEmail());
//            existingSupplier.setContactPhoneNo(suppliersCreationDto.getContactPhoneNo());
//            existingSupplier.setSupplierDescription(suppliersCreationDto.getSupplierDescription());
//            Suppliers updatedSupplier = supplierRepo.save(existingSupplier);
//            return new SuppliersDto(
//                    updatedSupplier.getId(),
//                    updatedSupplier.getSupplierName(),
//                    updatedSupplier.getContactEmail(),
//                    updatedSupplier.getContactPhoneNo(),
//                    updatedSupplier.getSupplierDescription(),
//                    updatedSupplier.getCreatedAt(),
//                    updatedSupplier.getUpdatedAt()
//            );
//        } catch (Exception e) {
//            throw new RuntimeException("An unexpected error occurred while updating the supplier", e);
//        }
//    }


}
