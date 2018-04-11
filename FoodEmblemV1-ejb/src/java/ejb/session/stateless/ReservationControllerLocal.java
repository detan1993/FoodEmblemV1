/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Reservation;
import java.util.List;


public interface ReservationControllerLocal {
      public Reservation createReservation(Reservation newReservation , String customerId , Long seatId);
      public List<Reservation>getCustomerReservations(String customerEmail, String status);
      public List <String> getRestaurantNameFromReservation(List<Reservation>reservationList);
      public Boolean checkCustomerHasReservation(String email);
      public boolean finishReservation(long id);
}
