package com.taybooking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taybooking.booking.controller.BookingController;
import com.taybooking.booking.dto.BookingReqDTO;
import com.taybooking.booking.dto.BookingRespDTO;
import com.taybooking.booking.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class BookingControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BookingService bookingService;

    @InjectMocks
    private BookingController bookingController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookingController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void getAll_shouldReturnOk() throws Exception {
        when(bookingService.getAll()).thenReturn(Collections.emptySet());

        mockMvc.perform(get("/bookings"))
                .andExpect(status().isOk());
    }

    @Test
    void create_shouldReturnCreated() throws Exception {
        BookingRespDTO bookingDTO = new BookingRespDTO();
        bookingDTO.setId(UUID.randomUUID());

        when(bookingService.create(any(BookingReqDTO.class))).thenReturn(bookingDTO);

        mockMvc.perform(post("/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookingDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    void getById_shouldReturnOk() throws Exception {
        UUID id = UUID.randomUUID();
        BookingRespDTO bookingDTO = new BookingRespDTO();
        bookingDTO.setId(id);

        when(bookingService.getById(id)).thenReturn(bookingDTO);

        mockMvc.perform(get("/bookings/{id}", id))
                .andExpect(status().isOk());
    }

    @Test
    void cancelBooking_shouldReturnOk() throws Exception {
        UUID id = UUID.randomUUID();
        BookingRespDTO bookingDTO = new BookingRespDTO();
        bookingDTO.setId(id);

        when(bookingService.cancelBooking(id)).thenReturn(bookingDTO);

        mockMvc.perform(patch("/bookings/{id}/cancel", id))
                .andExpect(status().isOk());
    }

    @Test
    void update_shouldReturnOk() throws Exception {
        UUID id = UUID.randomUUID();
        BookingRespDTO bookingDTO = new BookingRespDTO();
        bookingDTO.setId(id);

        when(bookingService.update(eq(id), any(BookingReqDTO.class))).thenReturn(bookingDTO);

        mockMvc.perform(put("/bookings/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookingDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void delete_shouldReturnNoContent() throws Exception {
        UUID id = UUID.randomUUID();

        mockMvc.perform(delete("/bookings/{id}", id))
                .andExpect(status().isNoContent());

        verify(bookingService, times(1)).delete(id);
    }
}
